package com.sdeshmukh.generators.CharacterCreator;

import com.sdeshmukh.generators.CharacterCreator.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdeshmukh on 06/07/2017.
 */
public class Party {
    private List<Character> party = new ArrayList<>();
    private String partyName;

    public Party(String partyName) {
        this.partyName = partyName;
    }

    public boolean addPlayer(Character character){
        if (character == null) {
            System.out.println("Character does not exist.");
            return false;
        }

        party.add(character);
        System.out.println(character.getName() + " added to " + this.getPartyName());
        return true;
    }

    public void printParty(){
        System.out.println(this.getPartyName() + ":");
        for(int index = 0; index < party.size(); ++index){
            System.out.println(party.get(index).getName() + ", Level " + party.get(index).getLevel());
        }
    }

    public String getPartyName() {
        return partyName;
    }

    public List<Character> getParty() {
        return party;
    }

    public int getSize(){
        return this.party.size();
    }
}
