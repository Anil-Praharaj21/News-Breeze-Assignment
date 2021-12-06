package com.anilpraharaj.newsbreeze.remoteDataSource;

import com.anilpraharaj.newsbreeze.entity.ArticleResponse;
import com.anilpraharaj.newsbreeze.network.ApiServices;
import com.anilpraharaj.newsbreeze.network.ArticleService;

import retrofit2.Callback;
import retrofit2.http.Query;

/**
 * @author anilpraharaj on 05/12/21
 */
public class ArticleDataSource {

    public void getArticles(String country, String pageSize, String page, Callback<ArticleResponse> callback) {
        ArticleService articleService = ApiServices.getInstance().call();
        articleService.getArticles(country, pageSize, page).enqueue(callback);
    }
}
