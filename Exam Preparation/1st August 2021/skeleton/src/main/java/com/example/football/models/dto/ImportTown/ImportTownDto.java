package com.example.football.models.dto.ImportTown;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportTownDto {

    @Size(min = 2)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private int population;

    @Size(min = 10)
    @NotBlank
    private String travelGuide;

    public ImportTownDto() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }
}
