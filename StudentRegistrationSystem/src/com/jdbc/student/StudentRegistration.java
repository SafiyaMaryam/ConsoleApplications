package com.jdbc.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentRegistration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/miniprojects", 
                "root", 
                "12345"
            );
            
            System.out.println("Database connected successfully!");
            System.out.println("===============================");
            
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.print("Enter Department: ");
            String dept = sc.nextLine();
            
            // Prepare SQL Insert Statement
            String sql = "INSERT INTO students (name, roll, dept) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, roll);
            pst.setString(3, dept);
            
            // Execute Update
            int rowsInserted = pst.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("\n✓ Student registered successfully!");
                System.out.println("Name: " + name);
                System.out.println("Roll: " + roll);
                System.out.println("Department: " + dept);
            }
            
            // Close Resources
            pst.close();
            con.close();
            sc.close();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
