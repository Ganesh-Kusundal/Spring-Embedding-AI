package com.striim.AI.factory;

import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.striim.AI.wrapper.*;
import org.springframework.ai.azure.openai.AzureOpenAiEmbeddingClient;
import org.springframework.ai.bedrock.cohere.BedrockCohereEmbeddingClient;
import org.springframework.ai.bedrock.cohere.api.CohereEmbeddingBedrockApi;
import org.springframework.ai.bedrock.titan.BedrockTitanEmbeddingClient;
import org.springframework.ai.bedrock.titan.api.TitanEmbeddingBedrockApi;
import org.springframework.ai.embedding.AbstractEmbeddingClient;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.postgresml.PostgresMlEmbeddingClient;
import org.springframework.ai.vertex.VertexAiEmbeddingClient;
import org.springframework.ai.vertex.api.VertexAiApi;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;


import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EmbeddingClientFactory {

    private static final  Map<String, AbstractEmbeddingClient> clientCache = new ConcurrentHashMap<>();

    public AbstractEmbeddingClient getClient(String appId, ClientType clientType, Object options) {
        String cacheKey = appId + "-" + clientType.name() + "-" + Objects.hashCode(options);
        return clientCache.computeIfAbsent(cacheKey, k -> createClient(clientType, options));
    }

    private AbstractEmbeddingClient createClient(ClientType clientType, Object options) {
        switch (clientType) {
            case AZURE_OPENAI:
                return createAzureClient((AzureOpenAiEmbeddingOptions) options);
            case OLLAMA:
                return createOllamaClient((OllamaEmbeddingOptions) options);
            case OPENAI:
                return createOpenAiClient((OpenAiEmbeddingOptions) options);
            case POSTGRESML:
                return createPostgresMlClient((PostgresMlEmbeddingOptions) options);
            case AMAZON_BED_ROCK:
                return createAmazonBedRockClient((AmazonBedrockEmbeddingOptions) options);
            case VERTEXAI:
                return createGoogleVertexClient((VertexAIEmbeddingOptions_V2) options);
            default:
                throw new IllegalArgumentException("Unsupported client type: " + clientType);
        }
    }

    private AbstractEmbeddingClient createGoogleVertexClient(VertexAIEmbeddingOptions_V2 options) {
        VertexAiApi vertexAiApi = new VertexAiApi(options.getApiKey());
        return new VertexAiEmbeddingClient(vertexAiApi);
    }


    private AbstractEmbeddingClient createAmazonBedRockClient(AmazonBedrockEmbeddingOptions options) {
        var awsCredentials = AwsBasicCredentials.create(options.getAwsAccessKey(), options.getAwsSecretKey());
        var awsCredentialsProvider = StaticCredentialsProvider.create(awsCredentials);
        switch (options.getModelType()) {
            case COHERE:
                var cohereEmbeddingApi = new CohereEmbeddingBedrockApi(
                        CohereEmbeddingBedrockApi.CohereEmbeddingModel.COHERE_EMBED_MULTILINGUAL_V1.id(),
                        awsCredentialsProvider, options.getAwsRegion(), new ObjectMapper());
                return new BedrockCohereEmbeddingClient(cohereEmbeddingApi);
            case TITAN:
                var titanEmbeddingApi = new TitanEmbeddingBedrockApi(
                        TitanEmbeddingBedrockApi.TitanEmbeddingModel.TITAN_EMBED_IMAGE_V1.id(), Region.US_EAST_1.id());
             return new BedrockTitanEmbeddingClient(titanEmbeddingApi);
            default:
                throw new IllegalArgumentException("Unsupported client type: " + options.getModelType());
        }
    }

    private AbstractEmbeddingClient createPostgresMlClient(PostgresMlEmbeddingOptions options) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(options.getDatabaseUrl());
        dataSource.setUsername(options.getUsername());
        dataSource.setPassword(options.getPassword());
        var jdbcTemplate = new JdbcTemplate(dataSource); // your posgresml data source
        var client = new PostgresMlEmbeddingClient(jdbcTemplate, options);
        client.afterPropertiesSet();
        return client;
    }

    private AzureOpenAiEmbeddingClient createAzureClient(AzureOpenAiEmbeddingOptions options) {
        var openAIClient = new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(options.getApiKey()))
                .endpoint(options.getEndpoint())
                .buildClient();
        return new AzureOpenAiEmbeddingClient(openAIClient, options.getMetadataMode(), options);
    }

    private OllamaEmbeddingClient createOllamaClient(OllamaEmbeddingOptions options) {
        var ollamaApi = new OllamaApi(options.getBaseUrl());
        return new OllamaEmbeddingClient(ollamaApi).withDefaultOptions(options);
    }

    private OpenAiEmbeddingClient createOpenAiClient(OpenAiEmbeddingOptions options) {
        var openAiApi = new OpenAiApi(options.getOpenAiApiKey());
        return new OpenAiEmbeddingClient(openAiApi, options.getMetadataMode(), options);
    }
}
