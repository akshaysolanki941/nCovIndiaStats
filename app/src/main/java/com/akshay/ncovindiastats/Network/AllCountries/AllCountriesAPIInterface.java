package com.akshay.ncovindiastats.Network.AllCountries;

import com.akshay.ncovindiastats.Models.AllCountries.Example;
import com.akshay.ncovindiastats.Models.CountryData.Example1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AllCountriesAPIInterface {

    @GET("countries")
    Call<Example> getAllCountriesData();

    @GET("countries/{country_code}")
    Call<Example1> getCountryData(@Path("country_code") String country_code);

}
