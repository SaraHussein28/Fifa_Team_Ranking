package Algorithms;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    Team team1,team2;
    Match match;
    @BeforeEach
    void setup(){
        team1=new Team("Egypt",1500,5);
        team2=new Team("England",1600,2);
        match=new Match(team1,team1, Match.Categories.World_Cup, Match.Rounds.Final,3,
                2,1,false,true,null);//Todo check this

    }

    //TODO should I test the constructor?

    @ParameterizedTest
    @MethodSource("calcImportance_arguments")
    void calcImportance(Match.Categories competitionType, Match.Rounds round, Boolean inCalendar, int expected) {
        //Todo
        int result = match.calcImportance(competitionType,round,inCalendar);
        assertEquals(expected,result);
    }
    private static Stream<Arguments> calcImportance_arguments(){
        Stream.Builder<Arguments> arg_builder= Stream.builder();
        for(Match.Rounds round : Match.Rounds.values()){
            arg_builder.add(Arguments.of(Match.Categories.Friendly_Match,round,false,5));
            arg_builder.add(Arguments.of(Match.Categories.Friendly_Match,round,true,10));
        }
        arg_builder.add(Arguments.of(Match.Categories.Nations_League,Match.Rounds.Group_Stage,false,15));
        arg_builder.add(Arguments.of(Match.Categories.Nations_League,Match.Rounds.Group_Stage,true,15));
        for(Match.Rounds round : Match.Rounds.values()){
            if(round!= Match.Rounds.Group_Stage){
                arg_builder.add(Arguments.of(Match.Categories.Nations_League,round,false,25));
                arg_builder.add(Arguments.of(Match.Categories.Nations_League,round,true,25));
            }
        }
        for(Match.Rounds round : Match.Rounds.values()){
            arg_builder.add(Arguments.of(Match.Categories.Q_for_Conf,round,false,25));
            arg_builder.add(Arguments.of(Match.Categories.Q_for_Conf,round,true,25));
            arg_builder.add(Arguments.of(Match.Categories.Q_for_World_Cup,round,false,25));
            arg_builder.add(Arguments.of(Match.Categories.Q_for_World_Cup,round,true,25));
        }
        arg_builder.add(Arguments.of(Match.Categories.Confederation_Final,Match.Rounds.Group_Stage,false,35));
        arg_builder.add(Arguments.of(Match.Categories.Confederation_Final,Match.Rounds.Group_Stage,true,35));
        arg_builder.add(Arguments.of(Match.Categories.Confederation_Final,Match.Rounds.Round_of_16,false,35));
        arg_builder.add(Arguments.of(Match.Categories.Confederation_Final,Match.Rounds.Round_of_16,true,35));
        for(Match.Rounds round : Match.Rounds.values()){
            if(round!= Match.Rounds.Group_Stage&&round != Match.Rounds.Round_of_16){
                arg_builder.add(Arguments.of(Match.Categories.Confederation_Final,round,false,40));
                arg_builder.add(Arguments.of(Match.Categories.Confederation_Final,round,true,40));
            }
        }
        for(Match.Rounds round : Match.Rounds.values()){
            arg_builder.add(Arguments.of(Match.Categories.Confederations_Cup,round,false,40));
            arg_builder.add(Arguments.of(Match.Categories.Confederations_Cup,round,true,40));
        }
        arg_builder.add(Arguments.of(Match.Categories.World_Cup,Match.Rounds.Group_Stage,false,50));
        arg_builder.add(Arguments.of(Match.Categories.World_Cup,Match.Rounds.Group_Stage,true,50));
        arg_builder.add(Arguments.of(Match.Categories.World_Cup,Match.Rounds.Round_of_16,false,50));
        arg_builder.add(Arguments.of(Match.Categories.World_Cup,Match.Rounds.Round_of_16,true,50));

        for(Match.Rounds round : Match.Rounds.values()){
            if(round!= Match.Rounds.Group_Stage&&round != Match.Rounds.Round_of_16){
                arg_builder.add(Arguments.of(Match.Categories.World_Cup,round,false,60));
                arg_builder.add(Arguments.of(Match.Categories.World_Cup,round,true,60));
            }
        }
        return arg_builder.build();
    }

    @RepeatedTest(10)
    void calculateExpectedWins() {
        Pair<Double,Double> result = match.calculateExpectedWins(1500,1500);
        assertEquals(0.5,result.getKey());
        assertEquals(0.5,result.getValue());
        Random rand = new Random();
        double team1_points = 2000*rand.nextDouble();
        double team2_points = 2000*rand.nextDouble();
        result = match.calculateExpectedWins(team1_points,team2_points);
        assertEquals(match.calcTeamExpectedWin(team1_points-team2_points),result.getKey());
        assertEquals(match.calcTeamExpectedWin(team2_points-team1_points),result.getValue());
    }

    @ParameterizedTest
    @MethodSource("TeamExpectedWin_arguments")
    void calcTeamExpectedWin(double dr, double expected) {
        double result = match.calcTeamExpectedWin(dr);
        assertEquals(expected,result,1e-11);
    }
    private static Stream<Arguments> TeamExpectedWin_arguments(){
        return Stream.of(
                // Format: dr, expected answer
                Arguments.of(0,0.5),
                Arguments.of(3.6,0.5034538227),
                Arguments.of(100.36,0.59511358927),
                Arguments.of(600,0.90909090909),
                Arguments.of(2000,0.99953605646),       //max diff possible
                Arguments.of(-2000,0.00046394354),
                Arguments.of(-559.6,0.10456091613),
                Arguments.of(-95.32,0.4095553064),
                Arguments.of(-2,0.49808118851)
                // should I test a function with randomly generated dr?
        );
    }

    @Test
    void calcRatingDifference() {
        double difference = match.calcRatingDifference(2000,1500.5);
        assertEquals(499.5,difference);
        difference = match.calcRatingDifference(500,640);
        assertEquals(-140,difference);
    }

    @Test
    void calculateWins() {
        //Todo
    }

    @ParameterizedTest
    @MethodSource("calcImmunity_arguments")
    void calcImmunity(Match.Categories competition_type, Match.Rounds round,boolean expected){
        boolean result = match.calcImmunity(competition_type,round);
        assertEquals(expected,result);
    }
    private static Stream<Arguments> calcImmunity_arguments(){
        return Stream.of(
                // Format: competition type, round, expected
                Arguments.of(Match.Categories.World_Cup,Match.Rounds.Group_Stage,false),
                Arguments.of(Match.Categories.Friendly_Match,Match.Rounds.Round_of_16,false),
                Arguments.of(Match.Categories.Confederations_Cup,Match.Rounds.Quarter_Final,true),
                Arguments.of(Match.Categories.Confederation_Final,Match.Rounds.Final,true),
                Arguments.of(Match.Categories.World_Cup,Match.Rounds.Semi_Final,true)
        );
    }

}