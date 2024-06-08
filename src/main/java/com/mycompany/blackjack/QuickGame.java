/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

import java.io.IOException;

/**
 *
 * @author farhaan
 */
/**
 *
 * @author farhaan
 */

//Subclass of GameBase
public class QuickGame extends GameBase {

    //Constructor
    public QuickGame(String playerName, double playerBalance) {
        //Calls constructor of GameBase
        super(playerName, playerBalance);
    }

    //Function to play quick game
    @Override
    public void play() {
            this.deck = new Deck();
            
            resetHands();
            displayInitialHands();
            playerTurn();

            if (player.getHand().calculateTotal() <= 21) {
                dealerTurn();
            }

            determineOutcome();
//            this.userInput.delay();
    }



}
