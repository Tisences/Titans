package com.tisen.titans.api;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tisen on 2017/7/19.
 */

public interface UserServes {
    @POST("user")
    rx.Observable<SmileResult> getUser(@Query("action")String action, @Query("pagesize")int pageSize, @Query("key")String key);
}
