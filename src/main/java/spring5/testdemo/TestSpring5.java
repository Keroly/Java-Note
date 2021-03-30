package spring5.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.User;

public class TestSpring5 {
    @Test
    public void testAdd() {
        // 1 加载spring配置文件

        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2 获得配置创建的对象

        User user = context.getBean("user", User.class);
        System.out.println(user);

        user.add();

    }
}
