/**
 * FitnessTrackerTest class
 * Prompts user input, creates a FitnessTracker object, and displays results.
 * Author: Zidan Bakari
 */

import java.util.Scanner;
public class FitnessTrackerTest {
	
	  /**
     * The main method prompts the user for input, creates a FitnessTracker object,
     * and displays fitness calculations.
     * @param args command-line arguments
     */
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

        System.out.println("===== Enter Your Fitness Information =====");
        System.out.print("First Name: ");
        String firstName = in.nextLine();

        System.out.print("Last Name: ");
        String lastName = in.nextLine();

        System.out.print("Gender (Male/Female): ");
        String gender = in.nextLine();

        System.out.print("Steps Taken (Daily): ");
        int stepsTaken = in.nextInt();

        System.out.print("Calories Burned (Daily): ");
        int caloriesBurned = in.nextInt();

        System.out.print("Active Minutes (Daily): ");
        int activeMinutes = in.nextInt();

        System.out.print("Maintenance Calories (Daily): ");
        int maintenanceCalories = in.nextInt();

     // FitnessTracker object
        FitnessTracker user = new FitnessTracker(
                firstName, lastName, gender,
                stepsTaken, caloriesBurned, activeMinutes, maintenanceCalories
        );

        System.out.println(" ");
        
        user.displayFitnessData();
        
        System.out.println(" ");
        
        System.out.println("----- Calculated Results -----");
        System.out.println("Distance Walked (Daily): " + user.calculateDailyDistanceMeters());
        System.out.println("Weekly Active Minutes (Total): " + user.calculateWeeklyActiveMinutesAverage());
        System.out.println("Fitness Level: " + user.determineFitnessLevel());
        System.out.println("Daily Calorie Delta: " + user.calculateDailyCalorieDelta());

        in.close();
	    }
	}