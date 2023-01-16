package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Truck extends Transport {
    private int cargoWeight;

    public Truck(int tirePunctureChance, int speed, String name, int cargoWeight) {
            super(tirePunctureChance, speed, name);
            this.cargoWeight = cargoWeight;
    }
}
