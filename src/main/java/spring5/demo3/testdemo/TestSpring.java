package spring5.demo3.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.demo3.config.SpringConfig;
import spring5.demo3.service.UserService;

public class TestSpring {

    @Test
    public void testService1() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("demo3/bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }

    @Test
    public void testService2() {
        //加载配置类
        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }

}
