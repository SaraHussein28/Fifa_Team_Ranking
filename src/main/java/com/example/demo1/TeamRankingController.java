package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class TeamRankingController {
    @FXML
    private ChoiceBox month_choice_box;

    @FXML
    protected void initialize(){
        initializeChoiceBoxes();
    }

    private void initializeChoiceBoxes() {
        ObservableList<String> listOfMonths = FXCollections.observableArrayList("April-2022", "March-2022", "February-2022", "January-2022", "December-2021", "November-2021", "October-2021", "September-2021", "August-2021", "July-2021", "June-2021", "May-2021", "April-2021");

        month_choice_box.setValue("April-2022");
        month_choice_box.setItems(listOfMonths);

 }
}
