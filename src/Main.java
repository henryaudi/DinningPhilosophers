public class Main {
    private static final int NUM_PHILOSOPHERS = 5;    // Initialize the number of philosophers.

    public static void main(String[] args) {
        // Instantiate the DinningPhilosopher class object and load the program to threads.
        new DinningPhilosopher(NUM_PHILOSOPHERS);
    }
}
