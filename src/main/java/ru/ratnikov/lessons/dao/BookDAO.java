package ru.ratnikov.lessons.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ratnikov.lessons.models.Book;
import ru.ratnikov.lessons.models.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author Mikhail Ratnikov
 */
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save (Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update (int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete (int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    public Optional<Person> getBookOwner (int id) {
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id = person.id WHERE book.id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release (int id) {
        jdbcTemplate.update("UPDATE book SET person_id = NULL WHERE id = ?", id);
    }

    public void assign (int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id = ?", person.getId(), id);
    }
}