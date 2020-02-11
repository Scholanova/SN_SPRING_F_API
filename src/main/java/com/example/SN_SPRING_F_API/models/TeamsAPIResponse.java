package com.example.SN_SPRING_F_API.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeamsAPIResponse {
    @JsonProperty
    private ArrayList<Team> teams;
    @JsonProperty
    private Area area;

    private Map<String, Object> optional = new HashMap<>();

    @JsonAnySetter
    public void addOptional(String name, Object value) {
        optional.put(name, value);
    }

    @JsonAnyGetter
    public Object getOptional(String name) {
        return optional.get(name);
    }
}
