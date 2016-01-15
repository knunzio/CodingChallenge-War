package org.nwea.challenge.hand;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nwea.challenge.card.Card;
import org.nwea.challenge.card.Rank;
import org.nwea.challenge.card.Suit;

import org.junit.Assert;

/**
 * Tests the Hand Class.
 */
public class HandTest {

    @Test
    public void testIsEmpty(){
        Hand hand = new Hand();
        Assert.assertTrue(hand.isEmpty());
        hand.addToTop(new Card(Rank.ACE, Suit.CLUB));
        Assert.assertFalse(hand.isEmpty());
    }

    @Test
    public void testAddToBottom(){
        Hand hand = new Hand();
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Rank.ACE, Suit.CLUB));
        hand.addToBottom(cardList);
        Assert.assertFalse(hand.isEmpty());
    }

    @Test
    public void testViewTopCardInPlay(){
        Hand hand = new Hand();
        Card card = new Card(Rank.ACE, Suit.CLUB);
        hand.addToTop(card);
        hand.flipBattleCard();
        Assert.assertEquals(hand.viewTopCardInPlay(), card);
    }

    @Test
    public void testFlipBattleCard(){
        Hand hand = new Hand();
        Card card1 = new Card(Rank.ACE, Suit.CLUB);
        Card card2 = new Card(Rank.ACE, Suit.SPADE);
        hand.addToTop(card1);
        hand.addToTop(card2);
        hand.flipWarCard();
        List<Card> cardsInPlay = hand.getCardsInPlay();
        Assert.assertTrue(cardsInPlay.contains(card1));
        Assert.assertTrue(cardsInPlay.contains(card1));
        Assert.assertEquals(2, cardsInPlay.size());
    }

}
