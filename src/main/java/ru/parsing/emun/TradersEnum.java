package ru.parsing.emun;

import lombok.Getter;

import java.util.List;

@Getter
public enum TradersEnum {

    MECHANIC("mechanic"),
    PRAPOR("prapor"),
    THERAPIST("therapist"),
    SKIER("skier"),
    PEACEMAKER("peacemaker"),
    RAGMAN("ragman"),
    YAEGER("yaeger"),
    FENCE("fence");

    private final String name;

    public static List<TradersEnum> getEnums;

    TradersEnum(String name) {
        this.name = name;
    }
}
