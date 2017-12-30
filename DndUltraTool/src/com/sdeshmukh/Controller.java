package com.sdeshmukh;

import com.sdeshmukh.generators.CharacterCreator.Character;
import com.sdeshmukh.generators.MagicEquipment.MagicEquipment;
import com.sdeshmukh.generators.Treasure;
import com.sdeshmukh.generators.Weather;
import com.sdeshmukh.tools.Dice;
import com.sdeshmukh.tools.TextFieldToIntListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by sdeshmukh on 18/12/2017.
 */
public class Controller {
    private Dice dice = new Dice();

    @FXML
    private TabPane mainTabPane;

    @FXML
    private GridPane characterGridPane;

    @FXML
    private GridPane lootGridPane;

    @FXML
    private CheckBox isMaleCheckBox;

    @FXML
    private CheckBox isAdventurerCheckBox;

    @FXML
    private ComboBox<String> randomCharacterRacePicker;

    @FXML
    private TextArea characterTextArea;

    @FXML
    private TextField lootCRTextField; //this needs to be an integer field instead, i.e. only accepts integer input

    @FXML
    private CheckBox isHoardCheckBox;

    @FXML
    private TextArea lootTextArea;

    @FXML
    private GridPane magicEquipmentGridPane;

    @FXML
    private ComboBox<String> equipmentPicker;

    @FXML
    private TextArea magicEquipmentTextArea;

    @FXML
    private Label somethingHappensLabel;


    public void initialize(){
        randomCharacterRacePicker.getSelectionModel().selectFirst();

        lootCRTextField.textProperty().addListener(new TextFieldToIntListener(this.lootCRTextField)); //this bit of code makes it so the TextField for challenge rating only allows integers to be entered
        lootCRTextField.setText("1");

        equipmentPicker.getItems().clear();
        equipmentPicker.getItems().add("Random");
        equipmentPicker.getItems().addAll(MagicEquipment.getInstance().getTypes());
        equipmentPicker.getSelectionModel().selectFirst();
    }

    @FXML
    public void showCharacterCreatorDialog(){
        Dialog<ButtonType> characterDialog = new Dialog<>();
        characterDialog.initModality(Modality.APPLICATION_MODAL);
        characterDialog.initOwner(mainTabPane.getScene().getWindow());

        try{
            Parent root = FXMLLoader.load(getClass().getResource("FXMLFiles/characterCreatorDialog.fxml"));
            characterDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        characterDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        characterDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = characterDialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){

        } else {
            System.out.println("User cancelled character creation.");
        }
    }

    //not sure if I should make the random generator classes singletons? Especially in the case of treasure and weather
    @FXML
    public void handleRandomCharacter(){
        boolean isMale = ((isMaleCheckBox.isSelected()));
        boolean isAdventurer = ((isAdventurerCheckBox.isSelected()));

        Character randomCharacter = new Character(isMale, isAdventurer);
        randomCharacter.setRace(randomCharacterRacePicker.getSelectionModel().getSelectedItem());
        randomCharacter.generateCharacter();
        characterTextArea.setText(randomCharacter.getCharacter());
    }

    @FXML
    public void handleTreasure(){
        Treasure.getInstance().setChallengeRating(Double.parseDouble(lootCRTextField.getText()));

        if(isHoardCheckBox.isSelected()){
            lootTextArea.setText(Treasure.getInstance().hoardTreasure());
        } else {
            lootTextArea.setText(Treasure.getInstance().individualTreasure());
        }
    }

    @FXML
    public void handleRandomMagicEquipment(){
        MagicEquipment.getInstance().setType(equipmentPicker.getSelectionModel().getSelectedItem());
        MagicEquipment.getInstance().consolidateName();
        magicEquipmentTextArea.setText(MagicEquipment.getInstance().getName());
    }

    @FXML
    public void handleSomethingHappens(){
        somethingHappensLabel.setText(Weather.getInstance().generateEvent());
    }
}
