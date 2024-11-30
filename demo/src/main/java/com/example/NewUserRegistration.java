// package com.example;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.io.*;

// public class NewUserRegistration {
//     private JFrame frame;
//     private JTextField nameField, collegeIdField;
//     private JPasswordField passwordField;

//     public NewUserRegistration() {
//         setDarkMode();

//         frame = new JFrame("New User Registration");
//         frame.setSize(400, 300);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new BorderLayout());

//         JPanel formPanel = new JPanel();
//         formPanel.setLayout(new GridBagLayout());
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(10, 10, 10, 10);

//         JLabel nameLabel = new JLabel("Full Name:");
//         nameField = new JTextField(20);
//         nameField.setBackground(new Color(60, 60, 60));
//         nameField.setForeground(Color.WHITE);

//         JLabel collegeIdLabel = new JLabel("College ID:");
//         collegeIdField = new JTextField(20);
//         collegeIdField.setBackground(new Color(60, 60, 60));
//         collegeIdField.setForeground(Color.WHITE);

//         JLabel passwordLabel = new JLabel("Password:");
//         passwordField = new JPasswordField(20);
//         passwordField.setBackground(new Color(60, 60, 60));
//         passwordField.setForeground(Color.WHITE);

//         JButton registerButton = new JButton("Register");
//         registerButton.setBackground(new Color(60, 60, 60));
//         registerButton.setForeground(Color.BLACK);
//         registerButton.setFocusPainted(false);

//         JButton goBackButton = new JButton("Go Back");
//         goBackButton.setBackground(new Color(60, 60, 60));
//         goBackButton.setForeground(Color.BLACK);
//         goBackButton.setFocusPainted(false);

//         // Adding components to form panel
//         gbc.gridx = 0; gbc.gridy = 0;
//         formPanel.add(nameLabel, gbc);
//         gbc.gridx = 1; gbc.gridy = 0;
//         formPanel.add(nameField, gbc);

//         gbc.gridx = 0; gbc.gridy = 1;
//         formPanel.add(collegeIdLabel, gbc);
//         gbc.gridx = 1; gbc.gridy = 1;
//         formPanel.add(collegeIdField, gbc);

//         gbc.gridx = 0; gbc.gridy = 2;
//         formPanel.add(passwordLabel, gbc);
//         gbc.gridx = 1; gbc.gridy = 2;
//         formPanel.add(passwordField, gbc);

//         gbc.gridx = 0; gbc.gridy = 3;
//         gbc.gridwidth = 2;
//         formPanel.add(registerButton, gbc);

//         gbc.gridx = 0; gbc.gridy = 4;
//         gbc.gridwidth = 2;
//         formPanel.add(goBackButton, gbc);

//         frame.add(formPanel, BorderLayout.CENTER);

//         // Register Button Action
//         registerButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String name = nameField.getText();
//                 String collegeId = collegeIdField.getText();
//                 String password = new String(passwordField.getPassword());

//                 if (name.isEmpty() || collegeId.isEmpty() || password.isEmpty()) {
//                     JOptionPane.showMessageDialog(frame, "All fields must be filled.");
//                 } else {
//                     // Automatically set the username to the college ID
//                     String username = collegeId;

//                     try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
//                         // Save the user information (College ID, Full Name, Username (same as College ID), Password)
//                         writer.write(collegeId + "," + name + "," + username + "," + password + "\n");
//                         JOptionPane.showMessageDialog(frame, "Registration successful!");
//                         frame.setVisible(false);

//                         // Extract the first two parts of the name to pass to UserDashboard
//                         String[] nameParts = name.split(" ");
//                         String firstTwoNames = nameParts.length > 1 ? nameParts[0] + " " + nameParts[1] : nameParts[0];

//                         // Pass the modified name to the UserDashboard
//                         new UserDashboard(firstTwoNames);
//                     } catch (IOException ex) {
//                         JOptionPane.showMessageDialog(frame, "Error saving user information.");
//                     }
//                 }
//             }
//         });

//         // Go Back Button Action
//         goBackButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 frame.setVisible(false);
//                 new Main();  // Navigate back to the main login page
//             }
//         });

//         frame.setLocationRelativeTo(null);
//         frame.setVisible(true);
//     }

//     private void setDarkMode() {
//         UIManager.put("Panel.background", new Color(40, 40, 40));
//         UIManager.put("Button.background", new Color(60, 60, 60));
//         UIManager.put("Button.foreground", Color.BLACK);
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
//         new NewUserRegistration();
//     }
// }

package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NewUserRegistration {
    private JFrame frame;
    private JTextField nameField, collegeIdField;
    private JPasswordField passwordField;

    public NewUserRegistration() {
        setDarkMode();

        frame = new JFrame("New User Registration");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Full Name:");
        nameField = new JTextField(20);
        nameField.setBackground(new Color(60, 60, 60));
        nameField.setForeground(Color.WHITE);

        JLabel collegeIdLabel = new JLabel("College ID:");
        collegeIdField = new JTextField(20);
        collegeIdField.setBackground(new Color(60, 60, 60));
        collegeIdField.setForeground(Color.WHITE);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(60, 60, 60));
        passwordField.setForeground(Color.WHITE);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(60, 60, 60));
        registerButton.setForeground(Color.BLACK);
        registerButton.setFocusPainted(false);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(60, 60, 60));
        goBackButton.setForeground(Color.BLACK);
        goBackButton.setFocusPainted(false);

        // Adding components to form panel
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(collegeIdLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(collegeIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(goBackButton, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        // Register Button Action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String collegeId = collegeIdField.getText();
                String password = new String(passwordField.getPassword());

                if (name.isEmpty() || collegeId.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled.");
                } else {
                    // Automatically set the username to the college ID
                    String username = collegeId.toUpperCase();

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                        // Save the user information (Full Name, College ID, Password)
                        writer.write(name + "," + collegeId + "," + password + "\n");
                        JOptionPane.showMessageDialog(frame, "Registration successful!");
                        frame.setVisible(false);

                        // Extract the first two parts of the name to pass to UserDashboard
                        String[] nameParts = name.split(" ");
                        String firstTwoNames = nameParts.length > 1 ? nameParts[0] + " " + nameParts[1] : nameParts[0];

                        // Pass the modified name and collegeId to the UserDashboard
                        new UserDashboard(firstTwoNames, collegeId);  // Fix: Pass both name and collegeId
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error saving user information.");
                    }
                }
            }
        });

        // Go Back Button Action
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Main();  // Navigate back to the main login page
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setDarkMode() {
        UIManager.put("Panel.background", new Color(40, 40, 40));
        UIManager.put("Button.background", new Color(60, 60, 60));
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("TextField.background", new Color(60, 60, 60));
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("PasswordField.background", new Color(60, 60, 60));
        UIManager.put("PasswordField.foreground", Color.WHITE);
        UIManager.put("OptionPane.background", new Color(40, 40, 40));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.buttonBackground", new Color(60, 60, 60));
        UIManager.put("OptionPane.buttonForeground", Color.WHITE);
        UIManager.put("OptionPane.titleForeground", Color.WHITE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NewUserRegistration();
    }
}
