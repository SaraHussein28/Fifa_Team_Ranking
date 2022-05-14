package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HelloController {

    ObservableList <String> listOfTeams = FXCollections.observableArrayList("Egypt", "Italy", "England");
    ObservableList <String> teams = FXCollections.observableArrayList("Team 1", "Team 2");
    ObservableList <String> listOfRounds = FXCollections.observableArrayList("Group Stage","Round of 16", "Quarter-finals", "Semi-finals", "Final");
    ObservableList <String> listOfCategories = FXCollections.observableArrayList("Friendly match", "Nations League", "Qualifier for Confederation final competition", "Qualifier for World Cup", "Confederation final competition", "Confederations Cup", "World Cup");

    @FXML
    private Button showFIFARankingBtn;
    @FXML
    private Button addMatchBtn;
    @FXML
    private ChoiceBox team1_choice_box;
    @FXML
    private ChoiceBox team2_choice_box;
    @FXML
    private ChoiceBox round_choice_box;
    @FXML
    private ChoiceBox category_choice_box;
    @FXML
    private CheckBox calender_check_box;
    @FXML
    private CheckBox shootout_check_box;

    @FXML
    private Label choose_winner_txt;

    @FXML
    private ChoiceBox winner_choice_box;
    @FXML
    private DatePicker date_picker;

    @FXML
    protected void initialize(){

        initializeInputValues();
    }

    private void initializeInputValues() {


        team1_choice_box.setValue("Egypt");
        team1_choice_box.setItems(listOfTeams);


        team2_choice_box.setValue("England");
        team2_choice_box.setItems(listOfTeams);

        round_choice_box.setValue("Group Stage");
        round_choice_box.setItems(listOfRounds);


        category_choice_box.setValue("Friendly match");
        category_choice_box.setItems(listOfCategories);

        winner_choice_box.setValue("Team 1");
        winner_choice_box.setItems(teams);
        choose_winner_txt.setVisible(false);
        winner_choice_box.setVisible(false);

        date_picker.setValue(LocalDate.now());
    }

    @FXML
    protected void onCategoryDragDone(){
        if (category_choice_box.getValue().equals("Friendly match")){
            calender_check_box.setDisable(false);
            round_choice_box.setDisable(true);
        }
        else {
            round_choice_box.setDisable(false);
            calender_check_box.setSelected(false);
            calender_check_box.setDisable(true);
        }

    }
    @FXML
    protected void onAddMatchButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("popupWindow.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Match is added");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(addMatchBtn.getScene().getWindow());
        primaryStage.show();
    }

    @FXML
    protected void OnShootoutSelected (){
        if (shootout_check_box.isSelected()){
            choose_winner_txt.setVisible(true);
            winner_choice_box.setVisible(true);
        }
        else {
            choose_winner_txt.setVisible(false);
            winner_choice_box.setVisible(false);
        }

    }

    @FXML
    protected void onShowRankingButtonClick () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("team-ranking-view.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("FIFA Ranking History");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(showFIFARankingBtn.getScene().getWindow());
        primaryStage.show();
    }




}