package com.bbzavrsni.zavrsni.model.pojo;

import com.bbzavrsni.zavrsni.model.enums.MessageTypes;
import lombok.Data;

@Data
public class WebsocketMessage {
    private MessageTypes type;
    private String message;
}
