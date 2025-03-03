package com.juanbai.example.common.dto.po;

import lombok.Data;
import lombok.Getter;

/**
 * @author ALL
 * @date 2024/8/12
 * @Description
 */
@Getter
@Data
public class Users {
  private long userId;
  private String userName;
  private String nickName;
  private String phone;
  private String passwordHash;
  private String qqNumber;
  private String wechat;
  private String email;
  private String role;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp updatedTime;
  private String userBio;
  private String userSchool;
  private String avatarUrl;


    public void setUserId(long userId) {
    this.userId = userId;
  }


    public void setUserName(String userName) {
    this.userName = userName;
  }


    public void setNickName(String nickName) {
    this.nickName = nickName;
  }


    public void setPhone(String phone) {
    this.phone = phone;
  }


    public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }


    public void setQqNumber(String qqNumber) {
    this.qqNumber = qqNumber;
  }


    public void setWechat(String wechat) {
    this.wechat = wechat;
  }


    public void setEmail(String email) {
    this.email = email;
  }


    public void setRole(String role) {
    this.role = role;
  }


    public void setCreatedTime(java.sql.Timestamp createdTime) {
    this.createdTime = createdTime;
  }


    public void setUpdatedTime(java.sql.Timestamp updatedTime) {
    this.updatedTime = updatedTime;
  }


    public void setUserBio(String userBio) {
    this.userBio = userBio;
  }


    public void setUserSchool(String userSchool) {
    this.userSchool = userSchool;
  }


    public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

}
