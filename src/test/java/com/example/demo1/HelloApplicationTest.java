package com.example.demo1;
import Algorithms.Match;
import Algorithms.Team;
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
//import org.junit.jupiter.api.Assertions;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;


import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

import java.io.IOException;

public class HelloApplicationTest extends ApplicationTest {

    Team England = new Team("England",0 ,0);
    Team France = new Team("France",0,0);
    Team Brazil = new Team("Brazil",0,0);
    Team Egypt = new Team("Egypt",0,0);
    Team Italy = new Team("Italy",0,0);

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("Fifa Team Ranking");
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    private void select_item(ChoiceBox choiceBox,Object object){
        interact(() -> {
            clickOn(choiceBox);
            choiceBox.getSelectionModel().select(object);
            System.out.println(choiceBox.getSelectionModel().getSelectedItem());
            System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
        });
    }

    @Test
    public void testCase1(){
        ChoiceBox<Team> team1_choice = lookup("#team1_choice_box").query();
        ChoiceBox<Team> team2_choice = lookup("#team2_choice_box").query();
        ChoiceBox<Match.Categories> category_choice = lookup("#category_choice_box").query();
        ChoiceBox<Match.Rounds> round_choice = lookup("#round_choice_box").query();
        ChoiceBox<Team> psoWinner_choice = lookup("#winner_choice_box").query();

        Team team1 = Italy;
        Team team2 = Egypt;

        //SelectionModel<String> team1s = team1.getSelectionModel();
        /*interact(() -> {
            team1.getSelectionModel().select(england);
            System.out.println(team1.getSelectionModel().getSelectedItem());
            System.out.println(team1.getSelectionModel().getSelectedIndex());
        });*/
        select_item(team1_choice,team1);
        //team1s.select("Italy");
        /*clickOn("#team1_choice_box");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);*/
        //clickOn("#team2_choice_box").clickOn("Egypt");
        select_item(team2_choice, team2);
        clickOn("#score_team_one").write("5");
        clickOn("#score_team_two").write("3");
        //clickOn("#category_choice_box").clickOn("Nations League");
        //clickOn("#round_choice_box").clickOn("Quarter-finals");
        select_item(category_choice,Match.Categories.Nations_League);
        select_item(round_choice,Match.Rounds.Quarter_Final);
        clickOn("#shootout_check_box");
        //clickOn("#winner_choice_box").clickOn("team 2");
        select_item(psoWinner_choice,team2);
        clickOn("#addMatchBtn");
        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Match is added");
    }

    @Test
    public void testCase2(){
        clickOn("#score_team_one").write("5");
        clickOn("#addMatchBtn");

        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Please enter all the fields");
    }

    @Test
    public void testCase3(){
        clickOn("#addMatchBtn");
        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Please enter all the fields");
    }


    @Test
    public void testCase4(){
        clickOn("#team1_choice_box").clickOn("Egypt");
        clickOn("#team2_choice_box").clickOn("egypt");
        clickOn("#score_team_one").write("5");
        clickOn("#score_team_two").write("3");
        clickOn("#category_choice_box").clickOn("Nations League");
        clickOn("#round_choice_box").clickOn("Quarter-finals");
        clickOn("#shootout_check_box");
        clickOn("#winner_choice_box").clickOn("team 2");
        clickOn("#addMatchBtn");
        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("The two teams should be different");
    }


    @Test
    public void testCase5(){
        clickOn("#team1_choice_box").clickOn("England");
        clickOn("#team2_choice_box").clickOn("Italy");
        clickOn("#score_team_one").write("3");
        clickOn("#score_team_two").write("2");
        clickOn("#shootout_check_box");
        clickOn("#winner_choice_box").clickOn("team 2");
        clickOn("#addMatchBtn");
        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Match is added");
    }


    @Test
    public void testCase6(){
        clickOn("#team1_choice_box").clickOn("England");
        clickOn("#team2_choice_box").clickOn("Italy");
        clickOn("#score_team_one").write("3");
        clickOn("#score_team_two").write("2");
        clickOn("#shootout_check_box");
        clickOn("#winner_choice_box").clickOn("team 2");
        clickOn("#calender_check_box");
        clickOn("#addMatchBtn");
        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Match is added");
    }


    @Test
    public void testCase7(){
        clickOn("#team1_choice_box").clickOn("England");
        clickOn("#team2_choice_box").clickOn("italy");
        clickOn("#score_team_one").write("2");
        clickOn("#shootout_check_box");
        clickOn("#winner_choice_box").clickOn("team 2");
        clickOn("#calender_check_box");
        clickOn("#addMatchBtn");
        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Please enter all the fields");
    }


    @Test
    public void testCase8(){
        clickOn("#team1_choice_box").clickOn("England");
        clickOn("#team2_choice_box").clickOn("italy");
        clickOn("#score_team_two").write("2");
        clickOn("#shootout_check_box");
        clickOn("#winner_choice_box").clickOn("team 2");
        clickOn("#calender_check_box");
        clickOn("#addMatchBtn");
        Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Please enter all the fields");
    }


// hwo to switch between scenes in testFX
    @Test
    public void testCase9() throws IOException {
        clickOn("#showFIFARankingBtn");
    }

}




/*    @Test
    public void testCase1(){
        ChoiceBox<Team> team1 = lookup("#team1_choice_box").query();
        //team1.getSelectionModel().selectFirst();
        //team1.getItems();
        System.out.println(team1.getItems());
        System.out.println(team1.getValue());
        SelectionModel<Team> team1s = team1.getSelectionModel();
        Team team = new Team("England",0,0);
        interact(() -> {

            //team1.getSelectionModel().select(team);
            //if (team1s.)
            team1.getSelectionModel().select(team);
            //System.out.println(team1.getSelectionModel().getSelectedItem());
            //System.out.println(team1.getSelectionModel());
        });
        //ObservableList<String>  team13 = FXCollections.observableArrayList(team1.getItems()) ;
        //Assertions.assertThat(team1.getSelectionModel().getSelectedItem().(team));
        //int idx = team13.indexOf(team1.getSelectionModel().getSelectedItem());
        //System.out.println(team1.getSelectionModel().getSelectedItem());
        //System.out.println(team1.getSelectionModel().getSelectedIndex());
        System.out.println(team1.getValue());
        clickOn("#score_team_one").write("5");
        clickOn("#score_team_two").write("3");
        //team1s.select("Italy");
        /*clickOn("#team1_choice_box");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);*/
        /*clickOn("#team2_choice_box").clickOn("Egypt");
        //clickOn("#team2_choice_box").;
        clickOn("#score_team_one").write("5");
        clickOn("#score_team_two").write("3");
        clickOn("#category_choice_box").clickOn("Nations League");
        clickOn("#round_choice_box").clickOn("Quarter-finals");
        clickOn("#shootout_check_box");
        clickOn("#winner_choice_box").clickOn("team 2");
        clickOn("#addMatchBtn");*/
        /*Node alert = lookup(".dialog-pane").query();
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Match is added");

    }*/