package com.amdudda;

import java.util.Scanner;

/**
 * Created by amdudda on 10/13/15.
 */
public class Human extends Player {
    // this is an extension of player to facilitate a human player
    // no new attributes yet

    // constructor
    public Human(String pName) {
        // creates a human player and deals them a hand
        this.name = pName;
        this.playerHand = new Hand();
        this.score = 0;
    }

    // take a turn!
    public void takeTurn() {
        Scanner s = CrazyEightsGame.scanner;
        int selection = 0;
        Card picked = new Card("fakesuit",-1);
        boolean valid_handindex;
        /*
        Human turn's logic:
        1.  list out hand and add option 0 = draw a card
        2.  if player chooses a legal play, play the card
        3.  else if == 0, draw a card and loop
        4.  else loop and pick again
        4.  once card successfully played, end of turn
        */

        // print out the player's info
        System.out.println(this);
        // add option zero to draw a card
        System.out.println("0.) draw a card");

        while (true) {
            // and prompt for a choice:
            System.out.println("Choose a card to play or enter 0 to draw a card:");
            int index = s.nextInt() - 1;
            s.nextLine(); // move the scanner to the next line
            valid_handindex = index >= 0 && index < this.playerHand.getSize();
            if (valid_handindex) picked = this.playerHand.getCardFromHand(index);

            // act on the user's choice
            if (index >= this.playerHand.getSize()) {
                System.out.println("You have chosen an invalid option.  Please try again.");
            } else if (valid_handindex && picked.isLegalToPlayOn(CrazyEightsGame.discard)) {
                playCard(picked);
                break;
            } else if (index == -1) {
                // draw a card
                this.drawCard(CrazyEightsGame.gameDeck);
            } else {
                // illegal play chosen, pick again
                System.out.println("The " + picked + " cannot be played on the " + CrazyEightsGame.discard + ".");
            }  // end if-else
        } // end while
    } // end takeTurn

    public String pickSuit() {
        // lets user pick a suit when they play an 8.
        // return "Hearts"; // fake data for now, we''l code this later once i've thought through logic a bit
        Scanner s = CrazyEightsGame.scanner;
        String suit_picked;
        String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
        int choice;
        while (true) {
            System.out.println("Pick a suit:");
            int i = 1;
            for (String suit : suits) {
                System.out.println(i + ".) " + suit);
                i++;
            }
            choice = s.nextInt() - 1;
            if (choice >=0 && choice < suits.length) { break; }  // can break out of loop if user makes valid selection
            System.out.println("Please make a valid selection.");
        }
        suit_picked = suits[choice];
        return suit_picked;
    }
}