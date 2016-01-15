package org.nwea.challenge.deck;

import org.junit.Assert;
import org.junit.Test;
import org.nwea.challenge.card.Card;

/**
 */
public class DeckImplTest {

    @Test
    public void testNewDeck(){
        Deck deck = new DeckImpl();
        Assert.assertNotNull(deck);
    }

    @Test
    public void testCreateDeck(){
        Deck deck1 = new DeckImpl();
        Deck deck2 = new DeckImpl();
        deck1.create(4,13);
        deck2.create(2,26);
        Assert.assertNotNull(deck1);
        Assert.assertNotNull(deck2);
    }

    @Test
    public void testShuffleDeck(){
        Deck deck = new DeckImpl();
        deck.create(4,13);
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
    }

    @Test
    public void testDealDeck(){
        Deck deck = new DeckImpl();
        deck.create(1,1);
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
        Card c = deck.deal();
        Assert.assertNotNull(c);
        c = deck.deal();
        Assert.assertNull(c);
    }

}
