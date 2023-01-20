package com.defers.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TruckTest {

    @Test
    public void shouldReturnObjectWhenPassedAllArgConstructor() {
        Truck truck = new Truck(15, 3, "Truck1", 90);
        assertNotNull(truck);
    }

    @Test
    public void shouldReturnCargoWeight() {
        Truck truck = new Truck(15, 3, "Truck1", 90);
        int expected = 90;
        assertEquals(expected, truck.getCargoWeight());
    }

    @Test
    public void shouldReturnTruckName() {
        Truck truck = new Truck(15, 3, "Truck1", 90);
        String expected = "Truck1";
        assertEquals(expected, truck.getName());
    }

    @Test
    public void shouldReturnTruckSpeed() {
        Truck truck = new Truck(15, 3, "Truck1", 90);
        int expected = 3;
        assertEquals(expected, truck.getSpeed());
    }

    @Test
    public void shouldReturnTruckTirePunctureChance() {
        Truck truck = new Truck(15, 3, "Truck1", 90);
        int expected = 15;
        assertEquals(expected, truck.getTirePunctureChance());
    }

}