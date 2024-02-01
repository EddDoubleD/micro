package org.united.project.micro.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogRecord implements Timestamped {
    @NotNull
    private Long id;
    @NotNull
    private Integer code;
    @NotNull
    private String message;
    private List<String> stack;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;

    @Override
    public long getEpochTs() {
        return timestamp.toEpochMilli();
    }
}
