package Algorithms;

import Database.*;

import java.sql.*;
import java.util.Objects;

public class Team {
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

            System.out.println("Team Name : " + name + " , Score = " + score + " , Rank = " + rank);
        }
    }

}
