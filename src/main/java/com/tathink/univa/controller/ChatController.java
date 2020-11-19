package com.tathink.univa.controller;

import com.tathink.univa.controller.form.ChatMessage;
import com.tathink.univa.controller.form.MessageType;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	@MessageMapping("/sendMessage/{room_id}")
	@SendTo("/subs/{room_id}")
	public ChatMessage sendMessage(
			@Payload ChatMessage chatMessage) {
		System.out.println("chat.sendMsg: "+chatMessage.getSender());
		return chatMessage;
	}
	
	@MessageMapping("/addUser/{room_id}")
	@SendTo("/subs/{room_id}")
	public ChatMessage addUser(
			@DestinationVariable(value = "room_id") String room_id,
			@Payload ChatMessage chatMessage,
			SimpMessageHeaderAccessor headerAccessor) {
		System.out.println("chat.addUser :");
		headerAccessor.getSessionAttributes().put("sender", chatMessage.getSender());
		headerAccessor.getSessionAttributes().put("room_id", room_id);
		chatMessage.setType(MessageType.JOIN);
		return chatMessage;
	}
	
	
}
