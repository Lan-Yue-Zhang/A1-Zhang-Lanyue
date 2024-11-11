
Feature: A2 Assignment
  Scenario: A1 scenario
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

    Then P2 draws a quest of 3 stages
    Then P2 is declines to sponsor
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


#  P1 draws a 4-stage quest and decides to sponsor it.
#  P1 builds 4 stages, the first of
#  which only has a foe and no weapon.
#  • P2, P3 and P4 par@cipate in stage 1 and build their aOack.
#  • P2 and P4 have their aOack win over this stage, whereas P3 loses.
#  • P2 and P4 par@cipate in and win stages 2, then 3 and then 4.
#  • P2 and P4 each earn 4 shields.
#  • P2 draws a 3 stage quest and declines to sponsor it. P3 sponsors this quest and
#  builds its stages.
#  • P1 declines to par@cipate.
#  • P2 and P4 par@cipate in and win stages 1, 2 and 3.
#  • P2 and P4 each earn 3 shields and both are declared (and asserted as) winners.


#  Scenario: 1winner_game_with_events

#  P1 draws a 4 stage quest and decides to sponsor it. P1 builds 4 stages
#  • P2, P3 and P4 par@cipate in and win all stages.
#  • P2, P3 and P4 each earn 4 shields
#  • P2 draws ‘Plague’ and loses 2 shields
#  • P3 draws ‘Prosperity’: All 4 players receive 2 adventure cards
#  • P4 draws ‘Queen’s favor’ and thus draws 2 adventure cards
#  • P1 draws a 3 stage quest and decides to sponsor it. P1 builds 3 stages
#  • P2, P3 and P4 par@cipate in stage 1. P2 and P3 win, whereas P4 loses.
#  • P2 and P3 par@cipate in and win stages 2 and 3
#  • P2 and P3 earn 3 shields: P3 is declared (and asserted as) the winner
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
