package Algorithms;

import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class Team {

    public static ObservableList<Team> teams_list = FXCollections.observableArrayList();
    private String name;
    private double points;
    private int rank;
    private int id;

    public Team(String name, double points, int rank){
        this.name = name;
        this.points = points;
        this.rank = rank;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void calculateNewPoints(int importance, double win, double expectedWin, Boolean immunity){
        double winDiff = calcWinDiff(win, expectedWin);
        if (immunity == true){
            if (win - expectedWin >= 0 ){
                this.setPoints(this.points + importance * winDiff);
            }
        }
        else{
            this.setPoints(this.points + importance * winDiff);
        }

    }
    public double calcWinDiff(double win, double expectedWin){
        return win - expectedWin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean equals(Object team){
        //team = (Team)team;
        if (team == null)return false;
        if (this.name.equals(((Team) team).getName()))return true;
        return false;
    }

    // to retrieve teams from db and create teams objects for each team
    // needs to be modified to create team objects and store them (e.g. in an array of Teams)
    public static void GetAllTeams() throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        Statement stmt = Objects.requireNonNull(conn).createStatement();
        ResultSet rs = stmt.executeQuery("select * from Teams");

        while (rs.next()){
            String name = rs.getString("Name");
            double score = rs.getDouble("Score");
            int rank = rs.getInt("Rank");
            teams_list.add(new Team(name,score,rank));
            System.out.println("Team Name : " + name + " , Score = " + score + " , Rank = " + rank);
        }
    }

}
