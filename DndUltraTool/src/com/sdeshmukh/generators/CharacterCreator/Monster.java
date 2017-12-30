package com.sdeshmukh.generators.CharacterCreator;

import com.sdeshmukh.generators.CharacterCreator.Creature;

/**
 * Created by sdeshmukh on 12/07/2017.
 */
public class Monster extends Creature {
    private int attackBonus;
    private int averageDamage;

    public Monster(String name, int maxHP, int currentHP, int armourClass) {
        super(name, maxHP, currentHP, armourClass);
        this.attackBonus = attackBonus;
        this.averageDamage = averageDamage;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getAverageDamage() {
        return averageDamage;
    }

}
