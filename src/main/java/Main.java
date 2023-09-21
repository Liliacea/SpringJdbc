import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
        CRUDaoImpl cruDao = (CRUDaoImpl) ctx.getBean("cruDAO");


//cruDao.createTable();
Person person = new Person.Builder()
        .id(1)
        .name("ccc")
        .age(25)
        .adress("sdf")
        .salary(35456)
        .build();
cruDao.update(person);
cruDao.findById(2);
//cruDao.update(person);
  //     cruDao.findById(1);



    }
}
