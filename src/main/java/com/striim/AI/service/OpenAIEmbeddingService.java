package com.striim.AI.service;

import com.striim.AI.factory.ClientType;
import com.striim.AI.factory.EmbeddingClientFactory;
import com.striim.AI.wrapper.OpenAiEmbeddingOptions;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.model.ModelResponse;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAIEmbeddingService {
    @Autowired
    private EmbeddingClientFactory clientFactory;
   public ModelResponse<Embedding> generateEmbedding(String appId, OpenAiEmbeddingOptions options) {
       var embeddingClient = clientFactory.getClient(appId, ClientType.OPENAI, options);
       return embeddingClient
               .embedForResponse(options.getContent());
    }

}

