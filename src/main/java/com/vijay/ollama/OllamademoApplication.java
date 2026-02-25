package com.vijay.ollama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ai.model.openai.autoconfigure.OpenAiEmbeddingAutoConfiguration;

@SpringBootApplication(exclude = {OpenAiEmbeddingAutoConfiguration.class})
public class OllamademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OllamademoApplication.class, args);
	}

}
