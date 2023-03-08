package web.andrii.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import web.andrii.models.Book;
import web.andrii.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> selectAllBook() {
        return jdbcTemplate.query("SELECT * FROM book ORDER BY name", new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, year, author)  VALUES(?, ?, ?)", book.getName(), book.getYear(), book.getAuthor());
    }

    public Book getBookById(int id) {
        Optional<Book> book = jdbcTemplate.query("SELECT * FROM book WHERE book.id=?",
                new BeanPropertyRowMapper<>(Book.class), new Object[]{id}).stream().findAny();
        return book.orElse(null);
    }

    public void setStatusBookIsFreeById(int id) {
        jdbcTemplate.update("UPDATE book SET reader = null WHERE id=?", id);
    }

    public List<Person> getPeopleIdList() {
        return jdbcTemplate.query("SELECT id, name FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void setReader(int id, Person reader) {
        jdbcTemplate.update("UPDATE book SET reader = ? WHERE id = ?", reader.getName(), id);
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?",
                        new BeanPropertyRowMapper<>(Book.class),
                        new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", book.getName(), book.getAuthor(), book.getYear(), id);
    }
}
