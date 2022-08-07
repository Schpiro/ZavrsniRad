package com.bbzavrsni.zavrsni.model.dto;

import com.bbzavrsni.zavrsni.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDTO {
    private final Long id;
    private final String creator;
    private final String details;
    private final String creationDate;

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
