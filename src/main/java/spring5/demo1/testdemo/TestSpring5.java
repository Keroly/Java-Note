package spring5.demo1.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.demo1.Book;
import spring5.demo1.Orders;
import spring5.demo1.User;

public class TestSpring5 {
    @Test
    public void testAdd() {
        // 1
        ApplicationContext context = new ClassPathXmlApplicationContext("demo1/bean1.xml");
        User user = context.getBean("user", User.class);
//        System.out.println(user);

        // 2
        Book book = context.getBean("book", Book.class);
//        book.testDemo();

        // 2
        Orders orders = context.getBean("orders", Orders.class);
        orders.ordersTest();
    }
}
