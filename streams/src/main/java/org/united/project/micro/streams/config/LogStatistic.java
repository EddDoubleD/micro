package org.united.project.micro.streams.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogStatistic {
    int errorCount;
    int totalCount;
}
