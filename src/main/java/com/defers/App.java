package com.defers;

import com.defers.domain.*;

public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        Transport car1 = new Car(1, 3, "Car1", 2);
        Transport car2 = new Car(2, 4, "Car2", 3);
        Transport truck1 = new Truck(3, 2, "Truck1", 90);

        Track track = new Track(20);
        track.addTransport(car1);
        track.addTransport(car2);
        track.addTransport(truck1);

        Race race = new Race(track);

        race.start();
    }
}
