package org.example;

import java.io.PrintWriter;
import java.util.*;

public class Main {
    public Scanner scanner;
    public PrintWriter output;
    public List<Player> players;
    public List<Card> deck;
    public List<Card> event_deck;
    public List<Card> playedCards;
    public List<Card> played_eventCards;
    public List<Card> stage_card;
    public List<Card> stage_card_F;
    public List<Card> sponsorCards;
    public int round;
    public int current_player = 0;
    public int current_player_round = 0;
    public int next_players = 0;
    public List<Player> players_Participants;
    public int [] stage;
    public int addcard;
    public int sponsor;
    public Main(){
        players = new ArrayList<>();
        deck = new ArrayList<>();
        event_deck = new ArrayList<>();
        played_eventCards = new ArrayList<>();
        playedCards = new ArrayList<>();
        players_Participants = new ArrayList<>();
        stage = new int[0];
        stage_card = new ArrayList<>();
        stage_card_F = new ArrayList<>();
        sponsorCards = new ArrayList<>();
        round = 0;
        current_player = 0;
        current_player_round = 0;
        next_players = 0;
        addcard = 0;
        sponsor = 100;
    }
    public static void main(String[] args) {
        Main newGame = new Main();
        newGame.startGame();
        newGame.distributeallCards();
        newGame.startRound(newGame.scanner);
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
            Card card = deck.remove(deck.size()-1);
            player.addToHand(card);
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
        System.out.println("Welcome to the game!");
        add_all_players();
        initializeDeck();
        initialize_event_Deck();
        shuffleDeck(deck);
        shuffleDeck(event_deck);
        System.out.flush();

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
    public String playCard(Scanner scanner, Player player) {
        Displaycard(player, output);
        System.out.println("\n Enter the card you want to draw (e.g. S(10)) or position: ");
        System.out.flush();
        String temp = scanner.nextLine();
        if (!temp.isEmpty() && Check_input_card(temp)){
            String suit = temp.substring(0, 1);
            int value = Integer.parseInt(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")));
            Card c = player.playedCard(suit,value);
            if (c != null) {
                playedCards.add(c);
                System.out.println("You removed the card ");
                System.out.print(" "+ c.getSuit() + "(" + c.getValue() + ")\n");
                System.out.flush();
                return "You removed the card " + c.getSuit() + "(" + c.getValue() + ")\n";
            } else {
                System.out.print("Please enter the card you have \n");
                return "Please enter the card you have \n";
            }
        } else if (isInteger(temp)) {
            int position = Integer.parseInt(temp);
            if (position >= 1 && position <= player.getHand().size()) {
                Card c = player.getHand().get(position - 1);
                playedCards.add(c);
                player.getHand().remove(position - 1);
                System.out.println("You removed the card: ");
                System.out.println(" "+ c.getSuit() + "(" + c.getValue() + ")\n");
                System.out.flush();
                return "You removed the card " + c.getSuit() + "(" + c.getValue() + ")\n";
            } else {
                System.out.println("Invalid position. Please enter a valid card position.");
                return "Invalid position. Please enter a valid card position.";
            }

        } else {
            System.out.print("Please enter according to the format again \n");
        }
        System.out.flush();
        return "You removed the card";
    }
    public void Displaycard(Player player, PrintWriter output){
        // Display player's hands
        if (!player.getHand().isEmpty()) {
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
            for (Card card : temp) {
                output.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
            }
            player.setHand(temp);
        } else {
            output.print("     ");
        }

        output.println();

        output.flush();
    }
    public void Displayplayers(PrintWriter output){
        for (Player playersParticipant : players_Participants) {
            output.println("P" + playersParticipant.Get_id() + " is eligible ");
        }
        output.flush();
    }
    public List<Player>  determineWinner( PrintWriter output) {
        int num = 0;
        List<Player>  winner = new ArrayList<>();
        for (int i = 0 ; i < players.size(); i++) {
            if (players.get(i).Get_shields() >= 7) {
                num++;
                winner.add(players.get(i));
                output.println("The winner is: P" + players.get(i).Get_id());
                output.flush();
            }
        }
        if (num ==0) {
            output.println("No winner of game, continues the game");
        }
        return winner;
    }
    public void nextRound(Scanner scanner) {
        if (current_player != current_player_round) {
            System.out.print("current player: " + players.get(current_player_round).Get_id());
            Displaycard(players.get(current_player_round),output);
            Player currentPlayer = players.get(current_player_round);
            System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
            scanner.nextLine();
        }
        if (current_player_round < players.size()-1) current_player_round++;
        else current_player_round = 0;
        current_player = current_player_round;
        next_players = current_player_round;
        if (event_deck.isEmpty()) {
            reusedeventDeck();
        }
    }
    public void startRound(Scanner scanner){
        while (determineWinner(output).isEmpty()) {
            System.out.print("current player: " + players.get(current_player_round).Get_id());
            Displaycard(players.get(current_player_round), output);
            draws_event_card(scanner);
            if (stage.length > 0) startQRound(scanner);
            if (sponsor != 100) {
                runQRound(scanner);
            }
            nextRound(scanner);
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
    }

    public String event_card(){
        next_players = current_player_round;
        String temp = "";
        System.out.println();
        Card event = event_deck.remove(event_deck.size()-1);
        played_eventCards.add(event);
        if (event.getType().equals("E")) {
            if (event.getSuit().equals("Pl")) {
                System.out.println("The current player has drawn an Plague card ");
                temp = "The current player has drawn an Plague card ";
                int shields = players.get(current_player_round).Get_shields();
                if (shields >= 2) {
                    players.get(current_player_round).Set_shields(shields-2);
                    System.out.println("The player "+ players.get(current_player_round).Get_id() +" draws this card immediately loses 2 shields");
                    temp += "The player "+ players.get(current_player_round).Get_id() +" draws this card immediately loses 2 shields";
                }
                System.out.flush();
                return temp;
            } else if (event.getSuit().equals("Qf")) {
                System.out.println("The current player has drawn an Queen’s favor card");
                distributeCards(players.get(current_player_round), 2);
                return "The current player has drawn an Queen’s favor card";
            } else {
                System.out.println("The current player has drawn an Prosperity card");
                System.out.flush();
                for (Player player : players) {
                    distributeCards(player, 2);
                }
                return "The current player has drawn an Prosperity card";
            }
        } else {
            int n = event.getValue();
            stage = new int[n];
            System.out.println("The current player has drawn an "+ n +" stage Quest card");
            return "The current player has drawn an "+ n +" stage Quest card";
        }
    }
    public int removeCheck(){
        for (Player player : players) {
            if (player.getHand().size() > 12) {
                current_player = player.Get_id() - 1;
                return player.Get_id();
            }
        }
        return 100;
    }

    public void draws_event_card(Scanner scanner){
        String temp = event_card();
        if (temp.contains("Queen’s favor")) {
            removeCards(scanner, players.get(current_player_round));
        } else if (temp.contains("Prosperity")) {
            Player currentPlayer = players.get(current_player);
            System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
            scanner.nextLine();
            clearConsole();
            for (int i = 0; i < players.size(); i++) {
                if (removeCheck() != 100) {
                    System.out.print("current player: " + players.get(current_player).Get_id());
                    Displaycard(players.get(current_player),output);
                    removeCards(scanner, players.get(current_player));
                    currentPlayer = players.get(current_player);
                    System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
                    scanner.nextLine();
                    clearConsole();
                }
            }
        }
    }
    public String sponsor_input(String temp) {
        if (!temp.isEmpty() && Check_input_card(temp)){
            String suit = temp.substring(0, 1);
            int value = Integer.parseInt(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")));
            int c = hasCard(sponsorCards,suit,value);
            if (c != 1111) {
                if (suit.equals("F")) {
                    if (stage_card_F.isEmpty()) {
                        stage_card_F.add(sponsorCards.get(c));
                    } else {
                        System.out.println("Each stage must consist of a single Foe card \n");
                        return "Each stage must consist of a single Foe card \n";
                    }
                } else {
                    if (!stage_card.contains(sponsorCards.get(c))) {
                        stage_card.add(sponsorCards.get(c));
                    } else {
                        System.out.println("non repeated weapon card \n");
                        return "non repeated weapon card \n";
                    }
                }
            } else {
                System.out.println("Please enter the card you have \n");
                return "Please enter the card you have \n";
            }
        } else if (isInteger(temp)) {
            int position = Integer.parseInt(temp);
            if (position >= 1 && position <= sponsorCards.size()) {
                Card c = sponsorCards.get(position - 1);
                if (c.getSuit().equals("F")) {
                    if (stage_card_F.isEmpty()) {
                        stage_card_F.add(c);
                    } else {
                        System.out.println("Each stage must consist of a single Foe card \n");
                        return "Each stage must consist of a single Foe card \n";
                    }
                } else {
                    if (!stage_card.contains(c)) {
                        stage_card.add(c);
                    } else {
                        System.out.println("non repeated weapon card \n");
                        return "non repeated weapon card \n";
                    }
                }
            } else {
                System.out.println("Invalid position. Please enter a valid card position.");
                return "Invalid position. Please enter a valid card position.";
            }

        }
        return "true";
    }
    public Card removeSponsorCards(String suit, int value) {
        int cardToPlay;
        for (int i = 0; i < sponsorCards.size(); i++) {
            if (suit.equals(sponsorCards.get(i).getSuit()) && sponsorCards.get(i).getValue() == value) {
                cardToPlay = i;
                return sponsorCards.remove(cardToPlay);
            }
        }
        return null;
    }
    public String sponsor_Quit() {
        if (stage_card_F.isEmpty()) {
            stage_card.clear();
            System.out.println("A stage cannot be empty \n");
            return "A stage cannot be empty \n";
        } else {
            int value = stage_card_F.get(0).getValue();
            for (Card card : stage_card) {
                value += card.getValue();
            }
            if (round == 0) {
                for (Card card : stage_card) {
                    playedCards.add(removeSponsorCards(card.getSuit(), card.getValue()));
                }
                playedCards.add(removeSponsorCards(stage_card_F.get(0).getSuit(),stage_card_F.get(0).getValue()));
                addcard += 1 + stage_card.size();
                stage[round] = value;
                stage_card.clear();
                stage_card_F.clear();
            } else if (value > stage[round-1]) {
                for (Card card : stage_card) {
                    playedCards.add(removeSponsorCards(card.getSuit(), card.getValue()));
                }
                playedCards.add(removeSponsorCards(stage_card_F.get(0).getSuit(),stage_card_F.get(0).getValue()));
                addcard += 1 + stage_card.size();
                stage[round] = value;
                stage_card.clear();
                stage_card_F.clear();
            } else {
                stage_card.clear();
                stage_card_F.clear();
                System.out.println("Insufficient value for this stage \n");
                return "Insufficient value for this stage \n";
            }
        }
        if (stage[stage.length-1] != 0) {
            addcard += stage.length;
        }
        return "true";
    }
    public String printsponsorstagecard() {
        String temp;
        int value = 0;
        if (!stage_card_F.isEmpty()) value = stage_card_F.get(0).getValue();
        for (Card card : stage_card) {
            value += card.getValue();
        }
        System.out.print("\n The value of the cards used in this stage is "+ value + " : ");
        temp = "The value of the card used by P"+ (current_player+1) + " in stage "+ (round+1) + " is "+ value + " : ";
        if (!stage_card_F.isEmpty()) {
            System.out.print(" "+ stage_card_F.get(0).getSuit() + "(" + stage_card_F.get(0).getValue() + ") ");
            temp += stage_card_F.get(0).getSuit() + "(" + stage_card_F.get(0).getValue() + ") ";
        }
        for (Card card : stage_card) {
            System.out.print(" "+ card.getSuit() + "(" + card.getValue() + ") ");
            temp += card.getSuit() + "(" + card.getValue() + ") ";
        }
        System.out.flush();
        return temp;
    }
    public void start_set_stage_card(){
        round = 0;
        for (int i = 0; i < stage.length; i++) {
            if (stage[i] == 0) {
                round = i;
                break;
            }
        }
        current_player = sponsor;
    }
    public String start_set_stage(Scanner scanner) {
        start_set_stage_card();
        System.out.println("\n");
        System.out.println("current player: " + players.get(sponsor).Get_id());

        boolean done = true;

        while (done) {
            System.out.println("stage : " + (round+1));
            Displaycard(players.get(sponsor),output);
            System.out.println("Enter the card you want to play (e.g. S(10)): ");
            System.out.flush();
            String temp = scanner.nextLine();
            if (!temp.isEmpty() && Check_input_card(temp)){
                sponsor_input(temp);
                printsponsorstagecard();
            } else if (isInteger(temp)) {
                sponsor_input(temp);
                printsponsorstagecard();
            } else if (temp.contains("Quit")) {
                if (sponsor_Quit().equals("true"))
                    done = false;
            } else {
                System.out.println("Please enter according to the format again \n");
            }
            System.out.flush();
        }
        System.out.flush();

        return "true";
    }
    public int hasCard(List<Card> deck, String suit, int value) {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getSuit().equals(suit) && deck.get(i).getValue() == value) {
                return i;
            }
        }
        return 1111;
    }
    public String Get_sponsor_ans(Scanner scanner){
        String temp = "";
        System.out.println("current player: " + players.get(current_player).Get_id());
        Displaycard(players.get(current_player),output);
        System.out.println("\n The game has drawn a Q" + stage.length + " card");
        System.out.flush();
        if (checksponsor(players.get(current_player))) {
            System.out.println("\n Do you want to sponsor the current task? Answer Y or N: ");
            System.out.flush();
            String playerans = scanner.nextLine();
            if (playerans.contains("Y")) {
                System.out.println("P" + players.get(current_player).Get_id() + " is sponsor ");
                temp =  "P" + players.get(current_player).Get_id() + " is sponsor ";
                System.out.flush();
                sponsor = current_player;
                sponsorCards = new ArrayList<>(players.get(current_player).getHand());
            } else {
                temp = "P" + players.get(current_player).Get_id() + " is not sponsor ";
            }
        } else {
            System.out.println("\n player must NECESSARILY have cards that allow for the construction of a valid quest.");
            temp =  "player must NECESSARILY have cards that allow for the construction of a valid quest.";
            System.out.flush();
        }
        if (sponsor == 100) {
            Player currentPlayer = players.get(current_player);
            System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
            scanner.nextLine();
            clearConsole();
            if (current_player < players.size()-1) current_player++;
            else current_player = 0;
        }
        return temp;
    }
    public void Get_sponsor(Scanner scanner){
        for (int round = 0; round < players.size(); round++) {
            Get_sponsor_ans(scanner);
            if (sponsor != 100) break;
        }
    }

    public int changeParticipants(String playerans) {
        int temp = current_player;
        next_players = current_player;
        for (int i = 0; i < players_Participants.size(); i++) {
            if (next_players == players_Participants.get(players_Participants.size()-1).Get_id()-1) {
                next_players = players_Participants.get(0).Get_id() -1;
                break;
            } else if (next_players == players_Participants.get(i).Get_id() -1) {
                next_players = players_Participants.get(i+1).Get_id() -1;
                break;
            }
        }
        if (playerans.equals("Y")) {
            distributeCards(players.get(current_player), 1);
        } else {
            players_Participants.remove(players.get(current_player));
        }
        current_player = next_players;
        if (players_Participants.isEmpty()) {
            players.get(sponsor).setHand(sponsorCards);
        }
        return temp;
    }
    public void Get_Participants_ans(Scanner scanner) {
        System.out.println("current player: " + players.get(current_player).Get_id());
        Displaycard(players.get(current_player),output);
        System.out.println("\n Do you want to join the current task? Answer Y: ");
        System.out.flush();
        String playerans = scanner.nextLine();
        int temp = changeParticipants(playerans);
        if (players.get(temp).getHand().size() > 12) {
            removeCards(scanner, players.get(temp));
            Displaycard(players.get(temp),output);
        }
        Player currentPlayer = players.get(current_player);
        System.out.println("P" + currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
        scanner.nextLine();
        clearConsole();
        System.out.flush();
    }
    public void Get_Participants(Scanner scanner){
        int num = players_Participants.size();
        for (int i = 0; i < num; i++) {
            Get_Participants_ans(scanner);
        }
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
    public String Participants_input(String temp){
        if (!temp.isEmpty() && Check_input_card(temp)){
            String suit = temp.substring(0, 1);
            int value = Integer.parseInt(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")));
            int c = hasCard(players.get(current_player).getHand(),suit,value);
            if (c != 100) {
                if (suit.equals("F")) {
                    System.out.println("Please enter the weapon card you have \n");
                    return "Please enter the weapon card you have \n";
                }
                else {
                    if (!players.get(current_player).getAttackValueDeck().contains(players.get(current_player).getHand().get(c))) {
                        players.get(current_player).addToAttackValueDeck(players.get(current_player).getHand().get(c));
                        System.out.println(suit + "(" + value + ") ");
                    } else {
                        System.out.println(suit + "(" + value + ") ");
                        System.out.println("non repeated weapon card \n");
                        return "non repeated weapon card \n";
                    }
                }
            } else {
                System.out.println("Please enter the card you have \n");
                return "Please enter the card you have \n";
            }
        } else if (isInteger(temp)) {
            int position = Integer.parseInt(temp);
            if (position >= 1 && position <= players.get(current_player).getHand().size()) {
                Card c = players.get(current_player).getHand().get(position - 1);
                if (c.getSuit().equals("F")) {
                    System.out.println("Please enter the weapon card you have \n");
                    return "Please enter the weapon card you have \n";
                } else {
                    if (!players.get(current_player).getAttackValueDeck().contains(c)) {
                        players.get(current_player).addToAttackValueDeck(c);
                        System.out.println(c.getSuit() + "(" + c.getValue() + ") ");
                    } else {
                        System.out.println("non repeated weapon card \n");
                        return "non repeated weapon card \n";
                    }
                }
            } else {
                System.out.println("Please enter the card you have \n");
                return "Please enter the card you have \n";
            }
        }
        return "true";
    }
    public String Participants_Quit(){
        String test = "";
        int value = stage[round];
        if (current_player == players_Participants.get(players_Participants.size()-1).Get_id() -1) {
            stage[round] = 0;
            if (round == stage.length-1) {
                players.get(sponsor).setHand(sponsorCards);
            }
        }
        if (!players.get(current_player).getAttackValueDeck().isEmpty()) {
            printstagecard();
            for (Card card : players.get(current_player).getAttackValueDeck()) {
                playedCards.add(players.get(current_player).playedCard(card.getSuit(), card.getValue()));
            }
            if (players.get(current_player).calculateAttackValue() >= value) {
                test = "current stage win \n";
                if (round == stage.length-1) {
                    int shields = players.get(current_player).Get_shields();
                    players.get(current_player).Set_shields(shields + stage.length);
                    test += "P" +  players.get(current_player).Get_id() + " is the winner of the quest, get " + stage.length + " shields. \n";
                }
                System.out.println("\n current stage win \n");

            } else {
                players_Participants.remove(players.get(current_player));
                System.out.println("\n current stage fail \n");
                test = "current stage fail \n";
            }
        } else {
            players_Participants.remove(players.get(current_player));
            System.out.println("empty set of non repeated weapon cards \n");
            test = "empty set of non repeated weapon cards \n";
        }
        System.out.flush();
        players.get(current_player).cleanAttackValueDeck();
        if (players_Participants.isEmpty()) {
            players.get(sponsor).setHand(sponsorCards);
            System.out.println("The quest ends with no winner\n");
            test += "The quest ends with no winner \n";
        }
        current_player = next_players;
        return test;
    }
    public String printstagecard() {
        String temp;
        System.out.print("\n The value of the cards used in this stage is "+ players.get(current_player).calculateAttackValue() + " : ");
        temp = "The value of the card used by P"+ (current_player+1) + " in stage "+ (round+1) + " is "+ players.get(current_player).calculateAttackValue() + " : ";
        for (Card card : players.get(current_player).getAttackValueDeck()) {
            System.out.print(card.getSuit() + "(" + card.getValue() + ") ");
            temp += card.getSuit() + "(" + card.getValue() + ") ";
        }
        System.out.flush();
        return temp;
    }
    public void start_stage_card(){
        round = 0;
        for (int i = 0; i < stage.length; i++) {
            if (stage[i] > 0) {
                round = i;
                break;
            }
        }
        next_players = current_player;
        for (int i = 0; i < players_Participants.size(); i++) {
            if (next_players == players_Participants.get(players_Participants.size()-1).Get_id()-1) {
                next_players = players_Participants.get(0).Get_id() -1;
                break;
            } else if (next_players == players_Participants.get(i).Get_id() -1) {
                next_players = players_Participants.get(i+1).Get_id() -1;
                break;
            }
        }

    }
    public void start_stage(Scanner scanner) {

        start_stage_card();
        System.out.println("current player: " + players.get(current_player).Get_id());
        boolean done = true;
        while (done) {
            Displaycard(players.get(current_player),output);
            System.out.println();
            System.out.println("stage : " + (round+1));
            System.out.print(" Enter the card you want to play (e.g. S(10)): ");
            System.out.flush();
            String temp = scanner.nextLine();
            if (!temp.isEmpty() && Check_input_card(temp)){
                Participants_input(temp);
                printstagecard();
            } else if (isInteger(temp)) {
                Participants_input(temp);
                printstagecard();
            } else if (temp.contains("Quit")) {
                Participants_Quit();
                done = false;
            } else {
                System.out.println("Please enter according to the format again \n");
            }
            System.out.flush();
        }
        if (current_player != next_players) {
            Player currentPlayer = players.get(current_player);
            System.out.println("P"+currentPlayer.Get_id() + ", please leave the hot seat. Press <return> to continue...");
            scanner.nextLine();
            clearConsole();
        }
    }
    public void setParticipants() {
        players_Participants.clear();
        players_Participants.addAll(players);
        players_Participants.remove(players.get(sponsor));
        current_player = players_Participants.get(0).id-1;
    }
    public void set_stage(Scanner scanner) {
        for (int round = 0; round < stage.length; round++) {
            start_set_stage(scanner);
        }
        setParticipants();
    }
    public void startQRound(Scanner scanner){
        Get_sponsor(scanner);
        if (sponsor != 100) {
            set_stage(scanner);
            Player currentPlayer = players.get(sponsor);
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
    public void addSponsorCard(){
        current_player = sponsor;
        next_players = current_player_round;
        distributeCards(players.get(sponsor), addcard);
    }
    public void endQRound(Scanner scanner){
        addSponsorCard();
        if (players.get(sponsor).getHand().size() > 12) {
            System.out.println("current player: " + players.get(sponsor).Get_id());
            removeCards(scanner, players.get(sponsor));
        }
        reset();
    }
    public void reset(){
        stage = new int[0];
        addcard = 0;
        sponsor = 100;
        round = 0;
        sponsorCards.clear();
    }

    public void removeCards(Scanner scanner, Player player){
        while (player.getHand().size() > 12) {
            System.out.println("Player "+player.Get_id() +" need remove "+ (player.getHand().size() - 12) + " Cards");
            playCard(scanner, player);
        }
    }




}