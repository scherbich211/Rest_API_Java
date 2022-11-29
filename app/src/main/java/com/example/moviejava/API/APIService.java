package com.example.moviejava.API;


import com.example.moviejava.API.PostModel;

import java.util.List;
import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("posts")
    Observable<List<PostModel>> getPosts();

    @GET("posts/")
    Call<List<PostModel>> getUserById(@Query("id") Integer id);
}
