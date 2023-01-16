package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Car extends Transport {

    private int passengersNumber;

    public Car(int tirePunctureChance, int speed, String name, int passengersNumber) {
        super(tirePunctureChance, speed, name);
        if (passengersNumber > 5) {
            throw new RuntimeException("Number of passengers must be <= 5");
        }
        this.passengersNumber = passengersNumber;
    }

}
