package com.sdeshmukh.generators.CharacterCreator;

import com.sdeshmukh.Main;
import com.sdeshmukh.tools.Dice;
import com.sdeshmukh.tools.ReadFile;

import java.util.*;

/**
 * Created by sdeshmukh on 01/07/2017.
 */
public class Character extends Creature {
    private Dice dice = new Dice();
    private boolean isAdventurer = false; //if this is true, will roll stats & ask for level
    private List <String> maleNames = Main.maleNames;
    private List <String> femaleNames = Main.femaleNames;
    private List <String> familyNames = Main.familyNames;
    private List <String> races = Main.raceNames;
    private int[] abilityChanges;
    private List<int[]> abilityChangesList = Main.raceAbilityChanges;
    private Map <String, int[]> raceMap = new HashMap<>();
    private List<String> ideals = Main.ideals;
    private List<String> bonds = Main.bonds;
    private List<String> flaws = Main.flaws;
    private List<String> interactions = Main.interactions;
    private List<String> occupations = Main.occupation;
    private List<String> appearances = Main.appearance;
    private List<String> talents = Main.talents;
    private List<String> usefulKnowledges = Main.usefulKnowledge;
    private List<String> hairTypes = Main.hairType;
    private String race;
    private String description;
    private boolean isMale = true;
    private int experiencePoints; //using 3 pillar xp so level up is at 100 xp then xp goes back to 0

    public Character(String name, String race, int maxHP, int level, int armourClass, int experiencePoints, Profession profession) {
        super(name, maxHP, level, armourClass); //constructor from Creature
        this.experiencePoints = experiencePoints;
        this.race = race;
    }

    public Character(boolean isMale, boolean isAdventurer) { //for npc
        super(10, 1); //make editors for this later
        this.isMale = isMale;
        this.isAdventurer = isAdventurer;
        for(int index = 0; index < races.size(); ++index){
            raceMap.put(races.get(index), abilityChanges);
        }
    }

    private String rollForRace(){
        int line = dice.rollDice(1, races.size()) - 1;
        String race = races.get(line);

        return race;
    }

    private String rollForName(boolean isMale, String searcher){
        List<String> firstSearchList = new ArrayList<>(); //list of first names to roll on
        List<String> familySearchList = new ArrayList<>(); //list of family names to roll on
        String name = "";
        String searchParameter = "(" + searcher + ")"; //searching lists to filter by race
        //System.out.println(searchParameter);

        for(int index = 0; index < races.size(); ++index) { //going through the list of originalRaces
            if (this.getRace().equalsIgnoreCase(races.get(index)) && isMale) {//if list race matches NPC race & is male
                for(int secondIndex = 0; secondIndex < maleNames.size(); ++secondIndex){ //going through list of male names
                    if(hasSearchParameter(maleNames.get(secondIndex), searchParameter)){
                        firstSearchList.add(maleNames.get(secondIndex)); //add line from maleNames to firstSearchList
                    }
                }
            } else if (this.getRace().equalsIgnoreCase(races.get(index)) && !isMale) { //if list race matches female NPC race
                for(int secondIndex = 0; secondIndex < femaleNames.size(); ++secondIndex){ //go through list of female names
                    if(hasSearchParameter(femaleNames.get(secondIndex), searchParameter)){ //filter by race
                        firstSearchList.add(femaleNames.get(secondIndex)); //add line from femaleNames to firstSearchList
                    }
                }
            }

            if(this.getRace().equalsIgnoreCase(races.get(index))){ //if list race matches NPC race, regardless of gender
                for(int secondIndex = 0; secondIndex < familyNames.size(); ++secondIndex){ //go through list of family names
                    if(hasSearchParameter(familyNames.get(secondIndex), searchParameter)){ //filter by race
                        familySearchList.add(familyNames.get(secondIndex)); //add line from familyNames to familySearchList
                    }
                }
            }
        }

//        System.out.println(this.getRace()); //diagnostic

        name += firstSearchList.get(dice.rollDice(1, firstSearchList.size() - 1)) + " ";//roll for first name
        name = removeSearchParameter(name, searchParameter); //remove race and brackets from the name line
//        System.out.println(name); //diagnostic
        name += familySearchList.get(dice.rollDice(1, familySearchList.size() - 1)); //roll for family name
        name = removeSearchParameter(name, searchParameter); //remove race and brackets from the name line


        return name;
    }

    private String rollForDescription(){
        String ideal = ideals.get(dice.rollDice(1, ideals.size() - 1));
        String bond = bonds.get(dice.rollDice(1, bonds.size() - 1));
        String flaw = flaws.get(dice.rollDice(1, flaws.size() - 1));
        String interaction = interactions.get(dice.rollDice(1, interactions.size() - 1));
        String occupation = occupations.get(dice.rollDice(1, occupations.size() - 1));
        String appearance = appearances.get(dice.rollDice(1, appearances.size() - 1));
        String talent = talents.get(dice.rollDice(1, talents.size() - 1));
        String usefulKnowledge = usefulKnowledges.get(dice.rollDice(1, usefulKnowledges.size() - 1));
        String hairType = hairTypes.get(dice.rollDice(1, hairTypes.size() - 1));

        String desc = "Ideal: " + ideal + " " +
                "\nBond: " + bond + " " +
                "\nFlaw: " + flaw + " " +
                "\nInteraction with Others: " + interaction + " " +
                "\nOccupation: " + occupation + " " +
                "\nAppearance: " + appearance + " " +
                "\nTalent: " + talent + " " +
                "\nUseful Knowledge: " + usefulKnowledge + " " +
                "\nHair type: " + hairType + "\n";

        return desc;
    }

    public void generateCharacter(){
        int dummyAbilityScores[] = new int[6];
        ArrayList<String> abilityScores = new ArrayList<>();

        if(this.race.equalsIgnoreCase("random")){
            this.race = rollForRace();
        }

        this.setName(rollForName(isMale(), this.getRace()));

        if(isAdventurer){ //rolls stats, sets proficiency bonus
            this.setProficiencyBonus();

            for(int index = 0; index < dummyAbilityScores.length; ++index){
                dummyAbilityScores[index] = generateAbilityScore();
                abilityScores.add(dummyAbilityScores[index] + " (" + this.findModifier(dummyAbilityScores[index]) + ")");
            }

            this.setNewAbilityScores(abilityScores);
        }

        this.setDescription(rollForDescription());
    }

    public String getCharacter(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getName() + "(" + this.getRace().toLowerCase() + "). ");
        stringBuilder.append(this.getDescription());

        if(this.isAdventurer()){
            stringBuilder.append( "\nLevel: " + this.getLevel() +
                    "\nMax HP: " + this.getMaxHP() +
                    "\nProf. Bonus: " + this.getProficiencyBonus() +
                    "\nStrength: " + this.getNewAbilityScores().get(0) +
                    "\nDexterity: " + this.getNewAbilityScores().get(1) +
                    "\nConstitution: " + this.getNewAbilityScores().get(2) +
                    "\nIntelligence: " + this.getNewAbilityScores().get(3) +
                    "\nWisdom: " + this.getNewAbilityScores().get(4) +
                    "\nCharisma: " + this.getNewAbilityScores().get(5)
                    + "\n========================");
        }

        return stringBuilder.toString();
    }

    public void levelUp(Character player){
        player.setLevel(player.getLevel() + 1);
        setExperiencePoints(0);
        System.out.println(player.getName() + " leveled up to level " + player.getLevel());
    }

    public void levelDown(Character player){
        player.setLevel(player.getLevel() + 1);
        System.out.println(player.getName() + " leveled down to level " + player.getLevel());
    }

    private boolean hasSearchParameter(String line, String searchParameter){
        String[] words = line.split(" "); //split line into words at spaces

        if(Arrays.asList(words).contains(searchParameter)){
            return true;
        } else {
            return false;
        }
    }

    private String removeSearchParameter(String line, String searchParameter){
        String[] words = line.split(" ");
        String editedLine = "";

        for(int index = 0; index < words.length; ++index){
            if(!hasSearchParameter(words[index], searchParameter)){
                editedLine += words[index] + " ";
            }
        }

        return editedLine;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public String getRace() {
        return race;
    }

    public void setAbilityChanges(){

    }

    public boolean isMale() {
        return isMale;
    }

    public boolean isAdventurer() {
        return isAdventurer;
    }

    public void setExperiencePoints(int xp) {
        this.experiencePoints = xp;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
