package com.example.estacaometeorologica.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getChatWebSocketHandler(), "/dados-coletados-ws");
            //.setAllowedOrigins("http://localhost:3000");//todo limitar origens
    }

    @Bean
    public org.springframework.web.socket.WebSocketHandler getChatWebSocketHandler(){
        return new WebSocketHandler();
    }
}
