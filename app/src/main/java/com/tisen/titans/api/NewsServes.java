package com.tisen.titans.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tisen on 2016/12/19 16:30.
 * Email tisences@qq.com
 */
public interface NewsServes {
    @GET("index")
    Observable<NewsResult>getNews(@Query("key")String key,@Query("type")String type);
}
