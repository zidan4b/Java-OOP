package test;

public class ElectricVehicle extends Vehicle {

    private float batteryCapacity;

    public ElectricVehicle() {
        super();
        this.batteryCapacity = 0.0f;
    }

    public ElectricVehicle(float length, float width, String model, float batteryCapacity) {
        super(length, width, model);
        this.batteryCapacity = batteryCapacity;
    }

    public float getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(float batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    /** Electric vehicles need 10% more space */
    @Override
    public float parkingSpace() {
        return getLength() * getWidth() * 1.1f;
    }

    @Override
    public String toString() {
        return "ElectricVehicle(model=" + getModel() +
                ", length=" + getLength() +
                ", width=" + getWidth() +
                ", batteryCapacity=" + batteryCapacity + ")";
    }
}
