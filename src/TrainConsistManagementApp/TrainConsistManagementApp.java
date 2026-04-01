package TrainConsistManagementApp;

import java.util.ArrayList;
import java.util.List;

// Reusing Bogie class
class Bogie {
    String name;
    int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}

public class TrainConsistManagementApp {

     public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Create large dataset of bogies
        List<Bogie> bogies = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            bogies.add(new Bogie("Sleeper", (i % 100) + 1));
        }

        // ---------------- LOOP BASED FILTERING ----------------
        long startLoop = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        // ---------------- STREAM BASED FILTERING ----------------
        long startStream = System.nanoTime();

        List<Bogie> streamResult = bogies.stream().filter(b -> b.capacity > 60).toList();

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        // ---------------- RESULTS ----------------
        System.out.println("\nLoop Result Size: " + loopResult.size());
        System.out.println("Stream Result Size: " + streamResult.size());

        System.out.println("\nLoop Execution Time (ns): " + loopTime);
        System.out.println("Stream Execution Time (ns): " + streamTime);


    }
}
