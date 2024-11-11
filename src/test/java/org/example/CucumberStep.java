package org.example;

import io.cucumber.java.en.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import static org.junit.Assert.*;


public class CucumberStep {

    private Main newGame;
    private List<Player> players;
    private List<Player> players_Participants;

    @Given("Start game and decks are created")
    public void start_game_for_A1() {
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            cards.add( new Card("F", "F", 10));
        }
        cards.add( new Card("F", "F", 15));
        for (int i = 1; i <= 7; i++) {
            cards.add( new Card("F", "F", 20));
        }
        for (int i = 1; i <= 7; i++) {
            cards.add( new Card("F", "F", 25));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add( new Card("F", "F", 30));
        }
        for (int i = 1; i <= 4; i++) {
            cards.add( new Card("F", "F", 35));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add( new Card("F", "F", 50));
        }
        cards.add( new Card("F", "F", 70));
        // Create basic weapon cards: Swords, battle-axes, lances, horses, excaliburs, and daggers

        cards.add( new Card("W", "D", 5));

        for (int i = 1; i <= 7; i++) {
            // Swords (S)
            cards.add( new Card("W", "S", 10));
        }
        for (int i = 1; i <= 4; i++) {
            // Horses (H)
            cards.add( new Card("W", "H", 10));
        }

        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("F",  "F", 30));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "B", 15));

        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("F",  "F", 10));

        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("F",  "F", 30));

        //p4
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 15));

        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 40));
        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("W",  "E", 30));
        //p3
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));

        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));
        //p2
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));

        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 40));
        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "E", 30));
        //p1
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));

        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));
        
        List<Card> event_cards = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            if (i == 1) event_cards.add( new Card("E", "Pl", 2));
            else if (i <= 1 + 2) event_cards.add( new Card("E", "Qf", 2));
            else event_cards.add( new Card("E", "Pr", 2));
        }
        // Create quest (Q) cards
        for (int i = 1; i <= 12 - 1; i++) {
            if (i <= 3) event_cards.add( new Card("Q", "Q", 2));
            else if (i <= 3 + 4) event_cards.add( new Card("Q", "Q", 3));
            else if (i <= 3 + 4 + 3 - 1) event_cards.add( new Card("Q", "Q", 4));
            else event_cards.add( new Card("Q", "Q", 5));
        }
        event_cards.add(new Card("Q",  "Q", 4));
        newGame = new Main();
        newGame.startGame();
        newGame.deck = cards;
        newGame.event_deck = event_cards;
        assertEquals(100, newGame.deck.size());
        assertEquals(17, newGame.event_deck.size());
        newGame.distributeallCards();
        players = new ArrayList<>();
        players_Participants = new ArrayList<>();
        for (Player participant : newGame.players) {
            players.add(new Player(participant));
        }
    }

    @Given("A new game is started for 2winner")
    public void start_game_for_2winner() {
        List<Card> cards = new ArrayList<>();

        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));
        for (int i = 1; i <= 6 -1 -1 -1; i++) {
            cards.add( new Card("F", "F", 10));
        }

        cards.add( new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));

        for (int i = 1; i <= 7 -1 -1; i++) {
            cards.add( new Card("F", "F", 20));
        }
        for (int i = 1; i <= 7 -1; i++) {
            cards.add( new Card("F", "F", 25));
        }

        cards.add( new Card("F", "F", 70));
        // Create basic weapon cards: Swords, battle-axes, lances, horses, excaliburs, and daggers

        cards.add( new Card("W", "D", 5));

        for (int i = 1; i <= 7 -2 -2; i++) {
            // Swords (S)
            cards.add( new Card("W", "S", 10));
        }
        for (int i = 1; i <= 4 -2; i++) {
            // Horses (H)
            cards.add( new Card("W", "H", 10));
        }
        for (int i = 1; i <= 2; i++) {
            // Horses (H)
            cards.add(new Card("W",  "E", 30));
        }
        cards.add(new Card("W",  "L", 20));

//        for Q3
        cards.add( new Card("W", "S", 10));
        cards.add( new Card("W", "S", 10));

        cards.add( new Card("W", "S", 10));
        cards.add( new Card("W", "S", 10));

        cards.add( new Card("W", "H", 10));
        cards.add( new Card("W", "H", 10));

//        for P1 draws 8 random cards

        for (int i = 1; i <= 2; i++) {
            cards.add( new Card("F", "F", 30));
        }
        for (int i = 1; i <= 4; i++) {
            cards.add( new Card("F", "F", 35));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add( new Card("F", "F", 50));
        }

//        for Q4
        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("F",  "F", 30));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "B", 15));


        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("F",  "F", 10));

        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("F",  "F", 30));

        //p4
        cards.add(new Card("F", "F", 5));
        cards.add( new Card("F", "F", 10));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 40));

        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "D", 5));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));


        //p3
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 10));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 20));

        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));

        //p2
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));


        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));

        //p1
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 10));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 20));
        cards.add(new Card("F", "F", 25));
        cards.add(new Card("F", "F", 40));

        cards.add(new Card("W",  "D", 5));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "B", 15));


        List<Card> event_cards = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            if (i == 1) event_cards.add( new Card("E", "Pl", 2));
            else if (i <= 1 + 2) event_cards.add( new Card("E", "Qf", 2));
            else event_cards.add( new Card("E", "Pr", 2));
        }
        // Create quest (Q) cards
        for (int i = 1; i <= 12 - 1 - 1; i++) {
            if (i <= 3) event_cards.add( new Card("Q", "Q", 2));
            else if (i <= 3 + 4 - 1) event_cards.add( new Card("Q", "Q", 3));
            else if (i <= 3 + 4 + 3 - 1) event_cards.add( new Card("Q", "Q", 4));
            else event_cards.add( new Card("Q", "Q", 5));
        }
        event_cards.add(new Card("Q",  "Q", 3));
        event_cards.add(new Card("Q",  "Q", 4));
        newGame = new Main();
        newGame.startGame();
        newGame.deck = cards;
        newGame.event_deck = event_cards;
        assertEquals(100, newGame.deck.size());
        assertEquals(17, newGame.event_deck.size());
        newGame.distributeallCards();
        players = new ArrayList<>();
        players_Participants = new ArrayList<>();
        for (Player participant : newGame.players) {
            players.add(new Player(participant));
        }
    }

    @Given("A new game is started for 1winner")
    public void start_game_for_1winner() {
        List<Card> cards = new ArrayList<>();




        for (int i = 1; i <= 6 -1 -1 -1 -1; i++) {
            cards.add( new Card("F", "F", 10));
        }

        cards.add(new Card("F", "F", 15));

        for (int i = 1; i <= 7 -1 -1; i++) {
            cards.add( new Card("F", "F", 20));
        }
        for (int i = 1; i <= 7 -1; i++) {
            cards.add( new Card("F", "F", 25));
        }
        for (int i = 1; i <= 2 -1; i++) {
            cards.add( new Card("F", "F", 30));
        }

//        for Q3
        cards.add( new Card("W", "S", 10));
        cards.add( new Card("W", "S", 10));

        cards.add( new Card("W", "S", 10));
        cards.add( new Card("W", "S", 10));

        cards.add( new Card("W", "H", 10));
        cards.add( new Card("F", "F", 5));
        cards.add( new Card("W", "H", 10));

//        Queen’s favor
        //p4
        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "B", 15));

//        Prosperity
        //p4
        for (int i = 1; i <= 2; i++) {
            // Horses (H)
            cards.add(new Card("W",  "E", 30));
        }
        //p3
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "B", 15));
        //p2
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        //p1
        for (int i = 1; i <= 2; i++) {
            cards.add( new Card("F", "F", 50));
        }

//        for P1 draws 8 random cards
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 10));
        cards.add(new Card("F", "F", 15));
        for (int i = 1; i <= 7 -2 -2; i++) {
            // Swords (S)
            cards.add( new Card("W", "S", 10));
        }
        for (int i = 1; i <= 4 -2; i++) {
            // Horses (H)
            cards.add( new Card("W", "H", 10));
        }

//        for Q4
        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F",  "F", 30));


        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W", "D", 5));
        cards.add(new Card("W",  "B", 15));


        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("F",  "F", 10));

        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("F",  "F", 30));

        //p4
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 10));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 40));

        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));


        //p3
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 10));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 20));

        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));

        //p2
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));


        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));

        //p1
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 10));
        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 20));
        cards.add(new Card("F", "F", 25));

        cards.add( new Card("F", "F", 30));

        for (int i = 1; i <= 4; i++) {
            cards.add( new Card("F", "F", 35));
        }
        cards.add(new Card("F", "F", 40));
        cards.add( new Card("F", "F", 70));

        List<Card> event_cards = new ArrayList<>();

        event_cards.add( new Card("E", "Qf", 2));
        event_cards.add( new Card("E", "Pr", 2));

        // Create quest (Q) cards
        for (int i = 1; i <= 12 - 1 - 1; i++) {
            if (i <= 3) event_cards.add( new Card("Q", "Q", 2));
            else if (i <= 3 + 4 - 1) event_cards.add( new Card("Q", "Q", 3));
            else if (i <= 3 + 4 + 3 - 1) event_cards.add( new Card("Q", "Q", 4));
            else event_cards.add( new Card("Q", "Q", 5));
        }
        event_cards.add(new Card("Q",  "Q", 3));
        event_cards.add( new Card("E", "Qf", 2));
        event_cards.add( new Card("E", "Pr", 2));
        event_cards.add( new Card("E", "Pl", 2));
        event_cards.add(new Card("Q",  "Q", 4));
        newGame = new Main();
        newGame.startGame();
        newGame.deck = cards;
        newGame.event_deck = event_cards;
        assertEquals(100, newGame.deck.size());
        assertEquals(17, newGame.event_deck.size());
        newGame.distributeallCards();
        players = new ArrayList<>();
        players_Participants = new ArrayList<>();
        for (Player participant : newGame.players) {
            players.add(new Player(participant));
        }
    }

    @When("P{int} draws a quest of {int} stages")
    public void p_draws_a_quest_of_stages(int arg0,  int arg1) {
        newGame.draws_event_card(new Scanner(""));
        assertEquals(arg0 -1, newGame.current_player);
        assertEquals(newGame.played_eventCards.getLast(), new Card("Q", "Q", arg1));

    }

    @Then("P{int} is declines to sponsor")
    public void pIsDeclinesToSponsor(int arg0) {
        newGame.Get_sponsor_ans(new Scanner(" \n \n"));
        assertEquals(100,newGame.sponsor);
    }

    @And("P2 is sponsors and builds the 4 stages for A1 scenario")
    public void IsSponsorsAndBuildsTheStages() {
        newGame.Get_sponsor_ans(new Scanner("Y\n"));
        assertEquals(1,newGame.sponsor);
        newGame.set_stage(new Scanner("F(5)\nH(10)\nQuit\nF(15)\nS(10)\nQuit\nF(15)\nD(5)\nB(15)\nQuit\nF(40)\nB(15)\nQuit\n \n"));
        assertArrayEquals(new int[] {15, 25, 35, 55}, newGame.stage);

    }


    @Then("P{int} is decides to participate and draws {string} discards {string}")
    public void pIsDecidesToParticipateAndDrawsDiscards(int arg0, String arg1, String arg2) {
        newGame.Get_Participants_ans(new Scanner(String.format("Y\n%s\n \n", arg2)));
        assertEquals(12,newGame.players.get(arg0-1).getHand().size());

        StringWriter output = new StringWriter();
        newGame.Displaycard(newGame.players.get(arg0 -1),new PrintWriter(output));
        assertTrue(output.toString().contains(arg1));

        boolean flag = false;
        for (int i =0; i<newGame.players_Participants.size();i++){
            if (newGame.players_Participants.get(i).Get_id() == arg0) {
                flag = true;
            }
        }
        assertTrue(flag);

        players_Participants.clear();
        for (Player participant : newGame.players_Participants) {
            players_Participants.add(new Player(participant));
        }
    }

    @Then("P{int} draws {string} value of {int}")
    public void pDrawsValueOf(int arg0, String arg1, int arg2) {
        assertEquals(arg0 - 1, newGame.current_player);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        String[] parts = arg1.split(" ");
        StringBuilder inputBuilder = new StringBuilder();
        for (String part : parts) {
            inputBuilder.append(part).append("\n");
        }

        inputBuilder.append("Quit\n \n");
        newGame.start_stage(new Scanner(inputBuilder.toString()));

        System.setOut(originalOut);
        String output = outputStream.toString().trim();
        assertTrue(output.contains(("The value of the cards used in this stage is " + arg2 + " : " + arg1).trim()));
    }



    @And("{string} participants can go onto the next stage")
    public void pPPParticipantsCanGoOntoTheNextStage(String arg0) {
        String[] parts = arg0.split(" ");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i].replaceAll("\\D", ""));
            assertEquals(numbers[i],newGame.players_Participants.get(i).Get_id());
        }
        assertEquals(parts.length, newGame.players_Participants.size());
    }

    @And("{int} participants discard the cards")
    public void participantsDiscardTheCards(int arg0) {
        int num =0;
        for (int j = 0; j < newGame.players.size(); j++) {
            for (int i = 0; i < players_Participants.size(); i++) {
                if (players_Participants.get(i).Get_id() == newGame.players.get(j).Get_id()) {
                    if (newGame.players.get(j).getHand().size() < players_Participants.get(i).getHand().size()) num++;
                }
            }
        }
        assertEquals(arg0, num);
    }

    @Then("P{int} is decides to participate and draws {string}")
    public void pIsDecidesToParticipateAndDraws(int arg0, String arg1) {
        assertEquals(arg0 -1, newGame.current_player);
        Player test = new Player(newGame.players.get(arg0-1));

        StringWriter output = new StringWriter();
        newGame.Get_Participants_ans(new Scanner("Y\n \n"));
        newGame.Displaycard(newGame.players.get(arg0 -1),new PrintWriter(output));
        assertTrue(output.toString().contains(arg1));

        boolean flag = false;
        for (int i =0; i<newGame.players_Participants.size();i++){
            if (newGame.players_Participants.get(i).Get_id() == arg0) {
                flag = true;
            }
        }
        assertTrue(flag);

        assertTrue(test.getHand().size() < newGame.players.get(arg0 -1).getHand().size());
        players_Participants.clear();
        for (Player participant : newGame.players_Participants) {
            players_Participants.add(new Player(participant));
        }
    }


    @And("P1 loses and cannot go to the next stage")
    public void pLosesAndCannotGoToTheNextStage() {
        StringWriter output = new StringWriter();
        assertEquals(0,newGame.players.getFirst().Get_shields());
        newGame.Displaycard(newGame.players.getFirst(),new PrintWriter(output));
        assertTrue(output.toString().contains("F(5)  F(10)  F(15)  F(15)  F(30)  H(10)  B(15)  B(15)  L(20)"));
        assertEquals(9,newGame.players.get(0).getHand().size());
        int num =0;
        for (int i =0; i<newGame.players.size();i++){
            num += newGame.players.get(i).getHand().size();
        }
        assertEquals(100,newGame.playedCards.size() + newGame.deck.size() + num);
    }

    @And("P{int} receives {int} shields")
    public void pReceivesShields(int arg0, int arg1) {
        boolean flag = false;
        for (int i =0; i<newGame.players_Participants.size();i++){
            if (newGame.players_Participants.get(i).Get_id() == arg0) {
                flag = true;
            }
        }
        assertTrue(flag);
        for (int j = 0; j < newGame.players.size(); j++) {
            for (int i = 0; i < players_Participants.size(); i++) {
                if (players_Participants.get(i).Get_id() == newGame.players.get(j).Get_id() && players_Participants.get(i).Get_id() == arg0) {
                    assertEquals(arg1,newGame.players.get(j).Get_shields() - players_Participants.get(i).Get_shields());
                }
            }
        }

    }

    @And("P{int} loses and receives no shields")
    public void pLosesAndReceivesNoShields(int arg0) {
        for (int j = 0; j < newGame.players.size(); j++) {
            for (int i = 0; i < players_Participants.size(); i++) {
                if (players_Participants.get(i).Get_id() == newGame.players.get(j).Get_id() && players_Participants.get(i).Get_id() == arg0) {
                    assertEquals(0,newGame.players.get(j).Get_shields() - players_Participants.get(i).Get_shields());
                }
            }
        }

        boolean flag = true;
        for (int i =0; i<newGame.players_Participants.size();i++){
            if (newGame.players_Participants.get(i).Get_id() == arg0) {
                flag = false;
            }
        }
        assertTrue(flag);

    }
    @And("All 2 participants discard the cards")
    public void allParticipantsDiscardTheCards() {
        StringWriter output = new StringWriter();
        assertEquals(0,newGame.players.get(2).Get_shields());
        newGame.Displaycard(newGame.players.get(2),new PrintWriter(output));
        assertTrue(output.toString().contains("F(5)  F(5)  F(15)  F(30)  S(10)"));
        assertEquals(5,newGame.players.get(2).getHand().size());

        assertEquals(4,newGame.players.get(3).Get_shields());
        newGame.Displaycard(newGame.players.get(3),new PrintWriter(output));
        assertTrue(output.toString().contains("F(15)  F(15)  F(40)  L(20)"));
        assertEquals(4,newGame.players.get(3).getHand().size());

        int num =0;
        for (int i =0; i<newGame.players.size();i++){
            num += newGame.players.get(i).getHand().size();
        }
        assertEquals(100,newGame.playedCards.size() + newGame.deck.size() + num);
    }

    @Then("P{int} discards {int} cards and draws {int} random cards and then has {int} cards")
    public void pDiscardsCardsAndDrawsRandomCardsAndThenHasCards(int arg0, int arg1, int arg2, int arg3) {

        assertEquals(players.get(arg0-1).getHand().size() - arg1,newGame.players.get(arg0-1).getHand().size());
        assertEquals(arg2,newGame.addcard);
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 0; i < 12-newGame.players.get(arg0-1).getHand().size(); i++) {
            inputBuilder.append(5).append("\n");
        }
        inputBuilder.append(" \n");

        newGame.endQRound(new Scanner(inputBuilder.toString()));
        assertEquals(arg3,newGame.players.get(arg0-1).getHand().size());
        System.out.println(newGame.playedCards.size());

        int num =0;
        for (int i =0; i<newGame.players.size();i++){
            num += newGame.players.get(i).getHand().size();
        }
        assertEquals(100,newGame.playedCards.size() + newGame.deck.size() + num);

        players.clear();
        for (Player participant : newGame.players) {
            players.add(new Player(participant));
        }
        newGame.nextRound(new Scanner(" \n"));
    }

    @And("{string} loses and cannot go to the next stage")
    public void losesAndCannotGoToTheNextStage(String arg0) {
        String[] parts = arg0.split(" ");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i].replaceAll("\\D", ""));
        }
        boolean flag = true;
        for (int j =0; j<newGame.players_Participants.size();j++){
            for (int i = 0; i < parts.length; i++) {
                if (newGame.players_Participants.get(j).Get_id() == numbers[i]) {
                    flag = false;
                }
            }
        }
        assertTrue(flag);
    }
    @And("P{int} is sponsors and builds the {int} stages")
    public void pIsSponsorsAndBuildsTheStages(int arg0, int arg1) {

        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append("Y\n");
        int[] value = new int[arg1];
        int num = 1;
        for (int i = 0; i < arg1; i++) {
            if (i > 0) value[i] = value[i-1] + 5;
            else value[i] = 5;
            inputBuilder.append(num).append("\n");
            inputBuilder.append("Quit\n");
        }
        inputBuilder.append(" \n");
        newGame.startQRound(new Scanner(inputBuilder.toString()));
        assertEquals(arg0-1,newGame.sponsor);
        assertArrayEquals(value, newGame.stage);

    }

    @Then("P{int} declines to participate")
    public void pDeclinesToParticipate(int arg0) {
        newGame.Get_Participants_ans(new Scanner(" \n \n"));

        boolean flag = true;
        for (int j =0; j<newGame.players_Participants.size();j++){
            if (newGame.players_Participants.get(j).Get_id() == arg0) {
                flag = false;
            }
        }
        assertTrue(flag);
    }
    @And("P{int} are declared winners and has {int} shields")
    public void areDeclaredWinners(int arg0, int arg1) {
        StringWriter output = new StringWriter();
        newGame.determineWinner(new PrintWriter(output));
        assertTrue(output.toString().contains("The winner is: P" + arg0));
        assertEquals(arg1, newGame.players.get(arg0 -1).Get_shields());

    }

    @And("P{int} draws {string} and {string}")
    public void pDrawsAnd(int arg0, String arg1, String arg2) {
        assertEquals(arg0 - 1, newGame.current_player);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        String[] parts = arg1.split(" ");
        StringBuilder inputBuilder = new StringBuilder();
        for (String part : parts) {
            inputBuilder.append(part).append("\n");
        }

        inputBuilder.append("Quit\n \n");
        newGame.start_stage(new Scanner(inputBuilder.toString()));

        System.setOut(originalOut);
        String output = outputStream.toString().trim();
        assertTrue(output.contains(arg2.trim()));
    }


    @Then("P{int} draws ‘Plague’ and loses {int} shields")
    public void pDrawsPlagueAndLosesShields(int arg0, int arg1) {
        newGame.draws_event_card(new Scanner(""));
        assertEquals(arg0 -1, newGame.current_player);
        assertEquals(newGame.played_eventCards.getLast(), new Card("E", "Pl", 2));
        assertEquals(2, newGame.players.get(arg0-1).Get_shields());
        for (Player participant : newGame.players) {
            players.add(new Player(participant));
        }
        newGame.nextRound(new Scanner(" \n"));
    }

    @And("P{int} draws ‘Prosperity’ and {int} players draws {int} cards")
    public void pDrawsProsperityAndPlayersDrawsCards(int arg0, int arg1, int arg2) {

        assertEquals(12, newGame.players.get(0).getHand().size());
        for (int i = 1; i < newGame.players.size(); i++) {
            assertEquals(9, newGame.players.get(i).getHand().size());
        }
        assertEquals(arg0 -1, newGame.current_player);

        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append(" \n");
        for (int i = 0; i < newGame.players.size(); i++) {
            for (int j = 0; j < 12 - newGame.players.get(i).getHand().size(); j++) {
                inputBuilder.append(5).append("\n");
                inputBuilder.append(" \n");
            }
        }
        inputBuilder.append(" \n");

        newGame.draws_event_card(new Scanner(inputBuilder.toString()));

        assertEquals(newGame.played_eventCards.getLast(), new Card("E", "Pr", 2));
        for (int i = 1; i < newGame.players.size(); i++) {
            assertEquals(2, newGame.players.get(i).getHand().size() - players.get(i).getHand().size());
        }

        assertEquals(12, newGame.players.get(0).getHand().size());
        for (int i = 1; i < newGame.players.size(); i++) {
            assertEquals(11, newGame.players.get(i).getHand().size());
        }

        for (Player participant : newGame.players) {
            players.add(new Player(participant));
        }
        newGame.nextRound(new Scanner(" \n"));
    }

    @And("P{int} draws ‘Queen’s favor’ and draws {int} cards")
    public void pDrawsQueenSFavorAndDrawsCards(int arg0, int arg1) {
        StringBuilder inputBuilder = new StringBuilder();
        for (int j = 0; j < 12 - newGame.players.get(arg0 -1).getHand().size(); j++) {
            inputBuilder.append(5).append("\n");
            inputBuilder.append(" \n");
        }
        inputBuilder.append(" \n");
        newGame.draws_event_card(new Scanner(inputBuilder.toString()));
        assertEquals(arg0 -1, newGame.current_player);
        assertEquals(newGame.played_eventCards.getLast(), new Card("E", "Qf", 2));
        assertTrue(newGame.players.get(arg0 -1).getHand().size() > players.get(arg0 -1).getHand().size());
        assertEquals(12, newGame.players.get(arg0 -1).getHand().size());
        for (Player participant : newGame.players) {
            players.add(new Player(participant));
        }
        newGame.nextRound(new Scanner(" \n"));
    }

    @And("P{int} has {int} shields")
    public void pHasShields(int arg0, int arg1) {
        assertEquals(arg1, newGame.players.get(arg0-1).Get_shields());
    }
}