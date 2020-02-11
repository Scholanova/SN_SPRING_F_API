package com.example.SN_SPRING_F_API.controllers;

import com.example.SN_SPRING_F_API.models.Team;
import com.example.SN_SPRING_F_API.models.TeamsAPIResponse;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TeamsController {

    private final OkHttpClient httpClient = new OkHttpClient();

    private static final String API_KEY = "578c0fac52c943acb16a1d6aa8fb3fb0";
    private static final String API_BASE_URL = "https://api.football-data.org/v2";

    private static final String API_TEAMS = API_BASE_URL + "/teams";

    @GetMapping(path = "/teams")
    public ResponseEntity getTeams() throws IOException {
        Team[] teams = sendRequest<Teams>("/competitions/CL/teams?season=2019");

        return ResponseEntity.status(200).body(sendRequest("/competitions/CL/teams?season=2019"));
    }

    @GetMapping(path = "/teams/{id}")
    public ResponseEntity getTeams(@PathVariable String id) throws IOException {
        return ResponseEntity.status(200).body(sendRequest("/teams/" + id));
    }

    private String sendRequest(String endpoint) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + endpoint)
                .addHeader("X-Auth-Token", API_KEY)
                .build();

        try(Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String data = response.body().string();
            Gson gson = new Gson();
            TeamsAPIResponse team = gson.fromJson(data, TeamsAPIResponse.class);

            return data;
        }
    }
}
