package ru.ratnikov.lessons.dao;

import org.springframework.stereotype.Component;
import ru.ratnikov.lessons.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Ratnikov
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5433/mvc_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgre";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        Person person = null;
        try {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM person WHERE id = ?");

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setAge(resultSet.getInt("age"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save (Person person) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO person VALUES (1, ?, ?, ?)");

            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setString(3, person.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update (int id, Person person) {
        try {
            PreparedStatement ps =
                    connection.prepareStatement("UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?");

            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setString(3, person.getEmail());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (int id) {
        try {
            PreparedStatement ps =
                    connection.prepareStatement("DELETE FROM person WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
