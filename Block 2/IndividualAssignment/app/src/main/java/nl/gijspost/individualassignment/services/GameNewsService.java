package nl.gijspost.individualassignment.services;

import nl.gijspost.individualassignment.models.AppNewsResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameNewsService {

    String ACTION = "GetNewsForApp";
    String API_VERSION = "v0002";

    String URL = "http://api.steampowered.com/ISteamNews/" + ACTION + "/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @GET(API_VERSION)
    Call<AppNewsResponse> getGameNewsList(@Query("appid") int appId, @Query("count") int count, @Query("format") String format);
}