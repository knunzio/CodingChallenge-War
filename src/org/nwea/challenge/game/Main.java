package org.nwea.challenge.game;

public class Main {

    public static void main(String[] args) {

        War war = new War();

        //Play the game with a standard deck (4 Suits, 13 Ranks).
        //and two player.
        int suits = 4;
        int ranks = 13;
        int players = 2;
        war.play(suits,ranks,players);
    }
}
