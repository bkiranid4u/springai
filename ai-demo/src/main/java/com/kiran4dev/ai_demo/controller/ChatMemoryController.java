package com.kiran4dev.ai_demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/chat-memory")
public class ChatMemoryController {

    private final ChatClient chatClient;

    
    public ChatMemoryController(@Qualifier("chatMemoryChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/message")
    public ResponseEntity<String> chatMemory(@RequestParam("username") String username, @RequestParam("message") String message) {
        return ResponseEntity.ok(chatClient
                .prompt()
                .advisors(advisorspec -> advisorspec.param(CONVERSATION_ID, username))
                .user(message)
                .call()
                .content());
    }
    

}
