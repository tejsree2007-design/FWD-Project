import java.util.*;

// ---------------- Booking Class ----------------
class Booking {
    String name;
    String email;
    String roomType;
    String checkin;
    String checkout;

    Booking(String name, String email, String roomType, String checkin, String checkout) {
        this.name = name;
        this.email = email;
        this.roomType = roomType;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    void display() {
        System.out.println("--------------------------------------");
        System.out.println("Name      : " + name);
        System.out.println("Email     : " + email);
        System.out.println("Room Type : " + roomType);
        System.out.println("Check-in  : " + checkin);
        System.out.println("Check-out : " + checkout);
    }
}

// ---------------- Main Hotel System ----------------
public class SunriseHotel {

    static Scanner sc = new Scanner(System.in);

    // Queue for booking order
    static Queue<Booking> bookingQueue = new LinkedList<>();

    // Stack for cancel booking
    static Stack<Booking> bookingStack = new Stack<>();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== Sunrise Hotel =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Last Booking");
            System.out.println("5. Search Booking by Email");
            System.out.println("6. Sort Bookings by Name");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    showRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    viewBookings();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    searchBooking();
                    break;

                case 6:
                    sortBookings();
                    break;

                case 7:
                    System.out.println("Thank you for visiting Sunrise Hotel");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- Show Rooms ----------------
    static void showRooms() {

        System.out.println("\n--- Available Rooms ---");
        System.out.println("1. Single Room  - 1500 per night");
        System.out.println("2. Double Room  - 2500 per night");
        System.out.println("3. Suite        - 4000 per night");
    }

    // ---------------- Book Room ----------------
    static void bookRoom() {

        System.out.println("\n--- Room Booking ---");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        showRooms();

        System.out.print("Select Room Type (1-3): ");
        int roomChoice = sc.nextInt();
        sc.nextLine();

        String roomType = "";

        switch (roomChoice) {

            case 1:
                roomType = "Single Room";
                break;

            case 2:
                roomType = "Double Room";
                break;

            case 3:
                roomType = "Suite";
                break;

            default:
                System.out.println("Invalid selection");
                return;
        }

        System.out.print("Enter Check-in Date (yyyy-mm-dd): ");
        String checkin = sc.nextLine();

        System.out.print("Enter Check-out Date (yyyy-mm-dd): ");
        String checkout = sc.nextLine();

        if (checkout.compareTo(checkin) <= 0) {

            System.out.println("Checkout must be after checkin!");
            return;
        }

        Booking booking = new Booking(name, email, roomType, checkin, checkout);

        bookingQueue.add(booking); // queue
        bookingStack.push(booking); // stack

        System.out.println("Booking Successful!");
    }

    // ---------------- View Bookings (Queue FIFO) ----------------
    static void viewBookings() {

        if (bookingQueue.isEmpty()) {

            System.out.println("\nNo bookings found.");
            return;
        }

        System.out.println("\n===== Booking Details =====");

        for (Booking b : bookingQueue) {

            b.display();
        }
    }

    // ---------------- Cancel Booking (Stack LIFO) ----------------
    static void cancelBooking() {

        if (bookingStack.isEmpty()) {

            System.out.println("No booking to cancel.");
            return;
        }

        Booking removed = bookingStack.pop();
        bookingQueue.remove(removed);

        System.out.println("Last Booking Cancelled:");
        removed.display();
    }

    // ---------------- Searching (Linear Search) ----------------
    static void searchBooking() {

        System.out.print("Enter Email to Search: ");
        String email = sc.nextLine();

        boolean found = false;

        for (Booking b : bookingQueue) {

            if (b.email.equalsIgnoreCase(email)) {

                System.out.println("\nBooking Found:");
                b.display();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Booking not found.");
        }
    }

    // ---------------- Sorting (By Name) ----------------
    static void sortBookings() {

        if (bookingQueue.isEmpty()) {

            System.out.println("No bookings available.");
            return;
        }

        List<Booking> list = new ArrayList<>(bookingQueue);

        Collections.sort(list, new Comparator<Booking>() {

            public int compare(Booking a, Booking b) {
                return a.name.compareToIgnoreCase(b.name);
            }
        });

        System.out.println("\n===== Sorted Bookings (By Name) =====");

        for (Booking b : list) {
            b.display();
        }
    }
}