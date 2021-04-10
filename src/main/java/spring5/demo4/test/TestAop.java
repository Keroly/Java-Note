package spring5.demo4.test;



import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.demo4.aopanno.User;
import spring5.demo4.aopxml.Book;

public class TestAop {

    @Test
    public void testAopAnno() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("demo4/bean1.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }

    @Test
    public void testAopXml() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("demo4/bean2.xml");
        Book book = context.getBean("book", Book.class);
        book.buy();
    }
}
