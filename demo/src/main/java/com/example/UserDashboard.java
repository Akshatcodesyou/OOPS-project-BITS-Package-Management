// package com.example;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.time.LocalTime;

// public class UserDashboard {
//     private JFrame frame;
//     private JPanel cardPanel;
//     private CardLayout cardLayout;
//     private String userName; // Store the user's name
//     private String collegeId; // Store the user's college ID

//     // Modify the constructor to accept both userName and collegeId
//     public UserDashboard(String userName, String collegeId) {
//         this.userName = userName; // Assign the user's name
//         this.collegeId = collegeId; // Assign the user's collegeId

//         setDarkMode();

//         frame = new JFrame("User Dashboard");
//         frame.setSize(800, 600);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new BorderLayout());

//         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

//         JPanel sidebarPanel = createSidebarPanel();

//         cardLayout = new CardLayout();
//         cardPanel = new JPanel(cardLayout);

//         JPanel viewDeliveriesPanel = createViewDeliveriesPanel();
//         JPanel trackOrderPanel = createTrackOrderPanel();
//         JPanel logoutPanel = new JPanel();

//         cardPanel.add(viewDeliveriesPanel, "View Deliveries");
//         cardPanel.add(trackOrderPanel, "Track Order");
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

//         // Use JTextArea for the greeting message to properly handle text wrapping
//         JTextArea greetingTextArea = new JTextArea(getGreetingMessage(userName));
//         greetingTextArea.setForeground(Color.WHITE);
//         greetingTextArea.setFont(new Font("Arial", Font.BOLD, 18));
//         greetingTextArea.setBackground(new Color(40, 40, 40));
//         greetingTextArea.setWrapStyleWord(true);  // Wraps at word boundaries
//         greetingTextArea.setLineWrap(true); // Enables wrapping
//         greetingTextArea.setEditable(false);  // Make it non-editable
//         greetingTextArea.setCaretPosition(0);  // Move caret to the beginning
//         greetingTextArea.setPreferredSize(new Dimension(180, 80));  // Set preferred size for wrapping
        
//         // Wrap the JTextArea in a JScrollPane for potential long text
//         JScrollPane scrollPane = new JScrollPane(greetingTextArea);
//         scrollPane.setBackground(new Color(40, 40, 40));
//         scrollPane.setBorder(BorderFactory.createEmptyBorder());

//         JButton viewDeliveriesButton = createSidebarButton("View Deliveries");
//         JButton trackOrderButton = createSidebarButton("Track Order");
//         JButton logoutButton = createSidebarButton("Logout");

//         viewDeliveriesButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 cardLayout.show(cardPanel, "View Deliveries");
//             }
//         });

//         trackOrderButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 cardLayout.show(cardPanel, "Track Order");
//             }
//         });

//         logoutButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 frame.setVisible(false);
//                 new UserLogin(); // This will take user back to login page
//             }
//         });

//         sidebarPanel.add(Box.createVerticalStrut(50));
//         sidebarPanel.add(scrollPane);  // Add the JScrollPane with the greeting
//         sidebarPanel.add(Box.createVerticalStrut(20));
//         sidebarPanel.add(viewDeliveriesButton);
//         sidebarPanel.add(Box.createVerticalStrut(20));
//         sidebarPanel.add(trackOrderButton);
//         sidebarPanel.add(Box.createVerticalStrut(20));
//         sidebarPanel.add(Box.createVerticalGlue());
//         sidebarPanel.add(logoutButton);

//         return sidebarPanel;
//     }

//     private String getGreetingMessage(String userName) {
//         int hour = LocalTime.now().getHour();
//         String greeting;

//         if (hour < 12) {
//             greeting = "Good Morning, \n" + userName + "!";
//         } else if (hour < 18) {
//             greeting = "Good Afternoon, \n" + userName + "!";
//         } else {
//             greeting = "Good Evening, \n" + userName + "!";
//         }

//         return greeting;
//     }

//     private JButton createSidebarButton(String text) {
//         JButton button = new JButton("<html><div style='width:160px; word-wrap:break-word;'>" + text + "</div></html>");
//         button.setAlignmentX(Component.CENTER_ALIGNMENT);
//         button.setPreferredSize(new Dimension(160, 40)); // Smaller size
//         button.setBackground(new Color(80, 80, 80));
//         button.setForeground(Color.BLACK);
//         button.setFocusPainted(true);
//         button.setFont(new Font("Arial", Font.PLAIN, 16));
//         button.setHorizontalAlignment(SwingConstants.CENTER);  // Horizontally center the text
//         button.setVerticalAlignment(SwingConstants.CENTER);  // Vertically center the text
//         button.setMargin(new Insets(5, 5, 5, 5)); // Ensure there is padding for wrapping
//         return button;
//     }

//     private JPanel createViewDeliveriesPanel() {
//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(50, 50, 50));
//         JLabel label = new JLabel("This is the View Deliveries page");
//         label.setForeground(Color.WHITE);
//         panel.add(label);
//         return panel;
//     }

//     private JPanel createTrackOrderPanel() {
//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(50, 50, 50));
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//         JLabel label = new JLabel("Your Registered Packages");
//         label.setForeground(Color.WHITE);
//         panel.add(label);

//         displayPackages(panel);

//         return panel;
//     }

//     private void displayPackages(JPanel panel) {
//         String currentStudentId = this.collegeId.toUpperCase(); // Use the collegeId for comparison

//         try (BufferedReader reader = new BufferedReader(new FileReader("packages.txt"))) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 String[] packageDetails = line.split(",");
//                 String packageUid = packageDetails[0];
//                 String studentId = packageDetails[1];
//                 String description = packageDetails[2];
//                 String status = packageDetails[3];
//                 String arrivalDate = packageDetails[4];

//                 // Compare the collegeId with the studentId in the packages file
//                 if (studentId.equalsIgnoreCase(currentStudentId)) {
//                     JPanel packagePanel = new JPanel();
//                     packagePanel.setLayout(new BoxLayout(packagePanel, BoxLayout.Y_AXIS));
//                     packagePanel.setBackground(new Color(60, 60, 60));
//                     packagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//                     JLabel packageLabel = new JLabel("<html><b>Package UID:</b> " + packageUid + "<br><b>Description:</b> " + description + "<br><b>Status:</b> " + status + "<br><b>Arrival Date:</b> " + arrivalDate + "</html>");
//                     packageLabel.setForeground(Color.WHITE);

//                     packagePanel.add(packageLabel);
//                     panel.add(packagePanel);
//                 }
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         // Ensure the panel is refreshed after adding components
//         panel.revalidate();
//         panel.repaint();
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
//         new UserLogin();
//     }    
// }

package com.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.time.LocalTime;

public class UserDashboard {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel trackOrderPanel;
    private String fullName;
    private String collegeId;

    // Updated constructor to accept both fullName and collegeId
    public UserDashboard(String fullName, String collegeId) {
        this.fullName = fullName;
        this.collegeId = collegeId.toUpperCase(); // Store the college ID for the current user

        setDarkMode();

        frame = new JFrame("User Dashboard");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel sidebarPanel = createSidebarPanel();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel trackOrderPanel = createTrackOrderPanel();
        JPanel logoutPanel = new JPanel();

        cardPanel.add(trackOrderPanel, "Track Order");
        cardPanel.add(logoutPanel, "Logout");

        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.add(cardPanel, BorderLayout.CENTER);

        // Reload packages for the user
        displayPackages(trackOrderPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(40, 40, 40));
        sidebarPanel.setPreferredSize(new Dimension(200, frame.getHeight()));

        // Greeting text
        JTextArea greetingTextArea = new JTextArea(getGreetingMessage(fullName));
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
        JButton trackOrderButton = createSidebarButton("Track Order");
        JButton logoutButton = createSidebarButton("Logout");
        JButton refreshButton = createSidebarButton("Refresh");
        JButton viewReportsButton = createSidebarButton("View Reports"); // View Reports button

        // Track Order Button Action
        trackOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Track Order");
            }
        });

        // Logout Button Action
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new UserLogin(); // Return user to login screen
            }
        });

        // Refresh Button Action
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trackOrderPanel.removeAll();
                displayPackages(trackOrderPanel); // Reload the package information
                JOptionPane.showMessageDialog(frame, "Dashboard refreshed.");
            }
        });

        // View Reports Button Action
        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "No reports available.");
            }
        });

        // Add all components to sidebar
        sidebarPanel.add(Box.createVerticalStrut(50));
        sidebarPanel.add(scrollPane); // Greeting message
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(trackOrderButton);
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(refreshButton);
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(viewReportsButton); // View Reports button
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(logoutButton);

        return sidebarPanel;
    }

    private String getGreetingMessage(String fullName) {
        int hour = LocalTime.now().getHour();
        String greeting;

        if (hour < 12) {
            greeting = "Good Morning, \n" + fullName + "!";
        } else if (hour < 18) {
            greeting = "Good Afternoon, \n" + fullName + "!";
        } else {
            greeting = "Good Evening, \n" + fullName + "!";
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

    private JPanel createTrackOrderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50));

        trackOrderPanel = new JPanel();
        trackOrderPanel.setLayout(new BoxLayout(trackOrderPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(trackOrderPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void displayPackages(JPanel panel) {
        try (BufferedReader reader = new BufferedReader(new FileReader("packages.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] packageDetails = line.split(",");
                String packageUid = packageDetails[0];
                String studentId = packageDetails[1];
                String description = packageDetails[2];
                String status = packageDetails[3];
                String arrivalDate = packageDetails[4];

                if (studentId.equals(collegeId)) {
                    JPanel packagePanel = new JPanel();
                    packagePanel.setLayout(new BoxLayout(packagePanel, BoxLayout.Y_AXIS));
                    packagePanel.setBackground(new Color(60, 60, 60));
                    packagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    JLabel packageLabel = new JLabel("<html><b>Package UID:</b> " + packageUid + "<br><b>Description:</b> " + description + "<br><b>Status:</b> " + status + "<br><b>Arrival Date:</b> " + arrivalDate + "</html>");
                    packageLabel.setForeground(Color.WHITE);

                    packagePanel.add(packageLabel);
                    panel.add(packagePanel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        trackOrderPanel.revalidate();
        trackOrderPanel.repaint();
    }

    private void setDarkMode() {
        UIManager.put("Button.background", new Color(80, 80, 80));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Panel.background", new Color(50, 50, 50));
        UIManager.put("Label.foreground", Color.WHITE);
    }

    public static void main(String[] args) {
        new UserDashboard("John Doe", "S12345");
    }
}
