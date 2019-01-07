package nl.gijspost.individualassignment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newsitem {

    @SerializedName("gid")
    @Expose
    private String gid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("is_external_url")
    @Expose
    private boolean isExternalUrl;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("contents")
    @Expose
    private String contents;
    @SerializedName("feedlabel")
    @Expose
    private String feedlabel;
    @SerializedName("date")
    @Expose
    private int date;
    @SerializedName("feedname")
    @Expose
    private String feedname;
    @SerializedName("feed_type")
    @Expose
    private int feedType;
    @SerializedName("appid")
    @Expose
    private int appid;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Newsitem withGid(String gid) {
        this.gid = gid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Newsitem withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Newsitem withUrl(String url) {
        this.url = url;
        return this;
    }

    public boolean isIsExternalUrl() {
        return isExternalUrl;
    }

    public void setIsExternalUrl(boolean isExternalUrl) {
        this.isExternalUrl = isExternalUrl;
    }

    public Newsitem withIsExternalUrl(boolean isExternalUrl) {
        this.isExternalUrl = isExternalUrl;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Newsitem withAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Newsitem withContents(String contents) {
        this.contents = contents;
        return this;
    }

    public String getFeedlabel() {
        return feedlabel;
    }

    public void setFeedlabel(String feedlabel) {
        this.feedlabel = feedlabel;
    }

    public Newsitem withFeedlabel(String feedlabel) {
        this.feedlabel = feedlabel;
        return this;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Newsitem withDate(int date) {
        this.date = date;
        return this;
    }

    public String getFeedname() {
        return feedname;
    }

    public void setFeedname(String feedname) {
        this.feedname = feedname;
    }

    public Newsitem withFeedname(String feedname) {
        this.feedname = feedname;
        return this;
    }

    public int getFeedType() {
        return feedType;
    }

    public void setFeedType(int feedType) {
        this.feedType = feedType;
    }

    public Newsitem withFeedType(int feedType) {
        this.feedType = feedType;
        return this;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public Newsitem withAppid(int appid) {
        this.appid = appid;
        return this;
    }

}