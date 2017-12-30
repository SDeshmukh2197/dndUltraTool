package com.sdeshmukh.generators.CharacterCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdeshmukh on 15/08/2017.
 */
public class MonsterParty {
    private List<Monster> monsterParty = new ArrayList<>();

    public boolean addMonster(Monster monster){
        if(monster == null){
            System.out.println("Monster does not exist.");
            return false;
        }

        monsterParty.add(monster);
        System.out.println(monster.getName() + " added.");
        return true;
    }

    public void printMonsterParty(){
        for(int index = 0; index < monsterParty.size(); ++index){
            System.out.println((index + 1) + ". " + monsterParty.get(index).getName());
        }
    }

    public List<Monster> getMonsterParty() {
        return monsterParty;
    }

    public int getSize(){
        return this.monsterParty.size();
    }
}
