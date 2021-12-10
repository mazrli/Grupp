package se.sahlgrenska.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    //våran connection för att kommunicera med databasen
    private Connection connection = null;

    //för att skapa en connection behöver vi en host, username, password och namnet på databasen.
    public Database(String host, String username, String password, String database) {

        try {
            //detta är mysql's driver klass, det är en dependency som vi skapar anonymt
            Class.forName("com.mysql.cj.jdbc.Driver");

            //komplett url med alla uppgifter och inställningar
            String url = String.format("jdbc:mysql://%s:3306/%s?user=%s&password=%s&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&noAccessToProcedureBodies=true", host, database, username, password);

            //skapa en connection med url:en
            connection = DriverManager.getConnection(url);

            //den throwade inga exceptions (mycket kan gå fel) och vi kan nu kommunicera med databasen
            System.out.println("Connection successful.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //getter
    public Connection getConnection() {
        return connection;
    }

    //kollar om vi har en connection
    public boolean isConnected() {
        return connection != null;
    }

    public void close() {
        try {
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
