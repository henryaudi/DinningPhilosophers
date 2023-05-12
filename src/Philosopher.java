import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {
    // Data Fields
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private final Lock mainLock;

    // Constructor
    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.mainLock = new ReentrantLock();
    }

    // Getter and setter
    /** Get the ID of the Philosopher object */
    public int getId() { return this.id; }

    /** Get the left fork of the Philosopher object */
    public Lock getLeftFork() { return this.leftFork; }

    /** Get the rigjt fork of the Philosopher object */
    public Lock getRightFork() { return this.rightFork; }

    // Methods
    /** The philosopher is thinking */
    public void think() {
        System.out.println("Philosopher " + this.getId() + " is thinking.");
        try {
            Thread.sleep((int) (Math.random() * 5_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** The philosopher picks up the forks */
    public void takeForks() {
        mainLock.lock();
        try {
            leftFork.lock();
            System.out.println("Philosopher " + this.getId() + " picks up the left fork.");
            rightFork.lock();
            System.out.println("Philosopher " + this.getId() + " picks up the right fork.");
        } finally {
            mainLock.unlock();
        }
    }

    /** The philosopher is eating now */
    public void eat() {
        System.out.println("Philosopher " + this.getId() + " is eating.");
        try {
            Thread.sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** The philosopher returned the forks */
    private void returnForks() {
        mainLock.lock();
        try {
            leftFork.unlock();
            System.out.println("Philosopher " + this.getId() + " returned the left fork.");
            rightFork.unlock();
            System.out.println("Philosopher " + this.getId() + " returned the right fork.");
        } finally {
            mainLock.unlock();
        }
    }

    /** Execute the following methods in a thread */
    @Override
    public void run() {
        think();
        takeForks();
        eat();
        returnForks();
    }
}
