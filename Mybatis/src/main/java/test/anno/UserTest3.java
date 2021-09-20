package test.anno;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import test.dao2.UserMapper2;
import test.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 无名氏
 * @date 2021/9/20
 */
public class UserTest3 {

    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        //获得核心配置环境
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        sqlSession = sqlSessionFactory.openSession(true);
    }

    @Test
    public void findAll() throws IOException {
        UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void findUserById() {
        UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);
        User user = mapper.findUserById(7);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void insert() {
        User user = new User("lisa", "123456");
        UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);
        mapper.save(user);
        sqlSession.close();
    }

    @Test
    public void update() {
        User user = new User("tom", "123456");
        user.setId(2);
        UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);
        mapper.update(user);
        sqlSession.close();
    }

    @Test
    public void delete() {
        UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);
        mapper.delete(2);
        sqlSession.close();
    }
}
