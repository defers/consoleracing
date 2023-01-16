package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void shouldReturnObjectWhenPassedAllArgConstructor() {
        Car car = new Car(10, 5, "Car1", 5);
        assertNotNull(car);
    }

    @Test
    public void shouldReturnPassengersNumber() {
        Car car = new Car(10, 5, "Car1", 5);
        int expected = 5;
        assertEquals(expected, car.getPassengersNumber());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenPassengerNumberGreaterThen5() {
        Car car = new Car(10, 5, "Car1", 6);
    }

    @Test
    public void shouldReturnCarName() {
        Car car = new Car(10, 5, "Car1", 5);
        String expected = "Car1";
        assertEquals(expected, car.getName());
    }

    @Test
    public void shouldReturnCarSpeed() {
        Car car = new Car(10, 5, "Car1", 5);
        int expected = 5;
        assertEquals(expected, car.getSpeed());
    }

    @Test
    public void shouldReturnCarTirePunctureChance() {
        Car car = new Car(10, 5, "Car1", 5);
        int expected = 10;
        assertEquals(expected, car.getTirePunctureChance());
    }

}