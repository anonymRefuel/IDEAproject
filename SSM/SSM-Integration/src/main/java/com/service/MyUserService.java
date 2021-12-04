package com.service;


import com.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ������
 * @date 2021/11/21
 * @Description: TODO
 */
public class MyUserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //ģ�����ݿ��е��û�����
    public Map<String, User> map = new HashMap<>();

    public void initData() {
        User user1 = new User();
        user1.setUsername("zhang");
        user1.setPassword(bCryptPasswordEncoder.encode("1234"));

        User user2 = new User();
        user2.setUsername("li");
        user2.setPassword(bCryptPasswordEncoder.encode("1234"));

        map.put(user1.getUsername(), user1);
        map.put(user2.getUsername(), user2);
    }

    /**
     * �����û��������û���Ϣ
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        initData();
        System.out.println(bCryptPasswordEncoder.encode("admin"));
        System.out.println(bCryptPasswordEncoder.encode("1234"));
        //ģ������û�����ѯ���ݿ�
        User userInDB = map.get(username);
        if (userInDB == null) {
            return null;
        }
        //ģ�����ݿ��е����룬������Ҫ��ѯ���ݿ� {noop}��ʾ���ļ���
        //  String password = "{noop}" + userInDB.getPassword();
        String password = userInDB.getPassword();
        //��Ȩ��������Ҫ��Ϊ��ѯ���ݿ⶯̬����û�ӵ�е�Ȩ�޺ͽ�ɫ
        List<GrantedAuthority> list = new ArrayList<>();
        if (username.equals("zhang")){
            list.add(new SimpleGrantedAuthority("add"));
            //list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        org.springframework.security.core.userdetails.User user =
                new org.springframework.security.core.userdetails.User(username, password, list);
        return user;
    }
}
