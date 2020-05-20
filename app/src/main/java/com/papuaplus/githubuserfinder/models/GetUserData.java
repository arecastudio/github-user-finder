package com.papuaplus.githubuserfinder.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserData {
    @SerializedName("items")
    List<UserData> listUser;

    public List<UserData> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserData> listUser) {
        this.listUser = listUser;
    }
}
