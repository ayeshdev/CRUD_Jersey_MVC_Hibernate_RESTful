package com.ayesh.webapp.model;

public class UserDetails {
    private String mobile;
    private String password;
    private String userType;

    public UserDetails() {
    }

    public UserDetails(String mobile, String password, String userType) {
        this.mobile = mobile;
        this.password = password;
        this.userType = userType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
