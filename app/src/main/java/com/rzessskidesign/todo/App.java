package com.rzessskidesign.todo;

import android.app.Application;

import com.rzessskidesign.todo.DaggerInjection.AppComponent;
import com.rzessskidesign.todo.DaggerInjection.AppModule;
import com.rzessskidesign.todo.DaggerInjection.DaggerAppComponent;

public class App extends Application{

    public static AppComponent component;
    @Override
    public void onCreate(){
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
