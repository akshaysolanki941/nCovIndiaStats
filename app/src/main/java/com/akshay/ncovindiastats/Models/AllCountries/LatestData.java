
package com.akshay.ncovindiastats.Models.AllCountries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestData {

    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("confirmed")
    @Expose
    private Integer confirmed;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("critical")
    @Expose
    private Integer critical;
    @SerializedName("calculated")
    @Expose
    private Calculated calculated;

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Calculated getCalculated() {
        return calculated;
    }

    public void setCalculated(Calculated calculated) {
        this.calculated = calculated;
    }

}
