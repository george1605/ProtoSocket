package com.example.protosocket;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document("packets")
public class PacketEntity {

    private PacketEntity() {}
    @Id
    private String id;

    private long from;
    private long to;
    private int flags;
    private Map<String, String> opt;
    private String data;

    // getters / setters
    static PacketEntity fromPacket(Packet p)
    {
        PacketEntity pack = new PacketEntity();
        pack.from = p.getFrom();
        pack.to = p.getTo();
        pack.data = p.getData();
        pack.flags = p.getFlags();
        pack.opt = new HashMap<>(p.getOptMap());
        return pack;
    }
}