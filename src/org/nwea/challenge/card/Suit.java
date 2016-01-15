package org.nwea.challenge.card;

/**
 * Enumeration representing the suits of a french Deck of
 * Cards.
 */
public enum Suit {

    HEART("H"),
    SPADE("S"),
    DIAMOND("D"),
    CLUB("C");

    private final String id;

    Suit(String id){
        this.id = id;
    };

    public String toString(){
        return this.id;
    }
}
