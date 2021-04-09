package spring5.demo1.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.demo1.Book;
import spring5.demo1.Orders;
import spring5.demo1.User;
import spring5.demo1.bean.Dept;
import spring5.demo1.bean.Emp;
import spring5.demo1.dao.UserDaoImpl;
import spring5.demo1.service.UserService;

public class TestSpring5 {
    @Test
    public void testAdd() {
        // 1
        ApplicationContext context = new ClassPathXmlApplicationContext("demo1/bean1.xml");
        User user = context.getBean("user", User.class);
//        System.out.println(user);

        // 2 set方式注入
        Book book = context.getBean("book", Book.class);
//        book.testDemo();

        // 3 有参构造注入
        Orders orders = context.getBean("orders", Orders.class);
//        orders.ordersTest();

        // 4 注入属性 - 内部 Bean
        UserService userService = context.getBean("userService", UserService.class);
//        userService.add();

        // 5 注入属性 - 外部 Bean
        Emp emp = context.getBean("emp", Emp.class);
        emp.add();



    }
}
