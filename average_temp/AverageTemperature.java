// Average temperature project
// By: Brian Jackman
// 2025/01/20


import java.io.Console;

public class AverageTemperature {
    int arr[] = null;

    public AverageTemperature(int size) {
        arr = new int[size];
    }

    // Insert temperature
    public void insertTemperature(int location, int temperature) {
        arr[location] = temperature;
    }

    // Calculate average temperature
    public double calculateAverageTemperature() {
        int sum = 0;
        for (int temp : arr) {
            sum += temp;
        }
        return (double) sum / arr.length;
    }

    // Count days above average temperature
    public int countDaysAboveAverage(double averageTemperature) {
        int count = 0;
        for (int temp : arr) {
            if (temp > averageTemperature) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.out.println("No console available");
            return;
        }

        // Prompt for the number of days
        int numberOfDays = Integer.parseInt(console.readLine("Please enter the number of days: "));

        AverageTemperature averageTemperature = new AverageTemperature(numberOfDays);

        // Prompt for temperatures
        for (int i = 0; i < numberOfDays; i++) {
            int temperature = Integer.parseInt(console.readLine("Enter temperature for day " + (i + 1) + ": "));
            averageTemperature.insertTemperature(i, temperature);
        }

        double averageTemp = averageTemperature.calculateAverageTemperature();
        System.out.println("Average Temperature: " + averageTemp);

        int daysAboveAverage = averageTemperature.countDaysAboveAverage(averageTemp);
        System.out.println("Number of days above average temperature: " + daysAboveAverage);
    }
}