package com.example.SN_SPRING_F_API.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Team {

    private String id;
    private String name;
    private String abbrev;
    private String pays;
    private String year;

    @JsonProperty(value = "id")
    private void upId(String id) {
        this.id = id;
    }

    @JsonProperty(value = "name")
    private void upName(String name) {
        this.name = name;
    }

    @JsonProperty(value = "tla")
    private void unpackAbbrev(String tla) {
        this.abbrev = tla;
    }

    @JsonProperty(value = "area")
    private void unpackPays(@NotNull Map<String, String> area) {
        pays = area.get("name");
    }

    @JsonProperty(value = "founded")
    private void upFounded(String year) {
        this.setYear(year);
    }

    public Team() {
    }

    public Team(String id, String name, String abbrev, String pays) {
        this.id = id;
        this.name = name;
        this.abbrev = abbrev;
        this.pays = pays;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getPays() {
        return this.pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Team [id=" + getId() + ", name=" + getName() + ", pays=" + getPays() + "]";
    }
}
