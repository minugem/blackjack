/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author jason
 */
//GITHUB TEST
public class BlackJack {

    private UserManager userManager;
    private UserInput userInput;

    //Constructor
    public BlackJack() {
        this.userManager = new UserManager("users.txt");
        this.userInput = new UserInput();
    }

    //Display menu options
    public void displayMenu() {
        System.out.println();
        System.out.println("==============================");
        System.out.println("Welcome to the Blackjack CLI!");
        System.out.println("==============================");
        System.out.println();
        System.out.println("1. Quick Game");
        System.out.println("2. Login Game");
        System.out.println("3. Tutorial");
        System.out.println("4. Scoreboard");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    //Handle the user input option
    public void handleUserInput() {
        boolean running = true;
        while (running) {
            displayMenu();
            this.userInput.setOption();
            switch (this.userInput.getOption()) {
                case "1":
                    QuickGame quickGame = new QuickGame("Quickplay User",99999);
                    quickGame.play();
                    break;
                case "2":
                    loginGame();
                    break;
                case "3":
                    showTutorial();
                    break;
                case "4":
                    userManager.displayScoreboard();
                    this.userInput.delay();
                    break;
                case "5":
                    System.out.println("Exiting game. Thank you for playing!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    //Login game options
    private void loginGame() {
        System.out.println();
        System.out.println("1. Log in to your account");
        System.out.println("2. Create a new account");
        System.out.println("3. Change password");
        System.out.print("Select an option: ");
        this.userInput.setOption();

        if (this.userInput.getOption().equals("1")) {
            System.out.println();
            this.userInput.setUsername();
            this.userInput.setPassword();

            boolean isAuthenticated = userManager.loginPlayer(this.userInput.getUsername(), this.userInput.getPassword());
            if (isAuthenticated) {
                startGameWithUser(this.userInput.getUsername());
            }
        } else if (this.userInput.getOption().equals("2")) {
            System.out.println();
            this.userInput.setUsername();

            if (!userManager.userExist(this.userInput.getUsername())) {
                this.userInput.setPassword();
                double startingBalance = 1000.0;
                userManager.createPlayer(this.userInput.getUsername(), this.userInput.getPassword(), startingBalance);
                startGameWithUser(this.userInput.getUsername()); 
            } else {

                System.out.println("User already exists.");
                return;
            }

        } else if (this.userInput.getOption().equals("3")) {
            System.out.println();
            this.userInput.setUsername();
            this.userInput.setPassword();

            boolean isAuthenticated = userManager.loginPlayer(this.userInput.getUsername(), this.userInput.getPassword());
            if (isAuthenticated) {
                this.userInput.setNewPassword(); 
                userManager.resetUserPassword(this.userInput.getUsername(), this.userInput.getNewPassword()); 
            } else {
                System.out.println("Login failed. Please check your credentials and try again.");
            }
        } else {
            System.out.println("Invalid option, please try again.");
        }

    }
    
    //Login game with user details entered
    private void startGameWithUser(String username) {
        System.out.println();
        System.out.println("Welcome " + username + "!");
        double balance = userManager.getPlayerBalance(username);
        System.out.println("current balance: " + balance);
        LoginGame game = new LoginGame(username, balance);
        game.play();
    }

    //Show tutorial for game
    private void showTutorial() {
        System.out.println();
        System.out.println("================================");
        System.out.println("       Blackjack Tutorial       ");
        System.out.println("================================");
        System.out.println("• The goal of Blackjack is to beat the dealer's hand without going over 21.");
        System.out.println("• Each player starts with two cards, one of the dealer's cards is hidden until the end.");
        System.out.println("• To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.");
        System.out.println("• If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.");
        System.out.println("• The dealer will hit until his/her cards total 17 or higher.");
        System.out.println("• Cards 2 through 10 are worth their face value. Kings, Queens, and Jacks are worth 10.");
        System.out.println("• Aces are worth 1 or 11, whichever makes a better hand.");
        System.out.println("• You win by having a higher score than the dealer without busting, or by the dealer busting.");
        System.out.println("• Good luck, and have fun!");
        System.out.println("================================");
        this.userInput.delay();
    }

    //Run game
    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.handleUserInput();
    }
}
