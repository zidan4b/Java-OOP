/**
 * Name: Zidan Bakari
 * Student ID: 041140634
 * Assignment 3 – CST8284
 */
package Manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The RecipeManager class is responsible for loading recipes from the
 * text file, storing them in an ArrayList, managing bread orders, and
 * generating the final shopping list.
 *
 * Assignment3 communicates ONLY with this class.
 */
public class RecipeManager {
	
	/** List of all loaded bread recipes. */
    private ArrayList<Recipe> recipes = new ArrayList<>();

  
    public RecipeManager() {
        loadRecipes();
    }
    
    
    /**
     * Loads all recipes from the "recipelist.txt" file.
     * Reads recipe name, ingredients, and stores each recipe object.
     * Uses relative paths and handles exceptions to prevent crashes.
     */
    private void loadRecipes() {
        try {
            File file = new File("recipelist.txt"); 
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.startsWith("Recipe")) {

                    Recipe recipe = new Recipe();
                    recipe.setName(line.substring(7)); 

           
                    for (int i = 0; i < 5; i++) {
                        String ingredientLine = sc.nextLine().trim();
                        String[] parts = ingredientLine.split(" ");
                        String item = parts[0];
                        float amount = Float.parseFloat(parts[1]);

                        switch (item) {
                            case "sugar":  recipe.setSugar(amount); break;
                            case "eggs":   recipe.setEggs(amount); break;
                            case "flour":  recipe.setFlour(amount); break;
                            case "yeast":  recipe.setYeast(amount); break;
                            case "butter": recipe.setButter(amount); break;
                        }
                    }

                    recipes.add(recipe);

                  
                    if (sc.hasNextLine()) sc.nextLine();
                }
            }

            sc.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Recipe file not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in recipe file.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

    }

    /**
     * Returns all loaded recipes.
     * @return list of Recipe objects
     */
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Records how many loaves the user ordered for a given recipe.
     *
     * @param index index of the recipe in the ArrayList
     * @param quantity number of loaves ordered
     */
    public void orderBread(int index, int quantity) {
        if (quantity >= 0) {
            recipes.get(index).setQuantityOrdered(quantity);
        }
    }

    /**
     * Builds the shopping list based on all breads that were ordered.
     * Only prints recipes with a non-zero quantity.
     * Only prints ingredients with total > 0.
     *
     * @return formatted shopping list as String
     */
    public String buildShoppingList() {

        float totalSugar = 0;
        float totalEggs = 0;
        float totalFlour = 0;
        float totalYeast = 0;
        float totalButter = 0;

        StringBuilder sb = new StringBuilder();

      
        for (Recipe r : recipes) {
            if (r.getQuantityOrdered() > 0) {
                sb.append(r.getQuantityOrdered())
                        .append(" ")
                        .append(r.getName())
                        .append(" loaf/loaves.\n");

                totalSugar += r.getSugar() * r.getQuantityOrdered();
                totalEggs += r.getEggs()   * r.getQuantityOrdered();
                totalFlour += r.getFlour() * r.getQuantityOrdered();
                totalYeast += r.getYeast() * r.getQuantityOrdered();
                totalButter += r.getButter() * r.getQuantityOrdered();
            }
        }

        sb.append("\nYou will need a total of:\n");

        if (totalYeast > 0) sb.append(totalYeast + " grams of yeast\n");
        if (totalFlour > 0) sb.append(totalFlour + " grams of flour\n");
        if (totalSugar > 0) sb.append(totalSugar + " grams of sugar\n");
        if (totalEggs > 0) sb.append(totalEggs + " egg(s)\n");
        if (totalButter > 0) sb.append(totalButter + " grams of butter\n");

        return sb.toString();
    }

    /**
     * Saves the generated shopping list into "shoppinglist.txt".
     *
     * @param list the formatted shopping list
     */
    public void saveShoppingList(String list) {
        try {
            FileWriter fw = new FileWriter("shoppinglist.txt");
            fw.write(list);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}
