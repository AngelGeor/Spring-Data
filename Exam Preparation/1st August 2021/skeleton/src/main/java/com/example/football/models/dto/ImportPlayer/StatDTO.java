package com.example.football.models.dto.ImportPlayer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatDTO {

    @XmlElement
    private Long id;

    public Long getId() {
        return id;
    }
}
