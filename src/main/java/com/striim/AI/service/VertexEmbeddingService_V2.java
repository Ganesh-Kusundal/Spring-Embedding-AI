package com.striim.AI.service;

import com.striim.AI.factory.ClientType;
import com.striim.AI.factory.EmbeddingClientFactory;
import com.striim.AI.wrapper.AmazonBedrockEmbeddingOptions;
import com.striim.AI.wrapper.VertexAIEmbeddingOptions_V2;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VertexEmbeddingService_V2 {
    @Autowired
    private EmbeddingClientFactory clientFactory;

    public EmbeddingResponse generateEmbedding(String appId, VertexAIEmbeddingOptions_V2 options) {
        var embeddingClient = clientFactory.getClient(appId, ClientType.AMAZON_BED_ROCK, options);
        return embeddingClient
                .embedForResponse(options.getContent());
    }
}
