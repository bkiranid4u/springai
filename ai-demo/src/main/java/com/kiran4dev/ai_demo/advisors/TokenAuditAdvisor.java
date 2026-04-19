package com.kiran4dev.ai_demo.advisors;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;

public class TokenAuditAdvisor implements CallAdvisor {

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public String getName() {
        return "TokenAuditAdvisor";
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = chatClientResponse.chatResponse();
        if (chatResponse != null && chatResponse.getMetadata() != null
                && chatResponse.getMetadata().getUsage() != null) {
            Usage usage = chatResponse.getMetadata().getUsage();
            System.out.println("Total tokens used in the response: " + usage.getTotalTokens());
        }
        return chatClientResponse;
    }

}
