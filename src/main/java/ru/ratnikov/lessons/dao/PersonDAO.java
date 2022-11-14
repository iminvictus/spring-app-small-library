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
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save (Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ", '" + person.getName() +
                    "'," + person.getAge() + ",'" + person.getEmail() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update (int id, Person person) {
//        Person personToUpdate = show(id);
//
//        personToUpdate.setName(person.getName());
//        personToUpdate.setAge(person.getAge());
//        personToUpdate.setEmail(person.getEmail());
    }

    public void delete (int id) {
//        people.removeIf(p -> p.getId() == id);
    }
}
