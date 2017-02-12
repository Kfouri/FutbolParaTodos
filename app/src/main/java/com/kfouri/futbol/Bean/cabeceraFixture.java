package com.kfouri.futbol.Bean;

public class cabeceraFixture implements itemFixture
{
    private final String title;

    public cabeceraFixture(String title) {
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
