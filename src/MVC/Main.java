package MVC;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    Order order = new Order();
    Scanner sc = new Scanner(System.in);
    static HashMap<String, Integer> Pizza_ID = new HashMap<>();
    ArrayList<String> Pname = new ArrayList<>();
    ArrayList<Integer> Pprice = new ArrayList<>();
    ArrayList<String> Dname = new ArrayList<>();
    ArrayList<Integer> Dprice = new ArrayList<>();
    ArrayList<String> Codes = new ArrayList<>();
    double time;
    SQL_Constants sql = new SQL_Constants();

    boolean CANCEL = false;
    String name;
    String postal;
    String tel;
    String code;
    Integer id;
    Integer idCODE;

    public Main() throws SQLException{

        getData();
        MakeArt("A&E PIZZA");
        askForData();
        askForPizza();
        askForDrink();
        askForCode();
        tellInfoVAT();
        confirmationOrder();
        orderProcessing();

    }
    public static void main(String[] args) throws SQLException {
        Main main = new Main();

    }

    public void orderProcessing() throws SQLException{

        if(!CANCEL){
            Conexion conexion = new Conexion();
            Statement statement = conexion.getStatement();
            System.out.println("INSERT INTO Orden values("+  + id + ",'"+ name + "','" + postal + "','" + tel +"'," + order.getPrice()+");");
            statement.execute("INSERT INTO Orden values("+  + id + ",'"+ name + "','" + postal + "','" + tel +"'," + order.getPrice()+");");

        }
    }

    public void askForData(){
        System.out.println("Hi this is A & E Pizza, whats your name?");
        this.name = sc.nextLine();
        System.out.println("Hi" + name + ", whats your postal code?");
        this.postal = sc.nextLine();
        System.out.println("Got it, last question which is your telephone?");
        this.tel = sc.nextLine();
    }

    public void confirmationOrder(){
        MakeArt("CONFIRMATION");
        System.out.println("Okay then I will confirm the order");
        double time = System.currentTimeMillis();
        System.out.println("You have 5 minutes to cancel the order");
        double rest;

        do{
            System.out.println("Type yes to cancel:");

            String cancel = sc.nextLine().toString();

            rest = System.currentTimeMillis() - time;

            if(cancel.equals("yes")){
                //30 secs is 30000
                if(rest < 30000){

                    double a = rest/60000;
                    System.out.println("The current time is of " + a + " minutes");
                    System.out.println("ORDER CANCELLED");
                    CANCEL = true;
                    break;

                }else{
                    double a = rest/60000;
                    System.out.println("The current time is of " + a + " minutes");
                    System.out.println("ORDER NOT CANCELLED");
                }
            }
            if(rest > 30000){
                break;
            }
        }while(rest < 30000);

    }

    public void tellInfoVAT(){
        System.out.println("Okay the price of your order is of " + order.getPrice() + "€");
        System.out.println("But we have to include 9% of the VAT");
        double currentPrice = order.getPrice()* 1.09;
        System.out.println("So the final price of your oder is " + currentPrice);
        order.setPrice(currentPrice);
    }

    public void askForCode() throws SQLException{
        if(order.Pizzas.size()>3){
            System.out.println("Do you have a discount code? (yes || no)");
            String yes = sc.nextLine().toLowerCase();
            if(yes.equals("yes")){
                System.out.println("Type the code please ;)");
                String code = sc.nextLine();
                checkCode(code);
            }else {
                String c = codeCreator();
                System.out.println("Your discount code for the next time you buy is: " + c);
                Conexion conexion = new Conexion();
                Statement statement = conexion.getStatement();
                statement.execute("INSERT INTO Codes values("+  + idCODE + ",'"+ c + "');");

            }
        }
    }

    public void checkCode(String code){
        if (Codes.contains(code)){
            System.out.println("Congratulations you have a discount of 10%");
            double currentPrice = + order.getPrice()*0.9;
            System.out.println("Your actual price is of " + currentPrice + "€");
            order.setPrice(currentPrice);
        }else{
            System.out.println("Sorry that code does not exist");
        }
    }

    public void askForPizza(){
        System.out.println("Hello do you want pizza? (Yes || No)");
        String yes = sc.nextLine().toLowerCase();
        while(yes.equals("yes")){
            order.addPizza(doesPizzaExist());
            System.out.println("Do you want more pizza? (Yes || No)");
            yes = sc.nextLine().toLowerCase();
            if (!yes.equals("yes")){
                break;
            }
        }
        System.out.println("Final price is of " + order.price + "€");

    }

    public void askForDrink(){

        System.out.println("Do you also want Drink? (Yes || No)");
        String yes = sc.nextLine().toLowerCase();
        while(yes.equals("yes")){
            order.addPizza(doesDrinkExist());
            System.out.println("Do you want more Drink? (Yes || No)");
            yes = sc.nextLine().toLowerCase();
            if (!yes.equals("yes")){
                break;
            }
        }
        System.out.println("Final price is of " + order.price + "€");
    }

    public Integer doesPizzaExist(){
        String pizza = "";
        System.out.println("Select Pizza please:");
        System.out.println(Pname.toString());
        pizza = sc.nextLine();
        Integer price = 0;
        if (Pname.contains(pizza)){
            int index = Pname.indexOf(pizza);
            price = this.Pprice.get(index);
            System.out.println("Price is of " + pizza + " is " + price + "€");
        }else{
            System.out.println("Name does not exist try again please");
            doesPizzaExist();
        }

        return price;
    }

    public Integer doesDrinkExist(){
        String pizza = "";
        System.out.println("Select Drink please:");
        System.out.println(Dname.toString());
        pizza = sc.nextLine();
        Integer price = 0;
        if (Dname.contains(pizza)){
            int index = Dname.indexOf(pizza);
            price = this.Dprice.get(index);
            System.out.println("Price is of " + pizza + " is " + price + "€");
        }else{
            System.out.println("Name does not exist try again please");
            doesDrinkExist();
        }

        return price;
    }


    public void MakeArt(String palabra){

        int width = 130;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(palabra, 10, 20);
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "/");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(sb);
        }

        System.out.println("");
        System.out.println("");

    }

    public String codeCreator(){

        StringBuilder c = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            double a = Math.random()*10;
            int b = (int) a;
            char x = Integer.toString(b).charAt(0);
            c.append(x);
        }
        return c.toString();
    }

    public void getData()throws SQLException{

        Conexion conexion = new Conexion();
        Statement statement = conexion.getStatement();
        ResultSet resultados = statement.executeQuery(sql.SELECT_PIZZA_ALL);


        while(resultados.next()){
            Pname.add(resultados.getString(2).toLowerCase());
            Pprice.add(resultados.getInt(3));
        }

        Conexion conexion2 = new Conexion();
        Statement statement2 = conexion2.getStatement();
        ResultSet resultados2 = statement2.executeQuery(sql.SELECT_DRINK_ALL);

        while(resultados2.next()){
            Dname.add(resultados2.getString(2).toLowerCase());
            Dprice.add(resultados2.getInt(3));
        }

        Conexion conexion3 = new Conexion();
        Statement statement3 = conexion3.getStatement();
        ResultSet resultados3 = statement3.executeQuery(sql.SELECT_ORDEN_ALL);

        while(resultados3.next()){
            id = resultados3.getInt(1);
        }
        id ++;

        Conexion conexion4 = new Conexion();
        Statement statement4 = conexion4.getStatement();
        ResultSet resultados4 = statement4.executeQuery(sql.SELECT_CODE_ALL);

        while(resultados4.next()){
            idCODE = resultados4.getInt(1);
            Codes.add(resultados4.getString(2));
            System.out.println(resultados4.getString(2));
        }
        idCODE ++;
    }
}
