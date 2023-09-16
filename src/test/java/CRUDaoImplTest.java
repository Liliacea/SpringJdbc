import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CRUDaoImplTest {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
    CRUDaoImpl cruDao = (CRUDaoImpl) ctx.getBean("cruDAO");



    Person person = new Person.Builder()
            .id(1)
            .name("ccc")
            .age(25)
            .adress("sdf")
            .salary(35456)
            .build();



    @Test
    public void select() {
    }

    @Test
    public void insert() {
        assertThat(cruDao.insert(person).getName(), is("ccc"));
    }

    @Test
    public void insertMap() {
        assertThat(cruDao.insertMap(person).getName(), is("ccc"));
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void createTable() {
    }

    @Test
    public void findById() {
    }
}