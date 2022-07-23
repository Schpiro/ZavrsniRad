package com.bbzavrsni.zavrsni.websockets;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {
    private final MessageHandler messageHandler;

    public SocketHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message){
        messageHandler.handleMessage(message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connected client: " + session.getId());
        session.sendMessage(new TextMessage("hello"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        var ID = session.getId();
        System.out.println("Connection Closed"+ ID);
    }
}