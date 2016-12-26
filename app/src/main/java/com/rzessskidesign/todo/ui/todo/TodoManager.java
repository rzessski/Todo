package com.rzessskidesign.todo.ui.todo;


import android.widget.Toast;
import com.rzessskidesign.todo.API.Api;
import com.rzessskidesign.todo.MainActivity;
import com.rzessskidesign.todo.API.TodoResponse;
import com.rzessskidesign.todo.UserProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class TodoManager {

    private MainActivity mainActivity;
    private Api api;
    private UserProfile userProfile;
    private Call<TodoResponse> call;
    public TodoManager(Api api, UserProfile userProfile){
        this.api=api;
        this.userProfile = userProfile;
    }
    public void onAttach(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    public void onStop(){
        this.mainActivity=null;
    }
    public void removeToDo(int id) {

        call = api.removeTodo(id,userProfile.getId());
        call.enqueue(new Callback<TodoResponse>() {

            @Override
            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(mainActivity, "Usunieto", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TodoResponse> call, Throwable t) {
            }  });
    }
    public void save(String dialogTitleToDo, String dialogDescriptionTodo) {
        call = api.saveTodo(dialogTitleToDo,dialogDescriptionTodo,userProfile.getId());
        call.enqueue(new Callback<TodoResponse>() {

            @Override
            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(mainActivity, "Dane zostały zapisane!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TodoResponse> call, Throwable t) {
            }  });}
}
