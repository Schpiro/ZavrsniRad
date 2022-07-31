package com.bbzavrsni.zavrsni.handler;

import com.bbzavrsni.zavrsni.model.dto.MessageDTO;
import com.bbzavrsni.zavrsni.model.enums.MessageTypes;
import com.bbzavrsni.zavrsni.model.pojo.WebsocketMessage;
import com.bbzavrsni.zavrsni.util.GsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandler {
    private static final Map<Long, WebSocketSession> openSessions = new HashMap<>();

    public void handleMessage(WebSocketSession session, String message) throws IOException {
        WebsocketMessage websocketMessage = GsonUtil.fromJson(message, WebsocketMessage.class);
        switch (websocketMessage.getType()) {
            case CLIENT_ID -> addSession(session, GsonUtil.fromJson(websocketMessage.getPayload().toString(),Long.class));
            case PRIVATE_MESSAGE -> sendRefreshRequest(GsonUtil.fromJson(websocketMessage.getPayload().toString(),MessageDTO.class));
            case GROUP_MESSAGE -> System.out.println("todo");
        }
    }

    private void sendRefreshRequest(MessageDTO message) throws IOException {
        Long recipientId = message.getRecipientId();
        if(openSessions.containsKey(recipientId)) openSessions.get(recipientId).sendMessage(new TextMessage(new WebsocketMessage(MessageTypes.NEW_MESSAGE,message).toString()));
    }

    private void addSession(WebSocketSession session, Long userId) {
        openSessions.put(userId, session);
    }

    public void closeConnection(WebSocketSession session) {
        openSessions.values().remove(session);
    }
}
