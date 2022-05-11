package Algorithm;

import javafx.util.Pair;

public class Match {
    int id;
    private Team team1;
    private Team team2;
    private Pair<Double, Double> wins;
    private Pair<Double, Double> expectedWins;
    private int importance;
    private Categories competitionType;
    private Rounds round;
    private Boolean PSO;


    public Match(Team team1, Team team2, Categories competitionType, Rounds round,
                 int team1Score, int team2Score, int importance, Boolean PSO,Boolean inCalendar) {
        this.team1 = team1;
        this.team2 = team2;
        this.competitionType = competitionType;
        this.round = round;
        this.PSO = PSO;
        this.importance = calcImportance(competitionType, round, inCalendar);
        wins = calculateWins(team1Score, team2Score,PSO);
        expectedWins = calculateExpectedWins();
        Boolean immunity = calcImmunity(competitionType,round);         //true if a match is in the knockout stages of a final competition
        if (this.round != Rounds.Group_Stage){
            immunity = true;
        }
        team1.calculateNewPoints(importance,wins.getKey(),expectedWins.getKey(), immunity);
        team2.calculateNewPoints(importance,wins.getValue(),expectedWins.getValue(),immunity);


    }
    public int calcImportance(Categories competitionType, Rounds round,Boolean inCalendar){
        int Importance;
        switch(competitionType){
            case Friendly_Match:
                if(inCalendar){
                    return 10;
                }else{
                    return 5;
                }
            case Nations_League:
                if(round == Rounds.Group_Stage){
                    return 15;
                }else{
                    return 25;
                }
            case Q_for_Conf:
            case Q_for_World_Cup:
                return 25;
            case Confederation_Final:
                if(round == Rounds.Group_Stage || round == Rounds.Round_of_16){
                    return 35;
                }else{
                    return 40;
                }
            case Confederations_Cup:
                return 40;
            case World_Cup:
                if(round == Rounds.Group_Stage || round == Rounds.Round_of_16){
                    return 50;
                }else{
                    return 60;
                }
            default:
                return 0;       // if fore some reason the competition type isn't from the list
        }
    }
    public Pair<Double, Double> calculateExpectedWins(){
        double drTeam1 = calcRatingDifference(team1.getPoints(), team2.getPoints());
        double drTeam2 = calcRatingDifference(team2.getPoints(), team1.getPoints());

        double team1ExpectedWin = calcTeamExpectedWin(drTeam1);
        double team2ExpectedWin = calcTeamExpectedWin(drTeam2);

        return new Pair<>(team1ExpectedWin, team2ExpectedWin);
    }
    public double calcTeamExpectedWin(double dr){
        return 1/(Math.pow(10, -dr/600)+1);
    }
    public double calcRatingDifference(double team1Points, double team2Points){
        return (team1Points - team2Points);
    }

    public Pair<Double, Double> calculateWins(int team1Score, int team2Score,boolean PSO){
        if (team1Score > team2Score){                       //this function needs to be modified Todo
            if (PSO){
                return new Pair<>(0.75, 0.5);
            }
            return new Pair<>(1.0, 0.0);
        }
        else if (team1Score < team2Score) {
            if (PSO){
                return new Pair<>(0.5, 0.75);
            }
            return new Pair<>(0.0, 1.0);
        }
        return new Pair<>(0.5, 0.5);
    }
    public boolean calcImmunity(Categories competitionType, Rounds round){
        if(round == Rounds.Group_Stage)return false;
        if(competitionType==Categories.Confederation_Final||competitionType==Categories.Confederations_Cup||competitionType==Categories.World_Cup){
            return true;
        }else{
            return false;
        }
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pair<Double, Double> getWins() {
        return wins;
    }

    public void setWins(Pair<Double, Double> wins) {
        this.wins = wins;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public Pair<Double, Double> getExpectedWins() {
        return expectedWins;
    }

    public void setExpectedWins(Pair<Double, Double> expectedWins) {
        this.expectedWins = expectedWins;
    }

    public Categories getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(Categories competitionType) {
        this.competitionType = competitionType;
    }

    public Rounds getRound() {
        return round;
    }

    public void setRound(Rounds round) {
        this.round = round;
    }

    public enum Rounds {
        Group_Stage("Group Stage"),
        Round_of_16("Round of 16"),
        Quarter_Final("Quarter Final"),
        Semi_Final("Semi Final"),
        Final("Final");

        public final String label;

        private Rounds(String label) {
            this.label = label;
        }
        public String toString() {          // check if you need this Todo
            return label;
        }
    }
    public static enum Categories{
        Friendly_Match("Friendly Match"),
        Nations_League("Nations League"),
        Q_for_Conf("Qualifier for Confederation final competition"),
        Q_for_World_Cup("Qualifier for World Cup"),
        Confederation_Final("Confederation final competition"),
        Confederations_Cup("Confederations Cup"),
        World_Cup("World Cup");
        public final String label;
        private Categories(String label) {
            this.label = label;
        }
        public String toString() {          // check if you need this Todo
            return label;
        }

    }
    //String r = Round.Final.label;
}
