package com.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ������
 * @date 2021/11/21
 * @Description: TODO
 */
@RestController
@RequestMapping("/hello")
public class SecurityController {

    /**
     * @PreAuthorize: ע���ʾ�û�����ӵ��addȨ�޲��ܵ��õ�ǰ����
     */

    @PreAuthorize("hasAuthority('add')")
    @RequestMapping("/add")
    public void add(){
        System.out.println("add...");
    }
}
