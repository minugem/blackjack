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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public void displayScoreboard(JLabel scoreBoardLabel) {
        try {
            List<String> fileContent = fileIO.readFileLines();
            List<String[]> parsedContent = new ArrayList<>();

            for (String line : fileContent) {
                String[] userData = line.split(",");
                parsedContent.add(userData);
            }

            parsedContent.sort((a, b) -> Double.compare(Double.parseDouble(b[2]), Double.parseDouble(a[2])));

            StringBuilder scoreboardText = new StringBuilder("<html><body><h2>Scoreboard</h2>");
            scoreboardText.append("<table border='1'>");
            scoreboardText.append("<tr><th>Username</th><th>Balance</th></tr>");

            for (int i = 0; i < parsedContent.size() && i < 10; i++) {
                String[] userData = parsedContent.get(i);
                scoreboardText.append("<tr><td>").append(userData[0]).append("</td><td>").append(userData[2]).append("</td></tr>");
            }

            scoreboardText.append("</table></body></html>");
            scoreBoardLabel.setText(scoreboardText.toString());
        } catch (Exception e) {
            scoreBoardLabel.setText("An error occurred while generating the scoreboard: " + e.getMessage());
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
