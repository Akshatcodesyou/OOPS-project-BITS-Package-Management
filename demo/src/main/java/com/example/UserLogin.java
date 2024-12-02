package com.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UserLogin extends JFrame {
    private JTextField collegeIdField;
    private JPasswordField passwordField;

    public UserLogin() {
        setTitle("User Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(44, 62, 80));

        JLabel collegeIdLabel = new JLabel("College ID:");
        collegeIdLabel.setForeground(Color.WHITE);
        panel.add(collegeIdLabel);
        collegeIdField = new JTextField();
        panel.add(collegeIdField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> login());
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private void login() {
        String collegeId = collegeIdField.getText().toUpperCase();
        String password = new String(passwordField.getPassword());

        String[] userData = authenticateUser(collegeId, password);
        if (userData != null) {
            JOptionPane.showMessageDialog(this, "Login successful. Welcome " + userData[0] + "!");
            dispose();
            new UserDashboard(userData[0], userData[1]);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid College ID or Password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[] authenticateUser(String collegeId, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[1].equals(collegeId) && userDetails[2].equals(password)) {
                    return new String[]{userDetails[0], userDetails[1]};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}