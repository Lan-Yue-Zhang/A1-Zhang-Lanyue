#
Feature: A2 Assignment
  Scenario: A1_scenario
    Given Start game and decks are created
    When P1 draws a quest of 4 stages
    Then P1 is declines to sponsor
    And P2 is sponsors and builds the 4 stages for A1 scenario
#    Stage 1:
    Then P1 is decides to participate and draws "F(30)" discards "F(5)"
    And P3 is decides to participate and draws "S(10)" discards "F(5)"
    And P4 is decides to participate and draws "B(15)" discards "F(5)"
    Then P1 draws "D(5) S(10)" value of 15
    And P3 draws "S(10) D(5)" value of 15
    And P4 draws "D(5) H(10)" value of 15
    And "P1 P3 P4" participants can go onto the next stage
    And 3 participants discard the cards
#    Stage 2:
    Then P1 is decides to participate and draws "F(10)"
    And P3 is decides to participate and draws "L(20)"
    And P4 is decides to participate and draws "L(20)"
    Then P1 draws "H(10) S(10)" value of 20
    And P3 draws "B(15) S(10)" value of 25
    And P4 draws "H(10) B(15)" value of 25
    And P1 loses and cannot go to the next stage
    And "P3 P4" participants can go onto the next stage
    And 3 participants discard the cards
#    Stage 3:
    Then P3 is decides to participate and draws "B(15)"
    And P4 is decides to participate and draws "S(10)"
    Then P3 draws "L(20) H(10) S(10)" value of 40
    And P4 draws "B(15) S(10) L(20)" value of 45
    And "P3 P4" participants can go onto the next stage
    And 2 participants discard the cards
#    Stage 4:
    Then P3 is decides to participate and draws "F(30)"
    And P4 is decides to participate and draws "L(20)"
    Then P3 draws "B(15) H(10) L(20)" value of 45
    And P4 draws "D(5) S(10) L(20) E(30)" value of 65
    And P3 loses and receives no shields
    And P4 receives 4 shields
    And All 2 participants discard the cards

    Then P2 discards 9 cards and draws 13 random cards and then has 12 cards

  Scenario: 2winner_game_2winner_quest
    Given A new game is started for 2winner
    When P1 draws a quest of 4 stages
    Then P1 is sponsors and builds the 4 stages
#    Stage 1:
    Then P2 is decides to participate and draws "F(30)" discards "F(5)"
    And P3 is decides to participate and draws "S(10)" discards "F(20)"
    And P4 is decides to participate and draws "B(15)" discards "F(5)"
    Then P2 draws "D(5)" value of 5
    And P3 draws "F(20)" and "empty set of non repeated weapon cards"
    And P4 draws "D(5)" value of 5
    And "P3" loses and cannot go to the next stage
    And "P2 P4" participants can go onto the next stage
    And 2 participants discard the cards
#    Stage 2:
    Then P2 is decides to participate and draws "F(10)"
    And P4 is decides to participate and draws "L(20)"
    Then P2 draws "H(10)" value of 10
    And P4 draws "H(10)" value of 10
    And "P2 P4" participants can go onto the next stage
    And 2 participants discard the cards
#    Stage 3:
    Then P2 is decides to participate and draws "B(15)"
    And P4 is decides to participate and draws "S(10)"
    Then P2 draws "B(15)" value of 15
    And P4 draws "B(15)" value of 15
    And "P2 P4" participants can go onto the next stage
    And 2 participants discard the cards
#    Stage 4:
    Then P2 is decides to participate and draws "F(30)"
    And P4 is decides to participate and draws "L(20)"
    Then P2 draws "L(20)" value of 20
    And P4 draws "L(20)" value of 20
    And P2 receives 4 shields
    And P4 receives 4 shields
    And 2 participants discard the cards

    Then P1 discards 4 cards and draws 8 random cards and then has 12 cards
    And P2 has 4 shields
    And P4 has 4 shields

    Then P2 draws a quest of 3 stages
    And P2 is declines to sponsor
    And P3 is sponsors and builds the 3 stages
#    Stage 1:
    Then P1 declines to participate
    And P2 is decides to participate and draws "H(10)"
    And P4 is decides to participate and draws "H(10)"
    Then P2 draws "S(10)" value of 10
    And P4 draws "H(10)" value of 10
    And "P2 P4" participants can go onto the next stage
    And 2 participants discard the cards
#    Stage 2:
    Then P2 is decides to participate and draws "S(10)"
    And P4 is decides to participate and draws "S(10)"
    Then P2 draws "S(10)" value of 10
    And P4 draws "S(10)" value of 10
    And "P2 P4" participants can go onto the next stage
    And 2 participants discard the cards
#    Stage 3:
    Then P2 is decides to participate and draws "S(10)"
    And P4 is decides to participate and draws "S(10)"
    Then P2 draws "B(15)" value of 15
    And P4 draws "B(15)" value of 15
    And P2 receives 3 shields
    And P4 receives 3 shields
    And 2 participants discard the cards

    Then P3 discards 3 cards and draws 6 random cards and then has 12 cards
    And P2 are declared winners and has 7 shields
    And P4 are declared winners and has 7 shields


  Scenario: 1winner_game_with_events
    Given A new game is started for 1winner
    When P1 draws a quest of 4 stages
    Then P1 is sponsors and builds the 4 stages
#    Stage 1:
    Then P2 is decides to participate and draws "F(30)" discards "F(5)"
    And P3 is decides to participate and draws "S(10)" discards "F(20)"
    And P4 is decides to participate and draws "B(15)" discards "F(5)"
    Then P2 draws "D(5)" value of 5
    And P3 draws "D(5)" value of 5
    And P4 draws "D(5)" value of 5
    And "P2 P3 P4" participants can go onto the next stage
    And 3 participants discard the cards
#    Stage 2:
    Then P2 is decides to participate and draws "F(10)"
    And P3 is decides to participate and draws "L(20)"
    And P4 is decides to participate and draws "L(20)"
    Then P2 draws "H(10)" value of 10
    And P3 draws "H(10)" value of 10
    And P4 draws "H(10)" value of 10
    And "P2 P3 P4" participants can go onto the next stage
    And 3 participants discard the cards
#    Stage 3:
    Then P2 is decides to participate and draws "B(15)"
    And P3 is decides to participate and draws "D(5)"
    And P4 is decides to participate and draws "S(10)"
    Then P2 draws "B(15) S(10)" value of 25
    And P3 draws "B(15) S(10)" value of 25
    And P4 draws "B(15) S(10)" value of 25
    And "P2 P3 P4" participants can go onto the next stage
    And 3 participants discard the cards
#    Stage 4:
    Then P2 is decides to participate and draws "F(30)"
    And P3 is decides to participate and draws "F(5)"
    And P4 is decides to participate and draws "L(20)"
    Then P2 draws "L(20) H(10)" value of 30
    And P3 draws "L(20) S(10)" value of 30
    And P4 draws "L(20) H(10)" value of 30
    And P2 receives 4 shields
    And P3 receives 4 shields
    And P4 receives 4 shields
    And 3 participants discard the cards

    Then P1 discards 4 cards and draws 8 random cards and then has 12 cards

    Then P2 draws ‘Plague’ and loses 2 shields
    And P3 draws ‘Prosperity’ and 4 players draws 2 cards
    And P4 draws ‘Queen’s favor’ and draws 2 cards

    Then P1 draws a quest of 3 stages
    And P1 is sponsors and builds the 3 stages
#      Stage 1:
    Then P2 is decides to participate and draws "H(10)"
    And P3 is decides to participate and draws "F(5)"
    And P4 is decides to participate and draws "H(10)" discards "F(10)"
    Then P2 draws "H(10)" value of 10
    And P3 draws "D(5)" value of 5
    And P4 draws " " and "empty set of non repeated weapon cards"
    And "P4" loses and cannot go to the next stage
    And "P2 P3" participants can go onto the next stage
    And 2 participants discard the cards
#    Stage 2:
    Then P2 is decides to participate and draws "S(10)"
    And P3 is decides to participate and draws "S(10)"
    Then P2 draws "S(10)" value of 10
    And P3 draws "S(10)" value of 10
    And "P2 P3" participants can go onto the next stage
    And 2 participants discard the cards
#    Stage 3:
    Then P2 is decides to participate and draws "S(10)"
    And P3 is decides to participate and draws "S(10)"
    Then P2 draws "B(15)" value of 15
    And P3 draws "B(15)" value of 15
    And P2 receives 3 shields
    And P3 receives 3 shields
    And 2 participants discard the cards

    Then P1 discards 3 cards and draws 6 random cards and then has 12 cards
    And P2 has 5 shields
    And P4 has 4 shields
    And P3 are declared winners and has 7 shields


#  Scenario: 0_winner_quest
#    Given A new game is started
#    When P1 draws a quest of 2 stages
#    Then P1 is sponsors and builds the 2 stages
##    Stage 1:
#    Then P2 is decides to participate and draws "F(30)" discards "F(5)"
#    And P3 is decides to participate and draws "S(10)" discards "F(5)"
#    And P4 is decides to participate and draws "B(15)" discards "F(5)"
#    Then P2 draws " " value of 15
#    And P3 draws " " value of 0
#    And P4 draws " " value of 15
#    And "P2" loses and cannot go to the next stage
#    And "P3" loses and cannot go to the next stage
#    And "P4" loses and cannot go to the next stage
#    And 3 participants discard the cards

#  P1 draws a 2 stage quest and decides to sponsor it. P1 builds 2 stages
#  • P2, P3 and P4 par@cipate in stage 1 and all lose stage 1!
#  • The quest ends with no winner but P1 does discards and draws. (Here you need to
#  figure out what to assert to confirm this outcome J.)
