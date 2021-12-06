package com.anilpraharaj.newsbreeze.network;

import com.anilpraharaj.newsbreeze.entity.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author anilpraharaj on 5/12/21
 */
public interface ArticleService {

    @GET("v2/top-headlines")
    Call<ArticleResponse> getArticles(
            @Query("country") String country,
            @Query("pageSize") String pageSize,
            @Query("page") String page);
}
