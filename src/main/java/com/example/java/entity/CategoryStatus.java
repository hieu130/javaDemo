package com.example.java.entity;

public enum CategoryStatus {
    ACTIVE(1),
    DEACTIVE(0),
    DELETED(-1),
    UNDEFINE(2);

    private int value;

    CategoryStatus(int value){this.value = value;}

    public int getValue() {
        return value;
    }

    public static CategoryStatus of (int value){
        for (CategoryStatus productEN : CategoryStatus.values()){
            if(productEN.getValue() == value){
                return productEN;
            }
        }
        return CategoryStatus.UNDEFINE;
    }
}
