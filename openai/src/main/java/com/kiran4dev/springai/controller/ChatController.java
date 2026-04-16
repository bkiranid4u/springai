package com.kiran4dev.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/chat")

public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }




    @GetMapping
    public String getMethodName(@RequestParam("message") String message) {

       String response = this.chatClient
            .prompt(message)
            .call()
            .content();
    
        return "Received message: " +   message + " | Response: " + response;
    }
    
}
