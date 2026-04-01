package TrainConsistManagementApp;

import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        //UC1  Welcome message
        System.out.println("=== Train Consist Management App ===");

        // Initialize empty train consist using ArrayList
        List<String> trainConsist = new ArrayList<>();

        // Display initial bogie count
        System.out.println("Train consist initialized.");
        System.out.println("Initial number of bogies: " + trainConsist.size());

    }
}

