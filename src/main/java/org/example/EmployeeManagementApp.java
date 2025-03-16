package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeManagementApp {
    public void launch() {
        JFrame frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and TextFields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel salaryLabel = new JLabel("Salary:");
        JTextField salaryField = new JTextField(20);

        JLabel hoursLabel = new JLabel("Working Hours:");
        JTextField hoursField = new JTextField(20);

        JLabel deptLabel = new JLabel("Department ID:");
        JTextField deptField = new JTextField(20);

        JButton addButton = new JButton("Add Employee");

        // Adding components
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(salaryLabel, gbc);

        gbc.gridx = 1;
        panel.add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(hoursLabel, gbc);

        gbc.gridx = 1;
        panel.add(hoursField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(deptLabel, gbc);

        gbc.gridx = 1;
        panel.add(deptField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        frame.add(panel);
        frame.setVisible(true);

        // Action listener for the button
        addButton.addActionListener(e -> addEmployee(nameField, salaryField, hoursField, deptField));
    }

    private void addEmployee(JTextField nameField, JTextField salaryField, JTextField hoursField, JTextField deptField) {
        String name = nameField.getText();
        try {
            double salary = Double.parseDouble(salaryField.getText());
            int workingHours = Integer.parseInt(hoursField.getText());
            int departmentID = Integer.parseInt(deptField.getText());

            try (Connection conn = Database_Connection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO Employees (Name, Salary, WorkingHours, DepartmentID) VALUES (?, ?, ?, ?)")) {

                if (conn == null) {
                    JOptionPane.showMessageDialog(null, "Failed to connect to the database. Please try again.");
                    return;
                }

                stmt.setString(1, name);
                stmt.setDouble(2, salary);
                stmt.setInt(3, workingHours);
                stmt.setInt(4, departmentID);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Employee added successfully!");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid data!");
        }
    }
}
