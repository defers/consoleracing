package com.defers.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Transport implements Moveable{
    private int tirePunctureChance;
    private int speed;
    private int currentSpeed;
    private boolean tirePunctured;
    private String name;

    public Transport(int tirePunctureChance, int speed, String name) {
        this.speed = speed;
        this.tirePunctureChance = tirePunctureChance;
        this.name = name;
    }

    @Override
    public void start() {
        currentSpeed = speed;
    }

    @Override
    public void stop() {
        currentSpeed = 0;
    }

    public void setTirePunctured() {
        tirePunctured = true;
        stop();
    }
}
