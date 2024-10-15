package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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

    @Test
    @DisplayName("check Game displays the id of each winner.\n")
    void RESP_04_test_01(){
        StringWriter output = new StringWriter();
        Main newGame = new Main();
        newGame.startGame();
        newGame.players.get(0).Set_shields(7);
        newGame.players.get(1).Set_shields(7);
        newGame.determineWinner(new PrintWriter(output));
        assertTrue(output.toString().contains("The winner is: P1"));
        assertTrue(output.toString().contains("The winner is: P2"));

    }
    @Test
    @DisplayName("check Game terminates \n")
    void RESP_04_test_02(){
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
        assertTrue(output.contains("Exiting game..."));
    }

    @Test
    @DisplayName("check displays player’s hand \n")
    void RESP_05_test_01(){
        StringWriter output = new StringWriter();
        Main newGame = new Main();
        newGame.startGame();
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.distributeallCards();
        newGame.Displaycard(newGame.players.get(0),new PrintWriter(output));
        assertTrue(output.toString().contains("F(5)  F(5)  F(15)  F(15)  D(5)  S(10)  S(10)  H(10)  H(10)  B(15)  B(15)  L(20)"));
    }
    @Test
    @DisplayName("check whose turn it is \n")
    void RESP_05_test_02(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        //p4
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 40));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.deck.add(new Card("W",  "E", 30));
        //p3
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        //p2
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 40));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "E", 30));
        //p1
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.distributeallCards();

        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("P1, please leave the hot seat. Press <return> to continue..."));
        assertTrue(output.contains("current player: 2"));
    }

    @Test
    @DisplayName("The player enters a valid position or name\n")
    void RESP_06_test_01(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.distributeallCards();
        newGame.playCard(new Scanner("L(20)\n"),newGame.players.get(0));
        System.setOut(originalOut);
        assertEquals(11, newGame.players.get(0).getHand().size());
    }

    @Test
    @DisplayName("The player enters non valid position or name\n")
    void RESP_06_test_02(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        newGame.distributeallCards();
        newGame.playCard(new Scanner("13\n"),newGame.players.get(0));
        System.setOut(originalOut);
        assertEquals(12, newGame.players.get(0).getHand().size());
    }

    @Test
    @DisplayName("The players draws some card and possibly trims their hand\n")
    void RESP_07_test_01(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        newGame.distributeallCards();
        newGame.distributeCards(newGame.players.get(0), 1);
        newGame.removeCards(new Scanner("13\n"), newGame.players.get(0));
        System.setOut(originalOut);
        assertEquals(12, newGame.players.get(0).getHand().size());
    }
    @Test
    @DisplayName("Plague: current player loses 2 shields\n")
    void RESP_08_test_01(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        StringWriter output = new StringWriter();
        newGame.distributeallCards();
        newGame.players.get(0).Set_shields(4);
        newGame.event_deck.add(new Card("E",  "Pl", 2));
        newGame.draws_event_card(new Scanner(""));
        assertEquals(2, newGame.players.get(0).Get_shields());
        newGame.players.get(0).Set_shields(0);
        newGame.event_deck.add(new Card("E",  "Pl", 2));
        newGame.draws_event_card(new Scanner(""));
        assertEquals(0, newGame.players.get(0).Get_shields());
    }

    @Test
    @DisplayName("Queen’s favor: current player draws 2 adventure cards \n")
    void RESP_08_test_02(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        StringWriter output = new StringWriter();
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.distributeallCards();
        newGame.event_deck.add(new Card("E",  "Qf", 4));

        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));

        newGame.draws_event_card(new Scanner("B(15)\nL(20)\n"));

        newGame.Displaycard(newGame.players.get(0),new PrintWriter(output));
        assertTrue(output.toString().contains("F(5)  F(5)  F(15)  F(15)  D(5)  S(10)  S(10)  H(10)  H(10)  B(15)  B(15)  L(20)"));
        System.setOut(originalOut);
        assertEquals(12, newGame.players.get(0).getHand().size());
    }

    @Test
    @DisplayName("Prosperity: All players draw 2 adventure cards\n")
    void RESP_08_test_03(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        StringWriter output = new StringWriter();

        for (int i = 0; i < 4;i++) {
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("W",  "D", 5));
            newGame.deck.add(new Card("W",  "S", 10));
            newGame.deck.add(new Card("W",  "S", 10));
            newGame.deck.add(new Card("W",  "H", 10));
            newGame.deck.add(new Card("W",  "H", 10));
            newGame.deck.add(new Card("W",  "B", 15));
            newGame.deck.add(new Card("W",  "B", 15));
            newGame.deck.add(new Card("W",  "L", 20));

        }
        newGame.distributeallCards();
        newGame.event_deck.add(new Card("E",  "Pr", 4));
        for (int i = 0; i < 4;i++) {
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "L", 20));
        }
        newGame.draws_event_card(new Scanner(" \nB(15)\nL(20)\n \nB(15)\nL(20)\n \nB(15)\nL(20)\n \nB(15)\nL(20)\n \n"));
        for (int i = 0; i < 4;i++) {
            newGame.Displaycard(newGame.players.get(i), new PrintWriter(output));
            assertTrue(output.toString().contains("F(5)  F(5)  F(15)  F(15)  D(5)  S(10)  S(10)  H(10)  H(10)  B(15)  B(15)  L(20)"));
            System.setOut(originalOut);
            assertEquals(12, newGame.players.get(i).getHand().size());
        }
    }
    @Test
    @DisplayName("All players decline to sponsor this quest\n")
    void RESP_09_test_01(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        StringWriter output = new StringWriter();
        newGame.distributeallCards();
        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \n \n \n \n \n \n \n"));
        assertEquals(100, newGame.sponsor);
    }
    @Test
    @DisplayName("A sponsor has been found\n")
    void RESP_09_test_02(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        StringWriter output = new StringWriter();
        for (int i = 0; i < 2;i++) {
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 40));
            newGame.deck.add(new Card("W", "D", 5));
            newGame.deck.add(new Card("W", "S", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "E", 30));
        }

        newGame.distributeallCards();
        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        assertEquals(1, newGame.sponsor);
    }

    @Test
    @DisplayName("no participants for this quest\n")
    void RESP_10_test_01(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        StringWriter output = new StringWriter();
        for (int i = 0; i < 2;i++) {
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 40));
            newGame.deck.add(new Card("W", "D", 5));
            newGame.deck.add(new Card("W", "S", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "E", 30));
        }

        newGame.distributeallCards();
        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        newGame.Get_Participants(new Scanner(" \n \n \n \n \n \n"));

        assertEquals(0, newGame.players_Participants.size());
    }
    @Test
    @DisplayName("A sponsor has been found\n")
    void RESP_10_test_02(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        StringWriter output = new StringWriter();
        for (int i = 0; i < 2;i++) {
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 40));
            newGame.deck.add(new Card("W", "D", 5));
            newGame.deck.add(new Card("W", "S", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "E", 30));
        }

        newGame.distributeallCards();
        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        for (int i = 0; i < 3;i++) newGame.deck.add(new Card("F",  "F", 5));
        newGame.Get_Participants(new Scanner("Y\nF(5)\n \nY\nF(5)\n \nY\nF(5)\n \n"));
        assertEquals(3, newGame.players_Participants.size());
    }
    @Test
    @DisplayName("The sponsor enters a necessarily valid position of a card\n")
    void RESP_11_test_01(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        StringWriter output = new StringWriter();
        for (int i = 0; i < 2;i++) {
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 40));
            newGame.deck.add(new Card("W", "D", 5));
            newGame.deck.add(new Card("W", "S", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "E", 30));
        }

        newGame.distributeallCards();
        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        assertEquals(15, newGame.stage[0]);
        assertEquals(25, newGame.stage[1]);
        assertEquals(35, newGame.stage[2]);
        assertEquals(55, newGame.stage[3]);

    }
    @Test
    @DisplayName(" ‘Quit’ is entered but the stage has no card associated with it,\n")
    void RESP_11_test_02(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        for (int i = 0; i < 2;i++) {
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 40));
            newGame.deck.add(new Card("W", "D", 5));
            newGame.deck.add(new Card("W", "S", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "E", 30));
        }

        newGame.distributeallCards();
        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nQuit\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("A stage cannot be empty"));

    }
    @Test
    @DisplayName("‘Quit’ is entered but the stage is insufficient value\n")
    void RESP_11_test_03(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        for (int i = 0; i < 2;i++) {
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 40));
            newGame.deck.add(new Card("W", "D", 5));
            newGame.deck.add(new Card("W", "S", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "E", 30));
        }

        newGame.distributeallCards();
        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(5)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Insufficient value for this stage"));

    }
    @Test
    @DisplayName("sole foe or non repeated weapon card\n")
    void RESP_11_test_04(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
        for (int i = 0; i < 2;i++) {
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 5));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 15));
            newGame.deck.add(new Card("F", "F", 40));
            newGame.deck.add(new Card("W", "D", 5));
            newGame.deck.add(new Card("W", "S", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "H", 10));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "B", 15));
            newGame.deck.add(new Card("W", "E", 30));
        }

        newGame.distributeallCards();
        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nH(10)\nF(5)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Each stage must consist of a single Foe card "));
        assertTrue(output.contains("non repeated weapon card "));
    }

    @Test
    @DisplayName("repeated weapon card\n")
    void RESP_12_test_01(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
//        StringWriter output = new StringWriter();
        //p4
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 40));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.deck.add(new Card("W",  "E", 30));
        //p3
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        //p2
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 40));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "E", 30));
        //p1
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.distributeallCards();

        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("F",  "F", 30));
        newGame.Get_Participants(new Scanner("Y\nF(5)\n \nY\nF(5)\n \nY\nF(5)\n \n"));
        newGame.start_stage(new Scanner("D(5)\nS(10)\nS(10)\nF(5)\nQuit\n \n"));
        newGame.start_stage(new Scanner("S(10)\nD(5)\nQuit\n \n"));
        newGame.start_stage(new Scanner("D(5)\nH(10)\nQuit\n \n"));

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Please enter the weapon card you have"));
        assertTrue(output.contains("non repeated weapon card "));
    }
    @Test
    @DisplayName("empty set of non repeated weapon cards\n")
    void RESP_12_test_02(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Main newGame = new Main();
        newGame.startGame();
//        StringWriter output = new StringWriter();
        //p4
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 40));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.deck.add(new Card("W",  "E", 30));
        //p3
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        //p2
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 40));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "E", 30));
        //p1
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 5));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("F", "F", 15));
        newGame.deck.add(new Card("W",  "D", 5));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "H", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.distributeallCards();

        newGame.event_deck.add(new Card("Q",  "Q", 4));
        newGame.draws_event_card(new Scanner(""));
        newGame.startQRound(new Scanner(" \n \nY\nF(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("F",  "F", 30));
        newGame.Get_Participants(new Scanner("Y\nF(5)\n \nY\nF(5)\n \nY\nF(5)\n \n"));
        newGame.start_stage(new Scanner("Quit\n \n"));
        newGame.start_stage(new Scanner("S(10)\nD(5)\nQuit\n \n"));
        newGame.start_stage(new Scanner("D(5)\nH(10)\nQuit\n \n"));

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("empty set of non repeated weapon cards "));
    }
}