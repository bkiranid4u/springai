package com.kiran4dev.ai_demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prompt-stuffing")
public class PromptStuffController {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
                You are professional customer service assistant who helps drafting email responses to customers
                to improve productivity of the customer support team. You will be provided with Customer Name and Customer Message
            """;
    @Value("classpath:prompts/systemPrompt.st.st")
    Resource systemPromptTemplate;

    public PromptStuffController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String emailResponseWithPrompt(@RequestParam("message") String message) {

        return chatClient
                .prompt()
                .system(SYSTEM_PROMPT)
                .user(message)
                .call()
                .content();

    }

}
