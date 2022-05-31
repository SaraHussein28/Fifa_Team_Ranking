package Algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    Team team;
    static double points = 1500.5;
    @BeforeEach
    void setup(){
        team = new Team("Egypt",points,3);
    }
    @ParameterizedTest
    @DisplayName("Test Win Diff")
    @MethodSource("calc_new_points_arguments")
    void calculateNewPoints(int importance,double win, double expectedWin, boolean immunity, double expected) throws SQLException {
        team.calculateNewPoints(importance,win,expectedWin,immunity);
        assertEquals(expected,team.getPoints());
    }

    private static Stream<Arguments> calc_new_points_arguments(){
        return Stream.of(
                // Format: importance, win, expectedWin, immunity, expected new points

                //Tests with immunity, the points shouldn't decrease if the win is less than the expected win
                //if any of those fail, then the immunity condition in the function is wrong
                Arguments.of(10,0,0.7,true,points),
                Arguments.of(10,0.5,0.8,true,points),
                Arguments.of(10,0,0.7,true,points),
                Arguments.of(10,1,0.7,true,1503.5),

                //Tests without immunity if the win value is smaller than the expected with the points should decrease
                Arguments.of(10,1,0.7,false,1503.5),
                Arguments.of(10,0,0.7,false,1493.5),
                Arguments.of(10,0.5,0.8,false,1497.5)
        );
    }


    @ParameterizedTest
    @DisplayName("Test Win Diff")
    @MethodSource("WinDiff_arguments")
    void calcWinDiff(double win, double expectedWin, double expected_result) {
        // this test checks if the function indeed the difference between the first and second arguments and nothing
        // is swapped
        // the tests also check if the result is indeed a double
        double result = team.calcWinDiff(win,expectedWin);
        assertEquals(expected_result,result,1e-15);
    }

    private static Stream<Arguments> WinDiff_arguments(){
        return Stream.of(
                // Format: W,We,expected answer
                // The W argument can only be (0, 0.5, 0.75, 1.0)
                //Testing W = 0 with a range of different values of We
                Arguments.of(0.0,0.0,0.0),
                Arguments.of(0.0,0.25,-0.25),
                Arguments.of(0.0,0.5,-0.5),
                Arguments.of(0.0,0.75,-0.75),
                Arguments.of(0.0,1.0,-1.0),
                //Testing W = 0.5 with a range of different values of We
                Arguments.of(0.5,0.0,0.5),
                Arguments.of(0.5,0.4,0.1),
                Arguments.of(0.5,0.5,0.0),
                Arguments.of(0.5,0.63,-0.13),
                Arguments.of(0.5,1.0,-0.5),
                //Testing W = 0.75 with a range of different values of We
                Arguments.of(0.75,0.0,0.75),
                Arguments.of(0.75,0.45,0.3),
                Arguments.of(0.75,0.5,0.25),
                Arguments.of(0.75,0.6,0.15),
                Arguments.of(0.75,0.9,0.-0.15),
                Arguments.of(0.75,1.0,-0.25),
                //Testing W = 1.0 with a range of different values of We
                Arguments.of(1.0,0.0,1.0),
                Arguments.of(1.0,0.25,0.75),
                Arguments.of(1.0,0.5,0.5),
                Arguments.of(1.0,0.75,0.25),
                Arguments.of(1.0,1.0,0)
        );
    }
}