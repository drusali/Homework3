package com.example.homework3.Model;

public class FavCats {
    private String name;

    public FavCats(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}
