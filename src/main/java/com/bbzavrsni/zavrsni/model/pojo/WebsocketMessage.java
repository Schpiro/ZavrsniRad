package com.bbzavrsni.zavrsni.model.pojo;

import com.bbzavrsni.zavrsni.model.enums.MessageTypes;
import com.bbzavrsni.zavrsni.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebsocketMessage {
    private MessageTypes type;
    private Object payload;

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
