package com.striim.AI.service;

import com.google.cloud.aiplatform.v1beta1.EndpointName;
import com.google.cloud.aiplatform.v1beta1.PredictResponse;
import com.google.cloud.aiplatform.v1beta1.PredictionServiceClient;
import com.google.cloud.aiplatform.v1beta1.PredictionServiceSettings;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;
import com.striim.AI.wrapper.VertexAIEmbeddingOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VertexAIEmbeddingService {

    private static final ConcurrentHashMap<String, PredictionServiceSettings> settingsCache = new ConcurrentHashMap<>();

    public PredictResponse generateEmbedding(String appId, VertexAIEmbeddingOptions options) throws IOException {
        // Unique cache key considering options excluding 'content'
        String cacheKey = appId + "-" + options.hashCode();

        // Reuse or create PredictionServiceSettings based on unique options
        PredictionServiceSettings settings = settingsCache.computeIfAbsent(cacheKey, k -> {
            try {
                // Construct endpoint URL and create settings
                String endpointUrl = String.format("%s-aiplatform.googleapis.com:443", options.getLocation());
                return PredictionServiceSettings.newBuilder().setEndpoint(endpointUrl).build();
            } catch (IOException e) {
                throw new RuntimeException("Failed to create PredictionServiceSettings for: " + cacheKey, e);
            }
        });

        // Use the settings to create a PredictionServiceClient
        try (PredictionServiceClient client = PredictionServiceClient.create(settings)) {
            EndpointName endpointName = EndpointName.ofProjectLocationPublisherModelName(options.getProject(), options.getLocation(), "google", options.getModel());
            Value.Builder instanceValueBuilder = Value.newBuilder();
            JsonFormat.parser().merge(options.getInstanceJson(), instanceValueBuilder);

            PredictResponse response = client.predict(endpointName, List.of(instanceValueBuilder.build()), Value.newBuilder().build());
            return response;
        }
    }
}
