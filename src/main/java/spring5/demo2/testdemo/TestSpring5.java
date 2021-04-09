package spring5.demo2.testdemo;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.demo2.autowire.Emp;
import spring5.demo2.bean.Orders;
import spring5.demo2.collectiontype.Book;
import spring5.demo2.collectiontype.Course;
import spring5.demo2.collectiontype.Stu;

public class TestSpring5 {

    @Test
    public void testCollection1() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("demo2/bean1.xml");
        Stu stu = context.getBean("stu", Stu.class);
        stu.test();
    }

    @Test
    public void testCollection2() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("demo2/bean2.xml");
        Book book1 = context.getBean("book", Book.class);
        Book book2 = context.getBean("book", Book.class);
       // book.test();
        System.out.println(book1);
        System.out.println(book2);
    }

    @Test
    public void test3() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("demo2/bean3.xml");
        Course course = context.getBean("myBean", Course.class);
        System.out.println(course);
    }

    @Test
    public void testBean3() {
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("bean4.xml");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("demo2/bean4.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("第四步 获取创建bean实例对象");
        System.out.println(orders);

        //手动让bean实例销毁
        context.close();
    }

    @Test
    public void test4() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("demo2/bean5.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }

}
