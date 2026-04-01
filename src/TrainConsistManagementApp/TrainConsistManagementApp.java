package TrainConsistManagementApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Reusing Bogie class
class Bogie {
    String name;
    int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return name + "(" + capacity + ")";
    }
}

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Create list of bogies
        List<Bogie> bogies = new ArrayList<>();

        // Add bogies (including duplicates for grouping)
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 54));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 72)); // duplicate type
        bogies.add(new Bogie("AC Chair", 54)); // duplicate type

        // Group bogies by type (name)
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // Display grouped result
        System.out.println("\nGrouped Bogies by Type:");

        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // Verify original list is unchanged
        System.out.println("\nOriginal Bogie List:");
        System.out.println(bogies);
    }
}
