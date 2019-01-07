package nl.gijspost.individualassignment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppNewsResponse {

    @SerializedName("appnews")
    @Expose
    private Appnews appnews;

    public Appnews getAppnews() {
        return appnews;
    }

    public void setAppnews(Appnews appnews) {
        this.appnews = appnews;
    }

    public AppNewsResponse withAppnews(Appnews appnews) {
        this.appnews = appnews;
        return this;
    }

}