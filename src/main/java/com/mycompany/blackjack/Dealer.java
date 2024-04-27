/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author jason
 */

//Implements the GameParticipant interfance
public class Dealer implements GameParticipant {
    private Hand hand;

    //Constructor
    public Dealer() {
        this.hand = new Hand();
    }

    //Returns Hand
    @Override
    public Hand getHand() {
        return hand;
    }

    //Gives a card from the deck to the dealer
    @Override
    public void draw(Deck deck) {
        Card card = deck.deal();
        hand.addCard(card);
    }

    // Implementing dealer strategy as per original design
    // If dealers card value is less than 17 they must keep using draw method
    public boolean play(Deck deck) {
        if (hand.calculateTotal() < 17) {
            hand.addCard(deck.deal());
            return true;
        }
        return false;
    }

    //Clears the cards in Dealer hand
    @Override
    public void resetHand() {
        this.hand = new Hand();
    }

    //toString method
    @Override
    public String toString() {
        return "Dealer's hand: " + hand;
    }
}
