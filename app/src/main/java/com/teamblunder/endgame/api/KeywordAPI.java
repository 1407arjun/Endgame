package com.teamblunder.endgame.api;

import com.teamblunder.endgame.models.ImageJSONPlaceholder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface KeywordAPI {

    @GET
    Call<ArrayList<String>> getResult(@Url String url);
}
