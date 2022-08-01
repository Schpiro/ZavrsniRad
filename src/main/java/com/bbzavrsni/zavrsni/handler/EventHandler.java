package com.bbzavrsni.zavrsni.handler;

import com.bbzavrsni.zavrsni.model.dto.MessageDTO;
import com.bbzavrsni.zavrsni.model.dto.MessageGroupDTO;
import com.bbzavrsni.zavrsni.model.enums.MessageTypes;
import com.bbzavrsni.zavrsni.model.pojo.WebsocketMessage;
import com.bbzavrsni.zavrsni.util.GsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventHandler {
    private static final Map<Long, WebSocketSession> openSessions = new HashMap<>();

    public void handleMessage(WebSocketSession session, String message) throws IOException {
        WebsocketMessage websocketMessage = GsonUtil.fromJson(message, WebsocketMessage.class);
        switch (websocketMessage.getType()) {
            case CLIENT_ID -> addSession(session, GsonUtil.fromJson(websocketMessage.getPayload().toString(), Long.class));
            case PRIVATE_MESSAGE -> sendPrivateMessage(GsonUtil.fromJson(websocketMessage.getPayload().toString(), MessageDTO.class), false);
            case GROUP_MESSAGE -> sendPrivateMessage(GsonUtil.fromJson(websocketMessage.getPayload().toString(), MessageDTO.class), true);
            case NEW_GROUP -> newGroupCreated(GsonUtil.fromJson(websocketMessage.getPayload().toString(), MessageGroupDTO.class));
        }
    }

    private void sendPrivateMessage(MessageDTO message, boolean groupMessage) throws IOException {
        List<Long> userIds = new ArrayList<>() {
            {
                if (groupMessage) addAll(message.getGroupParticipantsIds());
                else {
                    add(message.getCreator());
                    add(message.getRecipientId());
                }
            }
        };
        sendWebSocketMessage(message, MessageTypes.NEW_MESSAGE, userIds);
    }

    private void newGroupCreated(MessageGroupDTO messageGroup) throws IOException {
        List<Long> userIds = messageGroup.getGroupParticipants();
        sendWebSocketMessage(messageGroup, MessageTypes.NEW_GROUP, userIds);
    }

    private <T> void sendWebSocketMessage(T message, MessageTypes messageTypes, List<Long> userIds) throws IOException {
        for (Long userId : userIds) {
            if (openSessions.containsKey(userId))
                openSessions.get(userId).sendMessage(new TextMessage(new WebsocketMessage(messageTypes, message).toString()));
        }
    }

    private void addSession(WebSocketSession session, Long userId) {
        openSessions.put(userId, session);
    }

    public void closeConnection(WebSocketSession session) {
        openSessions.values().remove(session);
    }
}
