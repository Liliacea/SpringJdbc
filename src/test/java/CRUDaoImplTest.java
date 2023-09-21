import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;


import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CRUDaoImplTest {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
    CRUDaoImpl cruDao = (CRUDaoImpl) ctx.getBean("cruDAO");


    Person person = new Person.Builder()
            .id(5)
            .name("ccc")
            .age(25)
            .adress("sdf")
            .salary(35456)
            .build();


    @Test
    public void insert() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
               // .addScript("classpath:test-data.sql")
                .build();

        CRUDaoImpl cruDao = (CRUDaoImpl) ctx.getBean("cruDAO");
        cruDao.setDataSource(dataSource);

        assertThat(cruDao.insert(person).getName(), is("ccc"));
    }

    @Test
    public void select() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();

        CRUDaoImpl cruDao = (CRUDaoImpl) ctx.getBean("cruDAO");
        cruDao.setDataSource(dataSource);
        assertThat(cruDao.select().size(), is(4));
    }





    @Test
    public void insertMap() {
       // assertThat(cruDao.insertMap(person).getName(), is("ccc"));
    }

    @Test
    public void delete() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
        cruDao.insert(person);

        assertThat(cruDao.delete(person).getName(),is("ccc"));
    }

    @Test
    public void update() {

    }



    @Test
    public void findById() {
    }
}