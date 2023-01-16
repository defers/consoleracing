package domain;

import lombok.Data;

@Data
public class TransportStatus {
    private final Transport transport;
    private int positionsOnTrack;
    private boolean finished;
    private int finishedPosition;
    private static int positionCounter = 0;

    public TransportStatus(Transport transport) {
        this.transport = transport;
    }

    public static void incrementPositionCounter() {
        positionCounter++;
    }

    public static int getPositionCounter() {
        return positionCounter;
    }

    public static int getAndIncrementPositionCounter() {
        incrementPositionCounter();
        return getPositionCounter();
    }

}
