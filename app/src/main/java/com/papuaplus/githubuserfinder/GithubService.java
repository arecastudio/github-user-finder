package com.papuaplus.githubuserfinder;

import com.papuaplus.githubuserfinder.models.GetUserData;
import com.papuaplus.githubuserfinder.models.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {
    @GET("/search/users")
    Call<GetUserData> userList(@Query("q") String githubName);
}
