package com.example.pc4_programacionmovil_bravopaul.service;

import com.example.pc4_programacionmovil_bravopaul.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface JokeApiService {
    @GET("joke/Programming")
    Call<Joke> getProgrammingJoke(@Query("lang") String language, @Query("type") String type);

    @GET("joke/Any")
    Call<Joke> getAnyJoke(@Query("lang") String language, @Query("type") String type);
}
