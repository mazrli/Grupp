package se.sahlgrenska.database;

import se.sahlgrenska.sjukhus.person.employee.*;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IOManager {

    private final Database database = new Database("gamebacon.net", "baconwilliam", "tjena22", "sahlgrenska");


    public void executeQuery(String statement) {
        if(database.isConnected()) {

        }
    }



    public Employee getEmployee(LoginDetails loginDetails) {
        Employee employee = null;

        if(database.isConnected()) {
            try {
                ResultSet resultSet = callProcedure("getEmployee", loginDetails.getUsername(), loginDetails.getPassword());

                while (resultSet.next()) {

                    String id = String.valueOf(resultSet.getInt(1));
                    float salary = resultSet.getInt(3);
                    float workingHours = resultSet.getFloat(4);
                    Accessibility accessibility = Accessibility.valueOf(resultSet.getString(5));
                    employee = new Employee(id, salary, workingHours, accessibility, loginDetails);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employee;
    }

    /*
        metod för att köra mysql procedures
        oviktigt att veta hur den funkar
     */
    private ResultSet callProcedure(String name, Object... args) {

        try {

            String query = String.format("{ CALL %s(", name);

            for(int i = 0; i < args.length; i++)
                query += "?,";

            query = query.substring(0, query.length() - 1) + ") } ";
            System.out.println(query);

            CallableStatement callableStatement = database.getConnection().prepareCall(query);

            for(int i = 1; i <= args.length; i++)
                callableStatement.setObject(i, args[i - 1]);

            return callableStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void remember(LoginDetails loginDetails) {

        try {
            Path path = Paths.get(getClass().getResource("save.txt").toURI());
            Files.writeString(path, "Gay", null);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public LoginDetails getRemember() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass().getResource("save.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
