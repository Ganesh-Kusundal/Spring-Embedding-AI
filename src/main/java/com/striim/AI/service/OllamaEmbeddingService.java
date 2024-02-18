package com.striim.AI.service;

import com.striim.AI.factory.ClientType;
import com.striim.AI.factory.EmbeddingClientFactory;
import com.striim.AI.wrapper.OllamaEmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OllamaEmbeddingService {
    @Autowired
    private EmbeddingClientFactory clientFactory;
    public EmbeddingResponse generateEmbedding(String appId, OllamaEmbeddingOptions options) {
        var embeddingClient = clientFactory.getClient(appId, ClientType.OLLAMA, options);
        return embeddingClient
                .embedForResponse(options.getContent());
    }
}
