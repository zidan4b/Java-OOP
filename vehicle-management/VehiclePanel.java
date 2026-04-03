package test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class VehiclePanel extends JPanel {

    private String panelName;

    private JLabel vehiclePhotoLabel;
    private JButton addVehicleButton;
    private JRadioButton electricVehicleType;
    private JRadioButton commercialVehicleType;
    private JButton runTests;
    private JButton createVehicleList;
    private JButton sortByLoadType;
    private JTextArea testResults;
    private ButtonGroup vehicleTypeGroup;

    private Vehicle[] vehicles;
    private ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public VehiclePanel(String name) {
        this.panelName = name;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {

        // -- PANEL --
        JPanel topPanel = new JPanel();
        vehiclePhotoLabel = new JLabel("Vehicle Photo", SwingConstants.CENTER);
        vehiclePhotoLabel.setPreferredSize(new Dimension(200, 150));
        vehiclePhotoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.add(vehiclePhotoLabel);

        // -- LEFT PANEL --
        JPanel leftPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        electricVehicleType = new JRadioButton("Electric Vehicle");
        commercialVehicleType = new JRadioButton("Commercial Vehicle");

        vehicleTypeGroup = new ButtonGroup();
        vehicleTypeGroup.add(electricVehicleType);
        vehicleTypeGroup.add(commercialVehicleType);
        electricVehicleType.setSelected(true);

        addVehicleButton = new JButton("Add Vehicle");
        addVehicleButton.addActionListener(e -> addVehicleAction());

        leftPanel.add(electricVehicleType);
        leftPanel.add(commercialVehicleType);
        leftPanel.add(addVehicleButton);

        // -- RIGHT PANEL --
        JPanel rightPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        runTests = new JButton("Run Tests");
        createVehicleList = new JButton("Create Vehicle List");
        sortByLoadType = new JButton("Sort by Load Type");

        runTests.addActionListener(e -> runTestsAction());
        createVehicleList.addActionListener(e -> createVehicleListAction());
        sortByLoadType.addActionListener(e -> sortByLoadTypeAction());

        rightPanel.add(runTests);
        rightPanel.add(createVehicleList);
        rightPanel.add(sortByLoadType);

        // --- CENTER TEXT AREA ---
        testResults = new JTextArea(12, 45);
        testResults.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(testResults);

        // --- ADD TO MAIN PANEL ---
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addVehicleAction() {

        String lengthStr = JOptionPane.showInputDialog("Enter length:");
        String widthStr = JOptionPane.showInputDialog("Enter width:");
        String model = JOptionPane.showInputDialog("Enter model:");

        if (lengthStr == null || widthStr == null || model == null) return;

        try {
            float length = Float.parseFloat(lengthStr);
            float width = Float.parseFloat(widthStr);

            if (electricVehicleType.isSelected()) {

                String capStr = JOptionPane.showInputDialog("Battery capacity:");
                if (capStr == null) return;

                float cap = Float.parseFloat(capStr);

                ElectricVehicle ev = new ElectricVehicle(length, width, model, cap);
                vehicleList.add(ev);
                testResults.append("Added: " + ev + "\n");

            } else {

                String[] types = {
                        CommercialVehicle.TYPE_LIGHT,
                        CommercialVehicle.TYPE_MEDIUM,
                        CommercialVehicle.TYPE_HEAVY,
                        CommercialVehicle.TYPE_EXTRA_HEAVY
                };

                String selected = (String) JOptionPane.showInputDialog(
                        null, "Choose load type:",
                        "Load Type",
                        JOptionPane.PLAIN_MESSAGE,
                        null, types, types[0]
                );

                if (selected == null) return;

                CommercialVehicle cv = new CommercialVehicle(length, width, model, selected);
                vehicleList.add(cv);
                testResults.append("Added: " + cv + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format!");
        }
    }

    private void runTestsAction() {

        testResults.append("\n--- Electric Vehicle Tests ---\n");

        ElectricVehicle ev1 = new ElectricVehicle();
        testResults.append(ev1 + "\n");

        ElectricVehicle ev2 = new ElectricVehicle(4, 2, "EV Model", 60);
        testResults.append(ev2 + "\n");

        testResults.append("\n--- Commercial Vehicle Tests ---\n");

        CommercialVehicle cv1 = new CommercialVehicle();
        testResults.append(cv1 + "\n");

        CommercialVehicle cv2 =
                new CommercialVehicle(12, 3, "Truck", CommercialVehicle.TYPE_HEAVY);
        testResults.append(cv2 + "\n");

        testResults.append("\n=== Tests Complete ===\n");
    }

    private void createVehicleListAction() {

        if (vehicleList.isEmpty()) {
            testResults.append("\nNo vehicles in list.\n");
            return;
        }

        vehicles = vehicleList.toArray(new Vehicle[0]);

        testResults.append("\n=== Vehicle List Created ===\n");
        for (int i = 0; i < vehicles.length; i++) {
            testResults.append((i + 1) + ". " + vehicles[i] + "\n");
        }
    }

    private void sortByLoadTypeAction() {

        if (vehicles == null || vehicles.length == 0) {
            testResults.append("\nCreate list first.\n");
            return;
        }

        ArrayList<CommercialVehicle> list = new ArrayList<>();

        for (Vehicle v : vehicles) {
            if (v instanceof CommercialVehicle) {
                list.add((CommercialVehicle) v);
            }
        }

        if (list.isEmpty()) {
            testResults.append("\nNo commercial vehicles.\n");
            return;
        }

        list.sort(Comparator.comparing(CommercialVehicle::getLoadType));

        testResults.append("\n=== Sorted Commercial Vehicles ===\n");
        for (CommercialVehicle cv : list) {
            testResults.append(cv + "\n");
        }
    }
}
