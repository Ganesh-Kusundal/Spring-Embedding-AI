package com.striim.AI.wrapper;

import lombok.Builder;
import lombok.Getter;
import org.springframework.ai.document.MetadataMode;

import java.util.Objects;

@Getter
@Builder
public class VertexAIEmbeddingOptions {
    private final String project;
    private final String location;
    private final String publisher;
    private final String model;
    private final String content; // Assuming 'instance' JSON will be constructed from 'content'
    private MetadataMode metadataMode;

    // Generate the instance JSON from the content
    public String getInstanceJson() {
        return String.format("{ \"content\": \"%s\"}", this.content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexAIEmbeddingOptions that = (VertexAIEmbeddingOptions) o;
        return Objects.equals(project, that.project) &&
                Objects.equals(location, that.location) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(model, that.model);
        // Note: 'content' is deliberately ignored in the comparison
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, location, publisher, model);
        // Note: 'content' is deliberately excluded from the hash calculation
    }
}
