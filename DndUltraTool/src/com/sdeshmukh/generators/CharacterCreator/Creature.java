package com.sdeshmukh.generators.CharacterCreator;

import com.sdeshmukh.tools.Dice;

import java.util.ArrayList;

/**
 * Created by sdeshmukh on 15/08/2017.
 */
public class Creature implements CommonActions {
    private Dice dice = new Dice();
    private String name;
    private int level;
    private int maxHP;
    private int currentHP;
    private boolean isAlive = true;
    private int armourClass;
    private int proficiencyBonus;
    private int creatureInitiative;
    private int[] abilityScores = new int[6]; //order is STR, DEX, CON, INT, WIS, CHA
    private int[] modifiers = new int[6]; //order is STR, DEX, CON, INT, WIS, CHA
    private ArrayList<String> newAbilityScores = new ArrayList<>();

    public Creature(int maxHP, int level) { //default constructor without a name field
        this.maxHP = maxHP;
        this.level = level;
    }

    public Creature(String name, int maxHP, int level, int armourClass) {
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.level = level;
        this.armourClass = armourClass;
        this.proficiencyBonus = (int) Math.floor(level/3);

        setProficiencyBonus();

        for(int index = 0; index < abilityScores.length; ++index){
            abilityScores[index] = generateAbilityScore();
            this.modifiers[index] = findModifier(this.abilityScores[index]);
            this.newAbilityScores.add(this.abilityScores[index] + " (" + this.modifiers[index] + ")");
        }
    }

    public int generateAbilityScore(){
        int abilityScore = 0;
        int[] rolls = new int[4];
        int minRoll;
        rolls[0] = dice.rollDice(1, dice.d6);
        rolls[1] = dice.rollDice(1, dice.d6);
        rolls[2] = dice.rollDice(1, dice.d6);
        rolls[3] = dice.rollDice(1, dice.d6);
        minRoll = rolls[0];

        for(int index = 0; index < rolls.length; ++index){
            if(rolls[index] < minRoll){
                minRoll = rolls[index];
            }
        }

        for(int index = 0; index < rolls.length; ++index){
            abilityScore += rolls[index];
        }

        abilityScore = abilityScore - minRoll;

        return abilityScore;
    }

    public int findModifier(int abilityScore){
        int modifier = 0;

        if(abilityScore <= 0){
            System.out.println("Ability score must be greater than zero.");
            return 0;
        }

        if(abilityScore % 2 == 0){ //even ability scores
            modifier = (int) Math.floor((abilityScore - 10) / 2);
        } else if (abilityScore % 2 != 0){ //odd ability scores
            modifier = (int) Math.floor((abilityScore - 11) / 2);
            //essentially calc mod for abilityScore - 1, which is even and has the same mod
        }

        return modifier;

    }

    public int rollInitiative(){
        int initiative;
        initiative = dice.rollDice(1, dice.d20) + this.abilityScores[1];
        return initiative;
    }

    @Override
    public void move(int distance) { //not going to do this yet, will have to essentially make a playing field first

    }

    @Override
    //determines an attack using an attack roll and applies damage
    public boolean attack(Creature target, int numberOfDamageDice, int damageDie) {
        int attackRoll;
        int damage;
        attackRoll = dice.rollDice(1, dice.d20) + this.getModifiers()[0] + this.proficiencyBonus;
        //uses STR for attack rolls, maybe add more methods to decide what mod to use? Could be linked to weapon
        if (attackRoll > target.getArmourClass()){ //check for hit
            damage = dice.rollDice(numberOfDamageDice, damageDie); //roll for damage
            System.out.println("Attack hits " + target.getName() + " for " + damage + " damage!");
            target.setCurrentHP(target.getCurrentHP() - damage); //apply damage

            if(target.getCurrentHP() <= 0){ //check for unconsciousness/death
                target.loseConsciousness();
            }

            return true;

        } else {
            System.out.println("Attack misses " + target.getName() + "!");
        }

        return false;
    }

    @Override
    public boolean heal(Creature target, int numberOfHealDice, int healDie) {
        int healAmount;

        if (!target.isAlive){
            System.out.println(target.getName() + " is dead and cannot be healed.");
            return false;
        } else {
            healAmount = dice.rollDice(numberOfHealDice, healDie) + this.getModifiers()[4]; //uses WIS mod for healing
            target.setCurrentHP(target.getCurrentHP() + healAmount);
            System.out.println(this.getName() + " healed " + target.getName() + " for " + healAmount + ". " +
                    target.getName() + " is now at " + target.getCurrentHP() + " HP.");
            return true;
        }
    }

    @Override
    public void loseConsciousness() {
        if (this.getCurrentHP() * (-1) >= this.getMaxHP()){
            this.die();
        } else {
            this.setCurrentHP(0);
            System.out.println(this.getName() + " was knocked unconscious.");
        }
    }

    @Override
    public void die() {
        System.out.println(this.getName() + " died.");
        this.setAlive(false);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public int getCreatureInitiative() {
        return creatureInitiative;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public int[] getAbilityScores() {
        return abilityScores;
    }

    public int[] getModifiers() {
        return modifiers;
    }

    public ArrayList<String> getNewAbilityScores() {
        return newAbilityScores;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setArmourClass(int armourClass) {
        this.armourClass = armourClass;
    }

    public void setCreatureInitiative(int creatureInitiative) {
        this.creatureInitiative = creatureInitiative;
    }

    public void setProficiencyBonus() {

        if(this.level >= 1 && this.level <= 4){
            this.proficiencyBonus = 2;
        } else if(this.level >= 5 && this.level <= 8){
            this.proficiencyBonus = 3;
        } else if(this.level >= 9 && this.level <= 12){
            this.proficiencyBonus = 4;
        } else if(this.level >=13 && this.level <= 16){
            this.proficiencyBonus = 5;
        } else if(this.level >= 17 && this.level <= 20){
            this.proficiencyBonus = 6;
        }
    }

    public void setAbilityScores(int[] abilityScores) {
        this.abilityScores = abilityScores;
    }

    public void setModifiers(int[] modifiers) {
        this.modifiers = modifiers;
    }

    public void setNewAbilityScores(ArrayList<String> newAbilityScores) {
        this.newAbilityScores = newAbilityScores;
    }
}
