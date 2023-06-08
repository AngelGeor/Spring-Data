package com.example.football.models.dto.ImportPlayer;

import com.example.football.models.entity.Position;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayerDTO {

    @XmlElement(name = "first-name")
    @Size(min = 3)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 3)
    private String lastName;

    @XmlElement
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement
    private Position position;

    @XmlElement(name = "town")
    private NameDTO town;

    @XmlElement(name = "team")
    private NameDTO team;

    @XmlElement(name = "stat")
    private StatDTO stat;

    public ImportPlayerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public NameDTO getTown() {
        return town;
    }

    public void setTown(NameDTO town) {
        this.town = town;
    }

    public NameDTO getTeam() {
        return team;
    }

    public void setTeam(NameDTO team) {
        this.team = team;
    }

    public StatDTO getStat() {
        return stat;
    }

    public void setStat(StatDTO stat) {
        this.stat = stat;
    }
}
