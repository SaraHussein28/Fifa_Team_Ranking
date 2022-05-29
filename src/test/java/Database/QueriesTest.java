package Database;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

//    @Test
//    public void InsertMatchesTest1(){
//        boolean exp = q.addMatch("Egypt", "Morocco", "World Cup",
//                "Final", 10, 4, 9, false);
//
//        assertEquals(true, exp);
//    }
}