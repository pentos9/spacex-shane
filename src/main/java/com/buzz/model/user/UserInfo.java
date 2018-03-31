package com.buzz.model.user;

import java.time.LocalDate;

public class UserInfo {

    private long userId;
    private String address;
    private String description;
    private byte gender;
    private LocalDate birthday;

    public UserInfo() {
    }

    public UserInfo(long userId, String address, String description, byte gender, LocalDate birthday) {
        this.userId = userId;
        this.address = address;
        this.description = description;
        this.gender = gender;
        this.birthday = birthday;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
