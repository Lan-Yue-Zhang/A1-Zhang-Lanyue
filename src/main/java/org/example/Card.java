package org.example;

import java.util.Objects;

public class Card {
    String type;
    String suit;
    int value;

    Card(String type, String suit, int value) {
        this.type = type;
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return (value == card.value && suit.equals(card.suit)) && type.equals(card.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit + type, value);
    }
}
