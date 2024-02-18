package com.striim.AI.wrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.springframework.ai.document.MetadataMode;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PostgresMlEmbeddingOptions extends org.springframework.ai.postgresml.PostgresMlEmbeddingOptions {
    private String databaseUrl;
    private String username;
    private String password;
    private MetadataMode metadataMode;
    private List<String> content;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostgresMlEmbeddingOptions that = (PostgresMlEmbeddingOptions) o;
        return Objects.equals(databaseUrl, that.databaseUrl) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                metadataMode == that.metadataMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(databaseUrl, username, password, metadataMode);
    }
}
