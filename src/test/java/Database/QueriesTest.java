package Database;


import Algorithms.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class QueriesTest{

    Queries q;

    @BeforeEach
    public void init(){
        q = new Queries();

    }
    @Test
    public void getAllTeamsTest(){
        assertEquals(true, q.GetAllTeams());
    }

    @Test
    public void getAllMatchesTest1(){
        assertEquals(true, q.getAllMatches());
    }

    @Test
    public void getAllMatchesTest2(){
        assertEquals(true, q.deleteAllMatches());

        assertEquals(true, q.getAllMatches());
    }

    @Test
    public void addMatchTest1(){
        assertEquals(true, q.addMatch("England", "Egypt", "Friendl_Match",
                "Final", 2, 0, 3, false));
    }

    @Test
    public void addMatchTest2(){
        assertEquals(false, q.addMatch("Sweden", "Egypt", "Friendl_Match",
                "Final", 2, 0, 3, false));
    }

    @Test
    public void deleteTeamTest1() throws SQLException {
        assertTrue(q.deleteTeam("Ahly"));
    }
    @Test
    public void addTeamTest1() throws SQLException {
        assertTrue(q.addTeam("Sweden", 0, 0));
    }

    @Test
    public void addTeamTest2() throws SQLException {
        assertFalse(q.addTeamMissingField("Sweden",  0));
    }

    @Test
    public void getScoreFromHistoryTest() throws SQLException{
        assertTrue(q.getScoreFromHistory("Egypt",2, 2015));
    }


//    @Test
//    public void InsertMatchesTest1(){
//        boolean exp = q.addMatch("Egypt", "Morocco", "World Cup",
//                "Final", 10, 4, 9, false);
//
//        assertEquals(true, exp);
//    }
}