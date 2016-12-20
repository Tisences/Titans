package com.tisen.titans.api;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tisen on 2016/12/16 14:57.
 * Email tisences@qq.com
 */
public interface SmileServes {
    @GET("text.from")
    rx.Observable<SmileResult> getSmile(@Query("page")int page, @Query("pagesize")int pageSize, @Query("key")String key);
}
