package com.sdeshmukh.generators.MagicEquipment;

import com.sdeshmukh.tools.Dice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sdeshmukh on 19/12/2017.
 */
public class MagicEquipment {
    private static MagicEquipment instance = null;

    private String name;
    private String type;
    private String creator;
    private String history;
    private String quirk;
    private String quirkDescription;
    private String minorProperty;
    private String minorPropertyDescription;
    private List<String> types;
    private List<String> creators;
    private List<String> histories;
    private List<String> minorProperties;
    private List<String> minorPropertyDescriptions;
    private List<String> quirks;
    private List<String> quirkDescriptions;
    private Map<String, String> minorPropertyMap = new HashMap<>();
    private Map<String, String> quirkMap = new HashMap<>();
    private Dice dice = new Dice();

    private MagicEquipment(){

    }

    public static MagicEquipment getInstance(){
        if(instance == null){
            instance = new MagicEquipment();
        }

        return instance;
    }

    public void consolidateName() {
        int quirkRoll;
        int minorPropertyRoll;

        if(this.getType().equalsIgnoreCase("random")){
            this.setType(types.get(dice.rollDice(1, types.size() - 1)));
        }

        quirkRoll = dice.rollDice(1, quirks.size() - 1);
        minorPropertyRoll = dice.rollDice(1, minorProperties.size() - 1);

        this.setCreator(creators.get(dice.rollDice(1, creators.size() - 1)));
        this.setHistory(histories.get(dice.rollDice(1, histories.size() - 1)));
        this.setQuirk(quirks.get(quirkRoll));
        this.setQuirkDescription(quirkDescriptions.get(quirkRoll));
        this.setMinorProperty(minorProperties.get(minorPropertyRoll));
        this.setMinorPropertyDescription(minorPropertyDescriptions.get(minorPropertyRoll));
        this.setName("The " + this.getQuirk() + " " + this.type + " of " + this.getMinorProperty() +
                ". Creator: " + this.getCreator() + ". History: " + this.getCreator() +
                "\n---------------------------------------------\n" +
                "This " + this.getType().toLowerCase() + " " + this.getQuirkMap().get(getQuirk()) +
                "\nAlso, this " + this.getType().toLowerCase() + " " + this.getMinorPropertyMap().get(getMinorProperty()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getQuirk() {
        return quirk;
    }

    public void setQuirk(String quirk) {
        this.quirk = quirk;
    }

    public String getQuirkDescription() {
        return quirkDescription;
    }

    public void setQuirkDescription(String quirkDescription) {
        this.quirkDescription = quirkDescription;
    }

    public String getMinorProperty() {
        return minorProperty;
    }

    public void setMinorProperty(String minorProperty) {
        this.minorProperty = minorProperty;
    }

    public String getMinorPropertyDescription() {
        return minorPropertyDescription;
    }

    public void setMinorPropertyDescription(String minorPropertyDescription) {
        this.minorPropertyDescription = minorPropertyDescription;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getCreators() {
        return creators;
    }
    public void setCreators(List<String> creators) {
        this.creators = creators;
    }

    public List<String> getHistories() {
        return histories;
    }

    public void setHistories(List<String> histories) {
        this.histories = histories;
    }

    public List<String> getMinorProperties() {
        return minorProperties;
    }

    public void setMinorProperties(List<String> minorProperties) {
        this.minorProperties = minorProperties;
    }

    public List<String> getMinorPropertyDescriptions() {
        return minorPropertyDescriptions;
    }

    public void setMinorPropertyDescriptions(List<String> minorPropertyDescriptions) {
        this.minorPropertyDescriptions = minorPropertyDescriptions;
    }

    public List<String> getQuirks() {
        return quirks;
    }

    public void setQuirks(List<String> quirks) {
        this.quirks = quirks;
    }

    public List<String> getQuirkDescriptions() {
        return quirkDescriptions;
    }

    public void setQuirkDescriptions(List<String> quirkDescriptions) {
        this.quirkDescriptions = quirkDescriptions;
    }

    public Map<String, String> getMinorPropertyMap() {
        return minorPropertyMap;
    }

    public void setMinorPropertyMap() {
        for(int index = 0; index < getMinorProperties().size() - 1; ++index){
            minorPropertyMap.put(getMinorProperties().get(index), getMinorPropertyDescriptions().get(index));
        }

    }

    public Map<String, String> getQuirkMap() {
        return quirkMap;
    }

    public void setQuirkMap() {
        for(int index = 0; index < getQuirks().size() - 1; ++index){
            quirkMap.put(getQuirks().get(index), getQuirkDescriptions().get(index));
        }
    }
}
