package com.striim.AI.wrapper;

import lombok.Builder;
import lombok.Getter;
import org.springframework.ai.document.MetadataMode;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class AmazonBedrockEmbeddingOptions {
    private List<String> content;
    private String awsRegion;
    private String awsAccessKey;
    private String awsSecretKey;
    private ModelType modelType;
    private MetadataMode metadataMode;

    @Getter
    public enum ModelType {
        COHERE("cohere"),
        TITAN("titan");

        private final String modelName;

        ModelType(String name) {
            this.modelName = name;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmazonBedrockEmbeddingOptions that = (AmazonBedrockEmbeddingOptions) o;
        return Objects.equals(awsRegion, that.awsRegion) &&
                Objects.equals(awsAccessKey, that.awsAccessKey) &&
                Objects.equals(awsSecretKey, that.awsSecretKey);
        // Include other fields in comparison
    }

    @Override
    public int hashCode() {
        return Objects.hash(awsRegion, awsAccessKey, awsSecretKey);
        // Include other fields in hash calculation
    }
}
