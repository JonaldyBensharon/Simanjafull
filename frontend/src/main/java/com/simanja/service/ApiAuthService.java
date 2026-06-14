package com.simanja.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simanja.dto.LoginRequest;
import com.simanja.dto.RegisterRequest;
import com.simanja.dto.UserApiResponse;
import com.simanja.dto.UserResponse;
import com.simanja.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiAuthService {

    private static final String BASE_URL =
            "http://localhost:8080/api/auth";

    private final HttpClient client;
    private final ObjectMapper mapper;

    public ApiAuthService() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public User login(
            String email,
            String password
    ) throws IOException, InterruptedException {

        LoginRequest request =
                new LoginRequest(
                        email,
                        password
                );

        String json =
                mapper.writeValueAsString(request);

        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        BASE_URL + "/login"
                                )
                        )
                        .header(
                                "Content-Type",
                                "application/json"
                        )
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(json)
                        )
                        .build();

        HttpResponse<String> response =
                client.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString()
                );

        UserApiResponse apiResponse =
                mapper.readValue(
                        response.body(),
                        UserApiResponse.class
                );

        if (!apiResponse.isSuccess()) {
            throw new IllegalArgumentException(
                    apiResponse.getMessage()
            );
        }

        return mapToUser(
                apiResponse.getData()
        );
    }

    public User register(
            String nama,
            String email,
            String password,
            String konfirmPassword
    ) throws IOException, InterruptedException {

        RegisterRequest request =
                new RegisterRequest(
                        nama,
                        email,
                        password,
                        konfirmPassword
                );

        String json =
                mapper.writeValueAsString(request);

        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        BASE_URL + "/register"
                                )
                        )
                        .header(
                                "Content-Type",
                                "application/json"
                        )
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(json)
                        )
                        .build();

        HttpResponse<String> response =
                client.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString()
                );

        UserApiResponse apiResponse =
                mapper.readValue(
                        response.body(),
                        UserApiResponse.class
                );

        if (!apiResponse.isSuccess()) {
            throw new IllegalArgumentException(
                    apiResponse.getMessage()
            );
        }

        return mapToUser(
                apiResponse.getData()
        );
    }

    private User mapToUser(
            UserResponse response
    ) {

        User user = new User();

        user.setId(
                response.getId().intValue()
        );

        user.setNama(
                response.getNama()
        );

        user.setEmail(
                response.getEmail()
        );

        user.setRole(
                response.getRole()
        );

        user.setUsername(
                response.getUsername()
        );

        user.setTelepon(
                response.getTelepon()
        );

        user.setJenisKelamin(
                response.getJenisKelamin()
        );

        user.setTanggalLahir(
                response.getTanggalLahir()
        );

        user.setAlamat(
                response.getAlamat()
        );

        user.setProfileImagePath(
                response.getProfileImagePath()
        );

        return user;
    }
}