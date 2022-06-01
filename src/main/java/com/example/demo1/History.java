package com.example.demo1;

import Algorithms.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.SQLException;
import java.util.Comparator;

import static java.lang.Integer.parseInt;


public class History {
    private int rank;
    private String teamName;
    private double points;


    private static int changeToInt(String month){
        return switch (month) {
            case "January" -> 1;
            case "February" -> 2;
            case "March" -> 3;
            case "April" -> 4;
            case "May" -> 5;
            case "June" -> 6;
            case "July" -> 7;
            case "August" -> 8;
            case "September" -> 9;
            case "October" -> 10;
            case "November" -> 11;
            case "December" -> 12;
            default -> 0;
        };
    }



    public static ObservableList<History> getHistory(String date) throws SQLException{

        String[] tokens=date.split("-");

        String m = tokens[0];
        int month = changeToInt(m);
        int year = parseInt(tokens[1]);

        Team.GetAllTeams();
        ObservableList<Team> allTeams = Team.teams_list;
        ObservableList<History> list = FXCollections.observableArrayList();


        for (int i = 0; i < allTeams.size(); i++){

            Team t =allTeams.get(i);

            History h = new History();

            double p = Team.getScoreFromHistory(t.getName(), month, year);


            h.points = Math.round(p*100.0)/100.0;
            h.teamName = t.getName();

            list.add(h);

        }

        list.sort(new Comparator<History>() {
            @Override
            public int compare(History c1, History c2) {
                return Double.compare(c2.points, c1.points);
            }
        });
        int i = 1;
        for (History h: list){

            h.rank= i;
            i+=1;
            System.out.println(h.teamName + "   " + h.points);
        }
        return list;
    }
    public static void main(String args[]) throws SQLException {
        getHistory("May-2022");
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
