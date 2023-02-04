package ru.parsing.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ads_table")
@Data
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String advertise;
}
