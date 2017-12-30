package com.sdeshmukh.tools;

import java.util.Random;

/**
 * Created by sdeshmukh on 06/07/2017.
 */
public class Dice {
    Random rand = new Random();

    public final int d4 = 4;
    public final int d6 = 6;
    public final int d8 = 8;
    public final int d10 = 10;
    public final int d12 = 12;
    public final int d20 = 20;
    public final int d100 = 100;

    public int rollDice(int numberOfDice, int numberOfSides){
        int total = 0;

        for(int index = 0; index < numberOfDice; ++index){
            //pseudorandom number generator between 0 and numberOfSides, + 1, print out individual values & add together
            int n = rand.nextInt(numberOfSides) + 1;
            total += n;
        }

        return total;
    }

    public int[] rollDiceArray(int numberOfDice, int numberOfSides){
        int[] diceArray = new int[numberOfDice];

        for(int index = 0; index < diceArray.length; ++index){
            int rolledNumber = rand.nextInt(numberOfSides) + 1;
            diceArray[index] = rolledNumber;
        }

        return diceArray;
    }
}
