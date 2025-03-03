package com.juanbai.rpc.juanrpcspringbootprovider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.juanbai.example.common.dto.Result;
import com.juanbai.example.common.dto.UserDTO;
import com.juanbai.example.common.dto.po.Users;
import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;

import com.juanbai.juanrpcspingbootstarter.annotation.RpcService;
import com.juanbai.rpc.juanrpcspringbootprovider.getInstance.ThirdPartyClass;
import com.juanbai.rpc.juanrpcspringbootprovider.mapper.UserMapper;
import com.juanbai.rpc.juanrpcspringbootprovider.utils.SHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.juanbai.rpc.juanrpcspringbootprovider.utils.RedisConstants.*;
import static com.juanbai.rpc.juanrpcspringbootprovider.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * 用户服务实现类
 *
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    public UserServiceImpl(){
        if(this.userMapper==null){
            this.userMapper= (UserMapper) ThirdPartyClass.getVarInstance("userMapper");
        }
        if(this.stringRedisTemplate==null){
            this.stringRedisTemplate=(StringRedisTemplate) ThirdPartyClass.getVarInstance("stringRedisTemplate");
        }
    }

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }

    @Override
    public Users findByUserName(String userName) {
        return null;
    }

    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    @Autowired
    private  UserMapper userMapper;

    @Override
    public Result register(String username, String password,String rePassword, String phone,String code) throws NoSuchAlgorithmException {

        // 1 查看验证码是否一致
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        if (cacheCode == null || !cacheCode.equals(code)) {
            // 不一致，报错
            return Result.error("验证码错误");
        }

        // 2 判断两次密码是否一致
        if(!rePassword.equals(password)){
            return Result.error("两次密码不一致");
        }

        // 3 判断用户是否已存在
        Users t_user=userMapper.findByUserName(username);
        if(t_user!=null){
            return Result.error("用户已存在");
        }

        // 4 创建用户
        Users user=createUserWithPhone(phone,username,password);

        String tokenKey = createAndSetToken(user);

        return Result.success(tokenKey);
    }

    private String createAndSetToken(Users user) {
        // 7.1.生成token，作为登录令牌

        String token = UUID.randomUUID().toString().replace("-", "");
        // 7.3.存储
        String tokenKey = LOGIN_USER_KEY+"-"+ user.getUserId() +"-"+ token;

        // 7.2.将User对象转为HashMap存储
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setNickName(user.getUserName());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> {
                            if (fieldValue == null) {
                                return null; // 或者返回其他默认值
                            }
                            return fieldValue.toString();
                        }));
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        // 7.4.设置token有效期
        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
        return tokenKey;
    }

    public Users createUserWithPhone(String phone,String userName,String password) throws NoSuchAlgorithmException {
        // 1.创建用户
        Users user = new Users();
        user.setPhone(phone);
        user.setPasswordHash(SHAUtil.SHA256Encrypt(password));
        user.setUserName(userName);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        //保存用户
        userMapper.insert(user);
        user=userMapper.findByUserName(userName);
        return user;
    }

    @Override
    public Result registerWithoutEmail(String username, String password, String rePassword, String nickName) throws NoSuchAlgorithmException {
        return null;
    }

    @Override
    public Result createCode(String phone) {
        // 3.符合，生成验证码
        String code = RandomUtil.randomNumbers(6);

        // 4.保存验证码到 session
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);

        //返回ok
        return Result.success(code);
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
