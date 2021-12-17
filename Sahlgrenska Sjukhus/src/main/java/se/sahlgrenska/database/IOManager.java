package se.sahlgrenska.database;

import org.yaml.snakeyaml.Yaml;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.sjukhus.*;
import se.sahlgrenska.sjukhus.item.Equipment;
import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.item.ItemType;
import se.sahlgrenska.sjukhus.item.Medicine;
import se.sahlgrenska.sjukhus.person.Gender;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.*;
import se.sahlgrenska.sjukhus.person.patient.BloodType;
import se.sahlgrenska.sjukhus.person.patient.Disease;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import javax.management.Notification;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

public class IOManager {


    private Database database;

    public IOManager() {
        setupDB();
    }


    private void setupDB() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("database.yml");
        Map<String, String> obj = yaml.load(inputStream);

        String host = obj.get("host");
        String user = obj.get("username");
        String password = obj.get("password");

        database = new Database(host, user, password, "sahlgrenska");
    }



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

    public int getEmployeeID(String personNumber) {
        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();
                ResultSet resultSet = callProcedure("getEmployeeID", personNumber);

                while(resultSet.next())
                    return resultSet.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return -1;
    }

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

    private Patient getPatientPre(ResultSet resultSet) {
        Patient patient = null;
        try {
            Person person = getPersonPre(resultSet);
            int patient_id = resultSet.getInt("id");
            String conditionDescription = resultSet.getString("condition_description");
            boolean criticalCondition = resultSet.getBoolean("critical_condition");
            BloodType bloodType = BloodType.valueOf(resultSet.getString("blood_type"));
            //todo: get diseases, notifications

            List<Disease> diseases = getDiseases(patient_id);

            patient = new Patient(person, patient_id, diseases, new ArrayList<Notification>(), conditionDescription, criticalCondition, bloodType);
        } catch (SQLException e) {
        }

        return patient;
    }

    private List<Disease> getDiseases(int patient_id) {
        List<Disease> diseases = new ArrayList<>();


        return diseases;
    }

    private Person getPersonPre(ResultSet resultSet) {
        Person person = null;
        try {
        String personNumm = resultSet.getString("person_number");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String phoneNum= resultSet.getString("phone_number");
        Gender gender = Gender.valueOf(resultSet.getString("gender"));

        String country = resultSet.getString("country");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        String zip = resultSet.getString("zip");

        Address address = new Address(country, city, street, zip);
        person = new Person(firstName, lastName, personNumm, gender, phoneNum, address);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }


    public Set<Person> getAllPersons() {
        Set<Person> persons = new HashSet<>();

        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();
                String query = String.format("SELECT * FROM person p INNER JOIN address a ON p.address_id = a.id");
                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next()) {

                    String personNum = resultSet.getString(1);
                    String fistName = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    String phone = resultSet.getString(4);
                    Gender gender = Gender.valueOf(resultSet.getString(5));

                    int addressID = resultSet.getInt(6);
                    int addressID2 = resultSet.getInt(7);

                    String country = resultSet.getString(8);
                    String city = resultSet.getString(9);
                    String street = resultSet.getString(10);
                    String zip = resultSet.getString(11);

                    Address address = new Address(country, city, street, zip);


                    Person person = new Person(fistName, lastName, personNum, gender, phone, address);
                    persons.add(person);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return persons;
    }

    public Person getPerson(String personNumber) {
        Person person = null;

        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();
                String query = String.format("SELECT * FROM person WHERE person.person_number = \"%s\"", personNumber);
                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next()) {

                    String personNum = resultSet.getString(1);
                    String fistName = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    String phone = resultSet.getString(4);
                    Gender gender = Gender.valueOf(resultSet.getString(5));


                    /*
                    String country = resultSet.getString(10);
                    String city = resultSet.getString(11);
                    String street = resultSet.getString(12);
                    String zip = resultSet.getString(13);

                    Address address = new Address(country, city, street, zip);
                     */

                    person = new Person(fistName, lastName, personNum, gender, phone, new Address());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return person;
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
            File file = new File(getClass().getClassLoader().getResource("save.txt").toURI());

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            if(loginDetails != null)
                writer.write(loginDetails.getUsername() + " " + loginDetails.getPassword());
            else
                writer.write("");

            writer.close();

        } catch (Exception e) {
            System.out.println("Fel med save.txt, kolla att den är kvar eller skapa ny (i resource mappen)");
            e.printStackTrace();
        }
    }


    public LoginDetails getRemember() {
        LoginDetails loginDetails = new LoginDetails("", "");

        try {
            File file = new File(getClass().getClassLoader().getResource("save.txt").toURI());
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();

            if(line != null) {
                String[] stuff = line.split(" ");

                loginDetails = new LoginDetails(stuff[0], stuff[1]);
            }

        } catch (Exception e) {
            System.out.println("Fel med save.txt, kolla att den är kvar eller skapa ny (i resource mappen)");
            e.printStackTrace();
        }

        return loginDetails;
    }

    public void closeDB() {
        database.close();
    }

    public void deleteUser(String id) {
        query(String.format("DELETE FROM employee WHERE id = %s", id));
        query(String.format("DELETE FROM login_details WHERE employee_id = %s", id));
    }

    public static File getFileFromResource(String filePath) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return new File(Objects.requireNonNull(classloader.getResource(filePath)).getFile());
    }

    public Set<Patient> getPatients() {
        Set<Patient> patients = new HashSet<>();

        if (database.isConnected()) {
            ResultSet resultSet = callProcedure("getPatients");

            try {
                while(resultSet.next()) {
                    patients.add(getPatientPre(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return patients;
    }


    public Employee getEmployee(int employee_id) {
        Employee employee = null;
        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM employee emp INNER JOIN person per ON emp.person_id = per.person_id INNER JOIN address ad ON per.address_id = ad.id WHERE emp.id = " + employee_id);
                employee = getEmployeePre(resultSet);
            } catch (SQLException e) {

            }
        }
        return employee;
    }

    public Map<Patient, List<Journal>> getJournals() {
        Map<Patient, List<Journal>> journals = new HashMap<>();

        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM patient pat INNER JOIN person per ON pat.person_number = per.person_number INNER JOIN address ad ON per.address_id = ad.id");

                while(resultSet.next()) {

                    Patient patient = getPatientPre(resultSet);
                    Statement statement1 = database.getConnection().createStatement();
                    ResultSet resultJournalSet = statement1.executeQuery(String.format("SELECT * FROM journal j WHERE j.patient_id = %s", patient.getPatientID()));

                    List<Journal> journalList = new ArrayList<>();

                    while(resultJournalSet.next()) {
                        Journal journal = getJournalPre(resultJournalSet, patient);
                        journalList.add(journal);
                    }

                    journals.put(patient, journalList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return journals;
    }

    private Employee getEmployeePre(ResultSet resultSet) {
        Employee employee = null;
        try {
            String id = String.valueOf(resultSet.getString("id"));
            float salary = resultSet.getFloat("salary");
            float workingHours = resultSet.getFloat("working_hours");
            Accessibility accessibility = Accessibility.valueOf(resultSet.getString("accessibility"));

            String personNumm = resultSet.getString("person_number");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String phoneNum = resultSet.getString("phone_number");
            Gender gender = Gender.valueOf(resultSet.getString("gender"));

            //int addressID = resultSet.getInt(10);
            String sweden = resultSet.getString("country");
            String city = resultSet.getString("city");
            String street = resultSet.getString("street");
            String zip = resultSet.getString("zip");

            Address address = new Address(sweden, city, street, zip);

            Person person = new Person(firstName, lastName, personNumm, gender, phoneNum, address);

            employee = new Employee(person, id, salary, workingHours, accessibility, new LoginDetails("bla", "bla"));
        } catch (Exception e) {

        }
        return employee;
    }

    private Journal getJournalPre(ResultSet resultSet, Patient patient) {
        Journal journal = null;

            try {
                String note = resultSet.getString("note");
                Date date = resultSet.getDate("time");
                Employee employee = getEmployee(resultSet.getInt("employee_id"));
                journal = new Journal(patient, null, note, employee);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return journal;
    }

    private Address getAddress(ResultSet resultSet) {
        Address address = null;
        try {

        String country = resultSet.getString("country");
        String city = null;
            city = resultSet.getString("city");
        String street = resultSet.getString("street");
        String zip = resultSet.getString("zip");

        address = new Address(country, city, street, zip);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }




    private List<Ward> getWards(int hospital_id) {
        List<Ward> wards = new ArrayList<>();

            if(database.isConnected()) {
                try {
                    Statement statement = database.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM ward WHERE hospital_id = %s", hospital_id));
                    while(resultSet.next()) {
                        int ward_id = resultSet.getInt("id");

                        Set<Room> roomList = getRooms(ward_id);

                        Ward ward = new Ward(Integer.toString(ward_id), roomList);

                        wards.add(ward);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        return wards;
    }

    private Room getRoom(int room_id) {
        Room room = null;

            if(database.isConnected()) {
                try {
                    Statement statement = database.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM room r WHERE r.id = %d", room_id));

                    while(resultSet.next()) {

                        room = getRoomPRE(resultSet);

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        return room;
    }

    private Room getRoomPRE(ResultSet resultSet) {
        Room room = null;

        try {
            int item_id = resultSet.getInt("id");
            int size = resultSet.getInt("size");
            Map<Item, Integer> items = getItems(item_id);
            room = new Room(Integer.toString(item_id), size, items);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return room;
    }

    private Set<Room> getRooms(int ward_id) {
        Set<Room> rooms = new HashSet<>();

        if(database.isConnected()) {
            try {

                Statement statement = database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM room r WHERE r.ward_id = %d", ward_id));

                while(resultSet.next()) {
                    Room room = getRoomPRE(resultSet);
                    rooms.add(room);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rooms;
    }


    private Map<Item, Integer> getItemsPRE(ResultSet resultSet) {
        Map<Item, Integer> items = new HashMap<>();

        try {
            while (resultSet.next()) {
                Item item = null;

                ItemType type = ItemType.valueOf(resultSet.getString("type"));
                String description = resultSet.getString("description");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                Date expirationDate = resultSet.getDate("expiration_date");
                boolean reusable = resultSet.getBoolean("reusable");


                if (type == ItemType.MEDICINE) {
                    LocalDate d = new java.sql.Date(expirationDate.getTime()).toLocalDate();
                    item = new Medicine(name, description, price, d);
                } else
                    item = new Equipment(name, description, price, reusable);

                items.put(item, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }


    private Map<Item, Integer> getItems(int room_id) {
        try {
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM item i WHERE i.room_id = %s", room_id));
            return getItemsPRE(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<Item, Integer> getStorage(int hospital_id) {
        try {
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM item i WHERE i.hospital_id = %s", hospital_id));
            return getItemsPRE(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<Patient, List<Booking>> getBookings(Hospital hospital) {

        Map<Patient, List<Booking>> bookingMap = new HashMap<>();
        Set<Patient> patients = getPatients();

        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();

                for (Patient patient : patients) {

                    List<Booking> bookings = new ArrayList<>();
                    ResultSet resultSet = callProcedure("getBookings", patient.getPatientID());

                    while (resultSet.next()) {

                        int booking_id = resultSet.getInt("id");
                        String employee_id = String.valueOf(resultSet.getInt("employee_id"));
                        int patient_id = resultSet.getInt("patient_id");
                        Date d = resultSet.getDate("date");
                        int ward_id = resultSet.getInt("ward_id");
                        int room_id= resultSet.getInt("room_id");


                        LocalDateTime time = LocalDateTime.now(); //temp

                        Employee emp = null;

                        for(Employee employee : getAllEmployees(null))
                            if(employee.getId().equals(employee_id))
                                emp = employee;


                            Ward ward = null;
                            Room room = null;

                            for(Ward w : hospital.getWards())
                                if(w.getName().equals("" + ward_id)) {
                                    ward = w;
                                    for(Room ro: w.getRooms())
                                        if(ro.getName().equals("" + room_id))
                                            room = ro;

                                }

                        List<Patient> patientsList = new ArrayList<>();
                        patientsList.add(patient);

                        List<Employee> employeeList = new ArrayList<>();
                        employeeList.add(emp);


                        Booking booking = new Booking(time, patientsList, employeeList, ward, room, "");

                        bookings.add(booking);
                    }
                }

            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return bookingMap;
    }

    /*
    private Ward getWard(int ward_id) {
        Ward ward = null;
            if(database.isConnected())  {
                try {
                    Statement statement = database.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM room r WHERE r.ward_id = %s"));
                    getRoo
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        return ward;
    }

     */

    public Patient getPatient(String personNumber) {
        Patient patient = null;

        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();
                ResultSet resultSet = callProcedure("getPatient", personNumber);
                while(resultSet.next()) {
                    patient = getPatientPre(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return patient;
    }

    public void saveBooking(Booking booking) {
    }


    public Hospital loadHospital() {
        Hospital hospital = null;

        if(database.isConnected()) {
            try {
                Statement statement = database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM hospital hos INNER JOIN address adde ON hos.address_id = adde.id");

                while(resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    float balance = resultSet.getFloat("balance");
                    int maxCapacity = resultSet.getInt("max_capacity");

                    List<Ward> ward = getWards(id);

                    Address address = getAddress(resultSet);
                    Map<Item, Integer> storage = getStorage(id);

                    hospital = new Hospital(name, maxCapacity, balance, storage, address, ward, id);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return hospital;
    }
    public void loadHospitalData(Hospital hospital) {

        Set<Person> persons = getAllPersons();

        Map<Patient, List<Booking>> bookings = getBookings(hospital);
        Map<Patient, List<Journal>> journals = getJournals();


        Archive archive = new Archive();

        archive.setBookings(bookings);
        archive.setJournals(journals);



        hospital.setArchive(archive);
        hospital.setPersons(persons);
    }
}

