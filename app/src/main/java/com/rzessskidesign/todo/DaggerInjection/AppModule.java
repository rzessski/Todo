package com.rzessskidesign.todo.DaggerInjection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.rzessskidesign.todo.API.Api;
import com.rzessskidesign.todo.API.RestClient;
import com.rzessskidesign.todo.UserProfile;
import com.rzessskidesign.todo.ui.LoginRegister.LogRegManager;
import com.rzessskidesign.todo.ui.todo.TodoManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {

    private final Context context;
    public AppModule(Context context){
        this.context=context;
    }
    @Provides
     Context provideContext(){
        return context;
    }
    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        return new RestClient(context).provideRetrofit();
    }
    @Provides
     Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }
    @Provides
     SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    @Provides
     UserProfile provideProfile(SharedPreferences sharedPreferences){
        return new UserProfile(sharedPreferences);
    }
    @Provides
    LogRegManager provideLogRegManager(Api api, UserProfile userProfile){
        return new LogRegManager(api,userProfile);
    }
    @Provides
    TodoManager provideTodoManager(Api api,UserProfile userProfile){
        return new TodoManager(api, userProfile);
    }
}
