package com.amdudda;

import java.awt.*;

/**
 * Created by amdudda on 10/12/15.
 */
public abstract class Player {
    // Player class, will split into polymorphic Human and Computer classes
    // some string constants to help set player colors.
    protected static final String ANSI_YELLOW = "\u001B[33m";
    protected static final String ANSI_BLUE = "\u001B[34m";
    protected static final String ANSI_RESET_COLOR = "\u001B[0m";

    // attributes of all players - protected instead of private so subclasses can access & inherit
    protected String name;
    protected Hand playerHand;
    protected String playerColor;
    protected int score=0;
    protected int roundscore=0;

    // some methods my child objects MUST have
    public abstract void takeTurn();
    public abstract String pickSuit();

    @Override
    public String toString() {
        return this.name + "'s myHand is: \n" + playerHand.toString();
    }

    // various setters & getters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() { return this.score; }

    public void setScore(int gameScore) {
        this.score = gameScore;
    }

    public int getRoundScore() { return roundscore; }

    public void setRoundScore(int r_score) { this.roundscore = r_score; }

    protected Hand getPlayerHand() { return this.playerHand; }
    // end setters & getters

    // misc methods
    public void drawCard(Deck d){
        // draw a card from the deck and add it to the player's myHand
        // I'm passing a Deck variable here so the program is extensible to games with multiple decks to draw from
        // make sure the deck isn't empty before trying to draw from it...
        if (d.getSize() == 0) {
            // DONE: pass as a valid play.
            System.out.println("No cards left in deck!  You must play a card.");
            return;
        }

        Card card_to_add = d.drawCard();
        if (!this.name.equals("The computer")) {
            // hide what the computer draws from the other player(s)
            System.out.println(Colorize(this.name + " draws a " + card_to_add + "."));
        }
        // it's Player's job to draw a card, but it's Hand object's responsibility to keep track of it
        this.playerHand.addCard(card_to_add);

    }

    public void playCard(Card c) {
        // DONE: how to declare suit when an 8 is played?  reset the card's suit attribute during play?
        // updates the value of the top of the discardPile, updates the value of discard,
        // and removes the card from player's myHand.
        CrazyEightsGame.discard = c;
        CrazyEightsGame.discardPile.addCard(c);
        this.playerHand.dropCard(c);
        System.out.println(Colorize(this.name + " plays a " + c));
        System.out.println(Colorize(this.name + " has " + this.playerHand.getSize() + " card(s) in myHand."));
        if (CrazyEightsGame.discard.getValue() == 8) { CrazyEightsGame.discard.setSuit(pickSuit()); }
    }

    public String printRoundScore() { return Colorize(this.roundscore + " points."); }

    public String printGameScore() { return Colorize(this.score + " points."); }

    public int getHandValue() {
        return this.playerHand.getHandPoints();
    }

    protected String Colorize(String str) {
        return(this.playerColor + str + ANSI_RESET_COLOR);
    }

}
