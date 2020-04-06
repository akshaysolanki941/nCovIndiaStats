package com.akshay.ncovindiastats.Network.IndiaStates;

import com.akshay.ncovindiastats.Models.IndiaStates.Example2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatesAPIInterface {

    @GET("statewise")
    Call<Example2> getStateWiseData();

}
