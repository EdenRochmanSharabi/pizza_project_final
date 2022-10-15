package MVC;

import java.sql.*;

public class Controller {


    public static void main(String[]args) throws SQLException{

        SQL_Constants sql = new SQL_Constants();
        Conexion conexion = new Conexion();
        Statement statement = conexion.getStatement();

        ResultSet resultados = statement.executeQuery(sql.SELECT_INGRIDIENTS_ALL);

        Records records = new Records();



        while(resultados.next()){
            //System.out.println(resultados.getString(2));
            records.setRecord((Integer) resultados.getInt(1), resultados.getString(3));
        }
        System.out.println(records.toString());


    }


}
