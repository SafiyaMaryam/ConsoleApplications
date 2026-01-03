package com.jdbc.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PriceUpdater {
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
            
            System.out.println("Enter Product ID: ");
            int productId = sc.nextInt();
            
            System.out.println("Enter New Price: ");
            double newPrice = sc.nextDouble();
            
            // Prepare UPDATE Query
            String sql = "UPDATE products SET price = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1, newPrice);
            pst.setInt(2, productId);
            
            // Execute Update
            int rowsUpdated = pst.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("Product price updated successfully!");
                System.out.println(rowsUpdated + " row(s) affected.");
            } else {
                System.out.println("Product ID not found!");
            }
            
            // Close Resources
            pst.close();
            con.close();
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
