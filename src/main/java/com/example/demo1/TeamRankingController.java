package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class TeamRankingController {
    @FXML
    private ChoiceBox month_choice_box;

    @FXML
    private TableView<History> rankingTable;

    @FXML
    private TableColumn<History, Integer> col_teamRank;

    @FXML
    private TableColumn<History, String > col_teamName;

    @FXML
    private TableColumn<History, Double> col_points;

    ObservableList<History> listM;

    @FXML
    private Label monthRanking;

    public TeamRankingController() {
    }

    @FXML
    protected void initialize() throws SQLException, InterruptedException {

        initializeChoiceBoxes();
        initializeTable();
    }

    protected void initializeTable() throws SQLException, InterruptedException {
        listM = History.getHistory(month_choice_box.getValue().toString());
        rankingTable.setItems(listM);

        col_teamRank.setCellValueFactory(new PropertyValueFactory<History, Integer>("rank"));
        col_teamName.setCellValueFactory(new PropertyValueFactory<History, String>("teamName"));
        col_points.setCellValueFactory(new PropertyValueFactory<History, Double>("points"));


    }
/*
* l kol el teams call getScoreFromHistory
* sort descendingly according to score
*
* rank      name        points
*
* */
    private void initializeChoiceBoxes() {
        ObservableList<String> listOfMonths = FXCollections.observableArrayList("May-2022","April-2022", "March-2022", "February-2022", "January-2022", "December-2021", "November-2021", "October-2021", "September-2021", "August-2021", "July-2021", "June-2021", "May-2021", "April-2021");

        month_choice_box.setValue("April-2022");
        month_choice_box.setItems(listOfMonths);
        //rankingTable.setVisible(false);
    }

    @FXML
    public void onMonthDragDone() throws SQLException, InterruptedException {
        String chosenMonth = month_choice_box.getValue().toString();
        chosenMonth = chosenMonth + " Ranking";
        monthRanking.setText(chosenMonth);
        initializeTable();
    }
}
