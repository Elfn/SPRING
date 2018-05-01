package com.socket.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by Elimane on Dec, 2017, at 08:08
 *
 * @Simple (or Streaming) Text Oriented Message Protocol (STOMP), formerly known as TTMP,
 * is a simple text-based protocol, designed for working with message-oriented middleware (MOM).
 * It provides an interoperable wire format that allows STOMP clients to talk with any message broker supporting the protocol.
 * It is thus language-agnostic, meaning a broker developed for one programming language or platform can receive communications
 * from client software developed in another language.
 *
 */

@Configuration
@EnableWebSocketMessageBroker//enables WebSocket message handling, backed by a message broker.
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {


    //to configure the message broker
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");//to enable a simple memory-based message broker to carry the greeting messages back to the client on destinations prefixed with "/topic".
        config.setApplicationDestinationPrefixes("/app");//This prefix will be used to define all the message mappings; for example, "/app/hello" is the endpoint that the GreetingController.greeting() method is mapped to handle.
    }

    //The registerStompEndpoints() method registers the "/gs-guide-websocket" endpoint,
    // enabling SockJS fallback options so that alternate transports may be used if WebSocket is not available.
    // The SockJS client will attempt to connect to "/gs-guide-websocket" and use the best transport available
    // (websocket, xhr-streaming, xhr-polling, etc).
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/gs-guide-websocket").withSockJS();

    }
}
