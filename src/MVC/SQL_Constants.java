package MVC;

public class SQL_Constants {

    public SQL_Constants(){}

    public final String SELECT_PIZZA_ALL = "SELECT * FROM Pizzas";
    public final String SELECT_INGRIDIENTS_ALL = "SELECT * FROM Ingridients";
    public final String SELECT_DRINK_ALL = "SELECT * FROM Drink";
    public final String SELECT_EMPLOYE_ALL = "SELECT * FROM Employe";
    public final String SELECT_ORDEN_ALL = "SELECT * FROM Orden";

    public final String INSERT_ORDEN(Integer id, String name) {
        return "INSERT INTO Orden values(" + id +  ", " + name + ");";
    }

    public final String INSERT_INGRIDIENTS(Integer id, Integer pizzaID, String name, double price) {
        return "INSERT INTO Ingridients VALUES(" + id +  ", " + pizzaID + ", "+ name +  ", " + price +");";
    }



}
