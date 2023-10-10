import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springConfig.xml"})
public class CRUDaoImplTestMock {


    @Mock

    JdbcTemplate jdbcTemplate;

    @Before public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }




    Person person = new Person.Builder()
            .id(5)
            .name("ccc")
            .age(25)
            .adress("sdf")
            .salary(35456)
            .build();


    @Test
    public void findById() {
       CRUDao<Person,Integer> dao = new CRUDaoImpl(jdbcTemplate);
       Mockito.doReturn(person).when(jdbcTemplate).queryForObject(Matchers.any(),Matchers.any(),Matchers.<RowMapper<Person>>any());
       assertEquals(dao.findById(5).getAge(),25);
       assertEquals(dao.findById(5),person);
    }

    @Test
    public void insert(){
        CRUDao<Person,Integer> dao = new CRUDaoImpl(jdbcTemplate);
       // Mockito.doReturn(person).when(jdbcTemplate).queryForObject(Matchers.any(),Matchers.any(),Matchers.<RowMapper<Person>>any());
      //  Mockito.doReturn(5).when(jdbcTemplate).update("INSERT INTO company (name, age, adress, salary) VALUES (?,?,?,?)");
       assertEquals(dao.insert(person).getAge(),25);

    }
}