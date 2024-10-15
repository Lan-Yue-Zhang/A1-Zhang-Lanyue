package org.example;

import java.io.PrintWriter;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    public Scanner scanner;
    public PrintWriter output;
    List<Player> players;
    List<Card> deck;
    List<Card> event_deck;
    List<Card> playedCards;
    List<Card> played_eventCards;

    int current_player = 0;
    int current_player_round = 0;

    List<Player> players_Participants;
    int [] stage;
    int addcard = 0;
    int sponsor = 100;
    public Main(){
        players = new ArrayList<>();
        deck = new ArrayList<>();
        event_deck = new ArrayList<>();
        played_eventCards = new ArrayList<>();
        playedCards = new ArrayList<>();
        players_Participants = new ArrayList<>();
        stage = new int[0];
    }
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

    }
    public void initialize_event_Deck(){
        // Create events E cards
        for (int i = 1; i <= 5; i++) {
            if (i == 1 ) event_deck.add(new Card("E",  "Pl", 2));
            else if (i <= 1+2) event_deck.add(new Card("E",  "Qf", 2));
            else event_deck.add(new Card("E",  "Pr", 2));
        }
        // Create quest (Q) cards
        for (int i = 1; i <= 12; i++) {
            if (i <= 3 )event_deck.add(new Card("Q",  "Q", 2));
            else if (i <= 3+4 )event_deck.add(new Card("Q",  "Q", 3));
            else if (i <= 3+4+3 )event_deck.add(new Card("Q",  "Q", 4));
            else event_deck.add(new Card("Q",  "Q", 5));
        }
    }
    public void initializeDeck() {

        // Create Foes (F) cards
        for (int i = 1; i <= 50; i++) {
            if (i <= 8 ) deck.add(new Card("F",  "F", 5));
            else if (i <= 8+7) deck.add(new Card("F",  "F", 10));
            else if (i <= 8+7+8) deck.add(new Card("F",  "F", 15));
            else if (i <= 8+7+8+7) deck.add(new Card("F",  "F", 20));
            else if (i <= 8+7+8+7+7) deck.add(new Card("F",  "F", 25));
            else if (i <= 8+7+8+7+7+4) deck.add(new Card("F",  "F", 30));
            else if (i <= 8+7+8+7+7+4+4) deck.add(new Card("F",  "F", 35));
            else if (i <= 8+7+8+7+7+4+4+2) deck.add(new Card("F",  "F", 40));
            else if (i <= 8+7+8+7+7+4+4+2+2) deck.add(new Card("F",  "F", 50));
            else deck.add(new Card("F",  "F", 70));
        }
        // Create basic weapon cards: Swords, battle-axes, lances, horses, excaliburs, and daggers
        for (int i = 1; i <= 6; i++) {
            // Daggers (D)
            deck.add(new Card("W",  "D", 5));
        }
        for (int i = 1; i <= 16; i++) {
            // Swords (S)
            deck.add(new Card("W",  "S", 10));
        }
        for (int i = 1; i <= 12; i++) {
            // Horses (H)
            deck.add(new Card("W",  "H", 10));
        }
        for (int i = 1; i <= 8; i++) {
            // Battle-axes (B)
            deck.add(new Card("W",  "B", 15));
        }
        for (int i = 1; i <= 6; i++) {
            // lances (L)
            deck.add(new Card("W",  "L", 20));
        }
        for (int i = 1; i <= 2; i++) {
            // Excaliburs (E)
            deck.add(new Card("W",  "E", 30));
        }


    }

    //Shuffle the deck of cards
    public void shuffleDeck(List<Card> deck) {
        long seed = System.nanoTime();
        Random random = new Random(seed);
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    public void add_all_players(){
        for (int i = 1; i <= 4; i++) {
            players.add(new Player(0, i));
        }
    }

    public void distributeallCards(){
        for (Player player : players) {
            distributeCards(player, 12);  // Distribute 12 cards to each player
        }
    }
    public void distributeCards(Player player, int numCards) {
        for (int i = 0; i < numCards; i++) {
            if (deck.isEmpty()) {
                reusedDeck();
            }
            player.addToHand(deck.remove(deck.size() - 1));
        }
    }
    public void reusedDeck() {
        shuffleDeck(playedCards);
        deck.addAll(playedCards);
        playedCards.clear();
    }
    public void startGame() {
        scanner = new Scanner(System.in);
        output = new PrintWriter(System.out);
        output.println("Welcome to the game!");
        add_all_players();
        initializeDeck();
        initialize_event_Deck();
        shuffleDeck(deck);
        shuffleDeck(event_deck);
        output.flush();

    }
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean Check_input_card(String card){
        if (card.matches("[A-Z]\\(\\d+\\)")) {
            String suit = card.replaceAll("([A-Z])\\((\\d+)\\)", "$1");
            int number = Integer.parseInt(card.replaceAll("([A-Z])\\((\\d+)\\)", "$2"));
            if (number > 70 || number < 5) return false;
            return switch (suit) {
                case "S" -> number == 10;
                case "H" -> number == 10;
                case "D" -> number == 5;
                case "B" -> number == 15;
                case "L" -> number == 20;
                case "E" -> number == 30;
                case "F" ->
                        (number == 5 || number == 10 || number == 15 || number == 20 || number == 25 || number == 30 || number == 35 || number == 40 || number == 50 || number == 70);
                default -> false;
            };
        } else {
            return false;
        }
    }
    public Card playCard(Scanner scanner, Player player) {
        Displaycard(player, output);
        output.println("\n Enter the card you want to draw (e.g. S(10)) or position: ");
        output.flush();
        String temp = scanner.nextLine();
        if (!temp.isEmpty() && Check_input_card(temp)){
            String suit = temp.substring(0, 1);
            int value = Integer.parseInt(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")));
            Card c = player.playedCard(suit,value);
            if (c != null) {
                playedCards.add(c);
                output.println("You removed the card ");
                output.print(" "+ c.getSuit() + "(" + c.getValue() + ")\n");
                output.flush();
                return c;
            } else output.print("Please enter the card you have \n");
        } else if (isInteger(temp)) {
            int position = Integer.parseInt(temp);
            if (position >= 1 && position <= player.getHand().size()) {
                Card c = player.getHand().get(position - 1);
                playedCards.add(c);
                player.getHand().remove(position - 1);
                output.println("You removed the card: ");
                output.println(" "+ c.getSuit() + "(" + c.getValue() + ")\n");
                output.flush();
                return c;
            } else {
                output.println("Invalid position. Please enter a valid card position.");
            }
            Displaycard(player,output);
        } else {
            output.print("Please enter according to the format again \n");
        }

        output.flush();
        return null;
    }
    public void Displaycard(Player player, PrintWriter output){

    }

}