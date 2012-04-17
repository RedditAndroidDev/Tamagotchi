package com.redditandroiddevelopers.tamagotchi.model;

public enum Gender {
    MALE("M"), FEMALE("F");

    private String dbValue;

    Gender(String dbChar) {
        dbValue = dbChar;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static Gender fromDbValue(String dbValue) {
        for (Gender g : Gender.values()) {
            if (dbValue.equals(g.getDbValue())) {
                return g;
            }
        }
        return null;
    }
}
