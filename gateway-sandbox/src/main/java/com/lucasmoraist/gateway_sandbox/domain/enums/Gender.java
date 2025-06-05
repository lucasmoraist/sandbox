package com.lucasmoraist.gateway_sandbox.domain.enums;

public enum Gender {
    MAN("Man"),
    WOMAN("Woman"),
    NON_BINARY("Non Binary"),
    TRANSGENDER("Transgender"),
    OTHER("Other"),
    PREFER_NOT_SAY("Prefer Not To Say");

    private final String gender;

    public String getGender() {
        return gender;
    }

    Gender(String gender) {
        this.gender = gender;
    }
}
