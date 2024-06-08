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

public class LoginGame extends GameBase {

    //Constructor
    public LoginGame(String playerName, double playerBalance) {
        super(playerName, playerBalance);
    }

    //Initiate to play game for login game, overiding play from Gamebase for Polymorphism
    @Override
    public void play() {
        while (true) {
            if (player.getMoney() == 0) {
                break;
            }
            int bet = userInput.getValidBet(player.getMoney());

            if (bet == 0) break;
            try {
                player.bet(bet);
                userManager.updatePlayerBalance(player.getId(), player.getMoney());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            this.deck = new Deck();
            
            resetHands();
            displayInitialHands();

//            playerTurn();
            

            if (player.getHand().calculateTotal() <= 21) {
                dealerTurn();
            }

            determineOutcome(bet);

            System.out.println(player.getId() + ", your total money is now: " + player.getMoney());
        }
    }
}
