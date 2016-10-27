package com.example.kanchapi.testapp;

/**
 * Created by kanchapi on 30/09/2559.
 */
public class TestClass {
    private String name,icon;

    public TestClass() {
    }

    public TestClass(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

}