package com.fit2081.recyclercard;

public class Item {

    private String suit;
    private String card;

    public Item(String suit, String card) {
        this.suit = suit;
        this.card = card;
    }

    public String getSuit() {
        return suit;
    }

    public String getCard() {
        return card;
    }
}
