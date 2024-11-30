package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;

    public Main() {
        frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton adminButton = new JButton("Admin Login");
        adminButton.setBackground(new Color(60, 60, 60));
        adminButton.setForeground(Color.BLACK);
        adminButton.setFocusPainted(false);

        JButton existingUserButton = new JButton("Existing User");
        existingUserButton.setBackground(new Color(60, 60, 60));
        existingUserButton.setForeground(Color.BLACK);
        existingUserButton.setFocusPainted(false);

        JButton newUserButton = new JButton("New User");
        newUserButton.setBackground(new Color(60, 60, 60));
        newUserButton.setForeground(Color.BLACK);
        newUserButton.setFocusPainted(false);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(60, 60, 60));
        goBackButton.setForeground(Color.BLACK);
        goBackButton.setFocusPainted(false);

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 3;
        formPanel.add(adminButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3;
        formPanel.add(existingUserButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 3;
        formPanel.add(newUserButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(goBackButton, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AdminLogin();
            }
        });

        existingUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new UserLogin();
            }
        });

        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new NewUserRegistration();
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Main();
            }
        });

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(goBackButton);

        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        setDarkMode();
        new Main();
    }

    private static void setDarkMode() {
        UIManager.put("Panel.background", new Color(40, 40, 40));
        UIManager.put("Button.background", new Color(60, 60, 60));
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Label.foreground", Color.WHITE);
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
}
