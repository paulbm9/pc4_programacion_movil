package com.example.pc4_programacionmovil_bravopaul.service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeApiClient {
    private static final String BASE_URL = "https://v2.jokeapi.dev/";
    private static JokeApiService apiService;

    public static JokeApiService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(JokeApiService.class);
        }
        return apiService;
    }
}