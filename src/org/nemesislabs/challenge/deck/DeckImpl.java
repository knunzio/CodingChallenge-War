package org.nemesislabs.challenge.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nemesislabs.challenge.card.Card;
import org.nemesislabs.challenge.card.Rank;
import org.nemesislabs.challenge.card.Suit;

/**
 * Implements the Deck interface.  Object representing a
 * Deck of Cards.
 */
public class DeckImpl implements Deck{

    private List<Card> deck = null;

    /**
     * Constructs a DeckImpl Object.
     */
    public DeckImpl() {
        deck = new ArrayList<>();
    }

    /**
     * Creates a DeckImpl populated with French Deck Cards.  The number of cards will be the
     * product of the number of suits and ranks parameters.  A standard deck contains 4 suits of
     * 13 ranks.
     * @param numberOfSuits
     *     - Number of suits to use to populate this deck.  A standard French 4 suite is to
     *     build the suits.  More than 4 suits will result in duplicates as if multiple decks were
     *     combined.
     * @param numberOfRanks
     *     - Number of ranks to use to populate this deck.  The standard French 13 ranks are used
     *     to build the ranks. More than 13 ranks will result in duplicates as if multiple decks were
     *     combined.
     */
    public void create( int numberOfSuits, int numberOfRanks ) {

        Rank[] r = Rank.values();
        Suit[] s = Suit.values();

        for (int ii = 0; ii < numberOfRanks; ii++ ) {
            for(int jj = 0; jj < numberOfSuits; jj++) {
                Card card =  new Card(r[ii % r.length],s[jj % s.length]);
                deck.add(card);
            }
        }
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }


    /**
     * Deal a card from the deck.
     *
     * @return
     *     - Card object from the deck.
     */
    public Card deal() {

        Card card = null;

        if ( !deck.isEmpty() ) {
           card = deck.get(0);
           deck.remove(0);
        }
        return card;
    }

    @Override
    public String toString() {
       return deck.toString();
    }
}
