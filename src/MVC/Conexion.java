package MVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public  Connection con;
    public  Statement statement;
    public static final String USER = "root";
    public static final String PASSWORD = "eden";
    public static final String URL = "jdbc:mysql://localhost:3306/pizza_final";

    public Conexion(){
        try{
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = con.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  Connection getCon() {
        return con;
    }
    public Statement getStatement() {
        return statement;
    }
}
