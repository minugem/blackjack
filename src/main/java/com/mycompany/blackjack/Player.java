/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author farhaan
 */
//Implements the GameParticipant interfance
public class Player implements GameParticipant {
    private String id;
    private double money;
    private Hand hand;

    //constructor
    public Player(String id, double money) {
        this.id = id;
        this.money = money;
        this.hand = new Hand();
    }

    //Returns player hand
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

    //Bet amount of money for this round
    public void bet(double amount) {
        if (amount > money) {
            throw new IllegalArgumentException("Bet amount cannot exceed player's money.");
        }
        money -= amount;
    }

    //Increase Money on win
    public void win(double amount) {
        money += amount;
    }
    
    //Returns id of player
    public String getId() {
        return id;
    }
    
    //Returns player money
    public double getMoney() {
        return money;
    }

    
    //Resets Player Hand
    @Override
    public void resetHand() {
        this.hand = new Hand();
    }

    //toString Method
    @Override
    public String toString() {
        return "Player " + id + " has " + money + " money and hand " + hand;
    }
    
}
