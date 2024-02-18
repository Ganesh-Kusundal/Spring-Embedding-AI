package com.striim.AI.wrapper;

import lombok.Getter;
import org.springframework.ai.document.MetadataMode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Getter
public class AzureOpenAiEmbeddingOptions extends org.springframework.ai.azure.openai.AzureOpenAiEmbeddingOptions {
    private String apiKey;
    private String endpoint;
    private List<String> content;
    private MetadataMode metadataMode;
    // Manual Builder
    public static class Builder {
        private AzureOpenAiEmbeddingOptions options;

        public Builder() {
            options = new AzureOpenAiEmbeddingOptions();
        }

        public Builder withApiKey(String apiKey) {
            options.apiKey = apiKey;
            return this;
        }

        public Builder withEndpoint(String endpoint) {
            options.endpoint = endpoint;
            return this;
        }

        public Builder withContent(List<String> content) {
            options.content = content;
            return this;
        }

        public Builder withUser(String user) {
            options.setUser(user); // Leveraging setter from superclass
            return this;
        }

        public Builder withModel(String model) {
            options.setModel(model); // Leveraging setter from superclass
            return this;
        }

        public AzureOpenAiEmbeddingOptions build() {
            return options;
        }
    }

    // Override hashCode and equals as before

    @Override
    public int hashCode() {
        return Objects.hash(getModel(), getUser(), apiKey, endpoint);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AzureOpenAiEmbeddingOptions that = (AzureOpenAiEmbeddingOptions) o;
        return Objects.equals(apiKey, that.apiKey) &&
                Objects.equals(endpoint, that.endpoint) &&
                Objects.equals(content, that.content);
    }
}
