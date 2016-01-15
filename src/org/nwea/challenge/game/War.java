package org.nwea.challenge.game;

import java.util.List;

import org.nwea.challenge.deck.Deck;
import org.nwea.challenge.hand.Hand;

/**
 * Driver class for the War Card Game.
 */
public class War {

    /**
     * Driver method the War Card game.
     * @param numberOfSuits
     *     - Number of French Suits to use to create the Deck.
     * @param numberOfRanks
     *     - Number of French Ranks to use to create the Deck.
     * @param numberOfPlayers
     *     - Number of Player in this game.
     */
    public void play( int numberOfSuits, int numberOfRanks, int numberOfPlayers ) {

        //Create and shuffle the deck.
        Deck deck = WarGameUtils.initializeDeck(numberOfSuits, numberOfRanks);

        //Create the downStack for each player.
        List<Hand> hands = WarGameUtils.initializeHands(deck, numberOfPlayers);

        //Determine the number of cards to deal so
        // players don't have uneven down stacks.
        int numberOfCards = numberOfSuits * numberOfRanks;
        int numberOfCardsToDeal = numberOfCards - (numberOfCards % numberOfPlayers);

        //Deal the hands.
        WarGameUtils.dealDeckToHands(deck, hands, numberOfCardsToDeal);

        while (!WarGameUtils.hasWinner(hands)) {
            WarGameUtils.battle(WarGameConstants.BATTLE_TYPE_BATTLE, WarGameUtils.getHandsInPlay(hands));
        }
        WarGameUtils.printWinningHand(hands);
    }
}
