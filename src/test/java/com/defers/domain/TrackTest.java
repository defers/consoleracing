package com.defers.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrackTest {

    private Track track;

    @Before
    public void setup() {
        track = new Track(20);
        Transport car1 = new Car(1, 3, "Car1", 2);
        Transport car2 = new Car(2, 4, "Car2", 3);
        Transport truck1 = new Truck(3, 2, "Truck1", 90);
        track.addTransport(car1);
        track.addTransport(car2);
        track.addTransport(truck1);
    }

    @Test(expected = RuntimeException.class)
    public void addTransport_ShouldThrowRuntimeException_WhenAlreadyContainsTransport() {
        Transport car1 = new Car(1, 3, "Car1", 2);
        track.addTransport(car1);
    }

    @Test(expected = RuntimeException.class)
    public void addTransport_ShouldAddTransport() {
        Transport car5 = new Car(1, 5, "Car5", 5);
        track.addTransport(car5);
        assertEquals(4, track.getTransports().size());
    }

    @Test
    public void getTransports() {
        Transport car1 = new Car(1, 3, "Car1", 2);
        Transport car2 = new Car(2, 4, "Car2", 3);
        Transport truck1 = new Truck(3, 2, "Truck1", 90);
        Transport[] expected = {car1, car2, truck1};

        assertArrayEquals(expected, track.getTransports().toArray());
    }

    @Test
    public void getLength() {
        assertEquals(20, track.getLength());
    }
}