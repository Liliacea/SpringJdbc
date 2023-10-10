import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;


import javax.activation.DataSource;
import javax.activation.DataSource;

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

    public CRUDaoImplTest() throws BeansException {
    }


    @Test
    public void insert() throws BeansException {
        EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
               // .addScript("classpath:test-data.sql")
                .build();

        CRUDaoImpl cruDao = (CRUDaoImpl) ctx.getBean("cruDAO");
        cruDao.setDataSource(dataSource);

        assertThat(cruDao.insert(person).getName(), is("ccc"));
    }

    @Test
    public void select() {
        EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
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
        EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
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