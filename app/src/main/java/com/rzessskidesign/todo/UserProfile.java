package com.rzessskidesign.todo;

import android.content.SharedPreferences;

import com.rzessskidesign.todo.API.UserResponse;

public class UserProfile {

    public static final String id = "id";
    private static final String email = "email";
    private static final String token = "token";

    private final SharedPreferences preferences;

    public String getId(){
        return  String.valueOf(preferences.getString("id",id));
    }

    public UserProfile(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveResponse(UserResponse userResponse){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(id, userResponse.id);
        editor.putString(email,userResponse.email);
        editor.putString(token,userResponse.token);
        editor.apply();
    }
    boolean ifLogin(){
        return preferences.getString("id","").isEmpty();
    }
    void  logout(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
