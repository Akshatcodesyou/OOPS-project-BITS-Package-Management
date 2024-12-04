package com.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UserDashboard extends JFrame {
    private String fullName;
    private String collegeId;
    private JTextArea orderArea;

    public UserDashboard(String fullName, String collegeId) {
        this.fullName = fullName;
        this.collegeId = collegeId.toUpperCase();

        setTitle("User Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(44, 62, 80));

        JLabel welcomeLabel = new JLabel("Welcome, " + fullName + " (" + collegeId + ")");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        orderArea = new JTextArea();
        orderArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(44, 62, 80));

        JButton trackOrderButton = new JButton("Track Order");
        trackOrderButton.setBackground(new Color(52, 152, 219));
        trackOrderButton.setForeground(Color.WHITE);
        trackOrderButton.addActionListener(e -> trackOrder());
        buttonPanel.add(trackOrderButton);

        JButton reports = new JButton("Reports");
        reports.setBackground(new Color(52, 152, 219));
        reports.setForeground(Color.WHITE);
        reports.addActionListener(e -> reports());
        buttonPanel.add(reports);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(52, 152, 219));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> logout());
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void trackOrder() {
        orderArea.setText("Tracking your order...\n\n");
        try (BufferedReader reader = new BufferedReader(new FileReader("packages.txt"))) {
            String line;
            boolean foundPackages = false;
            while ((line = reader.readLine()) != null) {
                String[] packageDetails = line.split(",");
                if (packageDetails.length == 5) {
                    String packageId = packageDetails[0];
                    String ownerId = packageDetails[1];
                    String description = packageDetails[2];
                    String status = packageDetails[3];
                    String date = packageDetails[4];
    
                    if (ownerId.equalsIgnoreCase(collegeId) && status.equalsIgnoreCase("Pending")) {
                        orderArea.append("Package ID: " + packageId + "\n");
                        orderArea.append("Description: " + description + "\n");
                        orderArea.append("Status: " + status + "\n");
                        orderArea.append("Date: " + date + "\n\n");
                        foundPackages = true;
                    }
                }
            }
            if (!foundPackages) {
                orderArea.append("No pending packages found for your ID.");
            }
        } catch (IOException e) {
            orderArea.append("Error reading the package status file. Please try again later.");
            e.printStackTrace();
        }
    }

    private void reports() {
        orderArea.setText("Reports...\n\n");
        try (BufferedReader reader = new BufferedReader(new FileReader("reports.txt"))) {
            String line;
            boolean foundReports = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User ID: " + collegeId)) {
                    orderArea.append(line + "\n");
                    foundReports = true;
                }
            }
            if (!foundReports) {
                orderArea.append("No reports found for your account.");
            }
        } catch (IOException e) {
            orderArea.append("Error reading the report file. Please try again later.");
            e.printStackTrace();
        }
    }
    

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new MainMenu().setVisible(true);
        }
    }
}
