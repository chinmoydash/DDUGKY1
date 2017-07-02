package com.example.chinmoydash.ddugky.firebase;

/**
 * Created by chinmoydash on 21/06/17.
 */

public class Users {
    String email,pass,mob;

    public Users(){

    }
    public Users(String email, String pass, String mob) {
        this.email = email;
        this.pass = pass;
        this.mob = mob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }
}
