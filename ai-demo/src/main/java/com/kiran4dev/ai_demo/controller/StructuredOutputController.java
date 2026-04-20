package com.kiran4dev.ai_demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiran4dev.ai_demo.models.CountryCities;

@RestController
@RequestMapping("/api/structured-output")
public class StructuredOutputController {

    private final ChatClient chatClient;

    public StructuredOutputController(@Qualifier("openAiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public CountryCities getStructuredResponse(@RequestParam("message") String message) {
        CountryCities countryCities = this.chatClient
                .prompt()
                .user(message)
                .call()
                .entity(CountryCities.class);

        return countryCities;
    }

    @GetMapping("/chat-list")
    public List<String> getStructuredResponseList(@RequestParam("message") String message) {
        List<String> countryCities = this.chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter());

        return countryCities;
    }

    @GetMapping("/chat-map")
    public Map<String, Object> getStructuredResponseMap(@RequestParam("message") String message) {
        Map<String, Object> countryCities = this.chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new MapOutputConverter());

        return countryCities;
    }

    @GetMapping("/chat-bean-list")
    public List<CountryCities> getStructuredResponseBeanList(@RequestParam("message") String message) {
        List<CountryCities> countryCitiesList = this.chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<List<CountryCities>>() {});

        return countryCitiesList;
    }

}






















