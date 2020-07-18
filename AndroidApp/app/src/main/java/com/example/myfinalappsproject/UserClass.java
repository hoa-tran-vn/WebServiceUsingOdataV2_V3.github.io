package com.example.myfinalappsproject;

import org.odata4j.core.OProperty;

public class UserClass {

    private int ID;
    private String IdentityName;
    private String PassWord;
    private String UserName;
    private String Email;

    public UserClass() {
    }

    public String getEmail() {
        return Email;
    }

    public String getUserName() {
        return UserName;
    }

    public int getID() {
        return ID;
    }

    public String getIdentityName() {
        return IdentityName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setIdentityName(String identityName) {
        IdentityName = identityName;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
