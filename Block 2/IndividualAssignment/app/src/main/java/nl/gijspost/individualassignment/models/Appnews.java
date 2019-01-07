package nl.gijspost.individualassignment.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Appnews {

    @SerializedName("appid")
    @Expose
    private int appid;
    @SerializedName("newsitems")
    @Expose
    private List<Newsitem> newsitems = null;
    @SerializedName("count")
    @Expose
    private int count;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public Appnews withAppid(int appid) {
        this.appid = appid;
        return this;
    }

    public List<Newsitem> getNewsitems() {
        return newsitems;
    }

    public void setNewsitems(List<Newsitem> newsitems) {
        this.newsitems = newsitems;
    }

    public Appnews withNewsitems(List<Newsitem> newsitems) {
        this.newsitems = newsitems;
        return this;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Appnews withCount(int count) {
        this.count = count;
        return this;
    }

}