package com.example.demo;
import com.example.myw.Employee;
import com.example.myw.DatabaseConnection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BonusCalculatorApp {

    private DatabaseConnection databaseConnection;

    public BonusCalculatorApp() {
        try {
            this.databaseConnection = new DatabaseConnection(); // Инициализация DatabaseConnection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Employee saveEmployeeToDatabase(String name, String position) {
        Employee employee = null;
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO employees (name, position) VALUES (?, ?) RETURNING id");
            pstmt.setString(1, name);
            pstmt.setString(2, position);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                employee = new Employee(id, name, position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    private void savePerformanceToDatabase(int employeeId, double performanceScore) {
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO performance (employee_id, performance_score) VALUES (?, ?)");
            pstmt.setInt(1, employeeId);
            pstmt.setDouble(2, performanceScore);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveBonusToDatabase(int employeeId, double bonusAmount) {
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO bonuses (employee_id, bonus_amount) VALUES (?, ?)");
            pstmt.setInt(1, employeeId);
            pstmt.setDouble(2, bonusAmount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double calculateBonus(double performanceScore) {
        return performanceScore * 100; // Пример: премия = производительность * 100
    }

    public void stop() throws Exception {
        if (databaseConnection != null) {
            databaseConnection.close();
        }
    }

    public static void main(String[] args) {
        BonusCalculatorApp app = new BonusCalculatorApp(); // Создание экземпляра класса, чтобы инициализировать databaseConnection
        // Пример использования
        Employee employee = app.saveEmployeeToDatabase("John Doe", "Developer");
        if (employee != null) {
            app.savePerformanceToDatabase(employee.getId(), 95.5);
            double bonus = app.calculateBonus(95.5);
            app.saveBonusToDatabase(employee.getId(), bonus);
            System.out.println("Employee " + employee.getName() + " received a bonus of " + bonus);
        }

        try {
            app.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
