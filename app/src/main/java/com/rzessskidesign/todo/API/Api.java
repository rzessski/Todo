package com.rzessskidesign.todo.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    String URL = "http://www.yourgiwebsite.com";

    @FormUrlEncoded
    @POST("/API/login")
    Call<UserResponse> getLogin(@Field("email") String email,
                                 @Field("password") String password);
    @FormUrlEncoded
    @POST("/API/register")
    Call<UserResponse> saveUser(@Field("name") String name,
                                @Field("email") String email,
                                @Field("password") String password);

    @GET("/API/getAllToDo")
    Call<TodoResponse> getAllTodo(@Header("Authorization") String token);

    @GET("/API/remove/{id}")
    Call<TodoResponse> removeTodo(@Path("id") int id,
                                 @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/API/save")
    Call<TodoResponse> saveTodo(@Field("title") String title,
                                @Field("description") String description,
                                @Header("Authorization") String token);
}
