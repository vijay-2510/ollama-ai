package com.vijay.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MultimodelConfiguration {

  // Perplexity Configuration
  @Bean
  public OpenAiChatModel openAiChatModel(
      @Value("${spring.ai.openai.base-url}") String baseUrl,
      @Value("${spring.ai.openai.api-key}") String apiKey,
      @Value("${spring.ai.openai.chat.completions-path}") String completionsPath,
      @Value("${spring.ai.openai.chat.options.model}") String model,
      @Value("${spring.ai.openai.chat.options.temperature}") Double temperature) {

    var openAiApi = OpenAiApi.builder()
        .baseUrl(baseUrl)
        .apiKey(apiKey)
        .completionsPath(completionsPath)
        .build();

    var options = OpenAiChatOptions.builder()
        .model(model)
        .temperature(temperature)
        .build();

    return OpenAiChatModel.builder()
        .openAiApi(openAiApi)
        .defaultOptions(options)
        .build();
  }

  @Bean
  public ChatClient perplexityChatClient(@Qualifier("openAiChatModel") OpenAiChatModel chatModel) {
    return ChatClient.builder(chatModel).build();
  }

  // Ollama Configuration
  @Bean
  @Primary
  public OllamaChatModel ollamaChatModel(
      @Value("${spring.ai.ollama.base-url:http://localhost:11434}") String baseUrl,
      @Value("${spring.ai.ollama.chat.options.model:llama3.2}") String model) {

    var ollamaApi = OllamaApi.builder()
        .baseUrl(baseUrl)
        .build();

    var options = OllamaChatOptions.builder()
        .model(model)
        .build();

    return OllamaChatModel.builder()
        .ollamaApi(ollamaApi)
        .defaultOptions(options)
        .build();
  }

  @Bean
  public ChatClient ollamaChatClient(@Qualifier("ollamaChatModel") OllamaChatModel chatModel) {
    return ChatClient.builder(chatModel).build();
  }

}
