package com.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class NewUserRegistration extends JFrame {
    private JTextField nameField, collegeIdField;
    private JPasswordField passwordField, confirmPasswordField;

    public NewUserRegistration() {
        setTitle("New User Registration");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(44, 62, 80));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("College ID:"));
        collegeIdField = new JTextField();
        panel.add(collegeIdField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(52, 152, 219));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(e -> register());
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    private void register() {
        String name = nameField.getText();
        String collegeId = collegeIdField.getText().toUpperCase();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (name.isEmpty() || collegeId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                writer.write(name + "," + collegeId + "," + password);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error registering user.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}