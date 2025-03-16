package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeManagementApp {
    public void launch() {
        JFrame frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titre de l'application
        JLabel titleLabel = new JLabel("Employee Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 50, 0); // Margin-bottom pour le titre
        panel.add(titleLabel, gbc);

        // Labels et champs de texte
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField nameField = createLargeTextField();

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField salaryField = createLargeTextField();

        JLabel hoursLabel = new JLabel("Working Hours:");
        hoursLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField hoursField = createLargeTextField();

        JLabel deptLabel = new JLabel("Department ID:");
        deptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField deptField = createLargeTextField();

        JButton addButton = new JButton("Add Employee");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));

        // Ajouter les composants au panneau
        gbc.insets = new Insets(10, 10, 10, 10); // Réinitialisation des marges
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(salaryLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(hoursLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(hoursField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(deptLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(deptField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, gbc);

        // Ajouter le panneau au cadre
        frame.add(panel);
        frame.setVisible(true);

        // Action Listener pour le bouton
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String salary = salaryField.getText();
            String hours = hoursField.getText();
            String dept = deptField.getText();

            if (name.isEmpty() || salary.isEmpty() || hours.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double parsedSalary = Double.parseDouble(salary);
                int parsedHours = Integer.parseInt(hours);
                int parsedDept = Integer.parseInt(dept);

                addEmployee(name, parsedSalary, parsedHours, parsedDept);
                JOptionPane.showMessageDialog(frame, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input for Salary, Working Hours, or Department ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Méthode pour ajouter un employé dans la base de données
    private void addEmployee(String name, double salary, int workingHours, int departmentID) {
        try (Connection conn = Database_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Employees (Name, Salary, WorkingHours, DepartmentID) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, name);
            stmt.setDouble(2, salary);
            stmt.setInt(3, workingHours);
            stmt.setInt(4, departmentID);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while adding employee: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Méthode utilitaire pour créer des champs de texte agrandis
    private JTextField createLargeTextField() {
        JTextField textField = new JTextField(25);
        textField.setPreferredSize(new Dimension(0, 40)); // Ajuste la hauteur à 40 pixels
        return textField;
    }
}
