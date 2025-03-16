CREATE DATABASE EmployeeDB;

USE EmployeeDB;

CREATE TABLE Employees (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(100) NOT NULL,
    Salary DOUBLE NOT NULL,
    WorkingHours INT NOT NULL,
    DepartmentID INT NOT NULL
);

SELECT * FROM Employees;