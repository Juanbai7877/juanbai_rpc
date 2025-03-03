package com.juanbai.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.juanbai.core.model.RpcRequest;
import com.juanbai.core.model.RpcResponse;
import com.juanbai.core.serializer.JdkSerializer;
import com.juanbai.core.serializer.Serializer;
import com.juanbai.example.common.dto.Result;
import com.juanbai.example.common.dto.po.Users;
import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * 用户服务静态代理
 *
 */
public class UserServiceProxy implements UserService {

    public User getUser(User user) {
        // 指定序列化器
        final Serializer serializer = new JdkSerializer();

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            // 序列化（Java 对象 => 字节数组）
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 发送请求
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化（字节数组 => Java 对象）
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return (User) rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
