package edu.it10.vuquangdung.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
  @Override
  public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
    // Khai báo endpoint WebSocket để frontend có thể kết nối
    registry
      .addEndpoint("/ws")
      .setAllowedOrigins("http://localhost:3000") // Chỉ cho phép frontend testing
      .withSockJS(); // Hỗ trợ SockJS cho fallback khi WS không khả dụng
  }

  @Override
  public void configureMessageBroker(@NonNull MessageBrokerRegistry registry) {
    // Đặt tiền tố cho message đến client và server
    registry.enableSimpleBroker("/topic");
    registry.setApplicationDestinationPrefixes("/app");
  }
}
