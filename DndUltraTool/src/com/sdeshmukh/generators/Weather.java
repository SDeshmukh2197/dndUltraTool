package com.sdeshmukh.generators;

import com.sdeshmukh.tools.Dice;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sdeshmukh on 09/07/2017.
 */
public class Weather {
    //ideally, this will roll on a random table (file) depending on the season.

    private static Weather instance = null;

    private Dice dice = new Dice();
//    private ReadFile readFile = new ReadFile();
    private List<String> weatherList;
    private List<String> somethingHappens;

    private Weather(){

    }

    public static Weather getInstance(){
        if(instance == null){
            instance = new Weather();
        }

        return instance;
    }

//    public Weather() {
//        try{
//            weather = readFile.openFile("weather.txt");
//        } catch(IOException ex){
//            ex.getMessage();
//        }
//    }

    public List rollWeather(int numberOfDays){
        //also make a seasons drop-down box that changes which file this method reads
        String dayWeather = "DEFAULT";
        int consecutiveStormDays = 0;
        int stormChance;

        for(int index = 0; index < numberOfDays; ++index){
            //"smart" weather generation; i.e. weather patterns
            //if the previous day contained the word "storm", (50/consecutiveStormDays)% chance of "Storm continues"
            if (dayWeather.equalsIgnoreCase("storm")){
                consecutiveStormDays++;
                stormChance = (int) 0.5/consecutiveStormDays * 100;

                if(dice.rollDice(1, dice.d100) < stormChance){
                    dayWeather = "Storm continues.";
                }

            } else {
                dayWeather = dayWeather.replace("Storm", "Cloudy"); //temp fix to avoid storm loops
            }
                weatherList.add(dayWeather);
        }

        return weatherList;
    }

    public void printWeather(List<String> weather){
        for(int index = 0; index < weather.size(); ++index){
            System.out.println("Day " + (index + 1 ) + ": " + weather.get(index));
        }
        //have an option here to save to a file
    }

    public boolean hasSearchParameter(String line, String searchParameter){
        String[] words = line.split(" |\\)|\\(");
        if(Arrays.asList(words).contains(searchParameter)){
            return true;
        } else {
            return false;
        }
    }

    public void setWeather(List<String> weather) {
        this.weatherList = weather;
    }

    public List<String> getWeatherList() {
        return weatherList;
    }

    public void setSomethingHappens(List<String> somethingHappens){
        this.somethingHappens = somethingHappens;
    }

    public List<String> getSomethingHappens() {
        return somethingHappens;
    }

    public String generateEvent(){
        String somethingHappens = this.somethingHappens.get(dice.rollDice(1, getSomethingHappens().size() - 1));

        return somethingHappens;
    }
}
