package Algorithms;

import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
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

    public void calculateNewPoints(int importance, double win, double expectedWin, Boolean immunity) throws SQLException {
        double winDiff = calcWinDiff(win, expectedWin);
        if (immunity){
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
        teams_list.clear();
        while (rs.next()){
            String name = rs.getString("Name");
            double score = rs.getDouble("Score");
            int rank = rs.getInt("Rank");
            teams_list.add(new Team(name,score,rank));
            System.out.println("Team Name : " + name + " , Score = " + score + " , Rank = " + rank);
        }
    }
    public void updatePoints() throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement("update Teams set Score = ? where Name = ?");
        pstmt.setDouble(1, this.points);
        pstmt.setString(2, this.getName());

        pstmt.execute();
    }

    public static double getScoreFromHistory(String TeamName, int month, int year) throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(
                "select Score from score_history where TeamName = ? and Month = ? and Year = ? order by MatchId desc limit 1");
        pstmt.setString(1, TeamName);
        pstmt.setInt(2, month);
        pstmt.setInt(3, year);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            //System.out.println(rs.getDouble("Score"));
            return rs.getDouble("Score");
        }
        else{
            pstmt = Objects.requireNonNull(conn).prepareStatement(
                    "select Score from score_history where TeamName = ? and Month < ? and Year = ? order by Month desc, MatchId desc limit 1");
            pstmt.setString(1, TeamName);
            pstmt.setInt(2, month);
            pstmt.setInt(3, year);
            rs = pstmt.executeQuery();

            if(rs.next()){
                //System.out.println(rs.getDouble("Score"));
                return rs.getDouble("Score");
            }
            else{
                pstmt = Objects.requireNonNull(conn).prepareStatement(
                        "select Score from score_history where TeamName = ? and Year < ? order by Year desc, Month desc, MatchId desc limit 1");
                pstmt.setString(1, TeamName);
                pstmt.setInt(2, year);
                rs = pstmt.executeQuery();

                if(rs.next()){
                   // System.out.println(rs.getDouble("Score"));
                    return rs.getDouble("Score");
                }
                else return 0;
            }
        }

    }
}
