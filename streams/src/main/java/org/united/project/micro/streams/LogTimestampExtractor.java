package org.united.project.micro.streams;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import org.united.project.micro.common.LogRecord;
import org.united.project.micro.common.Timestamped;

public class LogTimestampExtractor implements TimestampExtractor {
    @Override
    public long extract(ConsumerRecord<Object, Object> record, long l) {
        final Object value = record.value();
        if(value instanceof LogRecord) {
            return ((Timestamped) value).getEpochTs();
        } else {
            return System.currentTimeMillis();
        }
    }
}
