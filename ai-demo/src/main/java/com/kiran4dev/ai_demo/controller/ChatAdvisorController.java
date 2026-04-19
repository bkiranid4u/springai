package com.kiran4dev.ai_demo.controller;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiran4dev.ai_demo.advisors.TokenAuditAdvisor;

@RestController
@RequestMapping("/api/chat-advisor")
public class ChatAdvisorController {

    private final ChatClient chatClient;

    @Value("classpath:prompts/systemPrompt.st")
    Resource systemPromptTemplate;

    public ChatAdvisorController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String emailResponseWithPrompt(@RequestParam("message") String message) {

        return chatClient
                .prompt()
                .advisors(List.of(new SimpleLoggerAdvisor(), new TokenAuditAdvisor()))
                .system(systemPromptTemplate)
                .user(message)
                .call()
                .content();

    }

}
