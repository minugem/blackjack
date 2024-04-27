/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author farhaan
 */
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;

    //Constructor
    public Hand() {
        cards = new ArrayList<>();
    }

    //Adds a card to hand
    public void addCard(Card card) {
        cards.add(card);
    }

    //Removes cards from hand
    public void clear() {
        cards.clear();
    }

    //Returns current cards
    public List<Card> getCards() {
        return cards;
    }

    //Returns the value of the first card
    public int firstTotal() {
        return cards.get(0).getValue();
    }
    
    //Returns the value of whole hand
    public int calculateTotal() {
        int total = 0;
        int numberOfAces = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.getRank().equals("A")) {
                numberOfAces++;
            }
        }

        // Adjust total if it's over 21 and contains Aces
        while (total > 21 && numberOfAces > 0) {
            total -= 10;
            numberOfAces--; 
        }

        return total;
    }

    //toString Method
    @Override
    public String toString() {
        return cards.toString();
    }
}
