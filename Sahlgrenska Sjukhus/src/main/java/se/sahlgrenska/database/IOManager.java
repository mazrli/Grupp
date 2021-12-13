package se.sahlgrenska.database;

import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Address;
import se.sahlgrenska.sjukhus.person.Gender;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.*;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class IOManager {


    private final Database database = new Database("gamebacon.net", "guest4life", "Lp3jRWB4FQrjC4z", "sahlgrenska");

    public Set<Employee> getOnline() {
        if(database.isConnected()) {

            ResultSet resultSet = callProcedure("getOnline");
            return getEmployees(resultSet);
        }

        return null;
    }


    public void query(String query) {
        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();
                boolean resultSet = statement.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //test3

    public Set<Employee> getAllEmployees(LoginDetails loginDetails) {

        if(database.isConnected()) {
            ResultSet resultSet = callProcedure("getAllEmployees", loginDetails.getUsername(), loginDetails.getPassword());
            return getEmployees(resultSet);
        }

        return null;
    }

    private Set<Employee> getEmployees(ResultSet resultSet) {
        Set<Employee> employees = new HashSet<>();

        try {
            while (resultSet.next()) {
                String id = String.valueOf(resultSet.getInt(1));
                float salary = resultSet.getInt(2);
                float workingHours = resultSet.getFloat(3);
                Accessibility accessibility = Accessibility.valueOf(resultSet.getString(4));

                String personNum = resultSet.getString(5);
                String fistName = resultSet.getString(6);
                String lastName = resultSet.getString(7);
                String phone = resultSet.getString(8);
                Gender gender = Gender.valueOf(resultSet.getString(9));


                String country = resultSet.getString(10);
                String city = resultSet.getString(11);
                String street = resultSet.getString(12);
                String zip = resultSet.getString(13);

                Address address = new Address(country, city, street, zip);

                Person person = new Person(fistName, lastName, personNum, gender, phone, address);

                String username = resultSet.getString(14);
                String password = resultSet.getString(15);

                LoginDetails loginDetails = new LoginDetails(username, password);

                Employee employee = new Employee(person, id, salary, workingHours, accessibility, loginDetails);
                employees.add(employee);

            }
        } catch (Exception e) {

            }
        return employees;
    }



    public Employee getEmployee(LoginDetails loginDetails) {
        Employee employee = null;

        if(database.isConnected()) {
            try {
                ResultSet resultSet = callProcedure("getEmployee", loginDetails.getUsername(), loginDetails.getPassword());

                while (resultSet.next()) {

                    String id = String.valueOf(resultSet.getInt(1));
                    float salary = resultSet.getInt(2);
                    float workingHours = resultSet.getFloat(3);
                    Accessibility accessibility = Accessibility.valueOf(resultSet.getString(4));

                    //todo get person
                    String personNumm = resultSet.getString(5);
                    String firstName = resultSet.getString(6);
                    String lastName = resultSet.getString(7);
                    String phoneNum= resultSet.getString(8);
                    Gender gender = Gender.valueOf(resultSet.getString(9));

                    //int addressID = resultSet.getInt(10);
                    String sweden = resultSet.getString(10);
                    String city = resultSet.getString(11);
                    String street = resultSet.getString(12);
                    String zip = resultSet.getString(13);

                    Address address = new Address(sweden, city, street, zip);

                    Person person = new Person(firstName, lastName, personNumm, gender, phoneNum, address);

                    employee = new Employee(person, id, salary, workingHours, accessibility, loginDetails);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employee;
    }

    /*
        metod för att köra mysql procedures
        oviktigt att veta hur den fungerar
     */
    private ResultSet callProcedure(String name, Object... args) {

        try {

            String query = String.format("{ CALL %s(", name);


            if(args.length == 0)
                query += ")";
            else
                for(int i = 0; i < args.length; i++)
                    query += "?,";


            query = query.substring(0, query.length() - 1) + ") } ";

            CallableStatement callableStatement = database.getConnection().prepareCall(query);

            for(int i = 1; i <= args.length; i++)
                callableStatement.setObject(i, args[i - 1]);

            return callableStatement.executeQuery();

        } catch (SQLNonTransientConnectionException e) {
            //tillfällig lösning för connection timeout problem
            UtilGUI.error("Något gick fel. (Starta om programmet)");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void remember(LoginDetails loginDetails) {
        try {
            File file = new File(getClass().getResource("/save.txt").toURI());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            if(loginDetails != null)
                writer.write(loginDetails.getUsername() + " " + loginDetails.getPassword());
            else
                writer.write("");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public LoginDetails getRemember() {
        LoginDetails loginDetails = null;

        try {
            File file = new File(getClass().getResource("/save.txt").toURI());
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();

            if(line != null) {
                String[] stuff = line.split(" ");

                loginDetails = new LoginDetails(stuff[0], stuff[1]);
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return loginDetails;
    }

    public void closeDB() {
        database.close();
    }
}
