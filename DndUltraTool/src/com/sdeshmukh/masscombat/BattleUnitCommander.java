package com.sdeshmukh.masscombat;

/**
 * Created by sdeshmukh on 17/07/2017.
 */
public class BattleUnitCommander extends BattleUnitMember{
    int charismaModifier;
    public BattleUnitCommander(String name, int size, double challengeRating, int charismaModifier) {
        super(name, size, challengeRating, 1);
        this.charismaModifier = charismaModifier;
    }
}
