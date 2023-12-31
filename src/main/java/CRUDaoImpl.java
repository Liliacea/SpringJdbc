import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


public class CRUDaoImpl implements CRUDao<Person, Integer> {

    private JdbcTemplate jdbcTemplate;


    public CRUDaoImpl() {
    }

    public CRUDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private static final class PersonMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setAge(rs.getInt("age"));
            person.setAdress(rs.getString("adress"));
            person.setSalary(rs.getFloat("salary"));
            System.out.println(person.toString());
            return new Person();
        }
    }

    @Override
    public List<Person> select() {
        List<Person> personList = this.jdbcTemplate.query("SELECT * FROM COMPANY",
                new PersonMapper());
        return personList;
    }


    @Override
    public Person insert(Person person) {
        jdbcTemplate.update("INSERT INTO company (name, age, adress, salary) VALUES (?,?,?,?)",
                person.getName(),
                person.getAge(),
                person.getAdress(),
                person.getSalary());
        return person;

    }

    @Override
    public Person insertMap(Person person) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", person.getName());
        params.put("age", person.getAge());
        params.put("Adress", person.getAdress());
        params.put("salary", person.getSalary());
        jdbcTemplate.update("INSERT INTO COMPANY (name, age, adress, salary)INSERT INTO COMPANY (name, age, adress, salary) VALUES (?,?,?,?)", params);
        return person;
    }

    @Override
    public Person delete(Person person) {
        jdbcTemplate.update("DELETE from COMPANY where ID=1");
        return person;
    }

    @Override
    public Person update(Person person) {
        jdbcTemplate.update("UPDATE COMPANY set salary = 55000 where ID=2");
        return person;
    }

    @Override
    public void createTable() {
        jdbcTemplate.update("CREATE TABLE public.COMPANY \" +\n" +
                "                    \"(id INT  PRIMARY KEY     NOT NULL GENERATED ALWAYS AS IDENTITY,\" +\n" +
                "                    \" name           TEXT    NOT NULL, \" +\n" +
                "                    \" age            INT     NOT NULL, \" +\n" +
                "                    \" adress        VARCHAR(50), \" +\n" +
                "                    \" salary         REAL)");

    }

    @Override
    public Person findById(Integer id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM COMPANY where id =?",
                new Object[]{id},
                new PersonMapper());
    }
}