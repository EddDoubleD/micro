package org.united.project.micro.adapter.dto;

import java.util.List;

public record IncomeLogRecord(Long id, int code, String message, List<String> stack, String timestamp) {
}
