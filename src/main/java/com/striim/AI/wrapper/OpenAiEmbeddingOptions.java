package com.striim.AI.wrapper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ai.document.MetadataMode;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class OpenAiEmbeddingOptions extends org.springframework.ai.openai.OpenAiEmbeddingOptions {
    private String openAiApiKey;
    private List<String> content;
    private MetadataMode metadataMode = MetadataMode.EMBED;

    public static class Builder {
        private String model;
        private String encodingFormat;
        private String user;
        private String openAiApiKey;
        private List<String> content;

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withEncodingFormat(String encodingFormat) {
            this.encodingFormat = encodingFormat;
            return this;
        }

        public Builder withUser(String user) {
            this.user = user;
            return this;
        }

        public Builder withOpenAiApiKey(String openAiApiKey) {
            this.openAiApiKey = openAiApiKey;
            return this;
        }

        public Builder withContent(List<String> content) {
            this.content = content;
            return this;
        }

        public OpenAiEmbeddingOptions build() {
            OpenAiEmbeddingOptions options = new OpenAiEmbeddingOptions();
            // Set superclass fields using setter methods
            options.setModel(this.model);
            options.setEncodingFormat(this.encodingFormat);
            options.setUser(this.user);
            // Set subclass fields
            options.setOpenAiApiKey(this.openAiApiKey);
            options.setContent(this.content);
            return options;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpenAiEmbeddingOptions)) return false;
        if (!super.equals(o)) return false;
        OpenAiEmbeddingOptions that = (OpenAiEmbeddingOptions) o;
        return Objects.equals(getOpenAiApiKey(), that.getOpenAiApiKey()) &&
                Objects.equals(getModel(), that.getModel()) &&
                Objects.equals(getEncodingFormat(), that.getEncodingFormat()) &&
                Objects.equals(getUser(), that.getUser());
        // Note: content is deliberately ignored in the comparison
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOpenAiApiKey(), getModel(), getEncodingFormat(), getUser());
        // Note: content is deliberately excluded from the hash calculation
    }
}
