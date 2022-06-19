package com.bbzavrsni.zavrsni.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageDTO {
    private final String creator;
    private final LocalDateTime creationDate;
    private final String messageBody;

    @Override
    public String toString() {
        return "EventDTO{" +
                "creator='" + creator + '\'' +
                "creationDate='" + creationDate + '\'' +
                "messageBody='" + messageBody + '\'' +
                '}';
    }
}
