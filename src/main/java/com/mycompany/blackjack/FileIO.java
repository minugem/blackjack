/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author jason
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    private String filePath;

    //Constructor
    public FileIO(String filePath) {
        this.filePath = filePath;
    }

    // Read all lines from the file
    public List<String> readFileLines() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    // Write lines to the file, overwriting existing content
    public void writeFileLines(List<String> lines) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                out.println(line);
            }
        }
    }

    // Append a single line to the file
    public void appendLine(String line) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath, true))) {
            out.println(line);
        }
    }

    // Remove a player's account from the file
    public void deletePlayerAccount(String playerId) throws IOException {
        List<String> fileContent = readFileLines();
        List<String> updatedContent = new ArrayList<>();

        // Iterate through the file content, skipping the player to delete
        for (String line : fileContent) {
            String[] userData = line.split(",");
            if (!userData[0].equals(playerId)) {
                updatedContent.add(line);
            }
        }

        // Rewrite the file with updated content
        writeFileLines(updatedContent);
    }

}

