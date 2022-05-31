package Algorithms;

import javafx.util.Pair;
import Database.*;
import java.sql.*;
import java.util.Objects;

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
    private Team PSOWinningTeam;
    private int team1score,team2score;
    private int month, year;


    public Match(Team team1, Team team2, Categories competitionType, Rounds round,
                 int team1Score, int team2Score, boolean PSO,Boolean inCalendar, Team PSOWinningTeam
                 ,int month, int year) throws SQLException {
        this.team1 = team1;
        this.team2 = team2;
        this.team1score = team1Score;
        this.team2score = team2Score;
        this.month = month;
        this.year = year;
        this.competitionType = competitionType;
        this.round = round;
        this.PSO = PSO;
        this.importance = calcImportance(competitionType, round, inCalendar);
        this.PSOWinningTeam=PSOWinningTeam;
        wins = calculateWins(team1Score, team2Score,PSO,PSOWinningTeam);
        expectedWins = calculateExpectedWins(team1.getPoints(),team2.getPoints());
        Boolean immunity = calcImmunity(competitionType,round);         //true if a match is in the knockout stages of a final competition
        if (this.round != Rounds.Group_Stage){
            immunity = true;
        }

        team1.calculateNewPoints(importance,wins.getKey(),expectedWins.getKey(), immunity);
        team2.calculateNewPoints(importance,wins.getValue(),expectedWins.getValue(),immunity);


    }
    public void execDBMethods() throws SQLException {
        this.addMatch();
        this.updateID();
        this.addMatchToHistory();
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
    public Pair<Double, Double> calculateExpectedWins(double team1_points, double team2_points){
        double drTeam1 = calcRatingDifference(team1_points, team2_points);
        double drTeam2 = calcRatingDifference(team2_points, team1_points);

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

    public Pair<Double, Double> calculateWins(int team1Score, int team2Score,boolean PSO, Team PSOWinner){
        if (team1Score > team2Score){
            return new Pair<>(1.0, 0.0);
        }
        else if (team1Score < team2Score) {

            return new Pair<>(0.0, 1.0);
        }
        else {
            if (PSO) {
                if (PSOWinner.getId() == team1.getId())
                    return new Pair<>(0.75, 0.5);
                if (PSOWinner.getId() == team2.getId())
                    return new Pair<>(0.5, 0.75);
            }
            else{
                return new Pair<>(0.5, 0.5);
            }
        }
        return new Pair<>(0.0, 0.0);
    }
    public boolean calcImmunity(Categories competitionType, Rounds round){
        if(round == Rounds.Group_Stage)return false;
        if(competitionType==Categories.Confederation_Final||competitionType==Categories.Confederations_Cup||competitionType==Categories.World_Cup){
            return true;
        }else{
            return false;
        }
    }

    public void addMatch() throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement
                ("INSERT INTO Matches(Team1, Team2, Importance, Competition_type, Round, Team1_Score, Team2_Score, PSO)" +
                        " VALUES(?, ?, ?,?,?,?,?,?)");
        pstmt.setString(1, this.team1.getName());
        pstmt.setString(2, this.team2.getName());
        pstmt.setInt(3, this.importance);
        pstmt.setString(4, String.valueOf(this.competitionType));
        pstmt.setString(5, String.valueOf(this.round));
        pstmt.setInt(6, team1score);
        pstmt.setInt(7, team2score);
        pstmt.setBoolean(8, this.PSO);
        pstmt.execute();
    }

    public static void getAllMatches() throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        Statement stmt = Objects.requireNonNull(conn).createStatement();
        ResultSet rs = stmt.executeQuery("select * from Matches");

        while(rs.next()){
            int id = rs.getInt("Id");
            String team1 = rs.getString("Team1");
            String team2 = rs.getString("Team2");
            int imp = rs.getInt("Importance");
            String comp_type = rs.getString("Competition_type");
            String round = rs.getString("Round");
            int team1_score = rs.getInt("Team1_Score");
            int team2_score = rs.getInt("Team2_Score");
            int PSO = rs.getInt("PSO");

            System.out.println("Match " + id + ":");
            System.out.println("Team1 : " + team1 + " , Team1 score: " + team1_score
                    + " Team 2 : " + team2 + " , Team2 score " + team2_score);
        }
    }

    public static void getLastMatch() throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        Statement stmt = Objects.requireNonNull(conn).createStatement();
        ResultSet rs = stmt.executeQuery("select * from Matches order by ID desc limit 1");
        rs.next();

        System.out.println("Last match data:");
        System.out.println("Match id : " + rs.getInt("Id"));
        System.out.println("Team1 : " + rs.getString("Team1") + " , Team1 score: " + rs.getString("Team1_Score")
                + " Team 2 : " + rs.getString("Team2") + " , Team2 score " + rs.getString("Team2_Score"));

    }

    // this method should be called each time a match is added
    // BUT before, updateId method should be called on the match
    //also the score of each of the 2 team objects should be updates so that updated scores
    //according to this match are added to the history.
    public void addMatchToHistory() throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement(
                "INSERT INTO score_history VALUES(?,?,?,?,?)");
        PreparedStatement pstmt2 = Objects.requireNonNull(conn).prepareStatement(
                "INSERT INTO score_history VALUES(?,?,?,?,?)");
        pstmt.setString(1, this.team1.getName());
        pstmt.setInt(2, this.getId());
        pstmt.setInt(3, this.getMonth());
        pstmt.setInt(4, this.getYear());
        //team1 points should be updated before calling this method to insert updated score to the history
        pstmt.setDouble(5, this.team1.getPoints());

        pstmt2.setString(1, this.team2.getName());
        pstmt2.setInt(2, this.getId());
        pstmt2.setInt(3, this.getMonth());
        pstmt2.setInt(4, this.getYear());
        //team1 points should be updated before calling this method to insert updated score to the history
        pstmt2.setDouble(5, this.team2.getPoints());

        pstmt.execute();
        pstmt2.execute();
    }

    public void updateID() throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        //get last added match
        Statement stmt = Objects.requireNonNull(conn).createStatement();
        ResultSet rs = stmt.executeQuery("select * from Matches order by ID desc limit 1");
        rs.next();

        this.id = rs.getInt("Id");
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

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
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
}
