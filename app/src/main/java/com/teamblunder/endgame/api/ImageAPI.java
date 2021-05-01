package com.teamblunder.endgame.api;

import com.teamblunder.endgame.models.ImageJSONPlaceholder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageAPI {

    String BASE_URL = "https://serpapi.com/";
    @GET("search.json?")
    Call<ImageJSONPlaceholder> getResult(@Query("q") String q, @Query("tbm") String tbm,
                                                    @Query("ijn") String ijn, @Query("api_key") String api_key);
}
