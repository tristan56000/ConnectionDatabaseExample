package fr.ensibs.database;

import java.sql.*;
import java.util.ArrayList;

public class HashClassTest {

    public static void main(String[] args) {
        if(args.length != 2){
            System.exit(-1);
        }
        String db = args[0];
        String table = args[1];
        String url = "jdbc:mysql://localhost:3306/"+db+"?autoReconnect=true&useSSL=false&serverTimezone=Europe/Paris";
        String user= "PHILIPPE";
        String password = "mysql";
        try{
            ArrayList<String> colums = new ArrayList<>();

            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();

            /*
            Query getting all the columns from the given table
             */
            String sql1 = "select column_name from information_schema.columns where table_name = \'"+table+"\'";
            ResultSet resultSet1 = statement.executeQuery(sql1);

            while(resultSet1.next()){
                colums.add(resultSet1.getString("column_name"));
            }

            String pre ="";
            for (String column: colums) {
                pre = pre + "\'"+column+"\',"+"ifnull("+column+",\'\'),";
            }

            pre = pre.substring(0,pre.length()-1);

            String sql2 = "select sha2(concat("+pre+"),256) as hash from "+db+"."+table;

            System.out.println("Hash");
            ResultSet resultSet2 = statement.executeQuery(sql2);

            while(resultSet2.next()){
                System.out.println(resultSet2.getString("hash"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}