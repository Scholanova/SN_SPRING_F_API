package com.example.SN_SPRING_F_API.controllers;

import com.example.SN_SPRING_F_API.helpers.CID;
import com.example.SN_SPRING_F_API.models.Team;
import com.example.SN_SPRING_F_API.repositories.TeamRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
public class TeamsController {

    private static final Logger log = LoggerFactory.getLogger(TeamsController.class);

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping(path = "/teams")
    public List<Team> getTeams(@RequestParam(required = false, defaultValue = "desc") String sort, HttpServletRequest request) {
        log.info("Client Request: " + CID.getInstance().getId());
        log.info(request.getMethod().toString() + " " + request.getRequestURL().toString());


        List<Team> teams = teamRepository.getAllTeams(sort);

        log.info("Client Response: " + CID.getInstance().getId());
        log.info("Teams count: " + teams.size());
        log.info(new Gson().toJson(teams));
        return teams;
    }
}
