package com.striim.AI.controller;

import com.google.cloud.aiplatform.v1beta1.PredictResponse;
import com.striim.AI.service.*;
import com.striim.AI.wrapper.*;
import org.springframework.ai.embedding.*;
import org.springframework.ai.model.ModelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/embeddings")
public class EmbeddingController {

    @Autowired
    @Lazy
    private OpenAIEmbeddingService embeddingService;

    @Autowired
    @Lazy
    private VertexAIEmbeddingService vertexAIEmbeddingService;

    @Autowired
    @Lazy
    private OllamaEmbeddingService ollamaEmbeddingService;

    @Autowired
    @Lazy
    private AzureOpenAiEmbeddingService azureOpenAiEmbeddingService;

    @Autowired
    @Lazy
    VertexEmbeddingService_V2 vertexEmbeddingServiceV2;

    @Autowired
    @Lazy
    PostgresMLEmbeddingService postgresMLEmbeddingService;

    @Autowired
    @Lazy
    AmazonBedRockEmbeddingService amazonBedRockEmbeddingService;
    

    @PostMapping("/openai")
    public ResponseEntity<ModelResponse<Embedding>> generateOpenAIEmbedding(@RequestBody OpenAiEmbeddingOptions options, @RequestHeader("App-ID") String appId) {
        // Assuming generateEmbedding is a method in the OpenAiEmbeddingClient
        return ResponseEntity.ok(embeddingService.generateEmbedding(appId, options));
    }

    @PostMapping("/vertexai")
    public ResponseEntity<?> generateVertexAIEmbedding(@RequestBody VertexAIEmbeddingOptions options, @RequestHeader("App-ID") String appId) {
        // Assuming a similar getClient method and generateEmbedding for VertexAI
        PredictResponse embeddings;
        try {
            embeddings = vertexAIEmbeddingService.generateEmbedding(appId, options);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to generate embeddings: " + e.getMessage());
        }
        return ResponseEntity.ok(embeddings);
    }

    @PostMapping("/palm")
    public ResponseEntity<ModelResponse<Embedding>> generatePalmEmbeddings(@RequestBody VertexAIEmbeddingOptions_V2 options, @RequestHeader("App-ID") String appId) {
        ModelResponse<Embedding> embeddings = vertexEmbeddingServiceV2.generateEmbedding(appId, options);
        return ResponseEntity.ok(embeddings);
    }
    @PostMapping("/ollama")
    public ResponseEntity<ModelResponse<Embedding>> generateEmbeddings(@RequestBody OllamaEmbeddingOptions options, @RequestHeader("App-ID") String appId) {
        ModelResponse<Embedding>  embeddings = ollamaEmbeddingService.generateEmbedding(appId, options);
        return ResponseEntity.ok(embeddings);
    }

    @PostMapping("/azure")
    public ResponseEntity<ModelResponse<Embedding>> generateAzureEmbeddings(@RequestBody AzureOpenAiEmbeddingOptions options, @RequestHeader("App-ID") String appId) {
        ModelResponse<Embedding>  embeddings = azureOpenAiEmbeddingService.generateEmbedding(appId, options);
        return ResponseEntity.ok(embeddings);
    }

    @PostMapping("/postgres")
    public ResponseEntity<ModelResponse<Embedding>> generatePostgresMLEmbeddings(@RequestBody PostgresMlEmbeddingOptions options, @RequestHeader("App-ID") String appId) {
        ModelResponse<Embedding> embeddings = postgresMLEmbeddingService.generateEmbedding(appId, options);
        return ResponseEntity.ok(embeddings);
    }

    @PostMapping("/amazon")
    public ResponseEntity<ModelResponse<Embedding>> generateAmazonBedRockEmbeddings(@RequestBody AmazonBedrockEmbeddingOptions options, @RequestHeader("App-ID") String appId) {
        ModelResponse<Embedding> embeddings = amazonBedRockEmbeddingService.generateEmbedding(appId, options);
        return ResponseEntity.ok(embeddings);
    }
}
