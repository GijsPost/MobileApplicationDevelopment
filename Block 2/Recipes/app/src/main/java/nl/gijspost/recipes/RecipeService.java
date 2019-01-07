package nl.gijspost.recipes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeService {

    String URL = "http://api.steampowered.com/ISteamNews";
    String ACTION = "GetNewsForApp";
    String API_VERSION = "v0002";
    String FORMAT = "JSON";
    String KEY = "63ba6c010886ad88a5ecddae3f836428";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @GET("search")
    Call<Recipes> getRecipes(@Query("key") String key, @Query("q") String query);
}