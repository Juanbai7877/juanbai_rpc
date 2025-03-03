package com.juanbai.examplespringbootprovider;

import com.juanbai.example.common.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExampleSpringbootProviderApplicationTests {
    @Resource
    private UserService exampleService;
    @Test
    void contextLoads() {
        exampleService.createCode("13172981710");
    }

}
