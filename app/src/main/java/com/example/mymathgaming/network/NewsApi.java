package com.example.mymathgaming.network;

import com.example.mymathgaming.models.NewsUpdatesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("everything")
    Call<NewsUpdatesSearchResponse> getNews(
//            @Query("country") String country,
//            @Query("category") String category,
//            @Query("apiKey") String apiKey

            @Query("domains") String domains,
            @Query("apiKey") String apiKey
    );
}
