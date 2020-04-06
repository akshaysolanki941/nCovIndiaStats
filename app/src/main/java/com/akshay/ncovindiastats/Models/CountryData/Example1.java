
package com.akshay.ncovindiastats.Models.CountryData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example1 {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("_cacheHit")
    @Expose
    private Boolean cacheHit;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean getCacheHit() {
        return cacheHit;
    }

    public void setCacheHit(Boolean cacheHit) {
        this.cacheHit = cacheHit;
    }

}
