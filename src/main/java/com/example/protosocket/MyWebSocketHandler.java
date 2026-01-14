package com.example.protosocket;

import com.google.protobuf.Message;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.nio.ByteBuffer;

public class MyWebSocketHandler extends BinaryWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Packet p = Packet.newBuilder().setFlags(MessageFlags.CONN.ordinal())
                   .setFrom(0x1).putOpt("type", "connection").build();
        byte[] bytes = p.toByteArray();
        System.out.println("New WebSocket connection: " + session.getId());
        session.sendMessage(new BinaryMessage(bytes));
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        ByteBuffer buffer = message.getPayload();
        byte[] outBytes = new byte[buffer.remaining()];
        buffer.get(outBytes);

        Packet p = Packet.newBuilder().setFlags(MessageFlags.ECHO.ordinal())
                .setFrom(0x1).putOpt("type", "echo").build();
        byte[] bytes = p.toByteArray();

        Packet packet = Packet.parseFrom(bytes);
        System.out.println("Packet Data: " + packet.getData());
        session.sendMessage(new BinaryMessage(bytes));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("WebSocket closed: " + session.getId());
    }
}
