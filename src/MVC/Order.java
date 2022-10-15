package MVC;

import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Scanner;

public class Order {

    Integer ID;
    String name;
    String postal;
    String tel;

    LinkedList<Integer> Pizzas = new LinkedList<>();
    double price = 0;

    public Order(){

    }

    public void addPizza(Integer Pizza){
        price += (double) Pizza;
        Pizzas.add(Pizza);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public double getPrice() {
        return price;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addOrder(){
        System.out.println(price);
    }

}
