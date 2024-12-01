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

        reloadPackages();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(40, 40, 40));
        sidebarPanel.setPreferredSize(new Dimension(200, frame.getHeight()));

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
        JButton refreshButton = createSidebarButton("Refresh");

        // Manage Deliveries Button Action
        manageDeliveriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Manage Deliveries");
            }
        });

        // View Reports Button Action
        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "View Reports");
            }
        });

        // Logout Button Action
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AdminLogin(); // This will take user back to login page
            }
        });

        // Refresh Button Action
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reload packages when refreshing
                reloadPackages();
                JOptionPane.showMessageDialog(frame, "Dashboard refreshed.");
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
        sidebarPanel.add(refreshButton);  // Refresh Button
        sidebarPanel.add(Box.createVerticalStrut(20));
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
    
        // Set background to white
        button.setBackground(Color.WHITE);
        // Set text color to black
        button.setForeground(Color.BLACK);
        
        // Remove default focus border effect
        button.setFocusPainted(false);
        
        // Set font for button text
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Align text in the center
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        
        // Add some padding to the button
        button.setMargin(new Insets(5, 5, 5, 5));
    
        // Add hover effect to change background color when mouse is over
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(220, 220, 220)); // Light gray when hovered
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE); // Reset to white when not hovered
            }
        });
    
        return button;
    }    
    private JPanel createManageDeliveriesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50));

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

                // Add the package to the display panel
                JPanel packagePanel = new JPanel();
                packagePanel.setLayout(new BoxLayout(packagePanel, BoxLayout.Y_AXIS));
                packagePanel.setBackground(new Color(60, 60, 60));
                packagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel packageLabel = new JLabel("<html><b>Package UID:</b> " + packageUid + "<br><b>Description:</b> " + description + "<br><b>Status:</b> " + status + "<br><b>Arrival Date:</b> " + arrivalDate + "</html>");
                packageLabel.setForeground(Color.WHITE);

                packagePanel.add(packageLabel);
                packageListPanel.add(packagePanel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        packageListPanel.revalidate();
        packageListPanel.repaint();
    }

    private String generatePackageUID() {
        Random rand = new Random();
        return String.format("PKG%05d", rand.nextInt(100000)); // Random Package UID with 5 digits
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    private void addPackageToList(String packageUid, String studentId, String description, String status, String arrivalDate) {
        // Add package to the list (this is just in the memory)
        String packageInfo = packageUid + "," + studentId + "," + description + "," + status + "," + arrivalDate;
    }

    private void savePackageToFile(String packageUid, String studentId, String description, String status, String arrivalDate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("packages.txt", true))) {
            String packageInfo = packageUid + "," + studentId + "," + description + "," + status + "," + arrivalDate;
            writer.write(packageInfo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JPanel createViewReportsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 50, 50));

        JLabel reportLabel = new JLabel("<html><b>Reports:</b><br>1. Package Pickup Status<br>2. Delivery Times</html>");
        reportLabel.setForeground(Color.WHITE);
        panel.add(reportLabel);

        return panel;
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
