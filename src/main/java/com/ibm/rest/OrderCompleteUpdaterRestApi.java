package com.ibm.rest;

import com.ibm.model.Order;

import lombok.extern.log4j.Log4j2;
import com.esotericsoftware.minlog.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Log4j2
public class OrderCompleteUpdaterRestApi {
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public void orderComplete(@RequestBody Order order) {
        Log.info("Receive order = " + order.toString());
        if (order.getUserInfo().getId() == null) {
            order.getUserInfo().setId("");
        }
        if (order.getPaymentId() == null) {
            order.setPaymentId("");
        }
        if (order.getSpecialNote() == null) {
            order.setSpecialNote("");
        }
        template.convertAndSend("/topic/orders", order);
    }

    @RequestMapping(value = "/errors", method = RequestMethod.POST)
    public void orderComplete(@RequestBody String errorMessage) {
        Log.info("Receive error = " + errorMessage);
        template.convertAndSend("/topic/orders", errorMessage);
    }
}
