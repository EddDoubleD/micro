package org.united.project.micro.adapter.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.united.project.micro.common.LogRecord;

@Service
public class LogRecordSender {

    private final KafkaTemplate<Long, LogRecord> kafkaTemplate;

    public LogRecordSender(KafkaTemplate<Long, LogRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(LogRecord record) {
        kafkaTemplate.send("log", record.getId(), record);
    }
}
