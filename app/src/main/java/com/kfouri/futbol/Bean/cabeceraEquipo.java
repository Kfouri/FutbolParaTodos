package com.kfouri.futbol.Bean;

public class cabeceraEquipo implements itemEquipo
{
    private final String title;

    public cabeceraEquipo(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}