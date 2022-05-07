import javafx.util.Pair;

public class Match {
    int id;
    private Team team1;
    private Team team2;
    private Pair<Double, Double> wins;
    private Pair<Double, Double> expectedWins;
    private int importance;
    private String competitionType;
    private String round;


    public Match(Team team1, Team team2, String competitionType, String round,
                 int team1Score, int team2Score, int importance) {
        this.team1 = team1;
        this.team2 = team2;
        this.competitionType = competitionType;
        this.round = round;
        this.importance = calcImportance(competitionType, round);
        wins = calculateW(team1Score, team2Score);
        expectedWins = calculateExpectedWins();


    }
    public int calcImportance(String competitionType, String round){
        // add if conditions to calculate importance
        return 1;

    }
    public Pair<Double, Double> calculateExpectedWins(){
        double drTeam1 = calcRatingDifference(team1.getPoints(), team2.getPoints());
        double drTeam2 = calcRatingDifference(team2.getPoints(), team1.getPoints());

        double team1ExpectedWin = calcTeamExpectedWin(drTeam1);
        double team2ExpectedWin = calcTeamExpectedWin(drTeam2);

        return new Pair<Double, Double>(team1ExpectedWin, team2ExpectedWin);
    }
    public double calcTeamExpectedWin(double dr){
        return 1/(Math.pow(10, -dr/600)+1);
    }
    public double calcRatingDifference(double team1Points, double team2Points){
        return (team1Points - team2Points);
    }

    public Pair<Double, Double> calculateW(int team1Score, int team2Score){
        if (team1Score > team2Score){
            return new Pair<Double, Double>(1.0, 0.0);
        }
        else if (team1Score < team2Score) {
            return new Pair<Double, Double>(0.0, 1.0);
        }
        return new Pair<Double, Double>(0.5, 0.5);
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

    public String getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(String competitionType) {
        this.competitionType = competitionType;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
