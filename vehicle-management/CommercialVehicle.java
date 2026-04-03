package test;

public class CommercialVehicle extends Vehicle {

    public static final String TYPE_LIGHT = "light duty";
    public static final String TYPE_MEDIUM = "medium duty";
    public static final String TYPE_HEAVY = "heavy duty";
    public static final String TYPE_EXTRA_HEAVY = "extra heavy duty";

    private String loadType = TYPE_LIGHT;

    public CommercialVehicle() {
        super();
    }

    public CommercialVehicle(float length, float width, String model, String loadType) {
        super(length, width, model);
        this.loadType = loadType;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    /** Parking space increases with load type */
    @Override
    public float parkingSpace() {

        float base = getLength() * getWidth();

        switch (loadType) {
            case TYPE_MEDIUM:
                return base * 1.2f;
            case TYPE_HEAVY:
                return base * 1.4f;
            case TYPE_EXTRA_HEAVY:
                return base * 1.6f;
            default:
                return base; 
        }
    }

    @Override
    public String toString() {
        return "CommercialVehicle(model=" + getModel() +
                ", length=" + getLength() +
                ", width=" + getWidth() +
                ", loadType=" + loadType + ")";
    }
}

