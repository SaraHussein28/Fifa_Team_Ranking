package Database;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;


public class MySQL_Connector {

    private static final String db_url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11493736";
    //private static final String db_url = "jdbc:mysql://localhost:3306/sql11493736";
    private static final String db_username = "sql11493736";
    private static final String db_password = "mymSDXZXIK";

    public static Connection ConnectDB(){

        try {
            // load db driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //establish db connection
            return DriverManager.getConnection(db_url, db_username, db_password);
        } catch (Exception e) {
            Alert alert =
                    new Alert(Alert.AlertType.ERROR,
                            "Cannot connect to online database",
                            ButtonType.OK);
            alert.setTitle("Alert");
            alert.showAndWait();
            return null;
        }
    }

}

