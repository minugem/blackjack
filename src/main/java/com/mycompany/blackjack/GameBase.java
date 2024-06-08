/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

import java.io.IOException;

/**
 *
 * @author jason
 */
public abstract class GameBase {
    protected FileIO fileIO;
    protected Deck deck;
    protected Player player;
    protected Dealer dealer;
    protected UserManager userManager;
    protected UserInput userInput;

    //Constructor
    public GameBase(String playerName, double playerBalance) {
        this.deck = new Deck(); 
        this.player = new Player(playerName, playerBalance);
        this.dealer = new Dealer();
        this.userInput = new UserInput();
        this.fileIO = new FileIO("users.txt");
        this.userManager = new UserManager("users.txt");
    }

    //Reset cards in hand
    protected void resetHands() {
        deck.shuffle();
        player.getHand().clear();
        dealer.getHand().clear();
        player.draw(deck);
        player.draw(deck);
        dealer.draw(deck);
    }

    //Display initial cards not revealing dealers hand
    protected void displayInitialHands() {
        System.out.println("\nDealer's hand: [" + dealer.getHand().getCards().get(0) + ", ?]");  
        System.out.println("Dealer current total: " + dealer.getHand().calculateTotal());
        System.out.println("\nYour hand: " + player.getHand());
        System.out.println("Player total: " + player.getHand().calculateTotal() + "\n");
    }
    
    //Inititate players turn
    protected void playerTurn() {
        boolean playerStands = false;
        while (!playerStands && player.getHand().calculateTotal() < 21) {
            String decision = userInput.getPlayerDecision();
            if ("h".equalsIgnoreCase(decision)) {
                player.draw(deck);
                System.out.println("\nYour hand: " + player.getHand() + "\nPlayer total: " + player.getHand().calculateTotal());
            } else if ("s".equalsIgnoreCase(decision)) {
                playerStands = true;
            }
        }
    }
    
    //Inititiate dealers turn
    protected void dealerTurn() {
        while (dealer.getHand().calculateTotal() < 17) {
            System.out.println("\nDealer's hand: " + dealer.getHand() + "\nDealer total: " + dealer.getHand().calculateTotal());
            dealer.draw(deck);
//            userInput.delay(); 
        }
        System.out.println("Dealer's final hand: " + dealer.getHand() + "\nDealer total: " + dealer.getHand().calculateTotal());
    }
    
    //Determine outcome of round and initiate to update player data
    protected void determineOutcome(int bet) {
        int playerTotal = player.getHand().calculateTotal();
        int dealerTotal = dealer.getHand().calculateTotal();
        if (playerTotal > 21 || (dealerTotal <= 21 && dealerTotal > playerTotal)) {
            handleLoss();
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            handleWin(bet);
        } else {
            handleTie(bet);
        }
    }
    
    //Determine outcome of round 
    protected void determineOutcome() {
        int playerTotal = player.getHand().calculateTotal();
        int dealerTotal = dealer.getHand().calculateTotal();
        if (playerTotal > 21 || (dealerTotal <= 21 && dealerTotal > playerTotal)) {
            handleLoss();
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            handleWin();
        } else {
            handleTie();
        }
    }

    //Handle loss
    protected void handleLoss() {
        System.out.println("\nXXX Dealer wins! XXX\n");
        if (player.getMoney() == 0) {
            try {
                fileIO.deletePlayerAccount(player.getId());
                System.out.println("Player account deleted due to zero balance.");
                
                
            } catch (IOException e) {
                System.out.println("Error deleting player account: " + e.getMessage());
            }
        }
    }

    //Handle win and update player data
    protected void handleWin(int bet) {
        System.out.println("\n$$$ Player wins! $$$\n");
        player.win(bet * 2); // Update player object
        userManager.updatePlayerBalance(player.getId(), player.getMoney());
    }

    //Handle tie and update player data
    protected void handleTie(int bet) {
        System.out.println();
        System.out.println("--- It's a tie! ---");
        System.out.println();
        player.win(bet); 
        userManager.updatePlayerBalance(player.getId(), player.getMoney()); 
    }
    
    //Handle win
    protected void handleWin() {
        System.out.println("\n$$$ Player wins! $$$\n");
    }

    //Handle tie
    protected void handleTie() {
        System.out.println();
        System.out.println("--- It's a tie! ---");
        System.out.println();
    }
    
    public abstract void play();
}