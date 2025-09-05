package com.student;

import java.sql.*;
import java.util.Scanner;

public class StudentApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DatabaseConnection.getConnection()) {

            while (true) {
                System.out.println("\n=== Student Database ===");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Choose: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                if (choice == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setInt(3, age);
                        ps.setString(4, course);
                        ps.setString(5, email);
                        ps.executeUpdate();
                        System.out.println("Student added.");
                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                } else if (choice == 2) {
                    String sql = "SELECT * FROM students";
                    try (Statement st = conn.createStatement();
                         ResultSet rs = st.executeQuery(sql)) {
                        if (!rs.isBeforeFirst()) {
                            System.out.println("No students found.");
                        } else {
                            while (rs.next()) {
                                System.out.println(
                                        rs.getInt("id") + " | " +
                                                rs.getString("name") + " | " +
                                                rs.getInt("age") + " | " +
                                                rs.getString("course") + " | " +
                                                rs.getString("email")
                                );
                            }
                        }
                    }

                } else if (choice == 3) {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();

                    String sql = "UPDATE students SET name = ? WHERE id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setInt(2, id);
                        int rows = ps.executeUpdate();
                        if (rows > 0) System.out.println("Student updated.");
                        else System.out.println("ID not found.");
                    }

                } else if (choice == 4) {
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    String sql = "DELETE FROM students WHERE id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        int rows = ps.executeUpdate();
                        if (rows > 0) System.out.println("Student deleted.");
                        else System.out.println("ID not found.");
                    }

                } else if (choice == 5) {
                    System.out.println("Goodbye!");
                    break;

                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        sc.close();
    }
}