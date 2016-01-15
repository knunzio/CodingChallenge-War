package org.nwea.challenge.game;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nwea.challenge.card.Card;
import org.nwea.challenge.card.Rank;
import org.nwea.challenge.card.Suit;
import org.nwea.challenge.hand.Hand;

/**
 * Test for the static War Game Utility Methods.
 */
public class WarGameUtilsTest {

    private Hand hand1 = null;
    private Card card1 = null;
    private Card card2 = null;
    private Hand hand2 = null;
    private Card card3 = null;
    private Card card4 = null;
    private List<Hand> hands = null;

    @Before
    public void setup(){
        hand1 = new Hand();
        card1 = new Card(Rank.KING, Suit.CLUB);
        card2 = new Card(Rank.KING, Suit.SPADE);

        hand1.addToTop(card1);
        hand1.addToTop(card2);
        hand1.flipWarCard();

        hand2 = new Hand();
        card3 = new Card(Rank.ACE, Suit.CLUB);
        card4 = new Card(Rank.ACE, Suit.SPADE);
        hand2.addToTop(card3);
        hand2.addToTop(card4);
        hand2.flipWarCard();

        hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
    }

    //TDD method.
    @Test
    public void testSurrender(){
        WarGameUtils.surrenderCardsToHandWinner(hand1,hands);
        Assert.assertFalse(hand1.isEmpty());
    }

    @Test
    public void testGetBattleWinnerForHand() {
        List<Hand> winner = WarGameUtils.getBattleWinnerForHands(hands);
        List<Card> cardsInPlay = winner.get(0).getCardsInPlay();
        Assert.assertTrue(cardsInPlay.contains(card3));
        Assert.assertTrue(cardsInPlay.contains(card4));

    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetBattleWinnerForEmptyHand() {
        hands.add(new Hand());
        List<Hand> winner = WarGameUtils.getBattleWinnerForHands(hands);
        List<Card> cardsInPlay = winner.get(0).getCardsInPlay();
        Assert.assertTrue(cardsInPlay.contains(card3));
        Assert.assertTrue(cardsInPlay.contains(card4));
    }

    @Test
    public void testFlipCards(){
        Hand hand = new Hand();
        Hand hand1 = new Hand();
        List<Hand> hands = null;

        Card card = new Card(Rank.ACE, Suit.CLUB);
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        hand.addToTop(card);
        hand.addToTop(card1);
        hand1.addToTop(card);
        hand1.addToTop(card1);
        hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Assert.assertTrue(hand.getCardsInPlay().isEmpty());
        Assert.assertTrue(hand1.getCardsInPlay().isEmpty());
        WarGameUtils.flipCardsForBattleType(WarGameConstants.BATTLE_TYPE_BATTLE,hands);
        Assert.assertFalse(hand1.getCardsInPlay().isEmpty());
        Assert.assertFalse(hand2.getCardsInPlay().isEmpty());
    }

    @Test
    public void testFlipCardsInvalidHand(){
        Hand h = new Hand();
        Hand h1 = new Hand();
        List<Hand> hl = null;

        Card c = new Card(Rank.ACE, Suit.CLUB);
        Card c1 = new Card(Rank.ACE, Suit.SPADE);
        h.addToTop(c);
        h.addToTop(c1);
        hl = new ArrayList<>();
        hl.add(h);
        hl.add(h1);
        Assert.assertTrue(h.getCardsInPlay().isEmpty());
        Assert.assertTrue(h1.getCardsInPlay().isEmpty());
        WarGameUtils.dumpHands(hl);
        WarGameUtils.flipCardsForBattleType(WarGameConstants.BATTLE_TYPE_BATTLE,hl);
        Assert.assertFalse(h.getCardsInPlay().isEmpty());
        Assert.assertTrue(h1.getCardsInPlay().isEmpty());
    }
}
