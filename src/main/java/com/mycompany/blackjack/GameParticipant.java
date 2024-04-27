/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author jason
 */
public interface GameParticipant {
    //Draw a card from deck
    void draw(Deck deck);
    //Display cards in hand
    Hand getHand();
    //Reset hand to have no cards
    void resetHand();
    //Display cards in string format
    @Override
    String toString();
}