import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DinningPhilosopher {
    // Data fields
    private final Philosopher[] philosophers;
    private final Lock[] forks;

    // Constructor
    public DinningPhilosopher(int numOfPhilosophers) {
        this.philosophers = new Philosopher[numOfPhilosophers];
        this.forks = new ReentrantLock[numOfPhilosophers];

        for (int i = 0; i < numOfPhilosophers; i++) { forks[i] = new ReentrantLock(); }

        for (int i = 0; i < numOfPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numOfPhilosophers]);
            new Thread(philosophers[i]).start();
        }
    }
}
