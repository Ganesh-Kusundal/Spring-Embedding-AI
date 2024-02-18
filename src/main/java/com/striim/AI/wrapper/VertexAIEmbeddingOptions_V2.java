package com.striim.AI.wrapper;

import lombok.Builder;
import lombok.Getter;
import org.springframework.ai.document.MetadataMode;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
public class VertexAIEmbeddingOptions_V2 {
    private String apiKey;
    private MetadataMode metadataMode;
    private List<String> content;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexAIEmbeddingOptions_V2 that = (VertexAIEmbeddingOptions_V2) o;
        return Objects.equals(apiKey, that.apiKey);
        // Note: 'content' is deliberately ignored in the comparison
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey);
        // Note: 'content' is deliberately excluded from the hash calculation
    }
}
