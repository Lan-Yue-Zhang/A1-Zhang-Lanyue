package org.example;

import java.io.PrintWriter;
import java.util.*;

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
        Main newGame = new Main();
        newGame.startGame();
        newGame.distributeallCards();
        newGame.startRound(newGame.scanner);
        System.exit(0);
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
    public void reusedeventDeck() {
        shuffleDeck(played_eventCards);
        event_deck.addAll(played_eventCards);
        played_eventCards.clear();
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
    public void playCard(Scanner scanner, Player player) {
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
                return;
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
                return;
            } else {
                output.println("Invalid position. Please enter a valid card position.");
            }
            Displaycard(player,output);
        } else {
            output.print("Please enter according to the format again \n");
        }

        output.flush();
    }
    public void Displaycard(Player player, PrintWriter output){
        // Display player's hands
        List<Card> temp = new ArrayList<>();
        temp.add(player.getHand().get(0));
        for (int i = 1; i < player.getHand().size(); i++) {
            Card newcard = player.getHand().get(i);
            for (int j = 0; j < temp.size(); j++) {
                if (newcard.getSuit().equals("F")) {
                    if (!temp.get(j).getSuit().equals("F")) {
                        temp.add(j, newcard);
                        break;
                    } else if (newcard.getValue() <= temp.get(j).getValue() && temp.get(j).getSuit().equals("F")) {
                        temp.add(j, newcard);
                        break;
                    } else if (j == temp.size() - 1) {
                        temp.add(newcard);
                        break;
                    }
                } else if (newcard.getValue() <= temp.get(j).getValue() && !temp.get(j).getSuit().equals("F")) {
                    if (newcard.getSuit().equals("H")) {
                        if (!temp.get(j).getSuit().equals("S") || temp.get(j).getSuit().equals("H")) {
                            temp.add(j, newcard);
                            break;
                        } else if (j == temp.size() - 1) {
                            temp.add(newcard);
                            break;
                        }
                    } else {
                        temp.add(j, newcard);
                        break;
                    }
                } else if (j == temp.size() - 1) {
                    temp.add(newcard);
                    break;
                }
            }
        }
        output.println("\nP"+ player.id + "'s hand:" + temp.size());
        for (Card card : temp) {
            output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
        }
        output.println();
        player.setHand(temp);
        output.flush();
    }
    public void Displayplayers(PrintWriter output){
        for (int i = 0; i < players_Participants.size(); i++) {
            output.println("P" + players_Participants.get(i).Get_id() + " is eligible ");
        }
    }
    public List<Player>  determineWinner( PrintWriter output) {
        List<Player>  winner = new ArrayList<>();
        for (int i = players.size() - 1; i >= 0; i--) {
            if (players.get(i).Get_shields() >= 7) {
                winner.add(players.get(i));
                output.println("The winner is: P" + players.get(i).Get_id());
                output.flush();
            }
        }
        return winner;
    }
    public void startRound(Scanner scanner){
        while (determineWinner(output).isEmpty()) {
            output.print("current player: " + players.get(current_player_round).Get_id());
            Displaycard(players.get(current_player_round), output);
            draws_event_card(scanner);
            if (stage.length > 0) startQRound(scanner);
            if (sponsor != 100) {
                runQRound(scanner);
            }
            if (current_player != current_player_round) {
                output.print("current player: " + players.get(current_player_round).Get_id());
                Displaycard(players.get(current_player_round),output);
                Player currentPlayer = players.get(current_player_round);
                System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
                scanner.nextLine();
            }
            if (current_player_round < players.size()-1) current_player_round++;
            else current_player_round = 0;
            current_player = current_player_round;
            if (event_deck.isEmpty()) {
                reusedeventDeck();
            }
            List<Player> Winner;
            Winner = determineWinner(output);
            if (!Winner.isEmpty()) break;
            clearConsole();
        }
        exit();
    }
    public static void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
    private void exit() {
        System.out.println("Exiting game...");
        scanner.close();
        output.close();
    }
    public void draws_event_card(Scanner scanner){
        output.println();
        Card event = event_deck.remove(event_deck.size()-1);
        if (event.getType().equals("E")) {
            if (event.getSuit().equals("Pl")) {
                output.println("The current player has drawn an Plague card ");
                int shields = players.get(current_player_round).Get_shields();
                if (shields >= 2) {
                    players.get(current_player_round).Set_shields(shields-2);
                    output.println("The player "+ players.get(current_player_round).Get_id() +" draws this card immediately loses 2 \n" +
                            "shields");
                }
                output.flush();
            } else if (event.getSuit().equals("Qf")) {
                output.println("The current player has drawn an Queen’s favor card");
                distributeCards(players.get(current_player_round), 2);
                removeCards(scanner, players.get(current_player_round));
            } else {
                output.println("The current player has drawn an Prosperity card");
                output.flush();
                Player currentPlayer = players.get(current_player);
                System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
                scanner.nextLine();
                clearConsole();
                for (Player player : players) {
                    current_player = player.Get_id() -1;
                    output.print("current player: " + players.get(current_player).Get_id());
                    Displaycard(players.get(current_player),output);
                    distributeCards(player, 2);
                    removeCards(scanner, player);
                    currentPlayer = players.get(current_player);
                    System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
                    scanner.nextLine();
                    clearConsole();
                }
            }
            played_eventCards.add(event);
        } else {
            int n = event.getValue();
            stage = new int[n];
        }
        output.flush();
    }
    public void start_set_stage(Scanner scanner) {
        int round = 0;
        for (int i = 0; i < stage.length; i++) {
            if (stage[i] == 0) {
                round = i;
                break;
            }
        }
        output.println("\n");
        output.println("current player: " + players.get(sponsor).Get_id());
        current_player = sponsor;
        boolean done = true;
        List<Card> stage_card = new ArrayList<>();
        List<Card> stage_card_F = new ArrayList<>();
        while (done) {
            output.println("stage : " + (round+1));
            Displaycard(players.get(sponsor),output);
            output.println("Enter the card you want to play (e.g. S(10)): ");
            output.flush();
            String temp = scanner.nextLine();
            if (!temp.isEmpty() && Check_input_card(temp)){
                String suit = temp.substring(0, 1);
                int value = Integer.parseInt(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")));
                int c = hasCard(players.get(sponsor),suit,value);
                if (c != 1111) {
                    if (suit.equals("F")) {
                        if (stage_card_F.isEmpty()) {
                            stage_card_F.add(players.get(sponsor).getHand().get(c));
                        } else {
                            output.println("Each stage must consist of a single Foe card \n");
                        }
                    } else {
                        if (!stage_card.contains(players.get(sponsor).getHand().get(c))) {
                            stage_card.add(players.get(sponsor).getHand().get(c));
                        } else {
                            output.println("non repeated weapon card \n");
                        }
                    }
                    output.println("The cards used for this stage: ");
                    for (Card card : stage_card) {
                        output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
                    }
                    output.print(" "+ stage_card_F.get(0).getSuit() + "(" + stage_card_F.get(0).getValue() + ") ");
                    output.flush();
                } else output.println("Please enter the card you have \n");
                output.flush();
            } else if (isInteger(temp)) {
                int position = Integer.parseInt(temp);
                if (position >= 1 && position <= players.get(sponsor).getHand().size()) {
                    Card c = players.get(sponsor).getHand().get(position - 1);
                    if (c.getSuit().equals("F")) {
                        if (stage_card_F.isEmpty()) {
                            stage_card_F.add(c);
                        } else {
                            output.println("Each stage must consist of a single Foe card \n");
                        }
                    } else {
                        if (!stage_card.contains(c)) {
                            stage_card.add(c);
                        } else {
                            output.println("non repeated weapon card \n");
                        }
                    }
                } else {
                    output.println("Invalid position. Please enter a valid card position.");
                }
                output.println("The cards used for this stage: ");
                for (Card card : stage_card) {
                    output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
                }
                output.print(" "+ stage_card_F.get(0).getSuit() + "(" + stage_card_F.get(0).getValue() + ") ");
                output.flush();
            } else if (temp.contains("Quit")) {
                if (stage_card_F.isEmpty()) {
                    stage_card.clear();
                    output.println("A stage cannot be empty \n");
                }
                else {
                    int value = stage_card_F.get(0).getValue();
                    for (Card card : stage_card) {
                        value += card.getValue();
                    }
                    if (round == 0) {
                        output.println("The cards used for this stage: ");
                        for (Card card : stage_card) {
                            output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
                            playedCards.add(players.get(sponsor).playedCard(card.getSuit(), card.getValue()));
                        }
                        output.print(" "+ stage_card_F.get(0).getSuit() + "(" + stage_card_F.get(0).getValue() + ") ");
                        playedCards.add(players.get(sponsor).playedCard(stage_card_F.get(0).getSuit(),stage_card_F.get(0).getValue()));
                        addcard += 1 + stage_card.size();
                        stage[round] = value;
                        done = false;
                    } else if (value > stage[round-1]) {
                        output.println("The cards used for this stage: ");
                        for (Card card : stage_card) {
                            output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
                            playedCards.add(players.get(sponsor).playedCard(card.getSuit(), card.getValue()));
                        }
                        output.print(" "+ stage_card_F.get(0).getSuit() + "(" + stage_card_F.get(0).getValue() + ") ");
                        playedCards.add(players.get(sponsor).playedCard(stage_card_F.get(0).getSuit(),stage_card_F.get(0).getValue()));
                        addcard += 1 + stage_card.size();
                        stage[round] = value;
                        done = false;
                    } else {
                        stage_card.clear();
                        stage_card_F.clear();
                        output.println("Insufficient value for this stage \n");
                    }
                    output.println();
                }
                output.flush();
            } else {
                output.println("Please enter according to the format again \n");
            }
            output.flush();
        }
    }
    public int hasCard(Player player, String suit, int value) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i).getSuit().equals(suit) && player.getHand().get(i).getValue() == value) {
                return i;
            }
        }
        return 1111;

    }
    public void Get_sponsor(Scanner scanner){
        for (int round = 0; round < players.size(); round++) {
            output.println("current player: " + players.get(current_player).Get_id());
            Displaycard(players.get(current_player),output);
            output.println("\n The game has drawn a Q" + stage.length + " card");
            output.flush();
            if (checksponsor(players.get(current_player))) {
                output.println("\n Do you want to sponsor the current task? Answer Y or N: ");
                output.flush();
                String playerans = scanner.nextLine();
                if (playerans.contains("Y")) {
                    output.println("P" + players.get(current_player).Get_id() + " is sponsor ");
                    output.flush();
                    sponsor = current_player;
                    break;
                }
            } else {
                output.println("\n player must NECESSARILY have cards that allow for the construction of a valid quest.");
                output.flush();
            }
            if (sponsor == 100) {
                Player currentPlayer = players.get(current_player);
                System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
                scanner.nextLine();
                clearConsole();
                if (current_player < players.size()-1) current_player++;
                else current_player = 0;
            }

        }
    }

    public void Get_Participants(Scanner scanner){
        List<Player> remove_player = new ArrayList<>();
        for (Player playersParticipant : players_Participants) {
            current_player = playersParticipant.Get_id() - 1;
            output.println("current player: " + players.get(current_player).Get_id());
            Displaycard(players.get(current_player),output);
            output.println("\n Do you want to join the current task? Answer Y: ");
            output.flush();
            String playerans = scanner.nextLine();
            if (playerans.equals("Y")) {
                distributeCards(players.get(current_player), 1);
                removeCards(scanner, players.get(current_player));
                Displaycard(players.get(current_player),output);
            } else {
                remove_player.add(players.get(current_player));
            }
            Player currentPlayer = players.get(current_player);
            System.out.println("P" + currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
            scanner.nextLine();
            clearConsole();
        }
        players_Participants.removeAll(remove_player);
        if (!players_Participants.isEmpty()) current_player = players_Participants.get(0).Get_id()-1;
    }
    public boolean checksponsor(Player player) {
        if (player.getHand().size() < stage.length) return false;
        int f = 0;
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i).getSuit().equals("F")) {
                f++;
            }
        }
        return f >= stage.length;
    }
    public void start_stage(Scanner scanner) {
        int round = 0;
        for (int i = 0; i < stage.length; i++) {
            if (stage[i] > 0) {
                round = i;
                break;
            }
        }
        int next_players = current_player;
        for (int i = 0; i < players_Participants.size(); i++) {
            if (next_players == players_Participants.get(players_Participants.size()-1).Get_id()-1) {
                next_players = players_Participants.get(0).Get_id() -1;
                break;
            } else if (next_players == players_Participants.get(i).Get_id() -1) {
                next_players = players_Participants.get(i+1).Get_id() -1;
                break;
            }
        }
        output.println("current player: " + players.get(current_player).Get_id());
        boolean done = true;
        while (done) {
            Displaycard(players.get(current_player),output);
            output.println();
            output.println("stage : " + (round+1));
            output.print(" Enter the card you want to play (e.g. S(10)): ");
            output.flush();
            String temp = scanner.nextLine();
            if (!temp.isEmpty() && Check_input_card(temp)){
                String suit = temp.substring(0, 1);
                int value = Integer.parseInt(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")));
                int c = hasCard(players.get(current_player),suit,value);
                if (c != 100) {
                    if (suit.equals("F")) output.println("Please enter the weapon card you have \n");
                    else {
                        if (!players.get(current_player).getAttackValueDeck().contains(players.get(current_player).getHand().get(c))) {
                            players.get(current_player).addToAttackValueDeck(players.get(current_player).getHand().get(c));
                            output.println(" "+ suit + "(" + value + ") ");
                        } else {
                            output.println(" "+ suit + "(" + value + ") ");
                            output.println("non repeated weapon card \n");
                        }
                    }
                } else output.println("Please enter the card you have \n");
                output.print("\n The value of the cards used in this stage is "+ players.get(current_player).calculateAttackValue() + " : ");
                for (Card card : players.get(current_player).getAttackValueDeck()) {
                    output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
                }
                output.flush();
            } else if (isInteger(temp)) {
                int position = Integer.parseInt(temp);
                if (position >= 1 && position <= players.get(current_player).getHand().size()) {
                    Card c = players.get(current_player).getHand().get(position - 1);
                    if (c.getSuit().equals("F")) {
                        output.println("Please enter the weapon card you have \n");
                    } else {
                        if (!players.get(current_player).getAttackValueDeck().contains(c)) {
                            players.get(current_player).addToAttackValueDeck(c);
                            output.println(" "+ c.getSuit() + "(" + c.getValue() + ") ");
                        } else {
                            output.println("non repeated weapon card \n");
                        }
                    }
                }
                output.print("\n The value of the cards used in this stage is "+ players.get(current_player).calculateAttackValue() + " : ");
                for (Card card : players.get(current_player).getAttackValueDeck()) {
                    output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
                }
                output.flush();
            } else if (temp.contains("Quit")) {
                if (!players.get(current_player).getAttackValueDeck().isEmpty()) {
                    int value = stage[round];
                    if (current_player == players_Participants.get(players_Participants.size()-1).Get_id() -1) {
                        stage[round] = 0;
                    }
                    output.print("\n The value of the cards used in this stage is "+ players.get(current_player).calculateAttackValue() + " : ");
                    for (Card card : players.get(current_player).getAttackValueDeck()) {
                        output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
                        playedCards.add(players.get(current_player).playedCard(card.getSuit(), card.getValue()));
                    }
                    if (players.get(current_player).calculateAttackValue() >= value) {
                        if (round == stage.length-1) {
                            int shields = players.get(current_player).Get_shields();
                            players.get(current_player).Set_shields(shields + stage.length);
                            players_Participants.remove(players.get(current_player));
                        }
                        output.println("\n current stage win \n");
                    } else {
                        players_Participants.remove(players.get(current_player));
                        output.println("\n current stage fail \n");
                    }
                } else {
                    output.println("empty set of non repeated weapon cards \n");
                }
                output.flush();
                players.get(current_player).cleanAttackValueDeck();
                done = false;
            } else {
                output.println("Please enter according to the format again \n");
            }
            output.flush();
        }
        if (current_player != next_players || players_Participants.isEmpty()) {
            Player currentPlayer = players.get(current_player);
            System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
            scanner.nextLine();
            clearConsole();
        }
        current_player = next_players;
    }
    public void startQRound(Scanner scanner){
        Get_sponsor(scanner);
        if (sponsor != 100) {
            for (int round = 0; round < stage.length; round++) {
                start_set_stage(scanner);
            }
            players_Participants.addAll(players);
            players_Participants.remove(players.get(sponsor));
            addcard += stage.length;
            Player currentPlayer = players.get(current_player);
            System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
            scanner.nextLine();
            clearConsole();
        } else {
            stage = new int[0];
        }
    }

    public void runQRound(Scanner scanner){
        for (int round = 0; round < stage.length; round++) {
            Displayplayers(output);
            Get_Participants(scanner);
            int num = players_Participants.size();
            for (int i = 0; i < num; i++) {
                start_stage(scanner);
            }
            if (players_Participants.isEmpty()) {
                break;
            }
        }
        endQRound(scanner);
    }
    public void endQRound(Scanner scanner){

        distributeCards(players.get(sponsor), addcard);
        if (players.get(sponsor).getHand().size() > 12) {
            output.println("current player: " + players.get(sponsor).Get_id());
            removeCards(scanner, players.get(sponsor));
        }
        stage = new int[0];
        addcard = 0;
        sponsor = 100;
    }

    public void removeCards(Scanner scanner, Player player){
        while (player.getHand().size() > 12) {
            output.println("Player "+player.Get_id() +" need remove "+ (player.getHand().size() - 12) + " Cards");
            playCard(scanner, player);
        }
    }

}