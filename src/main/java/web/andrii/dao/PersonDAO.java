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
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> selectAllPerson() {
        return jdbcTemplate.query("SELECT * FROM person ORDER BY name", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(int id) {
        Optional<Person> person = jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new BeanPropertyRowMapper<>(Person.class), new Object[]{id}).stream().findAny();
        person.ifPresent(value -> value.setBookList(jdbcTemplate.query("SELECT * FROM book WHERE reader=?",
                new BeanPropertyRowMapper<>(Book.class), person.get().getName())));
        return person.orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, age)  VALUES(?, ?)", person.getName(), person.getAge());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                        new BeanPropertyRowMapper<>(Person.class),
                        new Object[]{id})
                        .stream().findAny().orElse(null);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE id=?", person.getName(), person.getAge(), id);
    }
}
