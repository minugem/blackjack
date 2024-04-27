/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author jason
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private FileIO fileIO;

    //Constructor
    public UserManager(String filePath) {
        this.fileIO = new FileIO(filePath);
    }

    //Cheeck if users already exists for login using read
    public boolean userExist(String username) {
        try {
            List<String> fileContent = fileIO.readFileLines();
            for (String userDataLine : fileContent) {
                String[] userData = userDataLine.split(",");
                if (userData[0].equals(username)) {
                    return true; 
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while checking user existence: " + e.getMessage());
            return false;
        }
        return false; 
    }

    //WRITE new player detals to file
    public void createPlayer(String username, String password, double balance) {
        try {
            String hashedPassword = hashPassword(password);
            String userData = username + "," + hashedPassword + "," + balance;
            fileIO.appendLine(userData);
            System.out.println();
            System.out.println("User created successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Login in player by READING file
    public boolean loginPlayer(String username, String password) {
        try {
            List<String> fileContent = fileIO.readFileLines();
            String hashedPassword = hashPassword(password);
            for (String userDataLine : fileContent) {
                String[] userData = userDataLine.split(",");
                if (userData[0].equals(username) && userData[1].equals(hashedPassword)) {
                    System.out.println("Login successful.");
                    return true;
                }
            }
            System.out.println("Login failed.");
            return false;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    //Update player balance by WRITING to file
    public void updatePlayerBalance(String username, double newBalance) {
        try {
            List<String> fileContent = fileIO.readFileLines();
            for (int i = 0; i < fileContent.size(); i++) {
                String[] userData = fileContent.get(i).split(",");
                if (userData[0].equals(username)) {
                    userData[2] = String.valueOf(newBalance);
                    fileContent.set(i, String.join(",", userData));
                    fileIO.writeFileLines(fileContent);
                    System.out.println("User balance updated successfully.");
                    return;
                }
            }
            System.out.println("User not found.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Get player balance by READING file
    public double getPlayerBalance(String username) {
        try {
            List<String> fileContent = fileIO.readFileLines();
            for (String userDataLine : fileContent) {
                String[] userData = userDataLine.split(",");
                if (userData[0].equals(username)) {
                    return Double.parseDouble(userData[2]);
                }
            }
            System.out.println("User not found.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return 0;
    }

    // Get ranking by reading all player username and balance
    public void displayScoreboard() {
        try {
            List<String> fileContent = fileIO.readFileLines();
            List<String[]> parsedContent = new ArrayList<>();

            for (String line : fileContent) {
                String[] userData = line.split(",");
                parsedContent.add(userData);
            }

            parsedContent.sort((a, b) -> Double.compare(Double.parseDouble(b[2]), Double.parseDouble(a[2])));

            System.out.println("\nScoreboard:");
            System.out.printf("%-20s %-10s\n", "Username", "Balance");
            for (int i = 0; i < parsedContent.size() && i < 10; i++) {
                String[] userData = parsedContent.get(i);
                System.out.printf("%-20s %-10s\n", userData[0], userData[2]);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while generating the scoreboard: " + e.getMessage());
        }
    }

    //Reset password by writing to file
    public void resetUserPassword(String username, String newPassword) {
        try {
            List<String> lines = fileIO.readFileLines();
            boolean found = false;
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts[0].equals(username)) {
                    parts[1] = hashPassword(newPassword); // Hash the new password
                    lines.set(i, String.join(",", parts));
                    found = true;
                    break;
                }
            }
            if (found) {
                fileIO.writeFileLines(lines); // Write operation
                System.out.println("Password reset successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            System.out.println("Error resetting password: " + e.getMessage());
        }
    }

    //Hash user input password for secuirty and privacy
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
