package com.juanbai.example.provider;


import com.juanbai.example.common.dto.Result;
import com.juanbai.example.common.dto.po.Users;
import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;

import java.security.NoSuchAlgorithmException;

/**
 * 用户服务实现类
 *
 */
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }

    @Override
    public Users findByUserName(String userName) {
        return null;
    }

    @Override
    public Result register(String username, String password, String rePassword, String phone, String code) throws NoSuchAlgorithmException {
        return null;
    }

    @Override
    public Result registerWithoutEmail(String username, String password, String rePassword, String nickName) throws NoSuchAlgorithmException {
        return null;
    }

    @Override
    public Result createCode(String phone) {
        return null;
    }

    @Override
    public Result update(Users user) {
        return null;
    }

    @Override
    public Result searchUser(String msg) {
        return null;
    }

    @Override
    public void updateAvatar(String url) {

    }

    @Override
    public void updatePwd(String newPwd) {

    }

    @Override
    public Result loginByPhone(String phone, String code) {
        return null;
    }

    @Override
    public Result sendCode(String phone) {
        return null;
    }

    @Override
    public Result getFriendsList(String userName) {
        return null;
    }

    @Override
    public Result sendCodeByMail(String mail) {
        return null;
    }

    @Override
    public Result registerByMail(String userName, String nickName, String password, String rePassword, String email, String code) {
        return null;
    }

    @Override
    public Result findUserById(Long userId) {
        return null;
    }

    @Override
    public Result resetPassword(String userName, String oldPassword, String password, String rePassword) throws NoSuchAlgorithmException {
        return null;
    }
}
