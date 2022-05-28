package Algorithm;

import java.sql.SQLException;

public class Tester {

    public static void test_GetAllTeams() throws SQLException {
        Team.GetAllTeams();
    }

    public static void test_addMatch() throws SQLException {
        //creating dummy teams for testing
        Team team1 = new Team("Egypt", 0,0);
        Team team2 = new Team("France", 0, 0);
        Match tester_match = new Match (team1, team2, Match.Categories.Friendly_Match, Match.Rounds.Group_Stage, 2,4,1,true, true);
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
        test_getLastMatch();
    }


}
