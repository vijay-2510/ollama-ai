package com.vijay.ollama.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

  private final ChatClient ollamaChatClient;
  private final ChatClient perplexityChatClient;

  public ChatController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient,
      @Qualifier("perplexityChatClient") ChatClient perplexityChatClient) {
    this.ollamaChatClient = ollamaChatClient;
    this.perplexityChatClient = perplexityChatClient;
  }

  @GetMapping("/ollama/chat")
  public String ollamaChat(@RequestParam String message) {
    return ollamaChatClient.prompt(message).call().content();
  }

  @GetMapping("/perplexity/chat")
  public String perplexityChat(@RequestParam String message) {
    return perplexityChatClient.prompt(message).call().content();
  }

}
