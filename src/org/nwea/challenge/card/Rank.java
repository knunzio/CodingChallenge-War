package org.nwea.challenge.card;

import java.util.Comparator;

/**
 * Enumeration representing the suits of a french Deck of
 * Cards.
 */
public enum Rank implements Comparable<Rank>{
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q",12),
    KING("K",13),
    ACE("A",14);

    final Integer rankInt;
    final String id;

    Rank(String id, Integer rankInt){
        this.rankInt = rankInt;
        this.id = id;
    }

    private Integer getRankInteger(){
        return this.rankInt;
    }

    public boolean equals(Rank rank){

        return getRankInteger() == rank.getRankInteger();
    }

    public String toString(){
        return this.id;
    }

    /**
     * Custom comparator for Ranks.
     */
    static public Comparator<Rank> RankComparator = new Comparator<Rank>() {
        @Override
        public int compare(Rank rank1, Rank rank2) {
            return rank1.getRankInteger() - rank2.getRankInteger();
        }
    };
}
