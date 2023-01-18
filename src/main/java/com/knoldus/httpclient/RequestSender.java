package com.knoldus.httpclient;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class RequestSender {
    final HttpClient httpClient = HttpClient.newBuilder().build();
    public String printJoke() throws IOException, InterruptedException {
        String url = "https://official-joke-api.appspot.com/random_joke";
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        RandomJoke randomJoke1 = gson.fromJson(response.body().toString(),RandomJoke.class);
        System.out.println(randomJoke1.getSetup());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Headers: " + response.headers().allValues("content-type"));
        System.out.println("Body: " + response.body());
        return randomJoke1.getSetup();
    }
    public void generateFile() throws IOException, InterruptedException {
        String url = "https://www.sheldonbrown.com/web_sample1.html";
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse response = httpClient.send(httpRequest, HttpResponse
                .BodyHandlers.ofFile(Path.of("src/main/resources/output/output-template.html")));
        System.out.println("File generated successfully");
        System.out.println(response.body());
    }
    
    public void createUser() throws IOException, InterruptedException {
        Gson gson = new Gson();
        User user =new User("Aasif", "Software Consultant");
        String userString = gson.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users"))
                .POST(HttpRequest.BodyPublishers.ofString(userString))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
    
    public void predictGender(String name){
        String url = "https://api.genderize.io?name="+name;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url)).build();
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(b-> new Gson().fromJson(b, PredictGender.class))
                .thenAccept(System.out::println)
                .join();
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        RequestSender requestSender = new RequestSender();
        System.out.println("********Getting a joke for you********");
        requestSender.printJoke();
        System.out.println("********Generating a html file********");
        requestSender.generateFile();
        System.out.println("********Creating User********");
        requestSender.createUser();
        System.out.println("********Send a request in asynchronous way");
        requestSender.predictGender("Aasif");
    }
}
