package fr.ensibs.database;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class having a main method which will get all the entry of a table and hash them with SHA2
 * The point is two distinct entries have two distinct hashes
 */
public class HashClassTest {

    /**
     * Prints a usage message and exits
     */
    private static void usage(){
        System.out.println("Usage: ConnectDatabaseExample <dbmsUser> <userPassword> <databaseName> <table>");
        System.exit(-1);
    }

    /**
     * Main method of the class
     * @param args see usage
     */
    public static void main(String[] args) {
        if(args.length != 4){
            usage();
        }
        String user = args[0];String password = args[1];
        String db = args[2];String table = args[3];
        String url = "jdbc:mysql://localhost:3306/"+db+"?autoReconnect=true&useSSL=false&serverTimezone=Europe/Paris";
        try{
            ArrayList<String> colums = new ArrayList<>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            /*
            Query getting all the columns from the given table of the database
             */
            String sql1 = "select column_name from information_schema.COLUMNS where TABLE_NAME = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1,table);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while(resultSet1.next()){
                colums.add(resultSet1.getString("column_name"));
            }
            /*
            The point is that the value of a column for an entry will be concatenated with its column's name
            Afterward, all values+name are concatenated together for each entry, and this final concatenation
            is hashed through SHA2
             */
            String pre ="";
            for (String column: colums) {
                pre = pre + "\'"+column+"\',"+"ifnull("+column+",\'\'),";
            }
            pre = pre.substring(0,pre.length()-1);
            String sql2 = "SELECT SHA2(CONCAT("+pre+"),256) AS HASH FROM "+db+"."+table;
            System.out.println("HASH");
            Statement statement = connection.createStatement();
            ResultSet resultSet2 = statement.executeQuery(sql2);
            while(resultSet2.next()){
                System.out.println(resultSet2.getString("HASH"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String generateSecretToken() {
        SecureRandom r = new SecureRandom();
        return Long.toHexString(r.nextLong());
    }
}