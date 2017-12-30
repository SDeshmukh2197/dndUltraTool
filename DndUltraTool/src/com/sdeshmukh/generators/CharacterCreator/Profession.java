package com.sdeshmukh.generators.CharacterCreator;

/**
 * Created by sdeshmukh on 08/08/2017.
 */
public class Profession {
    private String professionName;
    private int hitDie;
    private int levels;

    public Profession(String professionName, int hitDie, int levels) {
        this.professionName = professionName;
        this.hitDie = hitDie;
        this.levels = levels;
    }
    //now I can either make each class (fighter, ranger, etc) a subclass of this or try and do it all in one class
    //if the latter, I need an identifier, could just be the name
    //still not sure what the benefit of making subclasses would be, but one example is making archetypes
    //not sure if I can do archetypes all in one class
}
