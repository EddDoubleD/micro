package org.united.project.micro.streams.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.united.project.micro.common.Timestamped;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogStatisticAggregation implements Timestamped {
    long id;

    int errorCount;

    double percent;

    private Instant timestamp;

    @Override
    public long getEpochTs() {
        return timestamp.toEpochMilli();
    }
}
