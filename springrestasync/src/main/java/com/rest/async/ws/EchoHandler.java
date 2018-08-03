package com.rest.async.ws;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class EchoHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        session.sendMessage(new TextMessage("CONNECTION ESTABLISHED"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = message.getPayload();
        session.sendMessage(new TextMessage("RECEIVED: " + msg));
    }

}
