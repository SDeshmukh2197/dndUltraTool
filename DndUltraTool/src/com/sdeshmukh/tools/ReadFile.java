package com.sdeshmukh.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdeshmukh on 20/08/2017.
 */
public class ReadFile {

    public List<String> openFile(String path) throws IOException{
        FileReader fileReader = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fileReader);

        int numberOfLines = readLines(path);
        List<String> textData = new ArrayList<>();

        for(int index = 0; index < numberOfLines; ++index){ //read out file line by line & save to temp memory buffer
            textData.add(textReader.readLine()); //buffered reader very usefully comes with readLine method
        }

        textReader.close(); //flushes temp memory buffer

//        for(int index = 0; index < textData.length; ++index){
//            System.out.println(textData[index]);
//        }

        return textData;
    }

    public List<String> openAlternateLines(String path, boolean isOdd) throws IOException{
        FileReader fileReader = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fileReader);
        String line;

        int numberOfLines = readLines(path);
        List<String> oddTextData = new ArrayList<>();
        List<String> evenTextData = new ArrayList<>();

        for(int index = 0; index < numberOfLines; ++index){
            line = textReader.readLine();

            if(isOdd){
                if(index % 2 != 0){
                    oddTextData.add(line);
                } else {
                    evenTextData.add(line);
                }
            } else {
                if(index % 2 == 0){
                    evenTextData.add(line);
                } else {
                    oddTextData.add(line);
                }
            }
        }

        if(isOdd){
            return oddTextData;
        } else {
            return evenTextData;
        }
    }

    private int readLines(String path) throws IOException{
        FileReader fileToRead = new FileReader(path);
        BufferedReader bf = new BufferedReader(fileToRead);

        String line;
        int numberOfLines = 0;

        while((line = bf.readLine()) != null){ //read each line of text & stop when null reached
            numberOfLines++;
        }

        bf.close(); //flush bf

        return numberOfLines;
    }
}
