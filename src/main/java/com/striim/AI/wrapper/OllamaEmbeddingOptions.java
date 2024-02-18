package com.striim.AI.wrapper;

import lombok.Builder;
import lombok.Getter;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.ollama.api.OllamaOptions;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class OllamaEmbeddingOptions extends OllamaOptions {
    private List<String> content;
    private String baseUrl;
    private MetadataMode metadataMode;

    @Override
    public int hashCode() {
        // Compute a hash code excluding the content field
        return Objects.hash(baseUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OllamaEmbeddingOptions that = (OllamaEmbeddingOptions) o;
        return Objects.equals(baseUrl, that.baseUrl);
        // Note: Content is deliberately ignored in equals to match the hashCode behavior
    }
}
