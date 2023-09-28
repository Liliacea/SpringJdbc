import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

public class CRUDaoImplTestMock {


    @Mock

    JdbcTemplate jdbcTemplate;



    Person person = new Person.Builder()
            .id(5)
            .name("ccc")
            .age(25)
            .adress("sdf")
            .salary(35456)
            .build();


    @Test
    public void insert() {
        CRUDaoImpl cruDao = new CRUDaoImpl();
        ReflectionTestUtils.setField(cruDao,"jdbcTemplate", jdbcTemplate);
        Mockito.when(jdbcTemplate.update("INSERT INTO company (name, age, adress, salary) VALUES (?,?,?,?)", Person.class)).then();

        assertThat(cruDao.insert(person).getAge(), is(25));
    }
}