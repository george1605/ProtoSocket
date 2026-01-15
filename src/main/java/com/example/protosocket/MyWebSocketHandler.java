package com.example.protosocket;

import com.google.protobuf.Message;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class MyWebSocketHandler extends BinaryWebSocketHandler {

    private final PacketRepository packetRepository;
    public MyWebSocketHandler(PacketRepository packetRepository)
    {
        this.packetRepository = packetRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New WebSocket connection: " + session.getId());
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
        packetRepository.insert(PacketEntity.fromPacket(packet));
        session.sendMessage(new BinaryMessage(outBytes));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("WebSocket closed: " + session.getId());
    }
}
