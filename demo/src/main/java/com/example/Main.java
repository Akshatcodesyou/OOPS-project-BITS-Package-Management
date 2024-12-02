package com.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}

class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Package Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(44, 62, 80));

        addButton(panel, "Admin Login", e -> new AdminLogin());
        addButton(panel, "User Login", e -> new UserLogin());
        addButton(panel, "New User Registration", e -> new NewUserRegistration());
        addButton(panel, "Exit", e -> System.exit(0));

        add(panel);
    }

    private void addButton(JPanel panel, String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        panel.add(button);
    }
}