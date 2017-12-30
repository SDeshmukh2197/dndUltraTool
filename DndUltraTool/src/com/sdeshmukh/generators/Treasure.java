package com.sdeshmukh.generators;

import com.sdeshmukh.tools.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sdeshmukh on 18/07/2017.
 */
public class Treasure{
    private static Treasure instance = null;

    private Dice dice = new Dice();
//    private ReadFile file = new ReadFile();
    private double challengeRating;
    private int copperCoins;
    private int silverCoins;
    private int goldCoins;
    private int platinumCoins;
    private boolean isGem = true;
    private List<String> gems;
    private List<String> artObjects;
    private List<String> magicItemTableA;
    private List<String> magicItemTableB;
    private List<String> magicItemTableC;
    private List<String> magicItemTableD;
    private List<String> magicItemTableE;
    private List<String> magicItemTableF;
    private List<String> magicItemTableG;
    private List<String> magicItemTableH;
    private List<String> magicItemTableI;

    private Treasure(){

    }

    public static Treasure getInstance(){
        if(instance == null){
            instance = new Treasure();
        }

        return instance;
    }

    public String individualTreasure(){
        String treasure;
        StringBuilder stringBuilder = new StringBuilder();
        copperCoins = 0;
        silverCoins = 0;
        goldCoins = 0;
        platinumCoins = 0;

        stringBuilder.append("============================\n");

        int luck = dice.rollDice(1, 100);

        if (this.getChallengeRating() < 0){
            System.out.println("Challenge Rating must be greater than 0!");
        } else if (this.getChallengeRating() <= 4){

            if (luck <= 30){
                copperCoins = dice.rollDice(5, dice.d6);
            } else if (luck > 30 && luck <= 60){
                silverCoins = dice.rollDice(4, dice.d6);
            } else if (luck > 60 && luck <= 70){
                silverCoins = dice.rollDice(2, dice.d6);
                goldCoins = dice.rollDice(1, dice.d6);
            } else if (luck > 70 && luck <= 95){
                goldCoins = dice.rollDice(3, dice.d6);
            } else if (luck > 95){
                platinumCoins = dice.rollDice(1, dice.d6);
            }

        } else if (this.getChallengeRating() > 4 && this.getChallengeRating() <= 10){

            if (luck <= 30){
                copperCoins = dice.rollDice(4, dice.d6) * 100;
                silverCoins = dice.rollDice(1, dice.d6) * 10;
            } else if (luck > 30 && luck <= 60){
                silverCoins = dice.rollDice(6, dice.d6) * 10;
                goldCoins = dice.rollDice(2, dice.d6) * 10;
            } else if (luck > 60 && luck <= 70){
                silverCoins = dice.rollDice(8, dice.d6) * 10;
                goldCoins = dice.rollDice(3, dice.d6) * 10;
            } else if (luck > 70 && luck <= 95){
                goldCoins = dice.rollDice(4, dice.d6) * 10;
            } else if (luck > 95){
                goldCoins = dice.rollDice(2, dice.d6);
                platinumCoins = dice.rollDice(3, dice.d6);
            }

        } else if (this.getChallengeRating() > 10 && this.getChallengeRating() <= 16){

            if (luck <= 20){
                silverCoins = dice.rollDice(4, dice.d6) * 100;
                goldCoins = dice.rollDice(1, dice.d6) * 100;
            } else if (luck > 20 && luck <= 35){
                silverCoins = dice.rollDice(2, dice.d6) * 100;
                goldCoins = dice.rollDice(1, dice.d6) * 100;
            } else if (luck > 35 && luck <= 75){
                goldCoins = dice.rollDice(2, dice.d6) * 100;
                platinumCoins = dice.rollDice(1, dice.d6) * 10;
            } else if (luck > 75){
                goldCoins = dice.rollDice(2, dice.d6) * 100;
                platinumCoins = dice.rollDice(2, dice.d6) * 10;
            }

        } else if (this.getChallengeRating() > 16){

            if (luck <= 15){
                silverCoins = dice.rollDice(4, dice.d6) * 1000;
                goldCoins = dice.rollDice(8, dice.d6) * 100;
            } else if (luck > 15 && luck <= 55){
                goldCoins = dice.rollDice(1, dice.d6) * 1000;
                platinumCoins = dice.rollDice(1, dice.d6) * 100;
            } else if (luck > 55){
                goldCoins = dice.rollDice(1, dice.d6) * 1000;
                platinumCoins = dice.rollDice(2, dice.d6) * 100;
            }
        }

        stringBuilder.append(platinumCoins + " platinum coins, " + goldCoins + " gold coins, " + silverCoins +" silver coins, " +
                + copperCoins + " copper coins." + "\n============================\n");
        treasure = stringBuilder.toString();
        return treasure;
    }

    public String hoardTreasure(){
        copperCoins = 0;
        silverCoins = 0;
        goldCoins = 0;
        platinumCoins = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String treasure;
        String gemsAndArt = "";
        //passing isGem tells rollGemsOrArt to roll on gems table, passing !isGem tells it to roll on artObjects table
        String magicItems = "";

        stringBuilder.append("============================\n");

        int luck = dice.rollDice(1, 100);

        if (this.getChallengeRating() < 0){
            System.out.println("Challenge Rating must be greater than zero!");

        } else if (challengeRating <= 4){
            copperCoins = dice.rollDice(6, dice.d6) * 100;
            silverCoins = dice.rollDice(3, dice.d6) * 100;
            goldCoins = dice.rollDice(2, dice.d6) * 10;

            if (luck <= 6){
                System.out.println("No gems, art objects or magic items.");
            } else if (luck > 6 && luck <= 16){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 10, isGem);
            } else if (luck > 16 && luck <= 26){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
            } else if (luck > 26 && luck <= 36){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 50, isGem);
            } else if (luck > 36 && luck <= 44){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 10, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'a');
            } else if (luck > 44 && luck <= 52){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'a');
            } else if (luck > 52 && luck <= 60){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'a');
            } else if (luck > 60 && luck <= 65){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 10, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'b');
            } else if (luck > 65 && luck <= 70){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'b');
            } else if (luck > 70 && luck <= 75){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'b');
            } else if (luck > 75 && luck <= 78){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 10, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'c');
            } else if (luck > 78 && luck <= 80){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'c');
            } else if (luck > 80 && luck <= 85){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'c');
            } else if (luck > 85 && luck <= 92){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'f');
            } else if (luck > 92 && luck <= 97){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'f');
            } else if (luck > 97 && luck <= 99){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(1, 'g');
            } else if (luck > 99){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(1, 'g');
            }

        } else if (this.getChallengeRating() > 4 && this.getChallengeRating() <= 10){
            copperCoins = dice.rollDice(2, dice.d6) * 100;
            silverCoins = dice.rollDice(2, dice.d6) * 1000;
            goldCoins = dice.rollDice(6, dice.d6) * 100;
            platinumCoins = dice.rollDice(3, dice.d6) * 10;

            if (luck <= 4){
                System.out.println("No gems, art objects or magic items.");
            } else if (luck > 4 && luck <= 10){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
            } else if (luck > 10 && luck <= 16){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 50, isGem);
            } else if (luck > 16 && luck <= 22){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 100, isGem);
            } else if (luck > 22 && luck <= 28){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
            } else if (luck > 28 && luck <= 32){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'a');
            } else if (luck > 32 && luck <= 36){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'a');
            } else if (luck > 36 && luck <= 40){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 100, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'a');
            } else if (luck > 40 && luck <= 44){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'a');
            } else if (luck > 44 && luck <= 49){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'b');
            } else if (luck > 49 && luck <= 54){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'b');
            } else if (luck > 54 && luck <= 59){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 100, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'b');
            } else if (luck > 59 && luck <= 63){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'b');
            } else if (luck > 63 && luck <= 66){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'c');
            } else if (luck > 66 && luck <= 69){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'c');
            } else if (luck > 69 && luck <= 72){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 100, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'c');
            } else if (luck > 72 && luck <= 74){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'c');
            } else if (luck > 74 && luck <= 76){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(1, 'd');
            } else if (luck > 76 && luck <= 78){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(1, 'd');
            } else if (luck == 79){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 100, isGem);
                magicItems = rollMagicItemTable(1, 'd');
            } else if (luck == 80){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(1, 'd');
            } else if (luck > 80 && luck <= 84){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 25, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'f');
            } else if (luck > 84 && luck <= 88){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 50, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'f');
            } else if (luck > 88 && luck <= 91){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 100, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'f');
            } else if (luck > 91 && luck <= 94){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck > 94 && luck <= 96){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 100, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck > 96 && luck <= 98){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck == 99){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 100, isGem);
                magicItems = rollMagicItemTable(1, 'h');
            } else if (luck > 99){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(1, 'h');
            }

        } else if (this.getChallengeRating() > 10 && this.getChallengeRating() <= 16){
            goldCoins = dice.rollDice(4, dice.d6) * 1000;
            platinumCoins = dice.rollDice(5, dice.d6) * 100;

            if (luck <= 3){
                System.out.println("No gems, art objects or magic items.");
            } else if (luck > 4 && luck <= 6){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
            } else if (luck > 6 && luck <= 9){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 750, !isGem);
            } else if (luck > 9 && luck <= 12){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 500, isGem);
            } else if (luck > 12 && luck <= 15){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
            } else if (luck > 15 && luck <= 19){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'a') + "\n"
                        + rollMagicItemTable(dice.rollDice(1, dice.d6), 'b');
            } else if (luck > 19 && luck <= 23){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 750, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'a') + "\n"
                        + rollMagicItemTable(dice.rollDice(1, dice.d6), 'b');
            } else if (luck > 23 && luck <= 26){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 500, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'a') + "\n"
                        + rollMagicItemTable(dice.rollDice(1, dice.d6), 'b');
            } else if (luck > 26 && luck <= 29){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'a') + "\n"
                        + rollMagicItemTable(dice.rollDice(1, dice.d6), 'b');
            } else if (luck > 29 && luck <= 35){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'c');
            } else if (luck > 35 && luck <= 40){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 750, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'c');
            } else if (luck > 40 && luck <= 45){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 500, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'c');
            } else if (luck > 45 && luck <= 50){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'c');
            } else if (luck > 50 && luck <= 54){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'd');
            } else if (luck > 54 && luck <= 58){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 750, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'd');
            } else if (luck > 58 && luck <= 62){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 500, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'd');
            } else if (luck > 62 && luck <= 66){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'd');
            } else if (luck > 66 && luck <= 68){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(1, 'e');
            } else if (luck > 68 && luck <= 70){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 750, !isGem);
                magicItems = rollMagicItemTable(1, 'e');
            } else if (luck > 70 && luck <= 72){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 500, isGem);
                magicItems = rollMagicItemTable(1, 'e');
            } else if (luck > 72 && luck <= 74){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(1, 'e');
            } else if (luck > 74 && luck <= 76){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(1, 'f') + "\n"
                        + rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck > 76 && luck <= 78){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 750, !isGem);
                magicItems = rollMagicItemTable(1, 'f') + "\n"
                        + rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck > 78 && luck <= 80){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 500, isGem);
                magicItems = rollMagicItemTable(1, 'f') + "\n"
                        + rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck > 80 && luck <= 82){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(1, 'f') + "\n"
                        + rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck > 82 && luck <= 85){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'h');
            } else if (luck > 85 && luck <= 88){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 750, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'h');
            } else if (luck > 88 && luck <= 90){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 500, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'h');
            } else if (luck > 90 && luck <= 92){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'h');
            } else if (luck > 92 && luck <= 94){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 250, !isGem);
                magicItems = rollMagicItemTable(1, 'i');
            } else if (luck > 94 && luck <= 96){
                gemsAndArt = rollGemsOrArt(dice.rollDice(2, dice.d4), 750, !isGem);
                magicItems = rollMagicItemTable(1, 'i');
            } else if (luck > 96 && luck <= 98){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 500, isGem);
                magicItems = rollMagicItemTable(1, 'i');
            } else if (luck > 99){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(1, 'i');
            }

        } else if (this.getChallengeRating() > 16){
            goldCoins = dice.rollDice(12, dice.d6) * 1000;
            platinumCoins = dice.rollDice(8, dice.d6) * 1000;

            if (luck <= 2){
                System.out.println("No gems, art objects or magic items.");
            } else if (luck > 2 && luck <= 5){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d8), 'c');
            } else if (luck > 5 && luck <= 8){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d10), 2500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d8), 'c');
            } else if (luck > 8 && luck <= 11){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d4), 7500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d8), 'c');
            } else if (luck > 11 && luck <= 14){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d8), 5000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d8), 'c');
            } else if (luck > 14 && luck <= 22){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'd');
            } else if (luck > 22 && luck <= 30){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d10), 2500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'd');
            } else if (luck > 30 && luck <= 38){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d4), 7500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'd');
            } else if (luck > 38 && luck <= 46){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d8), 5000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'd');
            } else if (luck > 46 && luck <= 52){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'e');
            } else if (luck > 52 && luck <= 58){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d10), 2500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'e');
            } else if (luck > 58 && luck <= 63){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d4), 7500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'e');
            } else if (luck > 63 && luck <= 68){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d8), 5000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d6), 'e');
            } else if (luck == 69){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck == 70){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d10), 2500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck == 71){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d4), 7500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck == 72){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d8), 5000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'g');
            } else if (luck > 72 && luck <= 74){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'h');
            } else if (luck > 74 && luck <= 76){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d10), 2500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'h');
            } else if (luck > 76 && luck <= 78){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d4), 7500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'h');
            } else if (luck > 78 && luck <= 80){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d8), 5000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'h');
            } else if (luck > 80 && luck <= 85){
                gemsAndArt = rollGemsOrArt(dice.rollDice(3, dice.d6), 1000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'i');
            } else if (luck > 85 && luck <= 90){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d10), 2500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'i');
            } else if (luck > 90 && luck <= 95){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d4), 7500, !isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'i');
            } else if (luck > 95){
                gemsAndArt = rollGemsOrArt(dice.rollDice(1, dice.d8), 5000, isGem);
                magicItems = rollMagicItemTable(dice.rollDice(1, dice.d4), 'i');
            }
        }

        stringBuilder.append(platinumCoins + " platinum coins, " + goldCoins + " gold coins, " + silverCoins +" silver coins, " +
                + copperCoins + " copper coins.\n");
        stringBuilder.append("Gems and Art Objects:\n" + gemsAndArt + "============================" +  "\nMagic Items:\n" + magicItems +
                "============================\n");
        treasure=stringBuilder.toString();

        return treasure;
    }

    private String rollMagicItemTable(int amount, char identifier){
        String itemOutput = "";

        switch(identifier){
            case 'a':
                itemOutput = easyDiceArray(amount, magicItemTableA);
                break;

            case 'b':
                itemOutput = easyDiceArray(amount, magicItemTableB);
                break;

            case 'c':
                itemOutput = easyDiceArray(amount, magicItemTableC);
                break;

            case 'd':
                itemOutput = easyDiceArray(amount, magicItemTableD);
                break;

            case 'e':
                itemOutput = easyDiceArray(amount, magicItemTableE);
                break;

            case 'f':
                itemOutput = easyDiceArray(amount, magicItemTableF);
                break;

            case 'g':
                itemOutput = easyDiceArray(amount, magicItemTableG);
                break;

            case 'h':
                itemOutput = easyDiceArray(amount, magicItemTableH);
                break;

            case 'i':
                itemOutput = easyDiceArray(amount, magicItemTableI);
                break;

            default:
                System.out.println("error in magic item table switch statement");
                break;
        }

        return itemOutput;
    }

    private String rollGemsOrArt(int amount, int singleValueInteger, boolean isGem){
        //can also replace these with dice made from these gems, same value, just keep in mind

        String singleValue = Integer.toString(singleValueInteger);
        String output;

        if(isGem){
            output = easyDiceArray(amount, this.gems, singleValue);
        } else {
            output = easyDiceArray(amount, this.artObjects, singleValue);
        }

        return output;

    }

    //this one is for gems and art objects or anything that requires a searchParameter in the list
    private String easyDiceArray(int amount, List<String> stringArray, String searchParameter){
        List<String> searchList = new ArrayList<>();
        //define a new string array whose size is the number of instances of searchParameter in stringArray
        List<String> genericList = new ArrayList<>();
        String output = "";

        for(int index = 0; index < stringArray.size(); ++index){
            if(hasSearchParameter(stringArray.get(index), searchParameter)){
                searchList.add(stringArray.get(index));
                //copy elements that contain searchParameter into searchList from stringArray
            }
        }

        int[] diceArray = dice.rollDiceArray(amount, searchList.size());
        //roll for items on searchList (should only contain items that contain searchParameter)

        for(int index = 0; index < diceArray.length; ++index){
            genericList.add(searchList.get(diceArray[index] - 1));
            //had to put a -1 here otherwise would get array index out of bounds sometimes, weird
            //add each item rolled to the arrayList
            output += genericList.get(index) + "\n";
            //turn that item into a string for printing
        }

        return output;
    }

    //this one is for magic items or anything that does not require a search parameter
    private String easyDiceArray(int amount, List<String> stringArray){
        String output = "";
        List<String> genericList = new ArrayList<>();
        int[] diceArray = dice.rollDiceArray(amount, stringArray.size());

        for(int index = 0; index < diceArray.length; ++index){
            genericList.add(stringArray.get(diceArray[index]));
            output += genericList.get(index) + "\n";
        }

        return output;
    }

    public void printList(List<String> list){
        for(int index = 0; index < list.size(); ++index){
            System.out.println(list.get(index));
        }
    }

    private boolean hasSearchParameter(String line, String searchParameter){
        String[] words = line.split(" |\\)|\\(");
        if(Arrays.asList(words).contains(searchParameter)){
            return true;
        } else {
            return false;
        }
    }

    public double getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(double challengeRating){
        this.challengeRating = challengeRating;
    }

    public void setGems(List<String> gems) {
        this.gems = gems;
    }

    public void setArtObjects(List<String> artObjects) {
        this.artObjects = artObjects;
    }

    public void setMagicItemTableA(List<String> magicItemTableA) {
        this.magicItemTableA = magicItemTableA;
    }

    public void setMagicItemTableB(List<String> magicItemTableB) {
        this.magicItemTableB = magicItemTableB;
    }

    public void setMagicItemTableC(List<String> magicItemTableC) {
        this.magicItemTableC = magicItemTableC;
    }

    public void setMagicItemTableD(List<String> magicItemTableD) {
        this.magicItemTableD = magicItemTableD;
    }

    public void setMagicItemTableE(List<String> magicItemTableE) {
        this.magicItemTableE = magicItemTableE;
    }

    public void setMagicItemTableF(List<String> magicItemTableF) {
        this.magicItemTableF = magicItemTableF;
    }

    public void setMagicItemTableG(List<String> magicItemTableG) {
        this.magicItemTableG = magicItemTableG;
    }

    public void setMagicItemTableH(List<String> magicItemTableH) {
        this.magicItemTableH = magicItemTableH;
    }

    public void setMagicItemTableI(List<String> magicItemTableI) {
        this.magicItemTableI = magicItemTableI;
    }
}
