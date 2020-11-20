package com.tathink.univa.controller;

import com.tathink.univa.controller.form.ChatMessage;
import com.tathink.univa.controller.form.MessageType;
import com.tathink.univa.service.SolutionService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	private final SolutionService solutionService;
	
	@Autowired
	public ChatController(SolutionService solutionService) {
		this.solutionService = solutionService;
	}
	
	@MessageMapping("/sendMessage/{room_id}")
	@SendTo("/subs/{room_id}")
	public ChatMessage sendMessage(
			@DestinationVariable(value = "room_id") int room_id,
			@Payload ChatMessage chatMessage) {
//		System.out.println("chat.sendMsg: "+chatMessage.getSender());
		chatMessage.setDate(LocalDateTime.now());
		solutionService.SolutionChatSave(room_id, chatMessage);
		return chatMessage;
	}
	
	@MessageMapping("/addUser/{room_id}")
	@SendTo("/subs/{room_id}")
	public ChatMessage addUser(
			@DestinationVariable(value = "room_id") String room_id,
			@Payload ChatMessage chatMessage,
			SimpMessageHeaderAccessor headerAccessor) {
		//System.out.println("chat.addUser :");
		headerAccessor.getSessionAttributes().put("sender", chatMessage.getSender());
		headerAccessor.getSessionAttributes().put("room_id", room_id);
		chatMessage.setType(MessageType.JOIN);
		return chatMessage;
	}
	
	
}
