/**
 * FitnessTrackerTest2
 * Author: Zidan Bakari
 * Description: 
 * This JUnit test class tests the FitnessTracker methods:
 *  - calculateDailyDistanceMeters()
 *  - calculateDistance()
 *  - calculateDailyCalorieDelta()
 * 
 * It includes two types of test scenarios:
 *  1. Successful execution (green bar)
 *  2. Failed execution (red bar)
 * 
 * Assumptions:
 *  - The distance differs between male and female users.
 *  - A small epsilon (0.0000001) is used for floating-point comparison.
 *  - Calorie deficit should return a non-zero value if burned > maintenance.
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FitnessTrackerTest2 {
	
	 /**
     * Tests calculateDailyDistanceMeters() for a male user.
     * Expected: 800.0 meters for given input.
     */	
    @Test
    void testCalculateDistanceMale() {
        FitnessTracker ft = new FitnessTracker
        		("Zidan", "Bakari", "Male", 1000, 1800, 40, 2200);
        assertEquals(800.0, ft.calculateDailyDistanceMeters(), 0.0000001);
    }
    
    /**
     * Tests calculateDailyDistanceMeters() for a female user.
     * Expected: 700.0 meters for given input.
     */
    @Test
    void testCalculateDistanceFemale() {
        FitnessTracker ft = new FitnessTracker("Sara", "Lee", "Female", 1000, 1800, 40, 2200);
        assertTrue(ft.calculateDailyDistanceMeters() == 700.00);//not right
    }
    
    /**
     * Tests calculateDailyCalorieDelta() to ensure a non-zero result
     * when calories burned exceed maintenance calories.
     */
    @Test
    void testCalculateCalorieDeficit() {
        FitnessTracker ft = new FitnessTracker("Zidan", "Bakari", "Male", 8000, 2600, 45, 2200);
        assertNotEquals(0, ft.calculateDailyCalorieDelta());
    }
}
