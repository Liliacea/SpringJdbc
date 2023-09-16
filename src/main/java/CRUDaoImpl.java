import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;


public class CRUDaoImpl implements CRUDao<Person, Integer> {

    private JdbcTemplate jdbcTemplate;


    public CRUDaoImpl() {
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Person> select() {
        //  List<Person> personList = jdbcTemplate.query("SELECT * FROM COMPANY", new BeanPropertyRowMapper<>(Person.class)); //попробовать потом с шаблоном строитель
        //return personList;
        return jdbcTemplate.query("SELECT * FROM COMPANY", (resultSet, i) -> {

            Integer id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            String adress = resultSet.getString(4);
            float salary = resultSet.getFloat(5);
            System.out.println(String.format("ID=%s NAME=%s AGE=%s ADRESS=%s SALARY=%s", id, name, age, adress, salary));
            return new Person(id, name, age, adress, salary);
        });


    }


    @Override
    public Person insert(Person person) {
        jdbcTemplate.update("INSERT INTO COMPANY (name, age, adress, salary) VALUES (?,?,?,?)",
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
        return jdbcTemplate.queryForObject("SELECT * FROM COMPANY where id = ?", new Object[]{id}, (resultSet, i) ->
        {
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            String adress = resultSet.getString(4);
            float salary = resultSet.getFloat(5);
            System.out.println(String.format("ID=%s NAME=%s AGE=%s ADRESS=%s SALARY=%s", id, name, age, adress, salary));
            return new Person(id, name, age, adress, salary);
        });

    }
}