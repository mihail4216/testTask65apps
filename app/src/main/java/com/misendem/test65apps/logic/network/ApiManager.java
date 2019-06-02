package com.misendem.test65apps.logic.network;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiManager {


    @GET("/65gb/static/raw/master/testTask.json")
    Flowable<Response> loadAllEmployees();


}
