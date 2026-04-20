package com.kiran4dev.ai_demo.controller;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiran4dev.ai_demo.advisors.TokenAuditAdvisor;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/stream-chat")
public class StreamChatController {

    private final ChatClient chatClient;


    public StreamChatController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/message")
    public Flux<String> streamChatResponse(@RequestParam("prompt") String prompt) {

        return chatClient
                .prompt()
                .advisors(List.of(new SimpleLoggerAdvisor(), new TokenAuditAdvisor()))
                .user(prompt)
                .stream()
                .content();

    }

}
