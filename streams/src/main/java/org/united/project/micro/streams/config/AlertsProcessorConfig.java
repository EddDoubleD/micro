package org.united.project.micro.streams.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class AlertsProcessorConfig {
    @Bean
    public Consumer<KStream<Long, LogStatistic>> alertsProcessor() {
        return input -> input.foreach(this::thresholdAlert);
    }

    private void thresholdAlert(Long id, LogStatistic statistic) {
        if (statistic.getErrorCount() > 10) {
            log.warn("alert attention on server {} error {}", id, statistic.getErrorCount());
            return;
        }

        log.info("window success!");
    }
}
