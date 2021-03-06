package com.bbzavrsni.zavrsni.model.dto;

import com.bbzavrsni.zavrsni.util.GsonUtil;
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
        return GsonUtil.toJson(this);
    }
}
