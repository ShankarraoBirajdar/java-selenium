package com.example.chatgpt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
//https://github.com/TheoKanning/openai-java?tab=readme-ov-file
public class ChatGPTClient {
    public static void chatGPT() throws Exception {
        String url = "https://api.openai.com/v1/chat/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer ");
        
        String model = "gpt-3.5-turbo";
        String prompt = "[{\"role\": \"user\", \"content\": \"Hello!\"}]";
        int maxTokens = 4000;
        
        con.setDoOutput(true);
        String body = "{\"model\": \"" + model + "\", \"messages\": " + prompt + ", \"max_tokens\": " + maxTokens + "}";
        
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(body);
        writer.flush();
        
        System.out.println(body);

        //Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString()); 
    }

    public static void main(String[] args) throws Exception {
    	chatGPT();
    }
}