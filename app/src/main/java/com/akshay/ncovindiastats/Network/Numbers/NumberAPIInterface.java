package com.akshay.ncovindiastats.Network.Numbers;

import com.akshay.ncovindiastats.Models.Numbers.Example3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NumberAPIInterface {

    @GET("contacts")
    Call<Example3> getContacts();

}
