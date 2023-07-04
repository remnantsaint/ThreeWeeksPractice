package org.jshand.web.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class User {
    public User() {
    }

    public User(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty("用户名")
    private String username;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
