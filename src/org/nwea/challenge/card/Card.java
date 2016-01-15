package org.nwea.challenge.card;

import java.util.Comparator;

/**
 * A Card object representing a Card from a French Deck.
 */
public class Card implements Comparable<Card>{

    private Rank rank = null;
    private Suit suit = null;

    /**
     * Creates a Card Instance.
     * @param rank
     *     - valid Rank Object.
     * @param suit
     *     - valid Suit Object.
     */
    public Card(Rank rank, Suit suit) {
        if(null == rank || null == suit ) {
            throw new IllegalArgumentException("Card must have a valid Card and Rank");
        }
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Getter for Card Rank.
     * @return
     *     - the card ranks - see org.nwea.challenge.card.Rank.
     */
    public Rank getRank(){
        return rank;
    }

    /**
     * Getter for Card Suit
     * @return
     *     - the card suit - see org.nwea.challenge.card.Suit.
     */
    public Suit getSuit(){
        return suit;
    }

    @Override
    public int compareTo(Card card) {
        return Rank.RankComparator.compare(card.getRank(), this.getRank());
    }

    @Override
    public String toString(){
        return this.rank.toString() + this.suit.toString();
    }

    /**
     * Custom Comparator for Cards.
     */
    public static Comparator<Card> CardComparator = new Comparator<Card>() {

        public int compare(Card card1, Card card2) {
            return card1.compareTo(card2);
        }
    };
}
