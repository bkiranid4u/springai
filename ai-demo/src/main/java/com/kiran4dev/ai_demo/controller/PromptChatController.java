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
@RequestMapping("/api/prompt-chat")
public class PromptChatController {

        private final ChatClient chatClient;

        private static final String SYSTEM_PROMPT = """
                            You are professional customer service assistant who helps drafting email responses to customers
                            to improve productivity of the customer support team. You will be provided with Customer Name and Customer Message
                        """;
        @Value("classpath:prompts/prompt.st")
        Resource userPromptTemplate;

        private static final String USER_PROMPT_TEMPLATE = """
                        A Customer names {customerName} sent the following message {customerMessage}

                        Write a polite and helpful email response addressing the issue.
                        Maintain a professional tone and reassurance.

                        Respond as if you're writing the email body only. Don't include subject and signature.
                        """;

        public PromptChatController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
                this.chatClient = chatClient;
        }

        @GetMapping("/email")
        public String emailResponseWithPrompt(@RequestParam("customerName") String customerName,
                        @RequestParam("customerMessage") String customerMessage) {

                return chatClient
                                .prompt()
                                .system(SYSTEM_PROMPT)
                                .user(prompt -> prompt
                                                .text(userPromptTemplate)
                                                .param("customerName", customerName)
                                                .param("customerMessage", customerMessage))
                                .call()
                                .content();

                // return chatClient
                // .prompt("Customer: " + customerName + "\nMessage: " + customerMessage)
                // .system(SYSTEM_PROMPT)
                // .user(prompt -> prompt
                // .text(USER_PROMPT_TEMPLATE.replace("{customerName}", customerName)
                // .replace("{customerMessage}", customerMessage)))
                // .call().content();
        }
}
