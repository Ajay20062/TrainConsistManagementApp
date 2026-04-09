package TrainConsistManagementApp;

import java.util.*;

// ================= CUSTOM RUNTIME EXCEPTION =================
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

// ================= ABSTRACT BOGIE =================
abstract class Bogie {
    protected String id;

    public Bogie(String id) {
        this.id = id;
    }

    public abstract void display();
}

// ================= PASSENGER BOGIE =================
class PassengerBogie extends Bogie {
    private final String type; // Sleeper, AC, First Class
    private final int capacity;
    private int bookedSeats;

    public PassengerBogie(String id, String type, int capacity) {
        super(id);
        this.type = type;
        this.capacity = capacity;
        this.bookedSeats = 0;
    }

    public void bookSeat(int seats) {
        if (bookedSeats + seats <= capacity) {
            bookedSeats += seats;
            System.out.println("✅ " + seats + " seats booked in " + type + " bogie.");
        } else {
            System.out.println("❌ Not enough seats available.");
        }
    }

    public void display() {
        System.out.println("Passenger Bogie [" + id + "] Type: " + type +
                " | Capacity: " + capacity +
                " | Booked: " + bookedSeats);
    }
}

// ================= GOODS BOGIE =================
class GoodsBogie extends Bogie {
    private final String shape;   // Rectangular / Cylindrical
    private String cargo;

    public GoodsBogie(String id, String shape) {
        super(id);
        this.shape = shape;
    }

    // UC15 IMPLEMENTATION
    public void assignCargo(String cargoType) {
        try {
            // Safety validation
            if (shape.equalsIgnoreCase("Rectangular") &&
                    cargoType.equalsIgnoreCase("Petroleum")) {

                throw new CargoSafetyException(
                        "Unsafe Cargo! Petroleum cannot be stored in Rectangular bogie."
                );
            }

            // Safe assignment
            this.cargo = cargoType;
            System.out.println("✅ Cargo '" + cargoType + "' assigned to " + shape + " bogie.");

        } catch (CargoSafetyException e) {
            System.out.println("❌ ERROR: " + e.getMessage());

        } finally {
            System.out.println("⚙️ Cargo assignment attempt completed for Bogie ID: " + id + "\n");
        }
    }

    public void display() {
        System.out.println("Goods Bogie [" + id + "] Shape: " + shape +
                " | Cargo: " + (cargo == null ? "None" : cargo));
    }
}

// ================= TRAIN =================
class Train {
    private final List<Bogie> bogies;

    public Train() {
        bogies = new ArrayList<>();
    }

    public void addBogie(Bogie b) {
        bogies.add(b);
        System.out.println("✅ Bogie added successfully.");
    }

    public void displayTrain() {
        System.out.println("\n🚆 Train Composition:");
        for (Bogie b : bogies) {
            b.display();
        }
    }

    public Bogie getBogieById(String id) {
        for (Bogie b : bogies) {
            if (b.id.equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    }
}

// ================= MAIN APPLICATION =================
public class TrainConsistManagementApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Train train = new Train();

        int choice;

        do {
            System.out.println("\n===== 🚆 Train Consist Management =====");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Add Goods Bogie");
            System.out.println("3. Book Seats");
            System.out.println("4. Assign Cargo (UC15)");
            System.out.println("5. Display Train");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Bogie ID: ");
                    String pid = sc.next();
                    System.out.print("Enter Type (Sleeper/AC/First): ");
                    String type = sc.next();
                    System.out.print("Enter Capacity: ");
                    int cap = sc.nextInt();

                    train.addBogie(new PassengerBogie(pid, type, cap));
                    break;

                case 2:
                    System.out.print("Enter Bogie ID: ");
                    String gid = sc.next();
                    System.out.print("Enter Shape (Rectangular/Cylindrical): ");
                    String shape = sc.next();

                    train.addBogie(new GoodsBogie(gid, shape));
                    break;

                case 3:
                    System.out.print("Enter Bogie ID: ");
                    String bid = sc.next();
                    Bogie b = train.getBogieById(bid);

                    if (b instanceof PassengerBogie) {
                        System.out.print("Enter seats to book: ");
                        int seats = sc.nextInt();
                        ((PassengerBogie) b).bookSeat(seats);
                    } else {
                        System.out.println("❌ Not a Passenger Bogie.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Bogie ID: ");
                    String gid2 = sc.next();
                    Bogie gb = train.getBogieById(gid2);

                    if (gb instanceof GoodsBogie) {
                        System.out.print("Enter Cargo Type: ");
                        String cargo = sc.next();
                        ((GoodsBogie) gb).assignCargo(cargo);
                    } else {
                        System.out.println("❌ Not a Goods Bogie.");
                    }
                    break;

                case 5:
                    train.displayTrain();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
