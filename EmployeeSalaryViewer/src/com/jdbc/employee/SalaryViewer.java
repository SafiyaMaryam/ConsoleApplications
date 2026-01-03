package com.jdbc.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SalaryViewer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/miniprojects",
                "root",
                "12345"
            );
            
            System.out.println("Enter Employee ID: ");
            int empId = sc.nextInt();
            
            // Prepare SELECT Query
            String sql = "SELECT emp_id, name, salary FROM employees WHERE emp_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, empId);
            
            // Execute Query
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                
                System.out.println("\n=== Employee Details ===");
                System.out.println("Employee ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Salary: ₹" + salary);
            } else {
                System.out.println("Record not found!");
            }
            
            // Close Resources
            rs.close();
            pst.close();
            con.close();
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
