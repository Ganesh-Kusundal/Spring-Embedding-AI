package com.striim.AI.service;

import com.striim.AI.factory.ClientType;
import com.striim.AI.factory.EmbeddingClientFactory;
import com.striim.AI.wrapper.AzureOpenAiEmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzureOpenAiEmbeddingService {
    @Autowired
    private EmbeddingClientFactory clientFactory;

    public EmbeddingResponse generateEmbedding(String appId, AzureOpenAiEmbeddingOptions options) {
        var embeddingClient = clientFactory.getClient(appId, ClientType.AZURE_OPENAI, options);
        return embeddingClient
                .embedForResponse(options.getContent());
    }
}
