package com.bbzavrsni.zavrsni.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventCommand {
    @NotNull(message = "messageBody must not be empty")
    private Long creator;
    @NotNull(message = "messageBody must not be empty")
    private String details;
}
