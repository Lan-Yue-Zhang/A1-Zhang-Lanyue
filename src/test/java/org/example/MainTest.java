package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
@Test
@DisplayName("Check the size of adventure and event decks")
void RESP_01_test_01(){
    Main newGame = new Main();
    newGame.initializeDeck();
    newGame.initialize_event_Deck();
    assertEquals(100, newGame.deck.size());
    assertEquals(17, newGame.event_deck.size());
}
    @Test
    @DisplayName("Check no duplicate card in adventure and event decks")
    void RESP_01_test_02(){
        Main newGame = new Main();
        newGame.initializeDeck();
        newGame.initialize_event_Deck();
        List<Card> deck = new ArrayList<>();
        boolean duplicate = false;
        for (int i = 0; i <newGame.event_deck.size(); i++) {
            Card newcard = newGame.event_deck.remove(0);
            for (int j = 0; j < deck.size(); j++) {
                if (newcard == deck.get(j)) duplicate = true;
            }
            deck.add(newcard);
        }
        assertFalse(duplicate);
        deck.clear();
        duplicate = false;
        for (int i = 0; i <newGame.deck.size(); i++) {
            Card newcard = newGame.deck.remove(0);
            for (int j = 0; j < deck.size(); j++) {
                if (newcard == deck.get(j)) duplicate = true;
            }
            deck.add(newcard);
        }
        assertFalse(duplicate);

    }
    @Test
    @DisplayName("check Shuffle the deck of cards\n")
    void RESP_01_test_03(){
        Main newGame = new Main();
        Main temp = new Main();
        newGame.initializeDeck();
        newGame.initialize_event_Deck();
        temp.initializeDeck();
        temp.initialize_event_Deck();
        newGame.shuffleDeck(newGame.deck);
        newGame.shuffleDeck(newGame.event_deck);
        assertNotSame(temp.deck, newGame.deck);
        assertNotSame(temp.event_deck, newGame.event_deck);
    }
}