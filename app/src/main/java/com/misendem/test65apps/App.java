package com.misendem.test65apps;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.misendem.test65apps.logic.database.Database;
import com.misendem.test65apps.logic.network.ApiManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static App instance;

    App(){
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public ApiManager apiManager;
    public Database database;

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase();
        initNetwork();
    }

    private void initDatabase() {
        database = Room.databaseBuilder(getApplicationContext(), Database.class, "database").build();
    }

    private void initNetwork() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        Retrofit network = new Retrofit.Builder()
                .baseUrl("http://gitlab.65apps.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        apiManager = network.create(ApiManager.class);
    }
}
