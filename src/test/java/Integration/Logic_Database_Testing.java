package Integration;


import Algorithms.Match;
import Algorithms.Team;
import Database.Queries;
import com.example.demo1.History;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Logic_Database_Testing {
    Team team1;
    Team team2;
    Match match;

    @BeforeEach
    void setup(){
        try {
            Queries.resetDB();
            //resets the database to a predefined state
            team1 = new Team("Egypt",1500,0);
            team2 = new Team("Germany",1500,0);
            match = new Match(team1,team2, Match.Categories.Friendly_Match, Match.Rounds.Group_Stage,1,
                    0,false,false,null,6,2022);
            match.execDBMethods();
            team1.updatePoints();
            team2.updatePoints();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void match_test() throws SQLException {
        //This tests if the data sent to the match table, match those in the match class
        ResultSet rs = Queries.getLastMatch();
        assertEquals(match.getTeam1().getName(),rs.getString("Team1"));
        assertEquals(match.getTeam2().getName(),rs.getString("Team2"));
        assertEquals(match.getCompetitionType().toString(),rs.getString("Competition_type"));
        assertEquals(match.getRound().toString(),rs.getString("Round"));
        assertEquals(match.getTeam1score(),rs.getInt("Team1_Score"));
        assertEquals(match.getTeam2score(),rs.getInt("Team2_Score"));
        assertEquals(match.getPSO(), rs.getBoolean("PSO"));
    }
    @Test
    void team1_test() throws SQLException {
        ResultSet rs = Queries.getTeams(team1);
        System.out.println(rs);
        while(rs.next()){
            //test that the updated points in the database matches the one team object
            assertEquals(team1.getPoints(),rs.getDouble("Score"));
            //test that the updated points in the database matches the expected new value (calculated manually)
            assertEquals(1502.5,rs.getDouble("Score"));
        }
    }

    @Test
    void team2_test() throws SQLException {
        ResultSet rs = Queries.getTeams(team2);
        System.out.println(rs);
        while(rs.next()){
            //test that the updated points in the database matches the one team object
            assertEquals(team2.getPoints(),rs.getDouble("Score"));
            //test that the updated points in the database matches the expected new value (calculated manually)
            assertEquals(1497.5,rs.getDouble("Score"));
        }
    }

    @Test
    void oldRank_test() throws SQLException{
        ObservableList<History> list = History.getHistory("January-2022");
        // at that date all the teams had the same points (1500) so they should be ranked alphabetically

        //These check that the list is indeed ordered according to rank
        assertEquals(1,list.get(0).getRank());
        assertEquals(2,list.get(1).getRank());
        assertEquals(3,list.get(2).getRank());

        //These check that each time has its correct rank
        assertEquals("Austria",list.get(0).getTeamName());
        assertEquals("Egypt",list.get(1).getTeamName());
        assertEquals("Germany",list.get(2).getTeamName());
    }

    @Test
    void newRank_test() throws SQLException{
        ObservableList<History> list = History.getHistory("June-2022");
        // at that date austria didn't play any matches, so their score is still 1500
        // Egypt and Germany played a match where Egypt won and their new scores are 1502.5 and 1497.5 respectively
        // So Egypt should be first then Austria then Germany

        //These check that the list is indeed ordered according to rank
        assertEquals(1,list.get(0).getRank());
        assertEquals(2,list.get(1).getRank());
        assertEquals(3,list.get(2).getRank());

        //These check that each time has its correct rank
        assertEquals("Egypt",list.get(0).getTeamName());
        assertEquals("Austria",list.get(1).getTeamName());
        assertEquals("Germany",list.get(2).getTeamName());
    }
}
