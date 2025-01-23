package movie_seats;

// Movie theater seat reservation system
// By: Brian Jackman
// 2025/01/23


import java.util.Scanner;

public class MovieSeats {
    private final int rows = 5;
    private final int cols = 10;
    private final boolean[][] seats;

    public MovieSeats() {
        seats = new boolean[rows][cols];
    }

    // Reserve a seat
    public boolean reserveSeat(String seat) {
        int[] seatPosition = parseSeat(seat);
        if (seatPosition == null) {
            System.out.println("Invalid seat.");
            return false;
        }
        int row = seatPosition[0];
        int col = seatPosition[1];
        if (seats[row][col]) {
            System.out.println("Seat " + seat + " is already taken. Suggesting an available seat...");
            suggestAvailableSeat();
            return false;
        } else {
            seats[row][col] = true;
            System.out.println("Seat " + seat + " reserved successfully.");
            return true;
        }
    }

    // Cancel a seat reservation
    public boolean cancelSeat(String seat) {
        int[] seatPosition = parseSeat(seat);
        if (seatPosition == null) {
            System.out.println("Invalid seat.");
            return false;
        }
        int row = seatPosition[0];
        int col = seatPosition[1];
        if (!seats[row][col]) {
            System.out.println("Seat " + seat + " is not reserved.");
            return false;
        } else {
            seats[row][col] = false;
            System.out.println("Seat " + seat + " reservation cancelled.");
            return true;
        }
    }

    // Retrieve the seating chart
    public void retrieveSeatingChart() {
        System.out.println("Seating Chart:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print((char) ('A' + i) + "" + (j + 1) + ": " + (seats[i][j] ? "Reserved" : "Available") + "  ");
            }
            System.out.println();
        }
    }

    // Suggest an available seat
    private void suggestAvailableSeat() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!seats[i][j]) {
                    System.out.println("Suggested available seat: " + (char) ('A' + i) + "" + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No available seats.");
    }

    // Parse seat input (e.g., A1, B2)
    private int[] parseSeat(String seat) {
        if (seat.length() < 2 || seat.length() > 3) {
            return null;
        }
        char rowChar = seat.charAt(0);
        int row = rowChar - 'A';
        int col;
        try {
            col = Integer.parseInt(seat.substring(1)) - 1;
        } catch (NumberFormatException e) {
            return null;
        }
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return null;
        }
        return new int[]{row, col};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieSeats movieSeats = new MovieSeats();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Show available seats");
            System.out.println("2. Reserve a seat");
            System.out.println("3. Cancel a seat");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    movieSeats.retrieveSeatingChart();
                    break;
                case 2:
                    System.out.print("Enter seat to reserve (e.g., A1): ");
                    String reserveSeat = scanner.nextLine();
                    movieSeats.reserveSeat(reserveSeat);
                    break;
                case 3:
                    System.out.print("Enter seat to cancel (e.g., A1): ");
                    String cancelSeat = scanner.nextLine();
                    movieSeats.cancelSeat(cancelSeat);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}