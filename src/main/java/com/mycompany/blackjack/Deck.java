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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    //Constructor
    public Deck() {
        cards = new ArrayList<>();
        List<String> suits = Arrays.asList("Spades", "Hearts", "Diamonds", "Clubs");
        List<String> ranks = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    //Shuffles Deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    //Removes a card from the top of the deck
    public Card deal() {
        return cards.remove(cards.size() - 1);
    }

    //Returns amount of cards left in the deck
    public int size() {
        return cards.size();
    }

}
