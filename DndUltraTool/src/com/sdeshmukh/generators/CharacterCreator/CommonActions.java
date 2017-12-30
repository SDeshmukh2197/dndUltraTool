package com.sdeshmukh.generators.CharacterCreator;

/**
 * Created by sdeshmukh on 15/08/2017.
 */
public interface CommonActions {
    void move(int distance);
    boolean attack(Creature target, int numberOfDamageDice, int damageDie);
    boolean heal(Creature target, int numberOfHealDice, int healDie);
    void loseConsciousness();
    void die();
}
