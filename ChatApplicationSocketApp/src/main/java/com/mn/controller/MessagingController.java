package com.mn.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mn.model.Message;

@RestController
public class MessagingController {
	// send data to every client
	@MessageMapping("/message")
	@SendTo("/target/return") // the client who subscribes can get all messages
	public Message getContent(@RequestBody Message msg) {
		return msg;
	}

}
