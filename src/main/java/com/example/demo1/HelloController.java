package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label teamIsAddedText;
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
    protected void initialize(){
        initializeChoiceBoxes();
    }

    private void initializeChoiceBoxes() {
        ObservableList <String> listOfTeams = FXCollections.observableArrayList("Egypt", "Italy", "England");
        ObservableList <String> listOfRounds = FXCollections.observableArrayList("Quarter-finals", "Semi-finals", "Final");
        ObservableList <String> listOfCategories = FXCollections.observableArrayList("Friendly match", "Group phase match", "Qualification match");


        team1_choice_box.setValue("Egypt");
        team1_choice_box.setItems(listOfTeams);


        team2_choice_box.setValue("England");
        team2_choice_box.setItems(listOfTeams);


        round_choice_box.setValue("Quarter-finals");
        round_choice_box.setItems(listOfRounds);


        category_choice_box.setValue("Friendly match");
        category_choice_box.setItems(listOfCategories);
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