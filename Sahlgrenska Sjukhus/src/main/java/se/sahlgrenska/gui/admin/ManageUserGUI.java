package se.sahlgrenska.gui.admin;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.gui.util.misc.SuggestionDropDownDecorator;
import se.sahlgrenska.gui.util.misc.TextComponentSuggestionClient;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;
import se.sahlgrenska.sjukhus.person.employee.LoginDetails;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManageUserGUI extends HelperGUI {
    private JTextField personNumField;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel bottomPanel;
    private JLabel titleLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JComboBox accessibilityComboBox;
    private JLabel personNumLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel repeatPasswordLabel;
    private JLabel accessibilityLabel;
    private JPanel repeatPasswordPanel;
    private JPanel personNumPanel;
    private JPanel passwordPanel;
    private JPanel accessibilityPanel;
    private JButton confirmButton;
    private JButton cancelButton;

    ComboBoxModel accessibilityModelBox = new DefaultComboBoxModel(Accessibility.getEmployeeRoles());

    private Employee user = null;


    public ManageUserGUI(String title) {
        this("Ny användare", false);

        SuggestionDropDownDecorator.decorate(personNumField, new TextComponentSuggestionClient(this::getSuggestions));
    }

    public ManageUserGUI(Employee employee) {
        this("Redigera användare", true);
        usernameField.grabFocus();

        UtilGUI.toggleVisibility(passwordField);

        user = employee;

        usernameField.setText(employee.getLoginDetails().getUsername());
        passwordField.setText(employee.getLoginDetails().getPassword());
        accessibilityModelBox.setSelectedItem(employee.getAccessibility());
        repeatPasswordPanel.setVisible(false);
        personNumPanel.setVisible(false);
    }

    private ManageUserGUI(String title, boolean b) {
        accessibilityComboBox.setModel(accessibilityModelBox);

        confirmButton.addActionListener(e -> {
            if(isFilled()) {
                if(validateNewUser()) {
                    this.dispose();
                    Driver.getAdminGUI().refreshList();
                }
            } else {
                UtilGUI.error("Alla fält är obligatoriska.");
            }
        });

        cancelButton.addActionListener(e -> {
            dispose(); //terminate JFrame
        });

        titleLabel.setText(title);
        init(mainPanel, title, Accessibility.ADMIN);
        setVisible(true);
    }

    private boolean validateNewUser() {
        String username = usernameField.getText();
        Accessibility accessibility = (Accessibility) accessibilityModelBox.getSelectedItem();
        String password = UtilGUI.getPasswordString(passwordField);
        String passwordConfirm = UtilGUI.getPasswordString(repeatPasswordField);
        String personnummer = personNumField.getText();

        boolean createNew = user == null;

        Person person = createNew ? Driver.getIOManager().getPerson(personnummer) : user;

        if(person == null) {
            UtilGUI.error(String.format("En person med personnummer \"%s\" hittades inte.", personnummer));
        } else if (createNew && !password.equals(passwordConfirm)) {
            UtilGUI.error("Lösenorden matchar inte.");
        } else if (password.length() < 5 || password.length() > 20) {
            UtilGUI.error("Lösenordet måste vara mellan 5-20 tecken långt.");
        } else if(username.length() < 5 || username.length() > 20)  {
            UtilGUI.error("Användarnamnet måste vara mellan 5-20 tecken långt.");
        } else {
            LoginDetails loginDetails = new LoginDetails(username, password);

            if(doesAccountExists(loginDetails) && createNew || !user.getLoginDetails().equals(loginDetails)) {
                    UtilGUI.error("Detta lösenordet eller användarnamnet är upptaget.");
            } else if (Driver.getIOManager().getEmployeeID(personnummer) != -1) {
                UtilGUI.error("Den här användaren har redan ett konto.");
            } else {

                String confirmMessage = String.format("\n%s\nAnvändarnamn: %s\nLösenord: %s\nRoll: %s",
                        person.getFullName(),
                        username,
                        password,
                        accessibility.toString()
                        );

                int resp = JOptionPane.showConfirmDialog(null, confirmMessage, "Bekräfta ändringar", JOptionPane.OK_CANCEL_OPTION);

                if(resp != 0)
                    return false;

                if(user != null) {
                    if(accessibility != user.getAccessibility())
                        Driver.getIOManager().query(String.format("UPDATE employee SET accessibility = \"%s\" WHERE id = \"%s\"", accessibility.toString(), user.getId()));
                    if(loginDetails != user.getLoginDetails())
                        Driver.getIOManager().query(String.format("UPDATE login_details SET username = \"%s\", password = \"%s\" WHERE employee_id = \"%s\"",loginDetails.getUsername(), loginDetails.getPassword(),  user.getId()));
                } else {
                    Driver.getIOManager().query(String.format("INSERT INTO employee (person_number, salary, working_hours, accessibility) VALUES(\"%s\", \"%s\", \"%s\", \"%s\")"
                            , personnummer, 500, 8, accessibility.toString()));

                    int id = Driver.getIOManager().getEmployeeID(personnummer);
                    Driver.getIOManager().query(String.format("INSERT INTO login_details (employee_id, username, password) VALUES(\"%s\", \"%s\", \"%s\")", id, username, password));
                }

                return true;
            }
        }
        return false;
    }

    private boolean doesAccountExists(LoginDetails loginDetails) {
         return Driver.getIOManager().getEmployee(loginDetails) != null;
    }

    private boolean isFilled() {
        return user == null ? isFilledForNewUser() : isFilledForEditUser();
    }

    private boolean isFilledForEditUser() {
        return usernameField.getText().length() > 0
                &&
                passwordField.getPassword().length > 0
                &&
                accessibilityModelBox.getSelectedItem() != Accessibility.NONE;
    }

    private List<String> getSuggestions(String key) {

        if (key.isEmpty())
            return null;

        List<String> output = new ArrayList<>();
        final String input = key.toUpperCase();

        Set<Person> persons = Driver.getHospital().getPersons();

        for(Person person : persons)
          if(person.getFullName().toUpperCase().startsWith(input)
                  || person.getFirstName().toUpperCase().startsWith(input)
                  || person.getLastName().toUpperCase().startsWith(input)
                  || person.getPersonNumber().startsWith(input)
          ) {
              output.add(person.toString());
          }


        return output.stream().limit(20).collect(Collectors.toList());
    }


    private boolean isFilledForNewUser() {
        return
                isFilledForEditUser()
                &&
                personNumField.getText().length() > 0
                &&
                repeatPasswordField.getPassword().length > 0;
    }

}
