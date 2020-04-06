
package com.akshay.ncovindiastats.Models.AllCountries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("today")
    @Expose
    private Today today;
    @SerializedName("latest_data")
    @Expose
    private LatestData latestData;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public LatestData getLatestData() {
        return latestData;
    }

    public void setLatestData(LatestData latestData) {
        this.latestData = latestData;
    }

}
