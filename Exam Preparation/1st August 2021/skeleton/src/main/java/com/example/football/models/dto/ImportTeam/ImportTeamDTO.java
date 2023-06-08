package com.example.football.models.dto.ImportTeam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ImportTeamDTO {

    @Size(min = 3)
    @NotBlank
    private String name;

    @Size(min = 3)
    private String stadiumName;

    @Min(1000)
    @NotNull
    private int fanBase;

    @Size(min = 10)
    @NotBlank
    private String history;

    @NotBlank
    private String townName;

    public ImportTeamDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTownName() {
        return townName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getFanBase() {
        return fanBase;
    }

    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }
}
