package com.rzessskidesign.todo.ui.LoginRegister;

import android.widget.Toast;

import com.rzessskidesign.todo.API.Api;
import com.rzessskidesign.todo.API.UserResponse;
import com.rzessskidesign.todo.UserProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogRegManager {

    private Api api;
    private LogReg logReg;
    private UserProfile userProfile;
    private Call<UserResponse> userResponseCall;

    public LogRegManager(Api api, UserProfile userProfile){
        this.api = api;
        this.userProfile = userProfile;
    }
    void onAttach(LogReg logReg){
        this.logReg = logReg;
    }
    void onStop(){
        this.logReg = null;
    }
    void login(String login, String password){
        userResponseCall = api.getLogin(login, password);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> Logincall, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse body = response.body();
                    if (body.error.equals("false")) {
                        System.out.println(body.toString());
                        userProfile.saveResponse(body);
                        logReg.success();
                    } else if (body.error.equals("true")) {
                        System.out.println("bledne dane");
                        Toast.makeText(logReg, "Wprowadzono niepoprawne dane!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<UserResponse> Logincall, Throwable t) {
            }
        });
    }
    void register(String regName, String regLogin, String regPass) {
        userResponseCall = api.saveUser(regName,regLogin, regPass);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> Logincall, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse body = response.body();
                    if (body.error.equals("false")) {
                        System.out.println(body.toString());
                        userProfile.saveResponse(body);
                        logReg.success();
                    } else if (body.error.equals("true")) {
                        Toast.makeText(logReg, "Wprowadzono niepoprawne dane!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<UserResponse> Logincall, Throwable t) {
            }
        });
    }
}
