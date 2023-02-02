package ru.parsing.emun;

import lombok.Getter;

import java.util.List;

@Getter
public enum TradersEnum {

    MECHANIC("mechanic","Механик" ),
    PRAPOR("prapor", "Прапор"),
    THERAPIST("therapist","Терапевт"),
    SKIER("skier","Лыжник"),
    PEACEMAKER("peacemaker","Миротворец"),
    RAGMAN("ragman","Барахольщик"),
    YAEGER("yaeger","Егерь"),
    FENCE("fence","Скупщик");

    private final String name;
    private final String userName;

    public static List<TradersEnum> getEnums;

    TradersEnum(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }
}
