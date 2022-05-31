package Algorithms;

import java.sql.SQLException;

public class Tester {

    public static void test_GetAllTeams() throws SQLException {
        Team.GetAllTeams();
    }

    public static void test_addMatch() throws SQLException {
        //creating dummy teams for testing
//        Team team1 = new Team("Egypt", 0,0);
//        Team team2 = new Team("France", 0, 0);
//        Match tester_match = new Match (team1, team2, Match.Categories.Friendly_Match, Match.Rounds.Group_Stage, 2,4,1,true, true,null);
//        tester_match.addMatch();


        Team team1 = new Team("Germany", 5.6,0);
        Team team2 = new Team("Brazil", 7.0, 4);
        Match tester_match = new Match (team1, team2, Match.Categories.Confederations_Cup, Match.Rounds.Quarter_Final, 2,3,1,false, true,null);
        tester_match.addMatch();

        team1 = new Team("England", 9.43,0);
        team2 = new Team("United States", 2.4, 0);
        tester_match = new Match (team1, team2, Match.Categories.Confederation_Final, Match.Rounds.Semi_Final, 2,0,1,true, true,team1);
        tester_match.addMatch();

        team1 = new Team("Morocco", 0,2);
        team2 = new Team("Algeria", 0, 8);
        tester_match = new Match (team1, team2, Match.Categories.Q_for_World_Cup, Match.Rounds.Final, 0,4,1,true, true,team2);
        tester_match.addMatch();

        team1 = new Team("France", 0,0);
        team2 = new Team("Austria", 0, 0);
        tester_match = new Match (team1, team2, Match.Categories.Nations_League, Match.Rounds.Round_of_16, 0,1,1,false, true,null);
        tester_match.addMatch();

        team1 = new Team("Sudan", 12.4,0);
        team2 = new Team("Libya", 4.6, 0);
        tester_match = new Match (team1, team2, Match.Categories.Friendly_Match, Match.Rounds.Group_Stage, 2,4,1,false, true,null);
        tester_match.addMatch();

        team1 = new Team("Egypt", 0,0);
        team2 = new Team("France", 0, 0);
        tester_match = new Match (team1, team2, Match.Categories.Friendly_Match, Match.Rounds.Quarter_Final, 2,4,1,true, true,team1);
        tester_match.addMatch();

    }

    public static void test_getAllMatches() throws SQLException {
        Match.getAllMatches();
    }

    public static void test_getLastMatch() throws SQLException{
        Match.getLastMatch();
    }

    public static void main(String args[]) throws SQLException {
        test_addMatch();
        //test_getLastMatch();
    }


}
