package com.example.protosocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private MyWebSocketHandler socketHandler;
    public WebSocketConfig(MyWebSocketHandler socketHandler)
    {
        this.socketHandler = socketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/ws")
                .setAllowedOrigins("*"); 
    }
}
