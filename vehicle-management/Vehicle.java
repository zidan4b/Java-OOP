package test;

public class Vehicle {

    private float length;
    private float width;
    private String model = "UNKNOWN";

    public Vehicle() {
        this.length = 0.0f;
        this.width = 0.0f;
    }

    public Vehicle(float length, float width) {
        this.length = length;
        this.width = width;
    }

    public Vehicle(float length, float width, String model) {
        this.length = length;
        this.width = width;
        this.model = model;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public String getModel() {
        return model;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /** Base parking space calculation */
    public float parkingSpace() {
        return length * width;
    }

    @Override
    public String toString() {
        return "Vehicle(model=" + model +
                ", length=" + length +
                ", width=" + width + ")";
    }
}
