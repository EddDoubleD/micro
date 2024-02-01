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
    public Function<KStream<Long, LogRecord>, KStream<Long, LogStatistic>> statisticProcessor() {
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

    private KeyValue<Long, LogStatistic> avg(Windowed<Long> window, LogStatistic aggState) {
        return KeyValue.pair(window.key(), new LogStatistic(1, 1));
    }

    private LogStatistic agg(Long deviceId, LogRecord incoming, LogStatistic aggState) {
        log.info("aggregate {}", incoming.toString());
        return new LogStatistic();
    }

    private LogStatistic init() {
        log.info("init");
        return new LogStatistic();
    }
}
