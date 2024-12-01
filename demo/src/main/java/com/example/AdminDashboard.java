package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

public class AdminDashboard {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel packageListPanel;

    public AdminDashboard() {
        setDarkMode();

        frame = new JFrame("Admin Dashboard");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel sidebarPanel = createSidebarPanel();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel manageDeliveriesPanel = createManageDeliveriesPanel();
        JPanel viewReportsPanel = createViewReportsPanel();
        JPanel logoutPanel = new JPanel();

        cardPanel.add(manageDeliveriesPanel, "Manage Deliveries");
        cardPanel.add(viewReportsPanel, "View Reports");
        cardPanel.add(logoutPanel, "Logout");

        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.add(cardPanel, BorderLayout.CENTER);

        // Reload packages when the admin dashboard is loaded
        reloadPackages();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(40, 40, 40));
        sidebarPanel.setPreferredSize(new Dimension(200, frame.getHeight()));

        // Greeting text
        JTextArea greetingTextArea = new JTextArea(getGreetingMessage("Admin"));
        greetingTextArea.setForeground(Color.WHITE);
        greetingTextArea.setFont(new Font("Arial", Font.BOLD, 18));
        greetingTextArea.setBackground(new Color(40, 40, 40));
        greetingTextArea.setWrapStyleWord(true);  // Wraps at word boundaries
        greetingTextArea.setLineWrap(true); // Enables wrapping
        greetingTextArea.setEditable(false);  // Make it non-editable
        greetingTextArea.setCaretPosition(0);  // Move caret to the beginning
        greetingTextArea.setPreferredSize(new Dimension(180, 80));  // Set preferred size for wrapping
        JScrollPane scrollPane = new JScrollPane(greetingTextArea);
        scrollPane.setBackground(new Color(40, 40, 40));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Buttons
        JButton manageDeliveriesButton = createSidebarButton("Manage Deliveries");
        JButton viewReportsButton = createSidebarButton("View Reports");
        JButton logoutButton = createSidebarButton("Logout");

        manageDeliveriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Manage Deliveries");
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "View Reports");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AdminLogin(); // This will take user back to login page
            }
        });

        // Add all components to sidebar
        sidebarPanel.add(Box.createVerticalStrut(50));
        sidebarPanel.add(scrollPane); // Greeting message
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(manageDeliveriesButton);
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(viewReportsButton);
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(logoutButton);

        return sidebarPanel;
    }

    private String getGreetingMessage(String role) {
        int hour = LocalTime.now().getHour();
        String greeting;

        if (hour < 12) {
            greeting = "Good Morning, \n" + role + "!";
        } else if (hour < 18) {
            greeting = "Good Afternoon, \n" + role + "!";
        } else {
            greeting = "Good Evening, \n" + role + "!";
        }

        return greeting;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton("<html><div style='width:160px; word-wrap:break-word;'>" + text + "</div></html>");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(160, 40));
        button.setBackground(new Color(80, 80, 80));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(true);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setMargin(new Insets(5, 5, 5, 5));
        return button;
    }

    private JPanel createManageDeliveriesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50));

        // Package Registration Form
        JPanel registerPackagePanel = new JPanel();
        registerPackagePanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel studentIdLabel = new JLabel("Student ID:");
        JTextField studentIdField = new JTextField();
        JLabel descriptionLabel = new JLabel("Package Description:");
        JTextField descriptionField = new JTextField();
        JLabel statusLabel = new JLabel("Package Status:");
        String[] statusOptions = {"Arrived and waiting to be picked up", "Picked up already"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);

        JButton registerPackageButton = new JButton("Register Package");

        registerPackagePanel.add(studentIdLabel);
        registerPackagePanel.add(studentIdField);
        registerPackagePanel.add(descriptionLabel);
        registerPackagePanel.add(descriptionField);
        registerPackagePanel.add(statusLabel);
        registerPackagePanel.add(statusComboBox);
        registerPackagePanel.add(new JLabel(""));  // Empty space
        registerPackagePanel.add(registerPackageButton);

        panel.add(registerPackagePanel, BorderLayout.NORTH);

        // Package List Panel
        packageListPanel = new JPanel();
        packageListPanel.setLayout(new BoxLayout(packageListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(packageListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show the scroll
        panel.add(scrollPane, BorderLayout.CENTER);

        // Package Registration Button Action
        registerPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText().toUpperCase();
                String description = descriptionField.getText();
                String status = (String) statusComboBox.getSelectedItem();

                if (studentId.isEmpty() || description.isEmpty() || status.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled.");
                } else {
                    String packageUid = generatePackageUID();
                    String arrivalDate = getCurrentDateTime();

                    String packageInfo = "UID: " + packageUid + ", Student ID: " + studentId +
                                         ", Description: " + description + ", Status: " + status +
                                         ", Arrival: " + arrivalDate;

                    addPackageToList(packageUid, studentId, description, status, arrivalDate);
                    savePackageToFile(packageUid, studentId, description, status, arrivalDate);
                    JOptionPane.showMessageDialog(frame, "Package Registered Successfully!");

                    // Reload packages to display newly added package
                    reloadPackages();
                }
            }
        });

        return panel;
    }

    private void addPackageToList(String packageUid, String studentId, String description, String status, String arrivalDate) {
        studentId = studentId.toUpperCase();

        // Create the UI elements for the package
        JPanel packagePanel = new JPanel();
        packagePanel.setLayout(new BoxLayout(packagePanel, BoxLayout.X_AXIS));
        packagePanel.setPreferredSize(new Dimension(300, 80));  // Increase height for buttons
        packagePanel.setBackground(new Color(60, 60, 60));
        packagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel infoLabel = new JLabel("<html><b>Student ID:</b> " + studentId + " | <b>UID:</b> " + packageUid + " | <b>Description:</b> " + description + "</html>");
        infoLabel.setForeground(Color.WHITE);
        packagePanel.add(infoLabel);

        // Create the buttons for Pick Up and Delete
        JButton pickUpButton = new JButton("Pick Up");
        pickUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = JOptionPane.showInputDialog(frame, "Enter the student ID picking up the package:");
                String studentName = getStudentNameFromId(studentId);
                if (studentName != null) {
                    logPackagePickedUp(packageUid, studentId, studentName);
                } else {
                    logPackagePickedUp(packageUid, studentId, "Unknown Student");
                }
                JOptionPane.showMessageDialog(frame, "Package picked up!");
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this package?");
                if (result == JOptionPane.YES_OPTION) {
                    // Remove the package from the user's dashboard
                    removePackageFromUserDashboard(packageUid);

                    // Remove the package from the admin's dashboard
                    packageListPanel.remove(packagePanel);
                    packageListPanel.revalidate();
                    packageListPanel.repaint();
                    
                    // Log the deletion in the reports
                    logPackageDeletion(packageUid);
                    
                    JOptionPane.showMessageDialog(frame, "Package deleted successfully!");
                }
            }
        });

        // Add both buttons to the package panel
        packagePanel.add(pickUpButton);
        packagePanel.add(deleteButton);

        // Add package panel to the list
        packageListPanel.add(packagePanel);
    }

    private void removePackageFromUserDashboard(String packageUid) {
        try {
            // Read the user dashboard file and remove the package
            File tempFile = new File("temp_packages.txt");
            BufferedReader reader = new BufferedReader(new FileReader("packages.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] packageDetails = line.split(",");
                if (!packageDetails[0].equals(packageUid)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();

            // Delete the old file and rename the temporary file
            new File("packages.txt").delete();
            tempFile.renameTo(new File("packages.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logPackageDeletion(String packageUid) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reports.txt", true))) {
            String report = "Package UID: " + packageUid + " was deleted by admin.";
            writer.write(report);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStudentNameFromId(String studentId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(studentId)) {
                    return userDetails[1]; // Assume the name is in the second column
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // If no student found
    }

    private void logPackagePickedUp(String packageUid, String studentId, String studentName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reports.txt", true))) {
            String report = "Package UID: " + packageUid + " picked up by " + (studentName.equals("Unknown Student") ? "ID: " + studentId : studentName);
            writer.write(report);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generatePackageUID() {
        return "UID" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    private String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private void savePackageToFile(String packageUid, String studentId, String description, String status, String arrivalDate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("packages.txt", true))) {
            writer.write(packageUid + "," + studentId + "," + description + "," + status + "," + arrivalDate);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reloadPackages() {
        packageListPanel.removeAll();
        try (BufferedReader reader = new BufferedReader(new FileReader("packages.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] packageDetails = line.split(",");
                String packageUid = packageDetails[0];
                String studentId = packageDetails[1];
                String description = packageDetails[2];
                String status = packageDetails[3];
                String arrivalDate = packageDetails[4];

                // Add package details to the list in the UI
                addPackageToList(packageUid, studentId, description, status, arrivalDate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Refresh UI
        packageListPanel.revalidate();
        packageListPanel.repaint();
    }

    private JPanel createViewReportsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 50, 50));

        JTextArea reportArea = new JTextArea(20, 60);
        reportArea.setBackground(new Color(40, 40, 40));
        reportArea.setForeground(Color.WHITE);
        reportArea.setFont(new Font("Arial", Font.PLAIN, 14));
        reportArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(reportArea);
        panel.add(scrollPane);

        // Load and display the reports
        loadReports(reportArea);

        return panel;
    }

    private void loadReports(JTextArea reportArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader("reports.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                reportArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDarkMode() {
        UIManager.put("Button.background", new Color(80, 80, 80));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Panel.background", new Color(50, 50, 50));
        UIManager.put("Label.foreground", Color.WHITE);
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
