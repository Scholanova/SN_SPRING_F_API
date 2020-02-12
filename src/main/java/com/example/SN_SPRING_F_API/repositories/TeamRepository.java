package com.example.SN_SPRING_F_API.repositories;

import com.example.SN_SPRING_F_API.helpers.CID;
import com.example.SN_SPRING_F_API.loggers.RequestResponseLoggingInterceptor;
import com.example.SN_SPRING_F_API.models.Team;
import com.example.SN_SPRING_F_API.models.TeamsAPIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamRepository {

    private static final Logger log = LoggerFactory.getLogger(TeamRepository.class);

    @Value("${API_KEY}")
    private String API_KEY;
    private static final String API_BASE_URL = "https://api.football-data.org/v2";

    public List<Team> getAllTeams(String sortField) {

        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        String url = API_BASE_URL + "/competitions/CL/teams?season=2018";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));

        TeamsAPIResponse result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity, TeamsAPIResponse.class).getBody();

        List<Team> teams = result.teams;
        switch ( sortField ) {
            case "name":
                teams = teams.stream().sorted(Comparator.comparing(Team::getName)).collect(Collectors.toList());
                break;
            case "year":
                teams = teams.stream().sorted(Comparator.comparing(Team::getYear)).collect(Collectors.toList());
                break;
        };
        return teams;
    }
}
