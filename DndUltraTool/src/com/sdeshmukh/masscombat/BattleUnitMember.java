package com.sdeshmukh.masscombat;

/**
 * Created by sdeshmukh on 16/07/2017.
 */
public class BattleUnitMember {
    private String name;
    private int size; //1 for tiny, 2 for small, 3 for medium, 4 for large, 5 for huge, 6 for gargantuan
    private int space; //how much space this unitMember takes up, maximum unit space is 400
    private double challengeRating;
    private int number;
    private double battleRating;

    public BattleUnitMember(String name, int size, double challengeRating, int number) {
        this.name = name;

        if(size < 1 || size > 6){
            System.out.println("Size must be between 1 and 6.");
            this.size = 3;
        } else {
            this.size = size;
        }
        this.number = number;

        if(challengeRating < 0){
            System.out.println("Challenge Rating must be greater than zero.");
            this.challengeRating = 1;
        } else {
            this.challengeRating = challengeRating;
        }

        this.battleRating = determineBattleRating(challengeRating, number);
        System.out.println(this.getNumber() + " " + this.getName() + "s have Battle Rating " + this.getBattleRating() +
        "\nIndividual Challenge Rating of " + this.getChallengeRating());

        this.space = determineSpace(size, number);
    }

    private double determineBattleRating(double challengeRating, int number){
        double battleRating = 1;

        if(challengeRating > 0 && challengeRating <= 0.125){
            battleRating = number*0.05;
        } else if (challengeRating > 0.125 && challengeRating <= 0.25){
            battleRating = number*0.1;
        } else if (challengeRating > 0.25 && challengeRating <= 0.5){
            battleRating = number*0.2;
        } else if (challengeRating > 0.5 && challengeRating <= 1){
            battleRating = number*0.5;
        } else if (challengeRating > 1 && challengeRating <= 7){
            battleRating = (challengeRating - 1)*number;
        } else if (challengeRating > 7 && challengeRating < 9){
            battleRating = challengeRating;
        } else if (challengeRating >= 9 && challengeRating < 10){
            battleRating = challengeRating + 1;
        } else if (challengeRating >= 10 && challengeRating < 12){
            battleRating = 4*challengeRating - 28;
        } else if (challengeRating >= 12 && challengeRating < 13){
            battleRating = 18;
        } else if (challengeRating >=13){
            battleRating = 4*challengeRating - 30;
        }

        return battleRating;
    }

    private int determineSpace(int size, int number){
        int space = 0;
        switch(size){
            case 1:
                space = number/4;
                break;

            case 2:
                space = number/2;
                break;

            case 3:
                space = number;
                break;

            case 4:
                space = number*4;
                break;

            case 5:
                space = number*9;
                break;

            case 6:
                space = number*16;
                break;

            default:
                System.out.println("Size not within range.");
                return 0;
        }

        return space;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public double getChallengeRating() {
        return challengeRating;
    }

    public int getNumber() {
        return number;
    }

    public double getBattleRating() {
        return battleRating;
    }

    public int getSpace() {
        return space;
    }
}
