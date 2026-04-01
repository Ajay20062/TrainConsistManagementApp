package TrainConsistManagementApp;


import java.util.*;
import java.util.stream.Collectors;

// ---------- UC14: Custom Exception ----------
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// ---------- UC7+: Bogie Classes ----------
class PassengerBogie {
    String type;
    int capacity;

    public PassengerBogie(String type, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return type + "(" + capacity + ")";
    }
}

class GoodsBogie {
    String type;
    String cargo;

    public GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return type + " → " + cargo;
    }
}

// ---------- MAIN APP ----------
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // ---------- UC1: Initialize ----------
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        // ---------- UC2: ArrayList ----------
        List<String> passengerList = new ArrayList<>();
        passengerList.add("Sleeper");
        passengerList.add("AC Chair");
        passengerList.add("First Class");
        passengerList.remove("AC Chair");
        System.out.println("\nPassenger List: " + passengerList);
        System.out.println("Contains Sleeper? " + passengerList.contains("Sleeper"));

        // ---------- UC3: HashSet ----------
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG101"); // duplicate ignored
        bogieIds.add("BG102");
        System.out.println("\nUnique Bogie IDs: " + bogieIds);

        // ---------- UC4: LinkedList ----------
        LinkedList<String> linkedTrain = new LinkedList<>();
        linkedTrain.add("Engine");
        linkedTrain.add("Sleeper");
        linkedTrain.add("AC");
        linkedTrain.add("Cargo");
        linkedTrain.add("Guard");
        linkedTrain.add(2, "Pantry");
        linkedTrain.removeFirst();
        linkedTrain.removeLast();
        System.out.println("\nOrdered Train: " + linkedTrain);

        // ---------- UC5: LinkedHashSet ----------
        Set<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper"); // duplicate ignored
        System.out.println("\nFormation: " + formation);

        // ---------- UC6: HashMap ----------
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 54);
        capacityMap.put("First Class", 24);

        System.out.println("\nBogie Capacities:");
        capacityMap.forEach((k, v) -> System.out.println(k + " → " + v));

        try {
            // ---------- UC7: Objects ----------
            List<PassengerBogie> bogies = new ArrayList<>();
            bogies.add(new PassengerBogie("Sleeper", 72));
            bogies.add(new PassengerBogie("AC Chair", 54));
            bogies.add(new PassengerBogie("First Class", 24));

            // ---------- UC7: Sorting ----------
            bogies.sort(Comparator.comparingInt(b -> b.capacity));
            System.out.println("\nSorted Bogies: " + bogies);

            // ---------- UC8: Filtering ----------
            List<PassengerBogie> filtered = bogies.stream()
                    .filter(b -> b.capacity > 50)
                    .toList();
            System.out.println("\nFiltered (>50): " + filtered);

            // ---------- UC9: Grouping ----------
            Map<String, List<PassengerBogie>> grouped =
                    bogies.stream().collect(Collectors.groupingBy(b -> b.type));
            System.out.println("\nGrouped Bogies: " + grouped);

            // ---------- UC10: Reduce ----------
            int total = bogies.stream()
                    .map(b -> b.capacity)
                    .reduce(0, Integer::sum);
            System.out.println("\nTotal Capacity: " + total);

            // ---------- UC11: Regex ----------
            String trainId = "TRN-1234";
            String cargoCode = "PET-AB";

            boolean validTrain = trainId.matches("TRN-\\d{4}");
            boolean validCargo = cargoCode.matches("PET-[A-Z]{2}");

            System.out.println("\nTrain ID Valid? " + validTrain);
            System.out.println("Cargo Code Valid? " + validCargo);

            // ---------- UC12: Safety ----------
            List<GoodsBogie> goods = new ArrayList<>();
            goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
            goods.add(new GoodsBogie("Rectangular", "Coal"));

            boolean safe = goods.stream().allMatch(g ->
                    !g.type.equalsIgnoreCase("Cylindrical") ||
                            g.cargo.equalsIgnoreCase("Petroleum")
            );

            System.out.println("\nSafety Status: " + (safe ? "SAFE" : "UNSAFE"));

            // ---------- UC13: Performance ----------
            long startLoop = System.nanoTime();
            List<PassengerBogie> loopResult = new ArrayList<>();
            for (PassengerBogie b : bogies) {
                if (b.capacity > 50) loopResult.add(b);
            }
            long loopTime = System.nanoTime() - startLoop;

            long startStream = System.nanoTime();
            List<PassengerBogie> streamResult = bogies.stream()
                    .filter(b -> b.capacity > 50)
                    .toList();
            long streamTime = System.nanoTime() - startStream;

            System.out.println("\nLoop Time: " + loopTime);
            System.out.println("Stream Time: " + streamTime);

            // ---------- UC14: Exception ----------
            PassengerBogie invalid = new PassengerBogie("Invalid", -10);

        } catch (InvalidCapacityException e) {
            System.out.println("\nException Caught: " + e.getMessage());
        }

        System.out.println("\n=== Program Completed ===");
    }
}
