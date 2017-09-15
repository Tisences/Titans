package com.tisen.titans.service;


import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tisen on 2017/8/24.
 */

public interface ControlService {
    @POST("control")
    Observable<Result> set(@Query("action") String action, @Query("model") String model, @Query("status") boolean status);

    @POST("control")
    Observable<Results> search(@Query("action") String action, @Query("time") long time);
}
