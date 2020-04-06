
package com.akshay.ncovindiastats.Models.Numbers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Regional {

    @SerializedName("loc")
    @Expose
    private String loc;
    @SerializedName("number")
    @Expose
    private String number;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
