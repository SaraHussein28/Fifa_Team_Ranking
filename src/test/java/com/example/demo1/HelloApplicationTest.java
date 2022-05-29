package com.example.demo1;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
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
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloApplicationTest extends ApplicationTest {
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

    @Test
    public void testCase1(){
        clickOn("#team1_choice_box").clickOn("Italy");
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
        clickOn("#team2_choice_box").clickOn("italy");
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
        clickOn("#team2_choice_box").clickOn("italy");
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



}