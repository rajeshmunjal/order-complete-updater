package com.ibm.rest;

import lombok.extern.log4j.*;
import com.esotericsoftware.minlog.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class WebSocketApi {
    @MessageMapping("/sendMessage")
    @SendTo("/topic/orders")
    public String sendMessage(String message) throws Exception {
        Log.info("Message received: " + message);
        return message;
    }
}
