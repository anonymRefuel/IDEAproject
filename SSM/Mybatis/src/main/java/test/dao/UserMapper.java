package test.dao;

import test.domain.User;

import java.io.IOException;
import java.util.List;

/**
 * @author 无名氏
 * @date 2021/9/18
 */
public interface UserMapper {

    void save(User user);

    void update(User user);

    void delete(int id);

    List<User> findAll() throws IOException;

    User findUserById(int id);

    List<User> findByCondition(User user);

    List<User> findByIds(List<Integer> ids);
}
