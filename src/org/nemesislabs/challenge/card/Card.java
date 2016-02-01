package org.nemesislabs.challenge.card;

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
     *     - the card ranks - see Rank.
     */
    public Rank getRank(){
        return rank;
    }

    /**
     * Getter for Card Suit
     * @return
     *     - the card suit - see Suit.
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
}
