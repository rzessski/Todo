package com.rzessskidesign.todo.API;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private Context context;

    public RestClient(Context context){
        this.context=context;
    }
    public Retrofit provideRetrofit(){
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient noInternet = new OkHttpClient.Builder()
                    .addInterceptor(new Error(context))
                    .addNetworkInterceptor(loggingInterceptor)
                    .build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Api.URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.client(noInternet);

        return builder.build();
    }
}
