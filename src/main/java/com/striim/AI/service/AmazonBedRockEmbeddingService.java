package com.striim.AI.service;

import com.striim.AI.factory.EmbeddingType;
import com.striim.AI.factory.EmbeddingClientFactory;
import com.striim.AI.wrapper.AmazonBedrockEmbeddingOptions;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.model.ModelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonBedRockEmbeddingService {
    @Autowired
    private EmbeddingClientFactory clientFactory;

    public ModelResponse<Embedding> generateEmbedding(String appId, AmazonBedrockEmbeddingOptions options) {
        var embeddingClient = clientFactory.getClient(appId, EmbeddingType.AMAZON_BED_ROCK, options);
        return embeddingClient
                .embedForResponse(options.getContent());
    }
}
