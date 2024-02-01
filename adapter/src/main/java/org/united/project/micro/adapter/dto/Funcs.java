package org.united.project.micro.adapter.dto;

import lombok.experimental.UtilityClass;
import org.united.project.micro.common.LogRecord;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

@UtilityClass
public class Funcs {
    private static final String pattern = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.ROOT);
    private static final ZoneId zoneId = ZoneId.of("Europe/Moscow");

    public static final Function<IncomeLogRecord, LogRecord> toLogRecord = record -> {
        LocalDateTime localDateTime = LocalDateTime.parse(record.timestamp(), dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return new LogRecord(
                record.id(),
                record.code(),
                record.message(),
                record.stack(),
                zonedDateTime.toInstant()
        );
    };
}
