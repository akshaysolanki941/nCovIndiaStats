
package com.akshay.ncovindiastats.Models.CountryData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Calculated {

    @SerializedName("death_rate")
    @Expose
    private Double deathRate;
    @SerializedName("recovery_rate")
    @Expose
    private Double recoveryRate;
    @SerializedName("recovered_vs_death_ratio")
    @Expose
    private Object recoveredVsDeathRatio;
    @SerializedName("cases_per_million_population")
    @Expose
    private Integer casesPerMillionPopulation;

    public Double getDeathRate() {
        return deathRate;
    }

    public void setDeathRate(Double deathRate) {
        this.deathRate = deathRate;
    }

    public Double getRecoveryRate() {
        return recoveryRate;
    }

    public void setRecoveryRate(Double recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public Object getRecoveredVsDeathRatio() {
        return recoveredVsDeathRatio;
    }

    public void setRecoveredVsDeathRatio(Object recoveredVsDeathRatio) {
        this.recoveredVsDeathRatio = recoveredVsDeathRatio;
    }

    public Integer getCasesPerMillionPopulation() {
        return casesPerMillionPopulation;
    }

    public void setCasesPerMillionPopulation(Integer casesPerMillionPopulation) {
        this.casesPerMillionPopulation = casesPerMillionPopulation;
    }

}
