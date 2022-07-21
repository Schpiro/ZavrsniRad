package com.bbzavrsni.zavrsni.websockets;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message){
        System.out.println(message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connected client: " + session.getId());
        String jsonString = new JSONObject()
                .put("Vlad:", "Hello World!")
                .toString();
        session.sendMessage(new TextMessage(jsonString));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        var ID = session.getId();
        System.out.println("Connection Closed"+ ID);
    }
}