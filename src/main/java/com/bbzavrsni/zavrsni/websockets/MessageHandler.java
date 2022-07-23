package com.bbzavrsni.zavrsni.websockets;

import com.bbzavrsni.zavrsni.model.enums.MessageTypes;
import com.bbzavrsni.zavrsni.model.pojo.WebsocketMessage;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private static Gson gson = new Gson();

    public String handleMessage(String message){
        WebsocketMessage websocketMessage = gson.fromJson(message,WebsocketMessage.class);
        if(websocketMessage.getType().equals(MessageTypes.PRIVATE_MESSAGE)) System.out.println("private");
        if(websocketMessage.getType().equals(MessageTypes.GROUP_MESSAGE)) System.out.println("group");
        return "hello";
    }
}
