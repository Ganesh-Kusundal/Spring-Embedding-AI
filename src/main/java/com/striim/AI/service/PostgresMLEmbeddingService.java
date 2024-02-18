package com.striim.AI.service;

import com.striim.AI.factory.ClientType;
import com.striim.AI.factory.EmbeddingClientFactory;
import com.striim.AI.wrapper.PostgresMlEmbeddingOptions;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.model.ModelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostgresMLEmbeddingService {
    @Autowired
    private EmbeddingClientFactory clientFactory;
   public ModelResponse<Embedding> generateEmbedding(String appId, PostgresMlEmbeddingOptions options) {
       var embeddingClient = clientFactory.getClient(appId, ClientType.POSTGRESML, options);
       return embeddingClient
               .embedForResponse(options.getContent());
    }

}

