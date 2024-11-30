// package com.example;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class AdminDashboard {
//     private JFrame frame;
//     private JPanel cardPanel;
//     private CardLayout cardLayout;

//     public AdminDashboard() {
//         setDarkMode();

//         frame = new JFrame("Admin Dashboard");
//         frame.setSize(800, 600);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new BorderLayout());

//         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


//         JPanel sidebarPanel = createSidebarPanel();

//         cardLayout = new CardLayout();
//         cardPanel = new JPanel(cardLayout);

//         JPanel manageDeliveriesPanel = createManageDeliveriesPanel();
//         JPanel viewReportsPanel = createViewReportsPanel();
//         JPanel logoutPanel = new JPanel();

//         cardPanel.add(manageDeliveriesPanel, "Manage Deliveries");
//         cardPanel.add(viewReportsPanel, "View Reports");
//         cardPanel.add(logoutPanel, "Logout");

//         frame.add(sidebarPanel, BorderLayout.WEST);
//         frame.add(cardPanel, BorderLayout.CENTER);

//         frame.setLocationRelativeTo(null);
//         frame.setVisible(true);
//     }

//     private JPanel createSidebarPanel() {
//         JPanel sidebarPanel = new JPanel();
//         sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
//         sidebarPanel.setBackground(new Color(40, 40, 40));
//         sidebarPanel.setPreferredSize(new Dimension(200, frame.getHeight()));

//         JButton manageDeliveriesButton = createSidebarButton("Manage Deliveries");
//         JButton viewReportsButton = createSidebarButton("View Reports");
//         JButton logoutButton = createSidebarButton("Logout");

//         manageDeliveriesButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 cardLayout.show(cardPanel, "Manage Deliveries");
//             }
//         });

//         viewReportsButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 cardLayout.show(cardPanel, "View Reports");
//             }
//         });

//         logoutButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 frame.setVisible(false);
//                 new AdminLogin();
//             }
//         });

//         sidebarPanel.add(Box.createVerticalStrut(50));
//         sidebarPanel.add(manageDeliveriesButton);
//         sidebarPanel.add(Box.createVerticalStrut(20));
//         sidebarPanel.add(viewReportsButton);
//         sidebarPanel.add(Box.createVerticalStrut(20));
//         sidebarPanel.add(Box.createVerticalGlue());
//         sidebarPanel.add(logoutButton);

//         return sidebarPanel;
//     }

//     private JButton createSidebarButton(String text) {
//         JButton button = new JButton("<html><div style='width:160px; word-wrap:break-word;'>" + text + "</div></html>");
//         button.setAlignmentX(Component.CENTER_ALIGNMENT);
//         button.setPreferredSize(new Dimension(160, 40));  
//         button.setBackground(new Color(80, 80, 80));  
//         button.setForeground(Color.BLACK);
//         button.setFocusPainted(true);
//         button.setFont(new Font("Arial", Font.PLAIN, 16));
//         button.setHorizontalAlignment(SwingConstants.CENTER);
//         button.setVerticalAlignment(SwingConstants.CENTER);
//         button.setMargin(new Insets(5, 5, 5, 5));
//         return button;
//     }

//     private JPanel createManageDeliveriesPanel() {
//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(50, 50, 50));
//         panel.add(new JLabel("This is the Manage Deliveries page"));
//         return panel;
//     }

//     private JPanel createViewReportsPanel() {
//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(50, 50, 50));
//         panel.add(new JLabel("This is the View Reports page"));
//         return panel;
//     }

//     private void setDarkMode() {
//         UIManager.put("Panel.background", new Color(40, 40, 40));
//         UIManager.put("Button.background", new Color(80, 80, 80));
//         UIManager.put("Button.foreground", Color.BLACK);
//         UIManager.put("Label.foreground", Color.WHITE);
//         UIManager.put("OptionPane.background", new Color(40, 40, 40));
//         UIManager.put("OptionPane.messageForeground", Color.WHITE);
//         UIManager.put("OptionPane.buttonBackground", new Color(80, 80, 80));
//         UIManager.put("OptionPane.buttonForeground", Color.BLACK);
//         UIManager.put("OptionPane.titleForeground", Color.WHITE);

//         try {
//             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void main(String[] args) {
//         new AdminDashboard();
//     }
// }


package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class AdminDashboard {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private DefaultListModel<String> packageListModel;
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

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(40, 40, 40));
        sidebarPanel.setPreferredSize(new Dimension(200, frame.getHeight()));

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
                new AdminLogin();
            }
        });

        sidebarPanel.add(Box.createVerticalStrut(50));
        sidebarPanel.add(manageDeliveriesButton);
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(viewReportsButton);
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(logoutButton);

        return sidebarPanel;
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
                    JOptionPane.showMessageDialog(frame, "Package Registered Successfully!");
                }
            }
        });

        return panel;
    }

    private void addPackageToList(String packageUid, String studentId, String description, String status, String arrivalDate) {
    // Uppercase the student ID
    studentId = studentId.toUpperCase();

    // Save to the file
    savePackageToFile(packageUid, studentId, description, status, arrivalDate);

    // Create the UI elements for the package
    JPanel packagePanel = new JPanel();
    packagePanel.setLayout(new BoxLayout(packagePanel, BoxLayout.X_AXIS));
    packagePanel.setPreferredSize(new Dimension(300, 50));
    packagePanel.setBackground(new Color(60, 60, 60));
    packagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    packagePanel.setToolTipText("Hover over to see options");

    JLabel infoLabel = new JLabel("<html><b>Student ID:</b> " + studentId + " | <b>UID:</b> " + packageUid + " | <b>Description:</b> " + description + "</html>");
    infoLabel.setForeground(Color.WHITE);
    packagePanel.add(infoLabel);

    JButton pickUpButton = new JButton("Pick Up");
    pickUpButton.setVisible(false); // Initially hidden
    pickUpButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "Package picked up!");
            // Mark as picked up (change status logic here)
        }
    });

    JButton deleteButton = new JButton("Delete");
    deleteButton.setVisible(false); // Initially hidden
    deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this package?");
            if (result == JOptionPane.YES_OPTION) {
                packageListPanel.remove(packagePanel);
                packageListPanel.revalidate();
                packageListPanel.repaint();
                JOptionPane.showMessageDialog(frame, "Package deleted successfully!");
            }
        }
    });

    // Mouse hover effect to show the buttons
    packagePanel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            pickUpButton.setVisible(true);
            deleteButton.setVisible(true);
            packagePanel.add(pickUpButton);
            packagePanel.add(deleteButton);
            packagePanel.revalidate();
            packagePanel.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            pickUpButton.setVisible(false);
            deleteButton.setVisible(false);
            packagePanel.revalidate();
            packagePanel.repaint();
        }
    });

    packageListPanel.add(packagePanel);
}

private void savePackageToFile(String packageUid, String studentId, String description, String status, String arrivalDate) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("packages.txt", true))) {
        String packageData = packageUid + "," + studentId + "," + description + "," + status + "," + arrivalDate;
        writer.write(packageData);
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private JPanel createViewReportsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 50, 50));
        panel.add(new JLabel("This is the View Reports page"));
        return panel;
    }

    private void setDarkMode() {
        UIManager.put("Panel.background", new Color(40, 40, 40));
        UIManager.put("Button.background", new Color(80, 80, 80));
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("OptionPane.background", new Color(40, 40, 40));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.buttonBackground", new Color(80, 80, 80));
        UIManager.put("OptionPane.buttonForeground", Color.BLACK);
        UIManager.put("OptionPane.titleForeground", Color.WHITE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generatePackageUID() {
        Random rand = new Random();
        StringBuilder uid = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < 10; i++) {
            int index = rand.nextInt(characters.length());
            uid.append(characters.charAt(index));
        }
        return uid.toString();
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
