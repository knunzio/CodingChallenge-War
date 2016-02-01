package org.nemesislabs.challenge.hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import org.nemesislabs.challenge.card.Card;
import org.nemesislabs.challenge.card.Rank;
import org.nemesislabs.challenge.game.WarGameConstants;

/**
 * Represents a Hand in the Card Game War.
 * https://en.wikipedia.org/wiki/War_%28card_game%29
 */
public class Hand implements Comparable<Hand>{

    private List<Card> downStack = null;

    private Stack<Card> cardsInPlayStack = null;

    /**
     * Creates a Hand Object.
     */
    public Hand() {
       downStack = new ArrayList<>();
       cardsInPlayStack = new Stack<>();
    }

    /**
     * Adds a card to the Front or Top of the Hand
     * downStack.
     * @param card
     *     - A valid Card object.
     */
    public void addToTop(Card card) {
        downStack.add(0, card);
    }

    /**
     * Tests if the downstack is empty.
     * @return
     *     - true if the downstack is empty otherwise false.
     */
    public boolean isEmpty() {
       return downStack.isEmpty();
    }

    /**
     * Adds a list of cards to the bottom or back of the downstack.
     * @param cardList
     *     - a List of Card objects.
     */
    public void addToBottom(List<Card> cardList) {
        downStack.addAll(cardList);
    }

    /**
     * Returns the Card at the top of the Cards in Play list
     * but doesn't remove it from the list.
     * @return
     *     - Card object at the top of the stack of cards
     *     in play.
     */
    public Card viewTopCardInPlay(){
        if(cardsInPlayStack.isEmpty()) {
            return null;
        }
       return cardsInPlayStack.peek();
    }

    /**
     * Moves two cards from the downstack to the stack of
     * cards in play.
     */
    public void flipWarCard(){
        flipCards(WarGameConstants.NUMBER_CARDS_TO_WAR);
    }

    /**
     * Moves one cards from the downstack to the stack of
     * cards in play.
     */
    public void flipBattleCard() {
        flipCards(WarGameConstants.NUMBER_CARDS_TO_BATTLE);
    }

    /**
     * Returns the list of cards in play.
     * @return
     *      - the list of cards in play.
     */
    public List<Card> getCardsInPlay() {
        List<Card> cards = new ArrayList<>(cardsInPlayStack);
        cardsInPlayStack.removeAllElements();
        return cards;
    }

    /**
     * "Flips" a number of cards from the downStack to the play stack.
     * @param numberToFlip
     *     - number of cards to flip.
     */
    private void flipCards(int numberToFlip) {
        for(int ii = 0; ii < numberToFlip; ii++) {
            if(!downStack.isEmpty()) {
                cardsInPlayStack.push(downStack.remove(0));
            }
        }
    }

    @Override
    public String toString() {
        return "DownStack: " + downStack.toString() + "\n"
                + "CardsInPlay: " + cardsInPlayStack;
    }

    @Override
    public int compareTo(Hand hand) {
        return Rank.RankComparator.compare(hand.viewTopCardInPlay().getRank(),this.viewTopCardInPlay().getRank());
    }

    /**
     * Custom hand comparator to compare two hands in play.
     */
    public static Comparator<Hand> HandComparator = new Comparator<Hand>() {

        public int compare(Hand hand1, Hand hand2) {
            return hand1.compareTo(hand2);
        }
    };
}
