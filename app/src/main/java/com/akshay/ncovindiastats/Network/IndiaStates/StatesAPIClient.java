package com.akshay.ncovindiastats.Network.IndiaStates;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatesAPIClient {

    public static final String BASE_URL = "https://api.rootnet.in/covid19-in/unofficial/covid19india.org/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
