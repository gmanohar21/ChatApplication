package com.mn.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mn.model.Member;

@RestController
public class MessagingController {
    private static final Logger log = LoggerFactory.getLogger(MessagingController.class);

	@Autowired
	private KafkaTemplate<String, Member> kafkaTemplate;

	// send data to every client
	@MessageMapping("/message")
	@SendTo("/target/return") // the client who subscribes can get all messages
	public Member getContent(@RequestBody Member msg) {
		log.info("inside get content");
		System.out.println(msg.toString());
	//	kafkaTemplate.send(KafkaConstants.TOPIC, msg);
		return msg;
	}
}
