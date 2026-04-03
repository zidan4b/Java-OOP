
/**
 * FitnessTracker
 * Author: Zidan Bakari
 * Description: Captures user fitness data and computes key metrics.
 */
public class FitnessTracker {

	private String firstName;
	private String lastName;
	private	String gender;
	private int stepsTaken;
	private int caloriesBurned;
	private int activeMinutes;
	private int maintenanceCalories;
   
	

	/**
	 * Constructs a FitnessTracker object with all fitness attributes initialized.
     * @param firstName user's first name
     * @param lastName user's last name
     * @param gender user's gender (Male/Female)
     * @param stepsTaken number of steps taken daily
     * @param caloriesBurned number of calories burned daily
     * @param activeMinutes number of active minutes daily
     * @param maintenanceCalories user's maintenance calories per day
     */
 
	public FitnessTracker(String firstName,
			              String lastName,
			              String gender,
                          int stepsTaken, 
                          int caloriesBurned,
                           int activeMinutes, 
                           int maintenanceCalories)

	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.stepsTaken = stepsTaken;
		this.caloriesBurned = caloriesBurned;
		this.activeMinutes = activeMinutes;
		this.maintenanceCalories = maintenanceCalories;
	}
   
	 /** @return user's first name */
	public String getFirstName()
	{ return firstName; }
	
	 /** @param firstName sets user's first name */
	public void setFirstName(String firstName)
	{ this.firstName = firstName; }

	
	 /** @return user's last name */
	public String getLastName()
	{ return lastName; }
	
	/** @param lastName sets user's last name */
	public void setLastName(String lastName)
	{ this.lastName = lastName; }

	
	 /** @return user's gender */
	public String getGender()
	{ return gender; }
	

    /** @param gender sets user's gender */
	public void setGender(String gender)
	{ this.gender = gender; }


    /** @return steps taken daily */
	public int getStepsTaken()
	{ return stepsTaken; }
	
    /** @param stepsTaken sets steps taken daily */
	public void setStepsTaken(int stepsTaken)
	{ this.stepsTaken = stepsTaken; }
		
	
	/** @return calories burned daily */
	public int getCaloriesBurned()
	{ return caloriesBurned; }
	
	/** @param caloriesBurned sets calories burned daily */
	public void setCaloriesBurned(int caloriesBurned)
	{ this.caloriesBurned = caloriesBurned; }

	
	   /** @return active minutes daily */
	public int getActiveMinutes()
	{ return activeMinutes; }
	

    /** @param activeMinutes sets active minutes daily */
	public void setActiveMinutes(int activeMinutes)
	{ this.activeMinutes = activeMinutes; }
	

    /** @return maintenance calories daily */
	public int getMaintenanceCalories()
	{ return maintenanceCalories; }
	

    /** @param maintenanceCalories sets maintenance calories daily */
	public void setMaintenanceCalories(int maintenanceCalories)
	{ this.maintenanceCalories = maintenanceCalories; }
	
	
    /**
     * Displays all user fitness data and the fitness level criteria.
     */
    public void displayFitnessData() {
        
    
    	System.out.println("===== Fitness Tracker Report =====");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Gender: " + gender);
        System.out.println("Steps Taken (Daily): " + stepsTaken);
        System.out.println("Calories Burned (Daily): " + caloriesBurned);
        System.out.println("Active Minutes (Daily): " + activeMinutes);
        System.out.println("Maintenance Calories (Daily): " + maintenanceCalories);
        System.out.println("==================================");      
       
        
        System.out.println("Fitness Level Criteria:");
        System.out.println("Active: More than 150 active minutes per week and over 2000 calories burned.");
        System.out.println("Moderately Active: 75-150 active minutes per week or 1000-2000 calories burned.");
        System.out.println("Sedentary: Less than 75 active minutes per week and under 1000 calories burned.");
    }
    
    /**
     * Calculates the daily distance walked based on gender.
     * @return distance in meters
     */
    public double calculateDailyDistanceMeters() {
        if (gender.equalsIgnoreCase("Male")) {
            return stepsTaken * 0.8;
        } else if (gender.equalsIgnoreCase("Female")) {
            return stepsTaken * 0.7;
        } else {
            System.out.println("You need to enter your gender.");
            return 0; 
        }
    }
    
    
    
    /**
     * Calculates the weekly total active minutes.
     * @return weekly active minutes
     */
    public double calculateWeeklyActiveMinutesAverage() {
        return (activeMinutes * 7);
    }
        
    
    
    
    /**
     * Determines the user's fitness level based on their weekly activity and calories burned.
     * @return a string representing the fitness level
     */
    public String determineFitnessLevel() {
    	  if (calculateWeeklyActiveMinutesAverage() > 150 && caloriesBurned > 2000) {
                 return "Active";
            } else if ((calculateWeeklyActiveMinutesAverage() >= 75 && calculateWeeklyActiveMinutesAverage() <= 150) ||
            (caloriesBurned >= 1000 && caloriesBurned <= 2000)) {
                   return "Moderately Active";
               } else {
                  return "Sedentary";
              }
    }
    
    
    
    
    /**
     * Calculates whether the user is in a calorie surplus, deficit, or maintenance level.
     * @return the calorie difference between burned and maintenance
     */
        public int calculateDailyCalorieDelta() {
            if (caloriesBurned > maintenanceCalories) {
                System.out.println("You are in a calorie deficit.");
                return caloriesBurned - maintenanceCalories;
                
            } else if (caloriesBurned < maintenanceCalories) {
                System.out.println("You are in a calorie serplus.");
                return maintenanceCalories - caloriesBurned;
                
            } else {
                System.out.println("You are exactly at maintenance level.");
                return 0;
            }
        

        }
} 


