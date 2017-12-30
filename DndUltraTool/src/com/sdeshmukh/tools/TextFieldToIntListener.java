package com.sdeshmukh.tools;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created by sdeshmukh on 21/12/2017.
 */
public class TextFieldToIntListener implements ChangeListener<String>{
    private final TextField textField;

    public TextFieldToIntListener(TextField textField){
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(!newValue.matches("\\d")){
            this.textField.setText(newValue.replaceAll("[^\\d]", ""));
        }
        if(newValue.length() > 2){
            this.textField.deleteText(2,textField.getLength());
        }
    }
}
