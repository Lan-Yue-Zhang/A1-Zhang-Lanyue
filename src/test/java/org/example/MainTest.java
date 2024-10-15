package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    @Test
    @DisplayName("check Game distributes 12 cards from the adventure deck to each player\n")
    void RESP_02_test_01(){
        Main newGame = new Main();
        newGame.add_all_players();
        newGame.initializeDeck();
        newGame.initialize_event_Deck();
        newGame.shuffleDeck(newGame.deck);
        newGame.shuffleDeck(newGame.event_deck);
        newGame.distributeallCards();
        for (int j = 0; j < newGame.players.size(); j++) {
            assertEquals(12, newGame.players.get(j).getHand().size());
        }
    }
    @Test
    @DisplayName("check updating the deck\n")
    void RESP_02_test_02(){
        Main newGame = new Main();
        newGame.add_all_players();
        newGame.initializeDeck();
        newGame.initialize_event_Deck();
        newGame.shuffleDeck(newGame.deck);
        newGame.shuffleDeck(newGame.event_deck);
        newGame.distributeallCards();
        assertEquals((100-12*4), newGame.deck.size());
    }

    @Test
    @DisplayName("check discard pile is reused as the deck.\n")
    void RESP_02_test_03(){
        Main newGame = new Main();
        newGame.startGame();
        newGame.distributeCards(newGame.players.get(0),newGame.deck.size());
        assertEquals(0, newGame.deck.size());
        assertEquals(0, newGame.playedCards.size());
        while (!newGame.players.get(0).getHand().isEmpty()) {
            newGame.playCard(new Scanner("1"),newGame.players.get(0));
        }
        assertEquals(0, newGame.deck.size());
        assertEquals(100, newGame.playedCards.size());
        newGame.distributeCards(newGame.players.get(0),1);
        assertEquals(99, newGame.deck.size());
        assertEquals(0, newGame.playedCards.size());

    }
    @Test
    @DisplayName("if one or more players have 7 shield.\n")
    void RESP_03_test_01(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        newGame.players.get(0).Set_shields(7);
        newGame.distributeallCards();
        newGame.startRound(new Scanner(""));
        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("The winner is: P1"));
    }
}