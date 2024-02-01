package org.united.project.micro.adapter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.united.project.micro.common.LogRecord;

@Service
@Slf4j
public class LogRecordSender {

    private final KafkaTemplate<Long, LogRecord> kafkaTemplate;

    public LogRecordSender(KafkaTemplate<Long, LogRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(LogRecord record) {
        log.info("send topic [log], message {}", record);
        kafkaTemplate.send("log-topic", record.getId(), record);
        kafkaTemplate.flush();
    }
}
