/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author farhaan
 */
import java.util.Scanner;

public class UserInput {
    private Scanner scanner;
    private String option;
    private String username;
    private String password;
    private String newPassword;

    //Constructor
    public UserInput() {
        this.scanner = new Scanner(System.in);
    }
    
    //Getter for player bet
   public int getBet() {
        int bet = -1; 
        while (true) {
            System.out.println("How much would you like to bet? (Enter 0 to quit)");
            try {
                bet = scanner.nextInt();
                scanner.nextLine(); 


                if (bet >= 0) {
                    return bet;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
    }
   
   //Getter for player valid bet
    public int getValidBet(double playerMoney) {
        int bet;
        do {
            bet = getBet();
            if (bet < 0) {
                System.out.println("You cannot bet a negative amount. Please enter a positive amount to bet, or 0 to quit.");
            } else if (bet > playerMoney) {
                System.out.println("You cannot bet more than your current balance. Please enter a valid amount to bet, or 0 to quit.");
                bet = -1; 
            }
        } while (bet < 0);
        return bet;
    }

    //Getter for player decision
    public String getPlayerDecision() {
        System.out.println("Do you want to (h)it or (s)tand?");
        return scanner.nextLine().trim();
    }
    
    //Setter for username
    public void setUsername() {
        System.out.print("Enter username: ");
        username = scanner.nextLine();
    }
    
    //Getter for username
    public String getUsername() {
        return username;
    }
    
    //Setter for password
    public void setPassword() {
        System.out.print("Enter password: ");
        password = scanner.nextLine();
    }
    
    //Getter for password
    public String getPassword() {
        return password;
    }
    
    //Setter for new password
    public void setNewPassword() {
        System.out.print("Enter new password: ");
        newPassword = scanner.nextLine();
    }
    
    //Getter for new password
    public String getNewPassword() {
        return newPassword;
    }
    
    //Setter for menu options
    public void setOption() {
        option = scanner.nextLine();
    }
    
    //Getter for menu options
    public String getOption() {
        return option;
    }
    
    //String and input to delay screen display
//    public void delay() {
//        System.out.print("Enter to continue:");
//        String delay = scanner.nextLine();
//    }
    
}
