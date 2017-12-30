package com.sdeshmukh.tools;

import com.sun.org.apache.xerces.internal.xs.StringList;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sdeshmukh on 24/12/2017.
 */
public class DataParser {
    private final List<String> stringList = new ArrayList<>();
    private final List<Integer> integerList = new ArrayList<>();

    //retains original list
    public void parseList(List<String> originalList){
        String intValue = "^[0-9]+";

        for(int index = 0; index < originalList.size(); ++index){
            Scanner intScanner = new Scanner(originalList.get(index));
            Scanner stringScanner = new Scanner(originalList.get(index));

            intScanner.useDelimiter(intValue);
            stringScanner.skip(intValue);

            while(intScanner.hasNextInt()){
                integerList.add(intScanner.nextInt());
            }

            while(stringScanner.hasNext()){
                stringList.add(stringScanner.next());
            }
        }
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void clearStringList(){
        stringList.clear();
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void clearIntList(){
        integerList.clear();
    }
}
