package test;

import javax.swing.*;

public class Vehicle_Test {

    public static void main(String[] args) {

        // Console Tests
        testElectricVehicle();
        testCommercialVehicle();

        // GUI
        JFrame frame = new JFrame("Vehicle Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new VehiclePanel("Vehicle Panel"));
        frame.pack(); //Resize
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);  
    }

    private static void testElectricVehicle() {
        System.out.println("--- Electric Vehicle Tests ---");

        ElectricVehicle ev1 = new ElectricVehicle();
        System.out.println(ev1);

        ElectricVehicle ev2 = new ElectricVehicle(4.5f, 2.0f, "EV Model", 60f);
        System.out.println(ev2);

        ElectricVehicle ev3 = new ElectricVehicle(5.0f, 2.5f, "Tesla Model S", 80f);
        System.out.println(ev3);
        
        //@Overridden
        System.out.println("Parking Space: " + ev3.parkingSpace());
        System.out.println();
    }

    private static void testCommercialVehicle() {
        System.out.println("--- Commercial Vehicle Tests ---");

        CommercialVehicle cv1 = new CommercialVehicle();
        System.out.println(cv1);

        CommercialVehicle cv2 = new CommercialVehicle(10f, 3f, "Delivery Truck",
                CommercialVehicle.TYPE_MEDIUM);
        System.out.println(cv2);

        CommercialVehicle cv3 = new CommercialVehicle(12f, 3.2f, "Freightliner Cascadia",
                CommercialVehicle.TYPE_HEAVY);
        System.out.println(cv3);

        System.out.println("Parking Space: " + cv3.parkingSpace());
        System.out.println();
    }
}
