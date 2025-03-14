package com.juanbai.example.common.service;



import com.juanbai.example.common.dto.Result;
import com.juanbai.example.common.dto.po.Users;

import java.security.NoSuchAlgorithmException;

/**
 * @author ALL
 * @date 2024/4/4
 * @Description
 */
public interface UserService {
    //根据用户名查询用户
    Users findByUserName(String userName);
    //注册
    Result register(String username, String password, String rePassword, String phone, String code) throws NoSuchAlgorithmException;

    Result registerWithoutEmail(String username, String password, String rePassword, String nickName) throws NoSuchAlgorithmException;

    //生成验证码
    Result createCode(String phone);

    Result update(Users user);

    Result searchUser(String msg);

    void updateAvatar(String url);

    void updatePwd(String newPwd);

    Result loginByPhone(String phone,String code);

    Result sendCode(String phone);
    Result getFriendsList(String userName);

    Result sendCodeByMail(String mail);

    Result registerByMail(String userName,String nickName, String password, String rePassword, String email, String code);

    Result findUserById(Long userId);

    Result resetPassword(String userName, String oldPassword, String password, String rePassword) throws NoSuchAlgorithmException;
}
