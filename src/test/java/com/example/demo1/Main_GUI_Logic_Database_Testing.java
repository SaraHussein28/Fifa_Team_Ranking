package com.example.demo1;

import Algorithms.Match;
import Algorithms.Team;
import Database.Queries;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main_GUI_Logic_Database_Testing extends ApplicationTest {

    //GUI Input Parameters
    Team team1 = new Team("Egypt",1500,0);
    Team team2 = new Team("Germany",1500,0);
    int team1_score = 1;
    int team2_score = 0;
    Match.Categories match_category = Match.Categories.Friendly_Match;
    Match.Rounds match_round = Match.Rounds.Group_Stage;
    boolean PSO = false;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Queries.resetDB();
            //resets the database to a predefined state
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("Fifa Team Ranking");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Before
    public void setUp() throws Exception {
        try {
            Queries.resetDB();
            //resets the database to a predefined state
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ChoiceBox<Team> team1_choice = lookup("#team1_choice_box").query();
        ChoiceBox<Team> team2_choice = lookup("#team2_choice_box").query();
        ChoiceBox<Match.Categories> category_choice = lookup("#category_choice_box").query();
        ChoiceBox<Match.Rounds> round_choice = lookup("#round_choice_box").query();
        ChoiceBox<Team> psoWinner_choice = lookup("#winner_choice_box").query();


        //GUI orders
        select_item(team1_choice,team1);
        select_item(team2_choice,team2);
        System.out.println(Integer.toString(team1_score));
        clickOn("#score_team_one").write(Integer.toString(team1_score));
        clickOn("#score_team_two").write(Integer.toString(team2_score));
        select_item(category_choice,match_category);
        select_item(round_choice,match_round);
        clickOn("#addMatchBtn");
        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Match is added");
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    private void select_item(ChoiceBox choiceBox,Object object){
        interact(() -> {
            //clickOn(choiceBox);
            choiceBox.getSelectionModel().select(object);
            System.out.println(choiceBox.getSelectionModel().getSelectedItem());
            System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
        });
    }

    @Test
    public void match_test() throws SQLException /*throws SQLException */{

        //This tests if the data sent to the match table, match those in the match we defined in the GUI
        ResultSet rs = Queries.getLastMatch();
        assertEquals(team1.getName(),rs.getString("Team1"));
        assertEquals(team2.getName(),rs.getString("Team2"));
        assertEquals(match_category.toString(),rs.getString("Competition_type"));
        assertEquals(match_round.toString(),rs.getString("Round"));
        assertEquals(team1_score,rs.getInt("Team1_Score"));
        assertEquals(team2_score,rs.getInt("Team2_Score"));
        assertEquals(PSO, rs.getBoolean("PSO"));
    }
    @Test
    public void team1_test() throws SQLException {
        ResultSet rs = Queries.getTeams(team1);
        System.out.println(rs);
        while(rs.next()){
            //test that the updated points in the database matches the expected new value (calculated manually)
            assertEquals(1502.5,rs.getDouble("Score"));
        }
    }

    @Test
    public void team2_test() throws SQLException {
        ResultSet rs = Queries.getTeams(team2);
        System.out.println(rs);
        while(rs.next()){
            //test that the updated points in the database matches the one team object
            //assertEquals(team2.getPoints(),rs.getDouble("Score"));
            //test that the updated points in the database matches the expected new value (calculated manually)
            assertEquals(1497.5,rs.getDouble("Score"));
        }
    }
}
