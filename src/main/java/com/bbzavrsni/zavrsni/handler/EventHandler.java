package com.bbzavrsni.zavrsni.handler;

import com.bbzavrsni.zavrsni.model.pojo.WebsocketMessage;
import com.bbzavrsni.zavrsni.util.GsonUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandler {
    private static final Map<Integer, WebSocketSession> openSessions = new HashMap<>();

    public void handleMessage(WebSocketSession session, String message) throws IOException {
        WebsocketMessage websocketMessage = GsonUtil.fromJson(message,WebsocketMessage.class);
        String payloadMessage = websocketMessage.getMessage();

        switch (websocketMessage.getType()){
            case CLIENT_ID -> addSession(session, Integer.valueOf(payloadMessage));
            case PRIVATE_MESSAGE -> sendRefreshRequest(Integer.valueOf(payloadMessage));
            case GROUP_MESSAGE -> System.out.println("todo");
        }
    }

    private void sendRefreshRequest(Integer userId) throws IOException {
        openSessions.get(userId).sendMessage(new TextMessage("refresh pls"));
    }

    private void addSession(WebSocketSession session, Integer userId){
        openSessions.put(userId,session);
    }

    public void closeConnection(WebSocketSession session){
        openSessions.values().remove(session);
    }
}
