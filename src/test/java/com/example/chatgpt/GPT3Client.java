package com.example.chatgpt;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class GPT3Client {
    private static final String OPENAI_API_KEY = ""; // Replace with your OpenAI API key

    public static void main(String[] args) {
        String prompt = "Once upon a time";
        CompletableFuture<String> responseFuture = sendRequest(prompt);
        responseFuture.thenAccept(System.out::println)
                      .join(); // Wait for response and print it
    }

    public static CompletableFuture<String> sendRequest(String data) {
//    	String model = "gpt-3.5-turbo";
//        String prompt = "{\r\n"
//        		+ "     \"model\": \"gpt-3.5-turbo\",\r\n"
//        		+ "     \"messages\": [{\"role\": \"user\", \"content\": \"Say this is a test!\"}],\r\n"
//        		+ "     \"temperature\": 0.7\r\n"
//        		+ "   }";
//        int maxTokens = 4000;
    	String body = "{\r\n"
    			+ "     \"model\": \"gpt-3.5-turbo\",\r\n"
    			+ "     \"messages\": [{\"role\": \"user\", \"content\": \"Say this is a test!\"}],\r\n"
    			+ "     \"temperature\": 0.7\r\n"
    			+ "   }";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body);
    }
}

