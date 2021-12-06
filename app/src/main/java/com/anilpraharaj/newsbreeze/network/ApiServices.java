package com.anilpraharaj.newsbreeze.network;

import android.content.Context;

import com.anilpraharaj.newsbreeze.NewsBreezeApplication;
import com.anilpraharaj.newsbreeze.R;
import com.anilpraharaj.newsbreeze.constant.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author anilpraharaj on 05/12/21
 */
public class ApiServices {

    ArticleService services;
    static ApiServices apiService;
    private static Retrofit retrofit;
    public static Context mContext = NewsBreezeApplication.getInstance();

    public static ApiServices getInstance() {

        synchronized (ApiServices.class) {
            apiService = new ApiServices();

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(120, TimeUnit.SECONDS);
            httpClient.readTimeout(120, TimeUnit.SECONDS);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter(Constant.API_KEY, Constant.API_KEY_VALUE)
                            .build();

                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();

                    return chain.proceed(request);
                }
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }

        return apiService;
    }

    private static Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public ArticleService call() {
        services = retrofit.create(ArticleService.class);
        return services;
    }
}
