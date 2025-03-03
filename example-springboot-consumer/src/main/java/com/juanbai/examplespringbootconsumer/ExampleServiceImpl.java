package com.juanbai.examplespringbootconsumer;

import com.juanbai.example.common.dto.Result;
import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;
import com.juanbai.juanrpcspingbootstarter.annotation.RpcReference;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    // 定义可用的字符集
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String getRandString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive number");
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            // 从 CHARACTERS 中随机选择一个字符
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    // 定义手机号码的第二位可选数字
    private static final String SECOND_DIGITS = "345789";

    public static String getRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder("1"); // 手机号码以 1 开头

        // 随机生成第二位数字
        int secondDigitIndex = random.nextInt(SECOND_DIGITS.length());
        phoneNumber.append(SECOND_DIGITS.charAt(secondDigitIndex));

        // 随机生成剩余的 9 位数字
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10)); // 生成 0-9 的随机数字
        }

        return phoneNumber.toString();
    }

    public void test() {
        User user = new User();
        user.setName(getRandString(6));
        String userName=getRandString(6);
        String password=getRandString(6);
        String rePassword=password;
        String code=null;
        String phone=getRandomPhoneNumber();
        Result<String> resultUser = null;
        try {
            Result<String> codeResult=userService.createCode(phone);
            if(codeResult.getData()!=null){
                code=codeResult.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ;
        }
        try {
            resultUser = userService.register(userName,password,rePassword,phone,code);
        }catch (Exception e){
        }
        if(resultUser!=null){
        }
    }
}
