package domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class Race {
    private final Track track;
    private boolean raceInProgress;
    private List<TransportStatus> transports;
    private Random rnd = new Random();
    private ReentrantLock lock = new ReentrantLock();

    public void start() throws InterruptedException {
        List<Transport> listTransport = track.getTransports();
        setTransportsOnRace(listTransport);
        raceInProgress = true;

        reportStartRace();
        ExecutorService executor = Executors.newFixedThreadPool(listTransport.size());
        startRaceLogic(executor);
        reportRating();
    }

    private void startRaceLogic(ExecutorService executor) throws InterruptedException {
        Collection<Callable<Boolean>> tasks = new ArrayList<>();

        for (TransportStatus ts: transports) {
            tasks.add(() -> {
                raceLogic(ts);
                return true;
            });
        }
        executor.invokeAll(tasks);
    }

    private void raceLogic(TransportStatus ts) {
        while (!ts.isFinished()) {
            doPause(ts.getTransport());
            if (!ts.getTransport().isTirePunctured()) {
                setNewPosition(ts);
                randomEncounter(ts);
                checkFinish(ts);
                System.out.println(ts.getTransport().getName() + " distance on track: " + ts.getPositionsOnTrack());
            }
            lock.lock();
            try {
                raceInProgress = checkEndRace();
            }
            finally {
                lock.unlock();
            }
        }
    }

    private void randomEncounter(TransportStatus ts) {
        int tirePunctureChance = ts.getTransport().getTirePunctureChance();
        if (tirePunctureChance > 0) {
            int rndResult = rnd.nextInt(100) + 1;
            if (rndResult <= tirePunctureChance) {
                ts.getTransport().setTirePunctured();
                System.out.println("Broken: " + ts.getTransport());
            }
        }
    }

    private void reportStartRace() {
        System.out.println("Race participants:");
        transports.stream()
                .sorted(Comparator.comparingInt(TransportStatus::getFinishedPosition))
                .forEach(e -> {
                    String result = String.format("Transport %s with stats: %s", e.getTransport().getName(), e.getTransport());
                    System.out.println(result);
                });
    }

    private void reportRating() {
        transports.stream()
                .sorted(Comparator.comparingInt(TransportStatus::getFinishedPosition))
                .forEach(e -> {
                    String result;
                    if (e.getFinishedPosition() == -1) {
                        result = String.format("%s has broken", e.getTransport().getName());
                    } else {
                        result = String.format("%s finished on %s place",
                                e.getTransport().getName(), e.getFinishedPosition());
                    }
                    System.out.println(result);
                });
    }

    private void doPause(Transport transport) {
        long time = 3000/transport.getCurrentSpeed();
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean checkEndRace() {
        return transports.stream().anyMatch(e -> !e.isFinished() && !e.getTransport().isTirePunctured());
    }

    private void setTransportsOnRace(List<Transport> transports) {
        this.transports = transports.stream()
                .map(e -> {
                    e.start();
                    return new TransportStatus(e);
                })
                .collect(Collectors.toList());
    }

    private void checkFinish(TransportStatus ts) {
        if (ts.getPositionsOnTrack() >= track.getLength() && !ts.isFinished()) {
            ts.getTransport().stop();
            ts.setFinished(true);
            setFinishedPosition(ts);
            System.out.println(String.format("%s has finished!", ts.getTransport().getName()));
        }
        if (ts.getTransport().isTirePunctured() && !ts.isFinished()) {
            ts.setFinishedPosition(-1);
            ts.setFinished(true);
        }
    }

    private void setFinishedPosition(TransportStatus ts) {
        lock.lock();
        try{
            ts.setFinishedPosition(TransportStatus.getAndIncrementPositionCounter());
        }
        finally {
            lock.unlock();
        }
    }

    private int setNewPosition(TransportStatus ts) {
        int newPosition = Math.min(track.getLength(),
                ts.getPositionsOnTrack() + ts.getTransport().getCurrentSpeed());
        ts.setPositionsOnTrack(newPosition);
        return newPosition;
    }

}
