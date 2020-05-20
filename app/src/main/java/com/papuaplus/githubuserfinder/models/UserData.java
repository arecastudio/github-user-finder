package com.papuaplus.githubuserfinder.models;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("login")
    private String loginName;
    @SerializedName("avatar_url")
    private String avatarURL;

    public UserData(String loginName, String avatarURL) {
        this.loginName = loginName;
        this.avatarURL = avatarURL;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
