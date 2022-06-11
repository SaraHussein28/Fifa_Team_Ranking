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

import static java.lang.Integer.parseInt;

public class MainWindowController {


    ObservableList <Team> listOfTeams;
    ObservableList <Team> selectedTeams = FXCollections.observableArrayList();

    @FXML
    private Button showFIFARankingBtn;
    @FXML
    private Button addMatchBtn;
    @FXML
    private ChoiceBox<Team> team1_choice_box;
    @FXML
    private ChoiceBox<Team> team2_choice_box;
    @FXML
    private ChoiceBox<Match.Rounds> round_choice_box;
    @FXML
    private ChoiceBox<Match.Categories> category_choice_box;
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listOfTeams = Team.teams_list;

        initializeInputValues();
    }

    private void initializeInputValues() {

        team1_choice_box.setItems(listOfTeams);
        team1_choice_box.setValue(listOfTeams.get(0));

        team2_choice_box.setItems(listOfTeams);
        team2_choice_box.setValue(listOfTeams.get(1));

        round_choice_box.setValue(Match.Rounds.Group_Stage);
        round_choice_box.getItems().setAll(Match.Rounds.values());


        category_choice_box.setValue(Match.Categories.Friendly_Match);
        category_choice_box.getItems().setAll(Match.Categories.values());

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
    protected void onAddMatchButtonClick() throws SQLException {
        Team team1 = team1_choice_box.getValue();
        Team team2 = team2_choice_box.getValue();

        if (team1.equals(team2)){
            ViewUtil.createAlert(Alert.AlertType.ERROR, "The two teams should be different").showAndWait();
        }
        else if (score_team_one.getText().isEmpty() || score_team_two.getText().isEmpty()){
            ViewUtil.createAlert(Alert.AlertType.ERROR, "Please enter all the fields").showAndWait();
        }
        else{
            Match.Categories category = category_choice_box.getValue();
            Match.Rounds round = null;
            System.out.println(round_choice_box.getValue());
            if (round_choice_box.getValue() != null)
                round = round_choice_box.getValue();

            int team1Score = parseInt(score_team_one.getText());
            int team2Score = parseInt(score_team_two.getText());
            boolean isPSO = shootout_check_box.isSelected();
            boolean isInCalendar = calender_check_box.isSelected();
            Team PSOWinningTeam = null;
            LocalDate date = date_picker.getValue();
            int month = date.getMonthValue();
            int year = date.getYear();
            if (isPSO){
                PSOWinningTeam = (Team) winner_choice_box.getValue();
            }

            Match newMatch = new Match(team1, team2,category, round, team1Score, team2Score, isPSO,isInCalendar,
                    PSOWinningTeam, month, year);

            newMatch.execDBMethods();
            team1.updatePoints();
            team2.updatePoints();
            System.out.println("New Match Added Successfully");
            System.out.println(newMatch.getYear() + newMatch.getTeam1().getName() + newMatch.getMonth()
                    + newMatch.getImportance() + newMatch.getTeam2().getName());

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