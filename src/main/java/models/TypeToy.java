package models;

import java.util.Arrays;

public enum TypeToy {
    FEMALE(1),
    MALE(2),
    UNISEX(3);
    private final int value;

    TypeToy(int value) {
        this.value = value;
    }
    public static TypeToy fromName(int value){
        return Arrays.stream(TypeToy.values()).filter(s-> s.value == value).findFirst().orElseThrow();
    }
}
