package com.jdbc.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BalanceChecker {
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
            
            System.out.println("Enter Account Number: ");
            long accNo = sc.nextInt();
            
            // Prepare SELECT Query
            String sql = "SELECT holder_name, balance FROM accounts WHERE acc_no = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, accNo);
            
            // Execute Query
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String name = rs.getString("holder_name");
                double balance = rs.getDouble("balance");
                
                System.out.println("\n=== Account Details ===");
                System.out.println("Account Holder: " + name);
                System.out.println("Current Balance: ₹" + balance);
            } else {
                System.out.println("Account not found!");
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
