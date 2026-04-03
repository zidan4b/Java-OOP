/**
 * Name: Zidan Bakari
 */
package Manager;


import java.util.Scanner;


/**
 * This class is the driver class for the program.
 * It displays menus, takes user input, and communicates with
 * the RecipeManager class to perform all operations.
 *
 * This class must NOT access Recipe objects directly.
 */
public class Driver {

	
	   /**
     * Main entry point of the program.
     * Sets up the RecipeManager, displays menus, and processes user input.
     * @param args unused
     */
    public static void main(String[] args) {

        RecipeManager manager = new RecipeManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Zidan's Recipe Manager!");

        printMenu();

        while (true) {
            System.out.print("Please enter your choice: ");
           
            String choice = sc.nextLine();

     
            if (!choice.matches("\\d+")) {
                System.out.println("Please only type digits.");
                System.out.println("Valid input are digits from 0 to 4.");
                continue;
            }
            
               //Change text to number
            int option = Integer.parseInt(choice);

            switch (option) {

                case 0:
                    printMenu();
                    break;

                case 1:
                    showRecipes(manager);
                    break;

                case 2:
                    createOrder(manager, sc);
                    break;

                case 3:
                    printShoppingList(manager, sc);
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid input. Enter 0–4.");
            }
        }
    }

    
    /**
     * Prints the main program menu.
     */
    private static void printMenu() {
        System.out.println("\nPlease select one of the following options:");
        System.out.println("1. Show available recipes.");
        System.out.println("2. Create Shopping List.");
        System.out.println("3. Print Shopping List.");
        System.out.println("4. Quit Program.");
        System.out.println("0. to reprint this menu.\n");
    }

    
    /**
     * Displays all available recipes with indexed numbering.
     * @param manager RecipeManager reference
     */
    private static void showRecipes(RecipeManager manager) {
        System.out.println("Available Recipes:");
        int index = 1;
        for (Recipe r : manager.getRecipes()) {
            System.out.println(index + ". " + r.getName());
            index++;
        }
    }

    
    /**
     * Allows the user to choose a recipe and specify a quantity.
     * Validates input and prevents negative quantities.
     *
     * @param manager RecipeManager reference
     * @param sc Scanner for user input
     */
    private static void createOrder(RecipeManager manager, Scanner sc) {

        System.out.print("Which bread would you like? ");

        String breadInput = sc.nextLine();
        if (!breadInput.matches("\\d+")) {
            System.out.println("Please only type digits.");
            return;
        }

        int bread = Integer.parseInt(breadInput) - 1;
        if (bread < 0 || bread >= manager.getRecipes().size()) {
            System.out.println("Valid input are digits from 1 to " + manager.getRecipes().size());
            return;
        }

        System.out.print("How much of this bread would you like? ");
        String qtyInput = sc.nextLine();

        if (!qtyInput.matches("-?\\d+")) {
            System.out.println("Please only type digits.");
            return;
        }

        int qty = Integer.parseInt(qtyInput);
     
    
        if (qty < 0) {
        	 System.out.println("Negative quantity ignored.");
            return;
           
        }

        manager.orderBread(bread, qty);
    }

    /**
     * Prints the shopping list and optionally saves it to a file.
     *
     * @param manager RecipeManager reference
     * @param sc Scanner for input
     */
    private static void printShoppingList(RecipeManager manager, Scanner sc) {

        String list = manager.buildShoppingList();
        System.out.println(list);

        System.out.print("Do you want to save this list (Y/n)? ");
        String ans = sc.nextLine().trim().toLowerCase();

        if (ans.equals("y") || ans.equals("")) {
            manager.saveShoppingList(list);
            System.out.println("Shopping list saved.");
        }
    }
}