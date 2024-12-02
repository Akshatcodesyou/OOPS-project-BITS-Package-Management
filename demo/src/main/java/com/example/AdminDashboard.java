package com.example;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class AdminDashboard extends JFrame {
    private PackageManager packageManager;
    private JTextArea displayArea;
    private JComboBox<String> packageComboBox;

    public AdminDashboard() {
        packageManager = new PackageManager();

        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(44, 62, 80));

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(new Color(44, 62, 80));

        packageComboBox = new JComboBox<>();
        controlPanel.add(packageComboBox);

        addButton(controlPanel, "Manage Deliveries", e -> manageDeliveries());
        addButton(controlPanel, "View Reports", e -> viewReports());
        addButton(controlPanel, "Register Package", e -> registerPackage());
        addButton(controlPanel, "Logout", e -> dispose());

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
        refreshPackageList();
        setVisible(true);
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void refreshPackageList() {
        packageComboBox.removeAllItems();
        List<Package> packages = packageManager.getPackages();
        for (Package pkg : packages) {
            packageComboBox.addItem(pkg.getPackageUid() + " - " + pkg.getDescription());
        }
    }

    private void manageDeliveries() {
        if (packageComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "No package selected.");
            return;
        }

        String selectedPackageUid = ((String) packageComboBox.getSelectedItem()).split(" - ")[0];
        String[] options = {"Pick up Package", "Delete Package", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this, "Choose an action for package " + selectedPackageUid,
                "Manage Package", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 0:
                String pickerId = JOptionPane.showInputDialog(this, "Enter picker ID:");
                if (pickerId != null && !pickerId.isEmpty()) {
                    packageManager.pickUpPackage(selectedPackageUid, pickerId);
                    JOptionPane.showMessageDialog(this, "Package picked up successfully.");
                }
                break;
            case 1:
                packageManager.deletePackage(selectedPackageUid);
                JOptionPane.showMessageDialog(this, "Package deleted successfully.");
                break;
        }
        refreshPackageList();
    }

    private void viewReports() {
        displayArea.setText("Recent Activity:\n\n");
        List<String> reports = packageManager.getReports();
        for (String report : reports) {
            displayArea.append(report + "\n");
        }
    }

    private void registerPackage() {
        JTextField studentIdField = new JTextField();
        JTextField descriptionField = new JTextField();
        Object[] message = {
            "Student ID:", studentIdField,
            "Package Description:", descriptionField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Register Package", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String studentId = studentIdField.getText();
            String description = descriptionField.getText();

            if (!studentId.isEmpty() && !description.isEmpty()) {
                String packageUid = packageManager.generatePackageUid();
                String arrivalDate = packageManager.getCurrentDateTime();

                try {
                    packageManager.registerPackage(packageUid, studentId, description, arrivalDate);
                    JOptionPane.showMessageDialog(this, "Package registered successfully!");
                    refreshPackageList();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error registering package.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}