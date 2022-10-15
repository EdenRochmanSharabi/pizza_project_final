package MVC;

import java.sql.*;

public class Controller {

    public static Connection con;
    public static Statement statement;

    static {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza_final", "root", "");
            statement = con.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[]args) throws SQLException{

        ResultSet resultados = statement.executeQuery("SELECT * FROM Pizzas");

        while(resultados.next()){
            System.out.println(resultados.getString("name"));
            System.out.println("test");
        }

    }


}
