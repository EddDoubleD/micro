package org.united.project.micro.streams.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.united.project.micro.common.LogRecord;

import java.time.Duration;
import java.util.function.Function;


@Configuration
@Slf4j
public class StatisticsProcessorConfig {
    @Bean
    public Function<KStream<Long, LogRecord>, KStream<Long, LogStatisticAggregation>> statisticProcessor() {
        return input -> input
            .groupByKey()
            .windowedBy(
                TimeWindows.of(Duration.ofSeconds(5))
                    .grace(Duration.ZERO)
            )
            .aggregate(
                this::init,
                this::agg,
                Materialized.with(Serdes.Long(), new JsonSerde<>(LogStatistic.class))
            )
            .suppress(
                Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded())
            )
            .toStream()
            .map(this::avg);
    }

    private KeyValue<Long, LogStatisticAggregation> avg(Windowed<Long> window, LogStatistic aggState) {
        double percent;
        if (aggState.getTotalCount()  == 0) {
            percent = 0;
        } else {
            percent = aggState.getErrorCount() / aggState.getTotalCount();
        }
        return KeyValue.pair(
                window.key(),
                new LogStatisticAggregation(
                        window.key(),
                        aggState.getErrorCount(),
                        percent,
                        window.window().endTime()
                )
        );
    }

    private LogStatistic agg(Long deviceId, LogRecord incoming, LogStatistic aggState) {
        if (incoming.getCode() >= 400) {
            return new LogStatistic(
                    aggState.getErrorCount() + 1,
                    aggState.getTotalCount() + 1
            );
        }

        return new LogStatistic(
                aggState.getErrorCount(),
                aggState.getTotalCount() + 1
        );
    }

    private LogStatistic init() {
        return new LogStatistic(0, 0);
    }
}
