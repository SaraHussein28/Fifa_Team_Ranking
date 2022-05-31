package com.example.demo1;

import Algorithms.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Integer.parseInt;

//class StudentComparator implements Comparator<History> {
//    @Override
//    public int compare(History h1, History h2) {
//        return h1.points > h2.points;
//    }
//}
//

public class History {
    private int rank;
    private String teamName;
    private double points;


    private static int changeToInt(String month){
        switch (month){
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return 0;
        }
    }



    public static ObservableList<History> getHistory(String date) throws SQLException, InterruptedException {

        String[] tokens=date.split("-");

        String m = tokens[0];
        int month = changeToInt(m);
        int year = parseInt(tokens[1]);

        Team.GetAllTeams();
        ObservableList<Team> allTeams = Team.teams_list;
        ObservableList<History> list = FXCollections.observableArrayList();


        for (int i = 0; i < 5; i++){

            Team t =allTeams.get(i);

            History h = new History();

            double p = Team.getScoreFromHistory(t.getName(), month, year);


            h.points = p;
            h.teamName = t.getName();

            list.add(h);

        }

        Collections.sort(list, new Comparator<History>() {
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
    public static void main(String args[]) throws SQLException, InterruptedException {
        //test_addMatch();
        //test_getLastMatch();
        //test_updateScore();
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
