package Database;

import java.sql.*;
import java.util.Objects;

public class Queries {

    public Boolean GetAllTeams(){
        Connection conn = MySQL_Connector.ConnectDB();


        try{
            Statement stmt = Objects.requireNonNull(conn).createStatement();
            ResultSet rs = stmt.executeQuery("select * from Teams");

            while (rs.next()){
                String name = rs.getString("Name");
                double score = rs.getDouble("Score");
                int rank = rs.getInt("Rank");

            }
            return true;
        }catch(SQLException e){
            return false;
        }

    }

    public Boolean addMatch(String team1Name, String team2Name, String competitionType, String round,
    int team1score, int team2score, int importance,boolean PSO){


        Connection conn = MySQL_Connector.ConnectDB();
        try{
            PreparedStatement pstmt = Objects.requireNonNull(conn).prepareStatement
                    ("INSERT INTO Matches(Team1, Team2, Importance, Competition_type, Round, Team1_Score, Team2_Score, PSO)" +
                            " VALUES(?, ?, ?,?,?,?,?,?)");
            pstmt.setString(1, team1Name);
            pstmt.setString(2, team2Name);
            pstmt.setInt(3, importance);
            pstmt.setString(4, competitionType);
            pstmt.setString(5, round);
            pstmt.setInt(6, team1score);
            pstmt.setInt(7, team2score);
            pstmt.setBoolean(8, PSO);
            pstmt.execute();
            return true;
        }catch (SQLException e){
            return false;
        }

    }

    public Boolean getAllMatches() {
        Connection conn = MySQL_Connector.ConnectDB();

        try{

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
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    public Boolean deleteAllMatches(){
        Connection conn = MySQL_Connector.ConnectDB();
        try{
            Statement stmt = Objects.requireNonNull(conn).createStatement();
            stmt.executeUpdate("DELETE FROM Matches");
            return true;
        }catch (SQLException e){
            return false;
        }
    }
    public void getLastMatch() throws SQLException {
        Connection conn = MySQL_Connector.ConnectDB();
        Statement stmt = Objects.requireNonNull(conn).createStatement();
        ResultSet rs = stmt.executeQuery("select * from Matches order by ID desc limit 1");
        rs.next();

        System.out.println("Last match data:");
        System.out.println("Match id : " + rs.getInt("Id"));
        System.out.println("Team1 : " + rs.getString("Team1") + " , Team1 score: " + rs.getString("Team1_Score")
                + " Team 2 : " + rs.getString("Team2") + " , Team2 score " + rs.getString("Team2_Score"));

    }
}
