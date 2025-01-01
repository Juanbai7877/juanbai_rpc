package com.juanbai.examplespringbootconsumer;

import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;
import com.juanbai.juanrpcspingbootstarter.annotation.RpcReference;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("yupi");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }

}
