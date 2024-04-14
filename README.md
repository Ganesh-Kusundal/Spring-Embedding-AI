# Embedding Services API

## Overview
This project provides a REST API for generating embeddings using various AI platforms such as OpenAI, VertexAI, Ollama, Azure OpenAI, and others. It allows interfacing with multiple AI services to fetch embeddings, which can be useful in various applications such as recommendation systems, search engines, and more.

## Technologies
- Java 17
- Spring Boot 3.2.2
- Maven and Gradle for dependency management
- Various AI Platform SDKs (OpenAI, Google VertexAI, Amazon AI, etc.)

## Getting Started

### Prerequisites
- JDK 17
- Gradle or Maven
- Access to the respective AI platforms with credentials

### Installation
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd <project-name>
   ```
3. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```

### Configuration
Ensure you have the appropriate keys and credentials configured for each AI service. These should be set up in your application properties or as environment variables.

### Running the Application
Start the application using Gradle:
```bash
./gradlew bootRun
```
The service will start at `http://localhost:8080`.

## API Usage

### Endpoints
**Generate OpenAI Embedding**
- **URL:** `/embeddings/openai`
- **Method:** `POST`
- **App ID:** Yes (`App-ID` header)
- **Playload:**
  ```json
  {
  "openAiApiKey": "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",
  "content": [
    "The first piece of text to generate embeddings for.",
    "The second piece of text to generate embeddings for."
  ],
  "model": "text-embedding-ada-002",
  "encodingFormat": "float",
  "user": "user-identifier"
  }
  ```

[Include similar sections for each other AI service endpoint.]

### Error Handling
Responses indicate success or failure. Failures provide an error message for debugging.

## Dependencies
- Lombok
- Spring Boot
- Various AI SDKs like Google Cloud AI Platform, Spring AI for OpenAI, Ollama, Azure OpenAI, Bedrock AI, PostgresML, and AWS SDK.
- Project Reactor and Springdoc for reactive support and API documentation.

## Contributing
Contributions are welcome. Please fork the repository and submit pull requests. Open an issue first to discuss major changes.

## License
This project is open-sourced under the MIT License, which permits reuse, distribution, and modification.

```license
MIT License

Copyright (c) [year] [full name]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
