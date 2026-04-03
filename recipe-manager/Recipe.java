/**
 * Name: Zidan Bakari
 * Student ID: 041140634
 * Assignment 3 – CST8284
 */
package Manager;

/**
 * The Recipe class represents a single type of bread.
 * It stores the bread name and the amounts of each ingredient
 * required to bake one loaf. It also holds the number of loaves
 * ordered by the user.
 *
 * This class is a plain data object with getters and setters only.
 */
public class Recipe {

    private String name;
    private float sugar;
    private float eggs;
    private float flour;
    private float yeast;
    private float butter;

    private int quantityOrdered = 0;   

    /**
     * Returns the name of the bread.
     * @return recipe name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the recipe name.
     * @param name bread name
     */
    public void setName(String name) {
        this.name = name;
    }

    public float getSugar() {
        return sugar;
    }
    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public float getEggs() {
        return eggs;
    }
    public void setEggs(float eggs) {
        this.eggs = eggs;
    }

    public float getFlour() {
        return flour;
    }
    public void setFlour(float flour) {
        this.flour = flour;
    }

    public float getYeast() {
        return yeast;
    }
    public void setYeast(float yeast) {
        this.yeast = yeast;
    }

    public float getButter() {
        return butter;
    }
    public void setButter(float butter) {
        this.butter = butter;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }
    
    /**
     * Sets how many loaves of this recipe the user ordered.
     * @param quantityOrdered number of loaves
     */
    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
}
