package Algorithm;

public class Team {
    private String name;
    private double points;
    private int rank;

    public Team(String name, double points, int rank){
        this.name = name;
        this.points = points;
        this.rank = rank;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void calculateNewPoints(int importance, int win, int expectedWin){
        int winDiff = calcWinDiff(win, expectedWin);

        this.setPoints(this.points + importance * winDiff);

    }
    public int calcWinDiff(int win, int expectedWin){
        return win - expectedWin;
    }
}
