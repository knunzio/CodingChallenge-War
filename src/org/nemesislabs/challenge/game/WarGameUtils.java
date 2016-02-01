package org.nemesislabs.challenge.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nemesislabs.challenge.deck.Deck;
import org.nemesislabs.challenge.deck.DeckImpl;
import org.nemesislabs.challenge.hand.Hand;

/**
 * War Game Utility class. Collection of data injected methods
 * to process data from the War game.
 */
public class WarGameUtils {

    private static int playCounter = 0;

    /**
     * Returns a shallow copy List of the Hands in play.  For limiting
     * losing hands from play.
     * @param hands
     *     - A list of hands that might be in play.
     * @return
     *     - shallow copy list of hands that still are valid
     *     for play.
     */
    static List<Hand> getHandsInPlay(List<Hand> hands ) {

        List<Hand> handsInPlay = new ArrayList<>();

        for(Hand hand : hands) {
            if(!hand.isEmpty()) {
                //First element of the sorted list
                // has the winning rank.
                handsInPlay.add(hand);
            }
        }
        return handsInPlay;
    }

    /**
     * Test if one hand with cards remains in the list of hands.
     * @param hands
     *     - a list of hands that are be in play.
     * @return
     *     - boolean true if one hand exist in the list that
     *     is NOT empty.
     */
    static boolean hasWinner(List<Hand> hands) {
        return getHandsInPlay(hands).size() == 1;
    }

    /**
     * Creates a populated, shuffled deck.
     * @param numberOfSuits
     *     - number of suits.
     * @param numberOfRanks
     *     - number of ranks.
     */
    static Deck initializeDeck( final int numberOfSuits, final int numberOfRanks) {
        Deck deck = new DeckImpl();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();
        return deck;
    }

    /**
     *  Displays the contents of the hands list.
     */
    static void dumpHands(List<Hand> hands) {

        System.out.println ("****************************************************************************");
        System.out.println ("Play Count: " + ++playCounter);
        for (int ii = 0; ii < hands.size(); ii++) {
            System.out.println("Hand " + ii + ": " + hands.get(ii).toString());
        }
        System.out.println ("****************************************************************************");
    }

    /**
     * Prints the attributes of the Hand that is the winner assuming
     * that the hand that is not empty has won all the cards.
     */
    static void printWinningHand(List<Hand> hands) {
        for (Hand hand : hands){
            if(!hand.isEmpty()){
                System.out.println ("The Winner is: " + hand.toString());
            }
        }
    }


    /**
     * Initializes War Game Card hands.
     * @param deck
     *     - a deck of Cards.
     * @param numberOfPlayers
     *     - number of players(Hands) in the game.
     * @return
     *     - a list of Hands reflecting the number of players in
     *     the game.
     */
    static List<Hand> initializeHands(final Deck deck, final int numberOfPlayers) {

        List<Hand> hands = new ArrayList<>();
        for(int ii = 0; ii < numberOfPlayers; ii++) {
            hands.add(new Hand());
        }
        return hands;
    }

    /**
     * Deals a deck of cards.
     *
     * @param deck
     *     - a deck of cards.
     * @param hands
     *     - a list of hands to deal the cards to.
     * @param numberOfCardsToDeal
     *     - total number of cards to deal.
     */
    static void dealDeckToHands(Deck deck, List<Hand> hands, int numberOfCardsToDeal) {

        for (int ii = 0; ii < numberOfCardsToDeal; ii++) {
            hands.get(ii % hands.size()).addToTop(deck.deal());
        }
    }

    /**
     * For the battle type, the required number of cards are flipped from
     * the hands downstack to the cards in play stack.
     * @param battleType
     *     - a valid battle type. BATTLE_TYPE_BATTLE, BATTLE_TYPE_WAR
     */
    static void flipCardsForBattleType(int battleType, List<Hand> hands)  {

        if( battleType != WarGameConstants.BATTLE_TYPE_BATTLE
                && battleType != WarGameConstants.BATTLE_TYPE_WAR) {
            throw new IllegalArgumentException("Invalid battle type for battle: " + battleType);
        }

        for (Hand hand : hands) {
            if(battleType == WarGameConstants.BATTLE_TYPE_BATTLE) {
                hand.flipBattleCard();
            }
            else {
                hand.flipWarCard();
            }
        }
    }

    /**
     * Driver method to drive a Battle iteration.
     * Recursively executes the War 'battle' and War 'War' if ranks match.
     * @param battleType
     *     - A battle type: BATTLE_TYPE_BATTLE, BATTLE_TYPE_WAR
     * @param handsToBattle
     *     - List of hands used in the battle.
     * @param activeHandsInGame
     *     - List of hands active in this game.
     */
    static void battle(int battleType, List<Hand> handsToBattle, List<Hand> activeHandsInGame) {

        flipCardsForBattleType(battleType, handsToBattle);

        List<Hand> winnerHandList = getBattleWinnerForHands(handsToBattle);

        WarGameUtils.dumpHands(handsToBattle);

        if(WarGameConstants.BATTLE_WINNER_HAND_COUNT == winnerHandList.size()) {
            surrenderCardsToHandWinner(winnerHandList.get(0), activeHandsInGame);
        } else {
            battle(WarGameConstants.BATTLE_TYPE_WAR, winnerHandList, getHandsInPlay(activeHandsInGame));
        }
    }

    /**
     * Getter for the war battle winner hand from the list of hands.
     * @param handList
     *     - Valid hand list with Cards in play.
     * @return
     *     - the hand with the highest rank card in play.
     */
    static List<Hand> getBattleWinnerForHands(List<Hand> handList) {

        for (Hand hand : handList) {
            if(null == hand.viewTopCardInPlay()) {
                throw new IllegalArgumentException("Hands must have cards in play.");
            }
        }

        List<Hand> battleWinnerList = new ArrayList<>();

        Collections.sort(handList, Hand.HandComparator);

        for(Hand hand : handList) {
            if(battleWinnerList.isEmpty()) {
                //First element of the sorted list
                // has the winning rank.
                battleWinnerList.add(hand);
                continue;
            }

            if(hand.viewTopCardInPlay().getRank().equals(battleWinnerList.get(0).viewTopCardInPlay().getRank())) {
                battleWinnerList.add(hand);
            }
        }
        return battleWinnerList;
    }

    /**
     * Moves the cards in play from the list of hands to the winner Hand.
     * @param winnerHand
     *     - the winning hand.
     * @param hands
     *     - list of hands for which cards in play will be moved to winning hand.
     */
    static void surrenderCardsToHandWinner(Hand winnerHand, List<Hand> hands) {
        //Shuffle the list before surrendering them to simulate
        //random card addition to the winners deck.
        List<Hand>handList = new ArrayList<>(hands);
        Collections.shuffle(handList);

        for(Hand hand : handList) {
            winnerHand.addToBottom(hand.getCardsInPlay());
        }
    }

    private WarGameUtils(){}
}
