package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Lancer l'application en toute sécurité
            SwingUtilities.invokeLater(() -> {
                EmployeeManagementApp app = new EmployeeManagementApp();
                app.launch();
            });
            System.out.println("Application started successfully!");
        } catch (Exception e) {
            // Gestion des exceptions inattendues
            System.err.println("An error occurred while starting the application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
