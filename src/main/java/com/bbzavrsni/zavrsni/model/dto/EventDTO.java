package com.bbzavrsni.zavrsni.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventDTO {
    private final String creator;
    private final String details;
    private final LocalDateTime creationDate;

    @Override
    public String toString() {
        return "EventDTO{" +
                "creator='" + creator + '\'' +
                "details='" + details + '\'' +
                "creationDate='" + creationDate + '\'' +
                '}';
    }
}
