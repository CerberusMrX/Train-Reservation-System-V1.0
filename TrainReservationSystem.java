import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TrainReservationSystem {

    private static class Train {
        String route;
        int price;
        int availableSeats;
        String description;

        public Train(String route, int price, int availableSeats, String description) {
            this.route = route;
            this.price = price;
            this.availableSeats = availableSeats;
            this.description = description;
        }

        @Override
        public String toString() {
            return route + " - " + price + " LKR, Seats Available: " + availableSeats + ", Description: " + description;
        }
    }

    private static class ScenicRoute {
        String route;
        String description;

        public ScenicRoute(String route, String description) {
            this.route = route;
            this.description = description;
        }

        @Override
        public String toString() {
            return route + " - " + description;
        }
    }

    private static class Booking {
        String trainRoute;
        int numberOfTickets;
        int totalCost;

        public Booking(String trainRoute, int numberOfTickets, int totalCost) {
            this.trainRoute = trainRoute;
            this.numberOfTickets = numberOfTickets;
            this.totalCost = totalCost;
        }

        @Override
        public String toString() {
            return "Train Route: " + trainRoute + ", Tickets: " + numberOfTickets + ", Total Cost: " + totalCost + " LKR";
        }
    }

    private List<Train> trains = new ArrayList<>();
    private List<ScenicRoute> scenicRoutes = new ArrayList<>();
    private List<Booking> bookingHistory = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public TrainReservationSystem() {
        // Initialize with some data
        trains.add(new Train("Colombo to Kandy", 1500, 50, "A scenic route through lush green hills."));
        trains.add(new Train("Colombo to Galle", 1200, 40, "A coastal route with beautiful ocean views."));
        trains.add(new Train("Colombo to Jaffna", 1800, 30, "A route to the cultural heart of Sri Lanka."));
        trains.add(new Train("Colombo to Anuradhapura", 1600, 20, "A route rich in historical significance."));
        trains.add(new Train("Colombo to Batticaloa", 1700, 25, "A route featuring stunning lagoons."));
        trains.add(new Train("Colombo to Nuwara Eliya", 2000, 15, "A route through tea plantations and cool climates."));
        trains.add(new Train("Colombo to Matara", 1300, 45, "A route with charming coastal views."));
        trains.add(new Train("Colombo to Trincomalee", 1400, 35, "A route with historical forts and beaches."));
        trains.add(new Train("Colombo to Ratnapura", 1100, 50, "A route through gem-rich areas."));
        trains.add(new Train("Colombo to Polonnaruwa", 1400, 30, "A route with ancient ruins and temples."));
        trains.add(new Train("Colombo to Dambulla", 1550, 35, "A route famous for its cave temples."));
        trains.add(new Train("Colombo to Badulla", 1700, 25, "A route through the heart of Sri Lanka's tea country."));
        trains.add(new Train("Colombo to Matale", 1400, 40, "A route with cultural and historical significance."));
        trains.add(new Train("Colombo to Chilaw", 1250, 50, "A route with beautiful beaches."));
        trains.add(new Train("Colombo to Ella", 1800, 20, "A route known for its stunning views of the Ella Gap."));
        trains.add(new Train("Colombo to Arugam Bay", 1900, 15, "A route leading to a popular surfing destination."));
        trains.add(new Train("Colombo to Puttalam", 1350, 30, "A route with serene coastal landscapes."));
        trains.add(new Train("Colombo to Vavuniya", 1650, 25, "A route with significant historical landmarks."));
        trains.add(new Train("Colombo to Kegalle", 1200, 45, "A route known for its scenic beauty and natural reserves."));

        scenicRoutes.add(new ScenicRoute("Ella to Demodara", "A breathtaking route with the famous Nine Arches Bridge."));
        scenicRoutes.add(new ScenicRoute("Kandy to Nuwara Eliya", "A picturesque route with tea estates and waterfalls."));
        scenicRoutes.add(new ScenicRoute("Galle to Matara", "A coastal route with stunning ocean views and historic forts."));
        scenicRoutes.add(new ScenicRoute("Colombo to Hikkaduwa", "A route with beautiful beaches and coral reefs."));
        scenicRoutes.add(new ScenicRoute("Colombo to Anuradhapura", "A route with ancient ruins and historical significance."));
        scenicRoutes.add(new ScenicRoute("Kandy to Badulla", "A route passing through lush green hills and tea plantations."));
        scenicRoutes.add(new ScenicRoute("Nuwara Eliya to Ella", "A scenic route with stunning views of the hill country."));
        scenicRoutes.add(new ScenicRoute("Jaffna to Vavuniya", "A route featuring unique cultural and historical sites."));
        scenicRoutes.add(new ScenicRoute("Batticaloa to Polonnaruwa", "A route with serene landscapes and ancient temples."));
        scenicRoutes.add(new ScenicRoute("Anuradhapura to Dambulla", "A route with historical temples and ancient ruins."));
    }

    private void printBanner() {
        System.out.println("****************************************");
        System.out.println("*    Welcome to the Train Reservation   *");
        System.out.println("*          System - Sri Lanka           *");
        System.out.println("****************************************");
    }

    private void showMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Book Ticket");
        System.out.println("2. View Trains");
        System.out.println("3. Update Train Details");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Search Train");
        System.out.println("6. List All Trains");
        System.out.println("7. Add Train");
        System.out.println("8. Delete Train");
        System.out.println("9. Export Data to CSV");
        System.out.println("10. View Scenic Routes");
        System.out.println("11. Calculate Total Cost");
        System.out.println("12. View Booking History");
        System.out.println("13. Help");
        System.out.println("14. Exit");
    }

    private void bookTicket() {
        System.out.println("\nAvailable Trains:");
        for (int i = 0; i < trains.size(); i++) {
            System.out.println((i + 1) + ". " + trains.get(i));
        }
        System.out.print("Select train number to book: ");
        int trainIndex = scanner.nextInt() - 1;
        System.out.print("Enter number of tickets: ");
        int numTickets = scanner.nextInt();
        Train selectedTrain = trains.get(trainIndex);

        if (numTickets <= selectedTrain.availableSeats) {
            selectedTrain.availableSeats -= numTickets;
            int totalCost = numTickets * selectedTrain.price;
            Booking booking = new Booking(selectedTrain.route, numTickets, totalCost);
            bookingHistory.add(booking);
            System.out.println("Booking successful!");
            System.out.println("Total Cost: " + totalCost + " LKR");
        } else {
            System.out.println("Not enough seats available.");
        }
    }

    private void viewTrains() {
        System.out.println("\nTrain Details:");
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    private void updateTrainDetails() {
        System.out.println("\nAvailable Trains:");
        for (int i = 0; i < trains.size(); i++) {
            System.out.println((i + 1) + ". " + trains.get(i));
        }
        System.out.print("Select train number to update: ");
        int trainIndex = scanner.nextInt() - 1;
        Train selectedTrain = trains.get(trainIndex);

        System.out.print("New Price (Current: " + selectedTrain.price + "): ");
        selectedTrain.price = scanner.nextInt();
        System.out.print("New Available Seats (Current: " + selectedTrain.availableSeats + "): ");
        selectedTrain.availableSeats = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("New Description (Current: " + selectedTrain.description + "): ");
        selectedTrain.description = scanner.nextLine();

        System.out.println("Train details updated successfully.");
    }

    private void cancelBooking() {
        System.out.println("\nBooking History:");
        for (int i = 0; i < bookingHistory.size(); i++) {
            System.out.println((i + 1) + ". " + bookingHistory.get(i));
        }
        System.out.print("Select booking number to cancel: ");
        int bookingIndex = scanner.nextInt() - 1;
        if (bookingIndex >= 0 && bookingIndex < bookingHistory.size()) {
            Booking canceledBooking = bookingHistory.remove(bookingIndex);
            for (Train train : trains) {
                if (train.route.equals(canceledBooking.trainRoute)) {
                    train.availableSeats += canceledBooking.numberOfTickets;
                    break;
                }
            }
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Invalid booking number.");
        }
    }

    private void searchTrain() {
        System.out.print("Enter route to search: ");
        String route = scanner.next();
        for (Train train : trains) {
            if (train.route.contains(route)) {
                System.out.println(train);
            }
        }
    }

    private void listAllTrains() {
        System.out.println("\nList of All Trains:");
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    private void addTrain() {
        System.out.print("Enter train route: ");
        String route = scanner.next();
        System.out.print("Enter train price: ");
        int price = scanner.nextInt();
        System.out.print("Enter available seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter train description: ");
        String description = scanner.nextLine();
        Train newTrain = new Train(route, price, seats, description);
        trains.add(newTrain);
        System.out.println("Train added successfully.");
    }

    private void deleteTrain() {
        System.out.println("\nAvailable Trains:");
        for (int i = 0; i < trains.size(); i++) {
            System.out.println((i + 1) + ". " + trains.get(i));
        }
        System.out.print("Select train number to delete: ");
        int trainIndex = scanner.nextInt() - 1;
        if (trainIndex >= 0 && trainIndex < trains.size()) {
            trains.remove(trainIndex);
            System.out.println("Train deleted successfully.");
        } else {
            System.out.println("Invalid train number.");
        }
    }

    private void exportDataToCSV() {
        try (FileWriter writer = new FileWriter("trains.csv")) {
            writer.write("Route,Price,Available Seats,Description\n");
            for (Train train : trains) {
                writer.write(String.format("%s,%d,%d,%s\n", train.route, train.price, train.availableSeats, train.description));
            }
            System.out.println("Data exported to trains.csv successfully.");
        } catch (IOException e) {
            System.out.println("Error exporting data: " + e.getMessage());
        }
    }

    private void viewScenicRoutes() {
        System.out.println("\nScenic Routes:");
        for (ScenicRoute route : scenicRoutes) {
            System.out.println(route);
        }
    }

    private void calculateTotalCost() {
        System.out.println("\nAvailable Trains:");
        for (int i = 0; i < trains.size(); i++) {
            System.out.println((i + 1) + ". " + trains.get(i));
        }
        System.out.print("Select train number: ");
        int trainIndex = scanner.nextInt() - 1;
        Train selectedTrain = trains.get(trainIndex);

        System.out.print("Enter number of tickets: ");
        int numTickets = scanner.nextInt();
        if (numTickets <= selectedTrain.availableSeats) {
            int totalCost = numTickets * selectedTrain.price;
            System.out.println("Total cost: " + totalCost + " LKR");
        } else {
            System.out.println("Not enough seats available.");
        }
    }

    private void viewBookingHistory() {
        System.out.println("\nBooking History:");
        for (Booking booking : bookingHistory) {
            System.out.println(booking);
        }
    }

    private void printHelp() {
        System.out.println("Help Menu:");
        System.out.println("1. Book Ticket: Allows you to book tickets for a train.");
        System.out.println("2. View Trains: Shows details of all available trains.");
        System.out.println("3. Update Train Details: Update details of a specific train.");
        System.out.println("4. Cancel Booking: Cancel an existing booking.");
        System.out.println("5. Search Train: Search for trains by route.");
        System.out.println("6. List All Trains: Lists all trains with details.");
        System.out.println("7. Add Train: Add a new train to the system.");
        System.out.println("8. Delete Train: Remove an existing train from the system.");
        System.out.println("9. Export Data to CSV: Export train data to a CSV file.");
        System.out.println("10. View Scenic Routes: View details of scenic routes.");
        System.out.println("11. Calculate Total Cost: Calculate the total cost for a ticket booking.");
        System.out.println("12. View Booking History: View a history of all bookings.");
        System.out.println("13. Help: Display this help menu.");
        System.out.println("14. Exit: Exit the application.");
    }

    public void run() {
        printBanner();
        while (true) {
            showMenu();
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    viewTrains();
                    break;
                case 3:
                    updateTrainDetails();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    searchTrain();
                    break;
                case 6:
                    listAllTrains();
                    break;
                case 7:
                    addTrain();
                    break;
                case 8:
                    deleteTrain();
                    break;
                case 9:
                    exportDataToCSV();
                    break;
                case 10:
                    viewScenicRoutes();
                    break;
                case 11:
                    calculateTotalCost();
                    break;
                case 12:
                    viewBookingHistory();
                    break;
                case 13:
                    printHelp();
                    break;
                case 14:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        TrainReservationSystem system = new TrainReservationSystem();
        system.run();
    }
}

