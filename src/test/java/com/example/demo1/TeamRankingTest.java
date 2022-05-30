package com.example.demo1;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class TeamRankingTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("team-ranking-view.fxml"));
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
        clickOn("#month_choice_box").clickOn("April-2022");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("April-2022 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }

    @Test
    public void testCase2(){
        clickOn("#month_choice_box").clickOn("March-2022");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("March-2022 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }


    @Test
    public void testCase3(){
        clickOn("#month_choice_box").clickOn("February-2022");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("February-2022 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }


    @Test
    public void testCase4(){
        clickOn("#month_choice_box").clickOn("January-2022");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("January-2022 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }

    @Test
    public void testCase5(){
        clickOn("#month_choice_box").clickOn("December-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("December-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }

    @Test
    public void testCase6(){
        clickOn("#month_choice_box").clickOn("November-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("November-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }


    @Test
    public void testCase7(){
        clickOn("#month_choice_box").clickOn("October-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("October-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }


    @Test
    public void testCase8(){
        clickOn("#month_choice_box").clickOn("September-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("September-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }


    @Test
    public void testCase9(){
        clickOn("#month_choice_box").clickOn("August-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("August-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }


    @Test
    public void testCase10(){
        clickOn("#month_choice_box").clickOn("July-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("July-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }


    @Test
    public void testCase11(){
        clickOn("#month_choice_box").clickOn("June-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("June-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }


    @Test
    public void testCase12(){
        clickOn("#month_choice_box").clickOn("May-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("May-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }

    @Test
    public void testCase13(){
        clickOn("#month_choice_box").clickOn("April-2021");
        Label label = lookup("#monthRanking").query();
        TableView tableView = lookup("#rankingTable").query();

        Assertions.assertThat(label.getText().equals("April-2021 Ranking"));
        Assertions.assertThat(tableView.isVisible());
    }

}