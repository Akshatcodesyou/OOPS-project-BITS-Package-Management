// package com.example;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class UserLogin {
//     private JFrame frame;
//     private JTextField usernameField;
//     private JPasswordField passwordField;

//     public UserLogin() {
//         setDarkMode();

//         frame = new JFrame("User Login");
//         frame.setSize(400, 300);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new BorderLayout());

//         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


//         JPanel formPanel = new JPanel();
//         formPanel.setLayout(new GridBagLayout());
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(10, 10, 10, 10);

//         JLabel usernameLabel = new JLabel("Username:");
//         usernameField = new JTextField(20);
//         usernameField.setBackground(new Color(60, 60, 60));
//         usernameField.setForeground(Color.WHITE);

//         JLabel passwordLabel = new JLabel("Password:");
//         passwordField = new JPasswordField(20);
//         passwordField.setBackground(new Color(60, 60, 60));
//         passwordField.setForeground(Color.WHITE);

//         JButton loginButton = new JButton("Login");
//         loginButton.setBackground(new Color(60, 60, 60));
//         loginButton.setForeground(Color.BLACK);  // Black text for the button
//         loginButton.setFocusPainted(false);

//         JButton goBackButton = new JButton("Go Back");
//         goBackButton.setBackground(new Color(60, 60, 60));
//         goBackButton.setForeground(Color.BLACK);  // Black text for the button
//         goBackButton.setFocusPainted(false);

//         gbc.gridx = 0; gbc.gridy = 0;
//         formPanel.add(usernameLabel, gbc);
//         gbc.gridx = 1; gbc.gridy = 0;
//         formPanel.add(usernameField, gbc);

//         gbc.gridx = 0; gbc.gridy = 1;
//         formPanel.add(passwordLabel, gbc);
//         gbc.gridx = 1; gbc.gridy = 1;
//         formPanel.add(passwordField, gbc);

//         gbc.gridx = 0; gbc.gridy = 2;
//         gbc.gridwidth = 2;
//         formPanel.add(loginButton, gbc);

//         gbc.gridx = 0; gbc.gridy = 3;
//         gbc.gridwidth = 2;
//         formPanel.add(goBackButton, gbc);

//         frame.add(formPanel, BorderLayout.CENTER);

//         loginButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if (usernameField.getText().equals("user") && new String(passwordField.getPassword()).equals("user123")) {
//                     new UserDashboard();
//                     frame.setVisible(false);
//                 } else {
//                     JOptionPane.showMessageDialog(frame, "Invalid credentials.");
//                 }
//             }
//         });

//         goBackButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 frame.setVisible(false);
//                 new Main();
//             }
//         });

//         JPanel footerPanel = new JPanel();
//         footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//         footerPanel.add(goBackButton);

//         frame.add(footerPanel, BorderLayout.SOUTH);

//         frame.setLocationRelativeTo(null);
//         frame.setVisible(true);
//     }

//     private void setDarkMode() {
//         UIManager.put("Panel.background", new Color(40, 40, 40));
//         UIManager.put("Button.background", new Color(60, 60, 60));
//         UIManager.put("Button.foreground", Color.BLACK);  // Black text for buttons
//         UIManager.put("Label.foreground", Color.WHITE);
//         UIManager.put("TextField.background", new Color(60, 60, 60));
//         UIManager.put("TextField.foreground", Color.WHITE);
//         UIManager.put("PasswordField.background", new Color(60, 60, 60));
//         UIManager.put("PasswordField.foreground", Color.WHITE);
//         UIManager.put("OptionPane.background", new Color(40, 40, 40));
//         UIManager.put("OptionPane.messageForeground", Color.WHITE);
//         UIManager.put("OptionPane.buttonBackground", new Color(60, 60, 60));
//         UIManager.put("OptionPane.buttonForeground", Color.WHITE);
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
import java.awt.event.*;
import java.io.*;

public class UserLogin {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public UserLogin() {
        setDarkMode();

        frame = new JFrame("User Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("College ID:");
        usernameField = new JTextField(20);
        usernameField.setBackground(new Color(60, 60, 60));
        usernameField.setForeground(Color.WHITE);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(60, 60, 60));
        passwordField.setForeground(Color.WHITE);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(60, 60, 60));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(60, 60, 60));
        goBackButton.setForeground(Color.BLACK);
        goBackButton.setFocusPainted(false);

        // Adding components to form panel
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(goBackButton, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        // Login Button Action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String collegeId = usernameField.getText().toUpperCase();
                String password = new String(passwordField.getPassword());
        
                if (collegeId.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter both college ID and password.");
                } else {
                    String[] userData = authenticateUser(collegeId, password);  // Authenticate based on college ID and password
                    if (userData != null) {
                        String fullName = userData[0]; // Full name from authentication
                        String storedCollegeId = userData[1]; // College ID from authentication
        
                        frame.setVisible(false);  // Close login screen
                        // Pass both fullName and storedCollegeId to the UserDashboard
                        new UserDashboard(fullName, storedCollegeId);  // Pass both fullName and collegeId dynamically
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid college ID or password.");
                    }
                }
            }
        });        

        // Go Back Button Action
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new NewUserRegistration(); // Redirect to registration page
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String[] authenticateUser(String collegeId, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                String storedName = userDetails[0];
                String storedCollegeId = userDetails[1];
                String storedPassword = userDetails[2];

                if (storedCollegeId.equals(collegeId) && storedPassword.equals(password)) {
                    return new String[]{storedName, storedCollegeId};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }

    private void setDarkMode() {
        UIManager.put("Button.background", new Color(80, 80, 80));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Panel.background", new Color(50, 50, 50));
        UIManager.put("Label.foreground", Color.WHITE);
    }

    public static void main(String[] args) {
        new UserLogin();
    }
}
