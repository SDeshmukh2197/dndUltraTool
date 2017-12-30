package com.sdeshmukh;

import com.sdeshmukh.tools.TextFieldToIntListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdeshmukh on 21/12/2017.
 */
public class CharacterCreatorDialogController {

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private ComboBox<String> raceComboBox;

    @FXML
    private ComboBox<String> statOptionComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private VBox statEntry;

    @FXML
    private TextField strength;

    @FXML
    private TextField dexterity;

    @FXML
    private TextField constitution;

    @FXML
    private TextField intelligence;

    @FXML
    private TextField wisdom;

    @FXML
    private TextField charisma;

    @FXML
    private Button statSaveButton;

    @FXML
    private Label statSaveLabel;

    private List<TextField> abilityScores = new ArrayList<>();

    private List<String> savedScores = new ArrayList<>();


    @FXML
    public void initialize(){

        abilityScores.add(strength);
        abilityScores.add(dexterity);
        abilityScores.add(constitution);
        abilityScores.add(intelligence);
        abilityScores.add(wisdom);
        abilityScores.add(charisma);

        statEntry.setDisable(true);

        genderComboBox.getSelectionModel().selectFirst();
        raceComboBox.getSelectionModel().selectFirst();
        statOptionComboBox.getSelectionModel().selectFirst();

        statOptionComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("No ability scores")){
                    statEntry.setDisable(false);
                } else {
                    statEntry.setDisable(true);
                }
            }
        });

        for(int index = 0; index < abilityScores.size(); ++index){
            abilityScores.get(index).textProperty().addListener(new TextFieldToIntListener(abilityScores.get(index)));
            abilityScores.get(index).setText("10");
        }
        //only allows integers to be entered in the text fields
    }

    public void handleSaveStats(){
        for(int index = 0; index < savedScores.size(); ++index){
            savedScores.set(index, abilityScores.get(index).toString());
        }

        statSaveLabel.setText("Ability scores saved.");
    }

    public void handleRandomName(){

    }
}
