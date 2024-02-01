package org.united.project.micro.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.united.project.micro.adapter.service.LogRecordSender;
import org.united.project.micro.adapter.dto.IncomeLogRecord;

import javax.validation.Valid;
import java.util.List;

import static org.united.project.micro.adapter.dto.Funcs.toLogRecord;

@RestController
@RequestMapping("/api/v1/log")
// @Slf4j
public class LogController {

    private final LogRecordSender sender;

    public LogController(LogRecordSender sender) {
        this.sender = sender;
    }

    @PostMapping
    public void collectLog(@Valid @RequestBody IncomeLogRecord record) {
        // log.info("message {}", record);
        sender.send(toLogRecord.apply(record));
    }

    @PostMapping("/list")
    public void collectLogs(@Valid @RequestBody List<IncomeLogRecord> records) {
        records.stream()
            .map(toLogRecord)
            .forEach(sender::send);
    }


}
