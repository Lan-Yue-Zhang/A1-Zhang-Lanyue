package org.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class GameController {

    private Main newGame;
    public String nextstep = "";
    private int num_Participants = 0;
    private int num = 0;
    public GameController() {
        resetGame();
    }

    // Resets the game and initializes all components
    private void resetGame() {
        newGame = new Main();
        newGame.startGame();
        newGame.distributeallCards();
    }

    @GetMapping("/start")
    public String startGame() {
        resetGame();
        return "Game started";
    }
    @GetMapping("/A1_scenario")
    public String start_game_for_A1() {
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
        newGame.distributeallCards();
        nextstep = "";
        num_Participants = 0;
        num = 0;

        return "Game started";
    }

    @GetMapping("/2_winner")
    public String start_game_for_2winner() {
        List<Card> cards = new ArrayList<>();

//        for (int i = 1; i <= 8 -2 -4 -1 -1; i++) {
//            cards.add( new Card("F", "F", 5));
//        }
        for (int i = 1; i <= 7 -2 -1 -1 -1; i++) {
            cards.add( new Card("F", "F", 10));
        }
//        for (int i = 1; i <= 8 -2 -1 -1 -2 -2; i++) {
//            cards.add( new Card("F", "F", 15));
//        }
//        for (int i = 1; i <= 7 -1 -4 -2; i++) {
//            cards.add( new Card("F", "F", 20));
//        }
        for (int i = 1; i <= 7 -2 -2 -1; i++) {
            cards.add( new Card("F", "F", 25));
        }
//        for (int i = 1; i <= 4 -1 -1 -1 -1; i++) {
//            cards.add( new Card("F", "F", 30));
//        }
        for (int i = 1; i <= 4; i++) {
            cards.add( new Card("F", "F", 35));
        }
//        for (int i = 1; i <= 2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 40));
//        }
//        for (int i = 1; i <= 2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 50));
//        }
//        cards.add( new Card("F", "F", 70));


        // Create basic weapon cards: Swords, battle-axes, lances, horses, excaliburs, and daggers
//        for (int i = 1; i <= 6 -1 -3 -2; i++) {
//            // Daggers (D)
//            cards.add(new Card("W",  "D", 5));
//        }
        for (int i = 1; i <= 16 -3 -3 -1; i++) {
            // Swords (S)
            cards.add(new Card("W",  "S", 10));
        }
        for (int i = 1; i <= 12 -2 -2 -5 -2; i++) {
            // Horses (H)
            cards.add(new Card("W",  "H", 10));
        }
        for (int i = 1; i <= 8 -2 -2 -2; i++) {
            // Battle-axes (B)
            cards.add(new Card("W",  "B", 15));
        }
//        for (int i = 1; i <= 6 -1 -2 -2 -1; i++) {
//            // lances (L)
//            cards.add(new Card("W",  "L", 20));
//        }
//        for (int i = 1; i <= 2 -1 -1; i++) {
//            // Excaliburs (E)
//            cards.add(new Card("W",  "E", 30));
//        }

//        for P3 draws 8 random cards

        cards.add(new Card("W",  "L", 20));

        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "B", 15));

        cards.add( new Card("W", "S", 10));

        cards.add( new Card("F", "F", 30));

        cards.add( new Card("F", "F", 25));

        cards.add( new Card("F", "F", 20));
        cards.add( new Card("F", "F", 20));

//        for Q3
        cards.add(new Card("F", "F", 25));
        cards.add(new Card("F", "F", 25));

        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));

        cards.add( new Card("W", "D", 5));
        cards.add( new Card("W", "D", 5));

//        for P1 draws 11 random cards
        cards.add(new Card("F", "F", 30));

        for (int i = 1; i <= 2; i++) {
            cards.add( new Card("F", "F", 25));
        }
        for (int i = 1; i <= 4; i++) {
            cards.add( new Card("F", "F", 20));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 15));
        }

        cards.add(new Card("F", "F", 10));
        cards.add(new Card("F", "F", 5));

//        for Q4
        cards.add(new Card("F",  "F", 20));
        cards.add(new Card("F",  "F", 15));

        cards.add(new Card("F",  "F", 15));
        cards.add(new Card("F",  "F", 30));

        cards.add(new Card("F",  "F", 30));
        cards.add(new Card("F",  "F", 10));

        cards.add(new Card("F",  "F", 10));
        cards.add(new Card("F",  "F", 40));
        cards.add(new Card("F",  "F", 5));

        //p4
        cards.add(new Card("F", "F", 50));
        cards.add(new Card("F", "F", 70));

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "H", 10));
        }

        for (int i = 1; i <= 3; i++) {
            cards.add(new Card("W",  "S", 10));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "B", 15));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "L", 20));
        }

        cards.add(new Card("W",  "E", 30));

        //p3
        for (int i = 1; i <= 4; i++) {
            cards.add(new Card("F", "F", 5));
        }
        for (int i = 1; i <= 3; i++) {
            cards.add(new Card("W",  "D", 5));
        }
        for (int i = 1; i <= 5; i++) {
            cards.add(new Card("W",  "H", 10));
        }

        //p2
        cards.add(new Card("F", "F", 40));

        cards.add(new Card("F", "F", 50));
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "H", 10));
        }

        for (int i = 1; i <= 3; i++) {
            cards.add(new Card("W",  "S", 10));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "B", 15));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "L", 20));
        }

        cards.add(new Card("W",  "E", 30));

        //p1
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 5));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 10));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 15));
        }

        cards.add(new Card("W",  "D", 5));

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "H", 10));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "B", 15));
        }

        cards.add(new Card("W",  "L", 20));


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
        newGame.distributeallCards();
        nextstep = "";
        num_Participants = 0;
        num = 0;

        return "Game started";
    }

    @GetMapping("/1_winner")
    public String start_game_for_1winner() {
        List<Card> cards = new ArrayList<>();

//        for (int i = 1; i <= 8 -2 -1 -1 -1 -1 -2; i++) {
//            cards.add( new Card("F", "F", 5));
//        }
//        for (int i = 1; i <= 7 -2 -1 -1 -1 -2; i++) {
//            cards.add( new Card("F", "F", 10));
//        }
        for (int i = 1; i <= 8 -2 -1 -4 ; i++) {
            cards.add( new Card("F", "F", 15));
        }
        for (int i = 1; i <= 7 -2 -1 -1 -1; i++) {
            cards.add( new Card("F", "F", 20));
        }
//        for (int i = 1; i <= 7 -1 -1 -1 -1 -2 -1; i++) {
//            cards.add( new Card("F", "F", 25));
//        }
//        for (int i = 1; i <= 4 -1 -1 -1 -1; i++) {
//            cards.add( new Card("F", "F", 30));
//        }
        for (int i = 1; i <= 4 -1; i++) {
            cards.add( new Card("F", "F", 35));
        }
//        for (int i = 1; i <= 2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 40));
//        }
//        for (int i = 1; i <= 2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 50));
//        }
        cards.add( new Card("F", "F", 70));


//         Create basic weapon cards: Swords, battle-axes, lances, horses, excaliburs, and daggers
        for (int i = 1; i <= 6 -4 ; i++) {
            // Daggers (D)
            cards.add(new Card("W",  "D", 5));
        }
//        for (int i = 1; i <= 16 -3 -3 -3 -1 -2 -4; i++) {
//            // Swords (S)
//            cards.add(new Card("W",  "S", 10));
//        }
        for (int i = 1; i <= 12 -2 -2 -2 -1 -1 -3; i++) {
            // Horses (H)
            cards.add(new Card("W",  "H", 10));
        }
//        for (int i = 1; i <= 8 -2 -2 -2 -1 -1; i++) {
//            // Battle-axes (B)
//            cards.add(new Card("W",  "B", 15));
//        }
//        for (int i = 1; i <= 6 -2 -2 -2; i++) {
//            // lances (L)
//            cards.add(new Card("W",  "L", 20));
//        }
//        for (int i = 1; i <= 2 -2; i++) {
//            // Excaliburs (E)
//            cards.add(new Card("W",  "E", 30));
//        }

//        for P1 draws 8 random cards
        for (int i = 1; i <= 3; i++) {
            cards.add(new Card("W",  "H", 10));
        }

        for (int i = 1; i <= 4; i++) {
            cards.add(new Card("W",  "S", 10));
        }
        cards.add(new Card("F", "F", 35));

//        for Q3
        cards.add(new Card("F", "F", 50));
        cards.add(new Card("F", "F", 40));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("F", "F", 50));
        cards.add(new Card("W", "H", 10));
        cards.add(new Card("W", "B", 15));

//        Queen’s favor

        cards.add(new Card("F", "F", 30));
        cards.add(new Card("F", "F", 25));

//        Prosperity

        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "D", 5));

        cards.add(new Card("F", "F", 40));
        cards.add(new Card("W", "B", 15));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "H", 10));

        cards.add(new Card("F", "F", 25));
        cards.add(new Card("F", "F", 25));

//        for P1 draws 8 random cards

        for (int i = 1; i <= 4; i++) {
            cards.add(new Card("F", "F", 15));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 10));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 5));
        }

//        for Q4
        cards.add(new Card("F",  "F", 20));
        cards.add(new Card("F",  "F", 10));
        cards.add(new Card("F",  "F", 5));

        cards.add(new Card("F",  "F", 20));
        cards.add(new Card("F",  "F", 10));
        cards.add(new Card("F",  "F", 5));

        cards.add(new Card("F",  "F", 25));
        cards.add(new Card("F",  "F", 5));
        cards.add(new Card("F",  "F", 15));

        cards.add(new Card("F",  "F", 20));
        cards.add(new Card("F",  "F", 10));
        cards.add(new Card("F",  "F", 5));

        //p4
        cards.add(new Card("F", "F", 25));

        cards.add(new Card("F", "F", 30));

        cards.add(new Card("F", "F", 70));

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "H", 10));
        }

        for (int i = 1; i <= 3; i++) {
            cards.add(new Card("W",  "S", 10));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "B", 15));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "L", 20));
        }

        //p3
        cards.add(new Card("F", "F", 25));

        cards.add(new Card("F", "F", 30));

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "H", 10));
        }

        for (int i = 1; i <= 3; i++) {
            cards.add(new Card("W",  "S", 10));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "B", 15));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "L", 20));
        }

        cards.add(new Card("W",  "E", 30));


        //p2
        cards.add(new Card("F", "F", 25));

        cards.add(new Card("F", "F", 30));

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "H", 10));
        }

        for (int i = 1; i <= 3; i++) {
            cards.add(new Card("W",  "S", 10));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "B", 15));
        }

        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("W",  "L", 20));
        }

        cards.add(new Card("W",  "E", 30));

        //p1
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 5));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 10));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 15));
        }
        for (int i = 1; i <= 2; i++) {
            cards.add(new Card("F", "F", 20));
        }
        for (int i = 1; i <= 4; i++) {
            cards.add(new Card("W",  "D", 5));
        }


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
        newGame.distributeallCards();
        nextstep = "";
        num_Participants = 0;
        num = 0;

        return "Game started";
    }

    @GetMapping("/0_winner")
    public String start_game_for_0winner() {
        List<Card> cards = new ArrayList<>();
//        for (int i = 1; i <= 8 -2 -2 -2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 5));
//        }
        for (int i = 1; i <= 7 -1 -1 -1 -1 -1; i++) {
            cards.add( new Card("F", "F", 10));
        }
//        for (int i = 1; i <= 8 -2 -2 -2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 15));
//        }
        for (int i = 1; i <= 7 -2 -2 -2; i++) {
            cards.add( new Card("F", "F", 20));
        }
        for (int i = 1; i <= 7 -1 -2 -2; i++) {
            cards.add( new Card("F", "F", 25));
        }
//        for (int i = 1; i <= 4 -2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 30));
//        }
        for (int i = 1; i <= 4 ; i++) {
            cards.add( new Card("F", "F", 35));
        }
//        for (int i = 1; i <= 2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 40));
//        }
//        for (int i = 1; i <= 2 -1 -1; i++) {
//            cards.add( new Card("F", "F", 50));
//        }
//            cards.add( new Card("F", "F", 70));


//         Create basic weapon cards: Swords, battle-axes, lances, horses, excaliburs, and daggers
//        for (int i = 1; i <= 6 -2 -4; i++) {
//            // Daggers (D)
//            cards.add(new Card("W",  "D", 5));
//        }
        for (int i = 1; i <= 16 -2 -3; i++) {
            // Swords (S)
            cards.add(new Card("W",  "S", 10));
        }
        for (int i = 1; i <= 12 -2 -4; i++) {
            // Horses (H)
            cards.add(new Card("W",  "H", 10));
        }
        for (int i = 1; i <= 8 -2; i++) {
            // Battle-axes (B)
            cards.add(new Card("W",  "B", 15));
        }
        for (int i = 1; i <= 6 -2 -1; i++) {
            // lances (L)
            cards.add(new Card("W",  "L", 20));
        }
//        for (int i = 1; i <= 2 -1 -1; i++) {
//            // Excaliburs (E)
//            cards.add(new Card("W",  "E", 30));
//        }

        //P1 draws 14 cards
        for (int i = 1; i <= 3; i++) {
            cards.add( new Card("W", "S", 10));
        }
        for (int i = 1; i <= 4; i++) {
            cards.add( new Card("W", "H", 10));
        }
        for (int i = 1; i <= 4; i++) {
            cards.add( new Card("W", "D", 5));
        }
        cards.add(new Card("F",  "F", 10));
        cards.add(new Card("F",  "F", 15));
        cards.add(new Card("F",  "F", 5));

        //Q2
        cards.add(new Card("F",  "F", 10));
        cards.add(new Card("F",  "F", 15));
        cards.add(new Card("F",  "F", 5));

        //p4
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));

        cards.add(new Card("F", "F", 10));

        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));;

        cards.add(new Card("F", "F", 20));
        cards.add(new Card("F", "F", 20));

        cards.add(new Card("F", "F", 25));
        cards.add(new Card("F", "F", 25));

        cards.add(new Card("F", "F", 30));

        cards.add(new Card("F", "F", 50));

        cards.add(new Card("W",  "E", 30));
        //p3
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));

        cards.add(new Card("F", "F", 10));

        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));;

        cards.add(new Card("F", "F", 20));
        cards.add(new Card("F", "F", 20));

        cards.add(new Card("F", "F", 25));
        cards.add(new Card("F", "F", 25));

        cards.add(new Card("F", "F", 30));

        cards.add(new Card("F", "F", 40));

        cards.add(new Card("W",  "L", 20));

        //p2
        cards.add(new Card("F", "F", 5));
        cards.add(new Card("F", "F", 5));

        cards.add(new Card("F", "F", 10));

        cards.add(new Card("F", "F", 15));
        cards.add(new Card("F", "F", 15));;

        cards.add(new Card("F", "F", 20));
        cards.add(new Card("F", "F", 20));

        cards.add(new Card("F", "F", 25));

        cards.add(new Card("F", "F", 30));
        cards.add(new Card("F", "F", 30));

        cards.add(new Card("F", "F", 40));

        cards.add(new Card("W",  "E", 30));
        //p1
        cards.add(new Card("F", "F", 50));
        cards.add(new Card("F", "F", 70));

        cards.add(new Card("W",  "D", 5));
        cards.add(new Card("W",  "D", 5));

        cards.add(new Card("W",  "H", 10));
        cards.add(new Card("W",  "H", 10));

        cards.add(new Card("W",  "S", 10));
        cards.add(new Card("W",  "S", 10));

        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "B", 15));
        cards.add(new Card("W",  "L", 20));
        cards.add(new Card("W",  "L", 20));



        List<Card> event_cards = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            if (i == 1) event_cards.add( new Card("E", "Pl", 2));
            else if (i <= 1 + 2) event_cards.add( new Card("E", "Qf", 2));
            else event_cards.add( new Card("E", "Pr", 2));
        }
        // Create quest (Q) cards
        for (int i = 1; i <= 12 - 1; i++) {
            if (i <= 3 -1) event_cards.add( new Card("Q", "Q", 2));
            else if (i <= 3 + 4) event_cards.add( new Card("Q", "Q", 3));
            else if (i <= 3 + 4 + 3 ) event_cards.add( new Card("Q", "Q", 4));
            else event_cards.add( new Card("Q", "Q", 5));
        }
        event_cards.add(new Card("Q",  "Q", 2));
        newGame = new Main();
        newGame.startGame();
        newGame.deck = cards;
        newGame.event_deck = event_cards;
        newGame.distributeallCards();
        nextstep = "";
        num_Participants = 0;
        num = 0;
        return "Game started";
    }


    @GetMapping("/drawEventCard")
    public String drawEventCard() {
        String temp = newGame.event_card();
        if (temp.contains("Quest card")){
            nextstep = "Get_sponsor";
        } else {
            nextstep = "check_next_round";
        }
        return temp;
    }

    @PostMapping("/Get_sponsor")
    public String Get_sponsor(@RequestParam(required = false, defaultValue = "N") String input) {
        num++;
        String temp = newGame.Get_sponsor_ans(new Scanner(input));
        if (newGame.sponsor != 100) {
            num = 0;
            nextstep = "set_stage";
            newGame.start_set_stage_card();
            return nextstep;
        } else if (num == newGame.players.size()) {
            num = 0;
            newGame.stage = new int[0];
            nextstep = "next_round";
            return nextstep;
        }
        return temp;
    }
    @PostMapping("/Get_Participants")
    public String getParticipants(@RequestParam(required = false, defaultValue = "N") String input) {
        num++;
        int temp = newGame.changeParticipants(input);
        System.out.println("Get_Participants: " + num);
        if (num >= num_Participants) {
            num = 0;
            System.out.println(num_Participants);
            System.out.println("num: " + num);
            num_Participants = newGame.players_Participants.size();
            System.out.println("num_Participants: " + num_Participants);
            if (num_Participants > 0) nextstep = "start_stage";
            else nextstep = "end_stage";
        }
        if (newGame.players.get(temp).getHand().size() > 12) {
            return "remove card";
        }
        return "Participants added\n";
    }
    @PostMapping("/remove_cards")
    public String remove_cards(@RequestParam(required = false, defaultValue = "1") String input) {
        String temp = newGame.playCard(new Scanner(input),newGame.players.get(newGame.current_player));

        return temp;
    }
    @GetMapping("/remove_check")
    public String remove_check() {
        int temp = newGame.removeCheck();
        if (temp != 100) {
            return "P"+ newGame.players.get(newGame.current_player).Get_id();
        } else if (nextstep.equals("getParticipants") || nextstep.equals("start_stage")) {
            newGame.current_player = newGame.next_players;
            return nextstep;
        } else if (nextstep.equals("next_round")) {
            newGame.reset();
            return nextstep;
        } else if (nextstep.equals("check_next_round")){
            nextstep = "next_round";
        }
        return "false";
    }
    @GetMapping("/leave")
    public String leave() {
        if (nextstep.equals("end_stage")) {
            newGame.addSponsorCard();
            nextstep = "next_round";
        } else if (nextstep.equals("next_round")) {
            newGame.nextRound(new Scanner(" \n"));
            nextstep = "new_round";
        } else if (nextstep.equals("start_stage")) {
            newGame.start_stage_card();
        }
        return nextstep;
    }
    @GetMapping("/winner")
    public String winner() {
        StringWriter output = new StringWriter();
        newGame.determineWinner(new PrintWriter(output));
        return output.toString();
    }
    @GetMapping("/display_set_stage")
    public String display_set_stage() {
        return newGame.printsponsorstagecard();

    }
    @GetMapping("/display_stage")
    public String display_stage() {
        return newGame.printstagecard();

    }
    @GetMapping("/updatePlayer")
    public String updatePlayer() {
        StringWriter output = new StringWriter();
        newGame.Displaycard(newGame.players.get(newGame.current_player),new PrintWriter(output));
        return output.toString().trim() + " P" + newGame.players.get(newGame.current_player).Get_id();
    }
    @GetMapping("/updateSelectPlayer")
    public String updateSelectPlayer(@RequestParam(required = false, defaultValue = "1") String input) {
        int num = Integer.parseInt(input);
        StringWriter output = new StringWriter();
        newGame.Displaycard(newGame.players.get(num-1),new PrintWriter(output));
        return output.toString().trim() + " P" + newGame.players.get(num-1).Get_id();
    }

    @GetMapping("/updateStage")
    public String updateStage() {
        if (nextstep.equals("set_stage")) newGame.start_set_stage_card();
        if (nextstep.equals("start_stage")) newGame.start_stage_card();
        return "Stage: " + (newGame.round+1);
    }
    @GetMapping("/updateCard")
    public String updateCard() {
        StringWriter output = new StringWriter();
        String temp = "";
        for (int i = 0; i < newGame.players.size(); i++) {
            newGame.Displaycard(newGame.players.get(i),new PrintWriter(output));
            temp += "P"+newGame.players.get(i).Get_id() + ": " + output.toString().trim() + "\n";
            output = new StringWriter();
        }
        return temp;
    }
    @GetMapping("/updateCardNumber")
    public String updateCardNumber() {
        String temp = "";
        for (int i = 0; i < newGame.players.size(); i++) {
            temp += "P"+newGame.players.get(i).Get_id() + " cards:" + newGame.players.get(i).getHand().size() + "    ";
        }
        return temp;
    }

    @GetMapping("/updateShield")
    public String updateShield() {
        String temp = "";
        for (int i = 0; i < newGame.players.size(); i++) {
            temp += "P"+newGame.players.get(i).Get_id() + " shields:" + newGame.players.get(i).Get_shields() + "    ";
        }
        return temp;
    }

    @PostMapping("/set_stage")
    public String set_stage(@RequestParam(required = false, defaultValue = "1") String input) {
        newGame.start_set_stage_card();
        String temp = newGame.sponsor_input(input);
        return temp;
    }
    @GetMapping("/set_stage_Quit")
    public String set_stage_Quit() {
        String temp = newGame.sponsor_Quit();
        if (temp.contains("true")) num++;
        if (num == newGame.stage.length) {
            num = 0;
            newGame.setParticipants();
            num_Participants = newGame.players_Participants.size();
            nextstep = "getParticipants";
            return nextstep;
        }
        return temp;
    }
    @PostMapping("/startStage")
    public String startStage(@RequestParam(required = false, defaultValue = "1") String input) {
        newGame.start_stage_card();
        return newGame.Participants_input(input);
    }
    @GetMapping("/startStage_Quit")
    public String startStage_Quit() {
        String temp = newGame.Participants_Quit();
        if (newGame.players_Participants.isEmpty()) {
            nextstep = "end_stage";
        } else if (newGame.stage[newGame.stage.length-1] == 0) {
            nextstep = "end_stage";
        } else if (newGame.stage[newGame.round] == 0) {
            nextstep = "getParticipants";
        }
        num_Participants = newGame.players_Participants.size();
        return temp;
    }

}

