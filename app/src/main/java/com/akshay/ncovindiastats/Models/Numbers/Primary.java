
package com.akshay.ncovindiastats.Models.Numbers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Primary {

    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("number-tollfree")
    @Expose
    private String numberTollfree;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("media")
    @Expose
    private List<String> media = null;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumberTollfree() {
        return numberTollfree;
    }

    public void setNumberTollfree(String numberTollfree) {
        this.numberTollfree = numberTollfree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

}
