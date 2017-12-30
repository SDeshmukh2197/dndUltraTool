package com.sdeshmukh;


import com.sdeshmukh.generators.MagicEquipment.MagicEquipment;
import com.sdeshmukh.generators.Weather;
import com.sdeshmukh.tools.ReadFile;
import javafx.application.Application;
import com.sdeshmukh.generators.Treasure;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


public class Main extends Application {

    private static ReadFile file = new ReadFile();

    private static List<String> gems;
    private static List<String> artObjects;
    private static List<String> magicItemTableA;
    private static List<String> magicItemTableB;
    private static List<String> magicItemTableC;
    private static List<String> magicItemTableD;
    private static List<String> magicItemTableE;
    private static List<String> magicItemTableF;
    private static List<String> magicItemTableG;
    private static List<String> magicItemTableH;
    private static List<String> magicItemTableI;

    public static List<String> raceNames = new ArrayList<>();
    public static List<String> ideals = new ArrayList<>();
    public static List<String> bonds = new ArrayList<>();
    public static List<String> flaws = new ArrayList<>();
    public static List<String> interactions = new ArrayList<>();
    public static List<String> occupation = new ArrayList<>();
    public static List<String> appearance = new ArrayList<>();
    public static List<String> talents = new ArrayList<>();
    public static List<String> usefulKnowledge = new ArrayList<>();
    public static List<String> hairType = new ArrayList<>();
    public static List<String> raceAbilitiesList = new ArrayList<>();
    public static List<int[]> raceAbilityChanges = new ArrayList<>();
    public static List <String> maleNames = new ArrayList<>();
    public static List <String> femaleNames = new ArrayList<>();
    public static List <String> familyNames = new ArrayList<>();

    private static List<String> types;
    private static List<String> creators;
    private static List<String> histories;
    private static List<String> minorProperties;
    private static List<String> minorPropertyDescriptions;
    private static List<String> quirks;
    private static List<String> quirkDescriptions;

    private static List<String> weatherList;
    private static List<String> somethingHappens; //this is inside weather since public declaration doesn't seem to work

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFiles/mainWindow.fxml"));
        primaryStage.setTitle("Dungeons & Dragons 5th Edition Ultra Tool");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {

        try{
            gems = file.openFile("gemsAndArt/gems.txt");
            artObjects = file.openFile("gemsAndArt/artObjects.txt");
            magicItemTableA = file.openFile("magicItemTables/mta.txt");
            magicItemTableB = file.openFile("magicItemTables/mtb.txt");
            magicItemTableC = file.openFile("magicItemTables/mtc.txt");
            magicItemTableD = file.openFile("magicItemTables/mtd.txt");
            magicItemTableE = file.openFile("magicItemTables/mte.txt");
            magicItemTableF = file.openFile("magicItemTables/mtf.txt");
            magicItemTableG = file.openFile("magicItemTables/mtg.txt");
            magicItemTableH = file.openFile("magicItemTables/mth.txt");
            magicItemTableI = file.openFile("magicItemTables/mti.txt");

            maleNames = file.openFile("npc/maleNames.txt");
            femaleNames = file.openFile("npc/femaleNames.txt");
            familyNames = file.openFile("npc/familyNames.txt");
            raceNames = file.openAlternateLines("npc/races.txt", false);
            raceAbilitiesList = file.openAlternateLines("npc/races.txt", true);
            ideals = file.openFile("npc/ideals.txt");
            bonds = file.openFile("npc/bonds.txt");
            flaws = file.openFile("npc/flaws.txt");
            interactions = file.openFile("npc/interactions.txt");
            occupation = file.openFile("npc/occupation.txt");
            appearance = file.openFile("npc/appearance.txt");
            talents = file.openFile("npc/talents.txt");
            usefulKnowledge = file.openFile("npc/usefulKnowledge.txt");
            hairType = file.openFile("npc/hairType.txt");

            types = file.openFile("magicQuirks/type.txt");
            creators = file.openFile("magicQuirks/creator.txt");
            histories = file.openFile("magicQuirks/history.txt");
            minorProperties = file.openAlternateLines("magicQuirks/minorProperty.txt", false);
            minorPropertyDescriptions = file.openAlternateLines("magicQuirks/minorProperty.txt", true);
            quirks = file.openAlternateLines("magicQuirks/quirk.txt", false);
            quirkDescriptions = file.openAlternateLines("magicQuirks/quirk.txt", true);
            //the true/false logic here is actually inverted since index and line number are displaced by 1 for some reason
            //if I figure out how to fix it, fix this logic...
            weatherList = file.openFile("weather.txt");

            somethingHappens = file.openFile("somethingHappens.txt");
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        //variables for scanning ability score changes from a list
        Scanner raceScanner;
        int[] dummy = new int[6];
        String[] pieces = new String[dummy.length];
        String data;

        for(int index = 0; index < raceAbilitiesList.size(); ++index){
            raceScanner = new Scanner(raceAbilitiesList.get(index));

            if(raceScanner.hasNextLine()){
                data = raceScanner.next();
                pieces = data.split(",");
            }

            for(int secondIndex = 0; secondIndex < dummy.length; ++secondIndex){
                dummy[secondIndex] = Integer.parseInt(pieces[secondIndex]);
            }

            raceAbilityChanges.add(dummy);
        }

        Treasure.getInstance().setArtObjects(artObjects);
        Treasure.getInstance().setGems(gems);
        Treasure.getInstance().setMagicItemTableA(magicItemTableA);
        Treasure.getInstance().setMagicItemTableB(magicItemTableB);
        Treasure.getInstance().setMagicItemTableC(magicItemTableC);
        Treasure.getInstance().setMagicItemTableD(magicItemTableD);
        Treasure.getInstance().setMagicItemTableE(magicItemTableE);
        Treasure.getInstance().setMagicItemTableF(magicItemTableF);
        Treasure.getInstance().setMagicItemTableG(magicItemTableG);
        Treasure.getInstance().setMagicItemTableH(magicItemTableH);
        Treasure.getInstance().setMagicItemTableI(magicItemTableI);

        MagicEquipment.getInstance().setTypes(types);
        MagicEquipment.getInstance().setCreators(creators);
        MagicEquipment.getInstance().setHistories(histories);
        MagicEquipment.getInstance().setQuirks(quirks);
        MagicEquipment.getInstance().setQuirkDescriptions(quirkDescriptions);
        MagicEquipment.getInstance().setMinorProperties(minorProperties);
        MagicEquipment.getInstance().setMinorPropertyDescriptions(minorPropertyDescriptions);
        MagicEquipment.getInstance().setQuirkMap();
        MagicEquipment.getInstance().setMinorPropertyMap();

        Weather.getInstance().setWeather(weatherList);
        Weather.getInstance().setSomethingHappens(somethingHappens);

        launch(args);
    }
}

//public class Main {
//
//    public static void main(String[] args) {
//        Dice dice = new Dice();
////        Profession fighter = new Profession("Fighter", dice.d10);
////        Character john = new Character(true, true);
////        Character fred = new Character("Fred", "human", 20, 3, 16, 40, fighter);
////
////        john.printCharacter();
////        fred.printCharacter();
////
////        Party voxMachina = new Party("Vox Machina");
////        voxMachina.addPlayer(john);
////        voxMachina.addPlayer(fred);
////        voxMachina.printParty();
////
////        john.rollDice(1, Dice.d20);
////
////        BattleUnitMember orc = new BattleUnitMember("Orc", 3, 0.5, 400);
////        BattleUnitMember goblin = new BattleUnitMember("Goblin", 2, 0.25, 100);
////        BattleUnitMember bear = new BattleUnitMember("Bear", 4, 1, 50);
////
////        BattleUnit morlakeOne = new BattleUnit("Morlake One");
////        morlakeOne.addBattleUnitMember(orc);
////        System.out.println(morlakeOne.getName() + " current total space is " + morlakeOne.getTotalSpace());
////        morlakeOne.addBattleUnitMember(goblin);
////        System.out.println(morlakeOne.getName() + " current total space is " + morlakeOne.getTotalSpace());
////        morlakeOne.removeBattleUnitMember(orc);
////        System.out.println(morlakeOne.getName() + " current total space is " + morlakeOne.getTotalSpace());
////        morlakeOne.addBattleUnitMember(goblin);
////        System.out.println(morlakeOne.getName() + " current total space is " + morlakeOne.getTotalSpace());
////        morlakeOne.addBattleUnitMember(bear);
////        System.out.println(morlakeOne.getName() + " current total space is " + morlakeOne.getTotalSpace());
////        morlakeOne.printUnit();
//
//        Treasure treasure = new Treasure();
//        treasure.individualTreasure(17);
//        treasure.hoardTreasure(17);
//
//    }
//
//    public static void combatSim(Party fullParty, MonsterParty fullMonsterParty){
//        List<Character> party = fullParty.getParty();
//        List<Monster> monsterParty = fullMonsterParty.getMonsterParty();
//        List<Creature> initiativeOrder = new ArrayList<>();
//
//        for(int index = 0; index < party.size(); ++index){ //rolling player initiative & adding to initiativeOrder
//            party.get(index).setCreatureInitiative(party.get(index).rollInitiative());
//            initiativeOrder.add(party.get(index));
//        }
//
//        for(int index = 0; index < monsterParty.size(); ++index){ //rolling monster initiative & adding to initiativeOrder
//            monsterParty.get(index).setCreatureInitiative(monsterParty.get(index).rollInitiative());
//            initiativeOrder.add(monsterParty.get(index));
//        }
//
//        //initiativeOrder.sort(Comparator.comparingInt(Creature::getCreatureInitiative));
//        Collections.sort(initiativeOrder, new Comparator<Creature>() { //sorts list by initiative in ascending order
//            @Override
//            public int compare(Creature creature1, Creature creature2) {
//                return Integer.compare(creature1.getCreatureInitiative(), creature2.getCreatureInitiative());
//            }
//        });
//        Collections.reverse(initiativeOrder); //put list in descending order
//
//        for(;;){
//            for(int index = 0; index < initiativeOrder.size(); index++){
//
//            }
//        }
//    }
//}
//
//                  everything commented above this point was for a console based application

