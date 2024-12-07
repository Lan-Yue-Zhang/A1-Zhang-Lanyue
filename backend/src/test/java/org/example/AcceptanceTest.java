package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class AcceptanceTest {
    @Test
    @DisplayName("A-TEST JP-Scenario")
    void ATEST_001(){
        Main newGame = new Main();
        StringWriter output = new StringWriter();
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
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("F",  "F", 30));
        newGame.Get_Participants(new Scanner("Y\nF(5)\n \nY\nF(5)\n \nY\nF(5)\n \n"));
        newGame.start_stage(new Scanner("D(5)\nS(10)\nQuit\n \n"));
        newGame.start_stage(new Scanner("S(10)\nD(5)\nQuit\n \n"));
        newGame.start_stage(new Scanner("D(5)\nH(10)\nQuit\n \n"));

        newGame.deck.add(new Card("W",  "L", 20));
        newGame.deck.add(new Card("W",  "L", 20));
        newGame.deck.add(new Card("F",  "F", 10));

        newGame.Get_Participants(new Scanner("Y\n \nY\n \nY\n \n"));
        newGame.start_stage(new Scanner("H(10)\nS(10)\nQuit\n \n"));
        newGame.start_stage(new Scanner("B(15)\nS(10)\nQuit\n \n"));
        newGame.start_stage(new Scanner("H(10)\nB(15)\nQuit\n \n"));
        assertEquals(0,newGame.players.get(0).Get_shields());
        newGame.Displaycard(newGame.players.get(0),new PrintWriter(output));
        assertTrue(output.toString().contains("F(5)  F(10)  F(15)  F(15)  F(30)  H(10)  B(15)  B(15)  L(20)"));

        newGame.deck.add(new Card("W",  "S", 10));
        newGame.deck.add(new Card("W",  "B", 15));
        newGame.Get_Participants(new Scanner("Y\n \nY\n \n"));
        newGame.start_stage(new Scanner("L(20)\nH(10)\nS(10)\nQuit\n \n"));
        newGame.start_stage(new Scanner("B(15)\nS(10)\nL(20)\nQuit\n \n"));

        newGame.deck.add(new Card("W",  "L", 20));
        newGame.deck.add(new Card("F",  "F", 30));
        newGame.Get_Participants(new Scanner("Y\n \nY\n \n"));
        newGame.start_stage(new Scanner("B(15)\nH(10)\nL(20)\nQuit\n \n"));
        newGame.start_stage(new Scanner("D(5)\nS(10)\nL(20)\nE(30)\nQuit\n \n"));

        assertEquals(0,newGame.players.get(2).Get_shields());
        newGame.Displaycard(newGame.players.get(2),new PrintWriter(output));
        assertTrue(output.toString().contains("F(5)  F(5)  F(15)  F(30)  S(10)"));

        assertEquals(4,newGame.players.get(3).Get_shields());
        newGame.Displaycard(newGame.players.get(3),new PrintWriter(output));
        assertTrue(output.toString().contains("F(15)  F(15)  F(40)  L(20)"));

        newGame.endQRound(new Scanner("1\n1\n1\n1\n \n"));
        assertEquals(12,newGame.players.get(1).getHand().size());
    }
}