package com.sdeshmukh.masscombat;

import java.util.*;

/**
 * Created by sdeshmukh on 16/07/2017.
 */
public class BattleUnit {
    private String name;
    public List <BattleUnitMember> unit = new ArrayList<>();
    private final int maximumSpace = 400;
    private int totalSpace;
    private int unitMorale;

    public BattleUnit(String name, int unitMorale) {
        this.name = name;
        this.totalSpace = 0;
        this.unitMorale = unitMorale; //need some way to find which member is the commander and add its CHA mod
    }

    public boolean addBattleUnitMember(BattleUnitMember member){
        if(unit.contains(member)){
            System.out.println(member.getNumber() + " " + member.getName() + " is already in this unit.");
            return false;
        }

        if(member.getSize() + this.totalSpace > maximumSpace){
            System.out.println("Member is too large (member space is " + member.getSpace());
            return false;
        }

        this.totalSpace += member.getSpace();
        unit.add(member);
        System.out.println(member.getNumber() + " " + member.getName() + " added to unit.");
        return true;
    }

    public boolean removeBattleUnitMember(BattleUnitMember member){
        if(!unit.contains(member)){
            System.out.println(member.getNumber() + " " + member.getName() + " does not exist in this unit.");
            return false;
        }

        totalSpace -= member.getSpace();
        unit.remove(member);
        System.out.println(member.getNumber() + " " + member.getName() + " removed from unit.");
        return true;
    }

    public void printUnit(){
        System.out.println(this.getName() + " consists of: ");

        for(int index = 0; index < unit.size(); ++index){
            System.out.println(unit.get(index).getNumber() + " " + unit.get(index).getName() + "s.");
        }

        System.out.println("Total unit space is " + this.getTotalSpace() + " out of " + this.getMaximumSpace());
    }

    public String getName() {
        return this.name;
    }

    public int getTotalSpace() {
        return this.totalSpace;
    }

    public int getMaximumSpace() {
        return maximumSpace;
    }
}
