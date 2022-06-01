package com.example.demo1;

import Algorithms.Match;
import Algorithms.Team;
import Database.Queries;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.assertions.api.Assertions;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ranking_GUI_Logic_Database_Testing extends ApplicationTest{
    //GUI Input Parameters
    Team team1;
    Team team2;
    Match match;

    TeamRankingController controller;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Queries.resetDB();
            //resets the database to a predefined state
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("team-ranking-view.fxml"));

        Parent root = loader.load();
        controller = loader.getController();
        stage.setTitle("Fifa Team Ranking");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Before
    public void setUp() throws Exception {
        try {
            Queries.resetDB();
            //resets the database to a predefined state
            team1 = new Team("Egypt",1500,0);
            team2 = new Team("Germany",1500,0);
            match = new Match(team1,team2, Match.Categories.Friendly_Match, Match.Rounds.Group_Stage,1,
                    0,false,false,null,6,2022);
            match.execDBMethods();
            team1.updatePoints();
            team2.updatePoints();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void oldRank_test() throws SQLException{
        clickOn("#month_choice_box").clickOn("January-2022");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("January-2022 Ranking"));
        Assertions.assertThat(tableView.isVisible());

        // at that date all the teams had the same points (1500) so they should be ranked alphabetically

        //These check that each team has its correct rank

        ObservableList actual = tableView.getItems();
        //first row
        assertEquals(1,((History)actual.get(0)).getRank());
        assertEquals("Austria",((History)actual.get(0)).getTeamName());
        assertEquals(1500,((History)actual.get(0)).getPoints());
        //second row
        assertEquals(2,((History)actual.get(1)).getRank());
        assertEquals("Egypt",((History)actual.get(1)).getTeamName());
        assertEquals(1500,((History)actual.get(1)).getPoints());
        //third row
        assertEquals(3,((History)actual.get(2)).getRank());
        assertEquals("Germany",((History)actual.get(2)).getTeamName());
        assertEquals(1500,((History)actual.get(2)).getPoints());
    }

    @Test
    public void newRank_test() throws SQLException{
        // at that date austria didn't play any matches, so their score is still 1500
        // Egypt and Germany played a match where Egypt won and their new scores are 1502.5 and 1497.5 respectively
        // So Egypt should be first then Austria then Germany
        clickOn("#month_choice_box").clickOn("April-2021");
        clickOn("#month_choice_box").clickOn("June-2022");

        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("June-2022 Ranking"));
        Assertions.assertThat(tableView.isVisible());

        //These check that each team has its correct rank
        ObservableList list = History.getHistory("June-2022");

        ObservableList actual = tableView.getItems();
        //first row
        assertEquals(1,((History)actual.get(0)).getRank());
        assertEquals("Egypt",((History)actual.get(0)).getTeamName());
        assertEquals(1502.5,((History)actual.get(0)).getPoints());
        //second row
        assertEquals(2,((History)actual.get(1)).getRank());
        assertEquals("Austria",((History)actual.get(1)).getTeamName());
        assertEquals(1500,((History)actual.get(1)).getPoints());
        //third row
        assertEquals(3,((History)actual.get(2)).getRank());
        assertEquals("Germany",((History)actual.get(2)).getTeamName());
        assertEquals(1497.5,((History)actual.get(2)).getPoints());
    }
}
