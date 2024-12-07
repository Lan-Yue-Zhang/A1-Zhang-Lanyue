package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    int id;
    int shields;
    List<Card> hand;
    List<Card> AttackValueDeck;
    boolean eligible;

    Player(int shields, int id) {
        this.shields = shields;
        this.id = id;
        this.hand = new ArrayList<>();
        this.AttackValueDeck = new ArrayList<>();
        this.eligible = true;
    }
    public Player(Player other) {
        this.shields = other.shields;
        this.id = other.id;
        this.hand = new ArrayList<>(other.hand);
        this.AttackValueDeck = new ArrayList<>(other.AttackValueDeck);
        this.eligible = other.eligible;
    }

    int Get_shields() {
        return shields;
    }

    int Get_id() {
        return id;
    }

    void Set_shields(int HP) {
        shields = HP;
    }

    void Set_eligible(boolean eligible) {
        this.eligible = eligible;
    }

    boolean Get_eligible() {
        return eligible;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public Card playedCard(String suit, int value) {
        int cardToPlay;
        for (int i = 0; i < hand.size(); i++) {
            if (suit.equals(hand.get(i).getSuit()) && hand.get(i).getValue() == value) {
                cardToPlay = i;
                return hand.remove(cardToPlay);
            }
        }
        return null;
    }

    public List<Card> getAttackValueDeck() {
        return AttackValueDeck;
    }

    public void addToAttackValueDeck(Card cards) {
        AttackValueDeck.add(cards);
    }

    public void cleanAttackValueDeck() {
        AttackValueDeck.clear();
    }

    public int calculateAttackValue() {
        int points = 0;
        for (Card card : AttackValueDeck) {
            points += card.getValue();
        }
        return points;
    }


}
