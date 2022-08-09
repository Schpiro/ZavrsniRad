package com.bbzavrsni.zavrsni.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventCommand {
    @NotNull(message = "creator must not be empty")
    private Long creator;
    @NotNull(message = "title must not be empty")
    private String title;
    @NotNull(message = "details must not be empty")
    private String details;
    @NotNull(message = "date must not be empty")
    private String date;
    @NotNull(message = "location must not be empty")
    private String location;
}
