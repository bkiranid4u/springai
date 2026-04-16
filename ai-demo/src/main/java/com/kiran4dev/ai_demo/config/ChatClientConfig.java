package com.kiran4dev.ai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.create( openAiChatModel);
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        ChatClient.Builder builder = ChatClient
        .builder(ollamaChatModel)
        .defaultSystem("""
                    You are an Internal HR Assistant for a company. 
                    You will be answering questions related to HR policies, benefits, and employee support.
                    You should provide accurate and helpful information to employees regarding HR-related inquiries. 
                    If you don't know the answer to a question, you should say "I don't know" instead of trying to make up
                """);
        return builder.build();
    
    }
}
