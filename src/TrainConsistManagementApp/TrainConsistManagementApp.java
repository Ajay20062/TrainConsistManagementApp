package TrainConsistManagementApp;

import java.util.ArrayList;
import java.util.List;

// GoodsBogie class
class GoodsBogie {
    String type;   // Cylindrical, Rectangular, Open, etc.
    String cargo;  // Petroleum, Coal, Grain, etc.

    public GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return type + " → " + cargo;
    }
}

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Create goods bogies list
        List<GoodsBogie> goodsBogies = new ArrayList<>();

        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Rectangular", "Coal"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        // Try invalid case:
        // goodsBogies.add(new GoodsBogie("Cylindrical", "Coal"));

        // Safety validation using Stream
        boolean isSafe = goodsBogies.stream()
                .allMatch(b ->
                        !b.type.equalsIgnoreCase("Cylindrical") ||
                                b.cargo.equalsIgnoreCase("Petroleum")
                );

        // Display bogies
        System.out.println("\nGoods Bogies:");
        for (GoodsBogie b : goodsBogies) {
            System.out.println(b);
        }

        // Display safety result
        System.out.println("\nSafety Compliance Status: " + (isSafe ? "SAFE" : "UNSAFE"));

    }
}
