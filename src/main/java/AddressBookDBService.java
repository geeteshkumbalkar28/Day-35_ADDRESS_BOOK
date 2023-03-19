

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private static AddressBookDBService addressBookDBService;

    public static AddressBookDBService getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbookservice";
        String userName = "root";
        String password = "123456";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful!!!" + connection);
        return connection;
    }

    public List<Person> readData() {
        String query = "SELECT * from addressbook;";
        return this.getPersonDetailsFromDatabase(query);
    }

    private List<Person> getPersonDetailsFromDatabase(String query) {
        List<Person> personList = new ArrayList<Person>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            personList = this.getPersonData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    private List<Person> getPersonData(ResultSet resultSet) {

        List<Person> personList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String mobileNumber = resultSet.getString("mobileNumber");
                String email = resultSet.getString("email");
                personList.add(new Person(id, firstName, lastName, address, city, state, zip, mobileNumber, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;

    }

    public static void main(String[] args) {

        AddressBookDBService addressBookDBService = new AddressBookDBService();
        List<Person> dataList = addressBookDBService.readData();
        System.out.println(dataList);

    }
}