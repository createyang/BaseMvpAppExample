package com.tianbao.tclive.componet.model.bean;

import java.io.Serializable;

/**
 * @author: Caoy
 * @created on: 2019/1/8 16:33
 * @description:
 */
public class LoginResultBean implements Serializable {
    /**
     * userId : 400
     * name : 李方可
     * mobile : 18038190540
     * token : 9c674d1f1cf142c4bd90f505001df0d4
     * userType : 2
     * logo :
     * officeName : 市场审核部
     * position : 预算总监
     */

    private int userId;
    private String name;
    private String mobile;
    private String token;
    private String userType;
    private String logo;
    private String officeName;
    private String position;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
