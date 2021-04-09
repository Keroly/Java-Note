package spring5.demo1.service;

import spring5.demo1.dao.UserDao;
import spring5.demo1.dao.UserDaoImpl;

public class UserService {

    //创建UserDao类型属性，生成set方法
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service add...............");
        userDao.update();

        /*
        原始方式
        UserDao userDao = new UserDaoImpl();
        userDao.update();
        */


    }
}
