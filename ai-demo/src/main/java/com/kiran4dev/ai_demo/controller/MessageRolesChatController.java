// package com.kiran4dev.ai_demo.controller;

// import org.springframework.ai.chat.client.ChatClient;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api")
// public class MessageRolesChatController {

// private final ChatClient openAiChatClient;
// private final ChatClient ollamaChatClient;

// public MessageRolesChatController(@Qualifier("ollamaChatClient") ChatClient
// ollamaChatClient, @Qualifier("openAiChatClient") ChatClient openAiChatClient)
// {
// this.ollamaChatClient = ollamaChatClient;
// this.openAiChatClient = openAiChatClient;
// }

// @GetMapping("/openai/chat")
// public String chat(@RequestParam("message") String message) {
// return openAiChatClient.prompt(message).call().content();
// }

// @GetMapping("/ollama/chat")
// public String ollamaChat(@RequestParam("message") String message) {
// return ollamaChatClient.prompt(message).call().content();
// }

// }