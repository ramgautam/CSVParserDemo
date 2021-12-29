package com.coda.test.datatype;


public enum DataType {
    PHONE_NUMBER("phone_number"),EMAIL("email"),STRING("String");

    private final String name;

    private DataType(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }

}
