package com.example.demo1;

import Algorithms.Match;
import Algorithms.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.ViewUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloController {


    ObservableList <Team> listOfTeams;// = FXCollections.observableArrayList("Egypt", "Italy", "England");
    //ObservableList <String> teams = FXCollections.observableArrayList("Team 1", "Team 2");
    ObservableList <Team> selectedTeams = FXCollections.observableArrayList();
    //ObservableList <String> listOfRounds = FXCollections.observableArrayList("Group Stage","Round of 16", "Quarter-finals", "Semi-finals", "Final");
    //ObservableList <String> listOfCategories = FXCollections.observableArrayList("Friendly match", "Nations League", "Qualifier for Confederation final competition", "Qualifier for World Cup", "Confederation final competition", "Confederations Cup", "World Cup");

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
    private TextField score_team_one;

    @FXML
    private TextField score_team_two;
    @FXML
    private ChoiceBox winner_choice_box;
    @FXML
    private DatePicker date_picker;

    @FXML
    protected void initialize(){

        try {
            Team.GetAllTeams();
            //System.out.println(Team.teams_list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listOfTeams = Team.teams_list;

        initializeInputValues();
    }

    private void initializeInputValues() {


        //team1_choice_box.setValue("Egypt");
        team1_choice_box.setItems(listOfTeams);
        team1_choice_box.setValue(listOfTeams.get(0));

        //team2_choice_box.setValue("England");
        team2_choice_box.setItems(listOfTeams);
        team2_choice_box.setValue(listOfTeams.get(1));

        round_choice_box.setValue("Group Stage");
        round_choice_box.getItems().setAll(Match.Rounds.values());


        category_choice_box.setValue(Match.Categories.Friendly_Match);
        category_choice_box.getItems().setAll(Match.Categories.values());

        //winner_choice_box.setValue("Team 1");
        //winner_choice_box.setItems(teams);
        //winner_choice_box.setValue(selectedTeams.get(0));
        //winner_choice_box.getItems().setAll(selectedTeams);
        choose_winner_txt.setVisible(false);
        winner_choice_box.setVisible(false);

        date_picker.setValue(LocalDate.now());
    }

    @FXML
    protected void onCategoryDragDone(){
        if (category_choice_box.getValue().equals(Match.Categories.Friendly_Match)){
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
    protected void updateSelectedTeams(){
        selectedTeams.setAll((Team) team1_choice_box.getValue(),(Team) team2_choice_box.getValue());
        //System.out.println(selectedTeams);
        winner_choice_box.getItems().setAll(selectedTeams);
        winner_choice_box.setValue(selectedTeams.get(0));
    }
    @FXML
    protected void onAddMatchButtonClick(){
        /*
        Parent root = FXMLLoader.load(getClass().getResource("popupWindow.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Match is added");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(addMatchBtn.getScene().getWindow());
        primaryStage.show();
        */
        String team1 = team1_choice_box.getValue().toString().toLowerCase();
        String team2 = team2_choice_box.getValue().toString().toLowerCase();
        if (team1.equals(team2)){
            ViewUtil.createAlert(Alert.AlertType.ERROR, "The two teams should be different").showAndWait();
        }
        else if (score_team_one.getText().isEmpty() || score_team_two.getText().isEmpty()){
            ViewUtil.createAlert(Alert.AlertType.ERROR, "Please enter all the fields").showAndWait();
        }
        else{
            ViewUtil.createAlert(Alert.AlertType.INFORMATION, "Match is added").showAndWait();
        }
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