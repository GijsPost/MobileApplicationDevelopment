package nl.gijspost.recipes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final int MAX_LOAD_AMOUNT = 20;
    private PageAdapter pageAdapter;
    private List<Recipe> recipeList;
    private RecipeService service;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the data first time
        this.service = RecipeService.retrofit.create(RecipeService.class);
        requestData();

        // Initialize the list and view elements
        this.recipeList = new ArrayList<>();
        this.pageAdapter = new PageAdapter(getSupportFragmentManager());
        this.viewPager = findViewById(R.id.viewPager);
        this.viewPager.setAdapter(this.pageAdapter);
    }

    private void requestData()
    {
        // Doesn't really matter what gets put in here, the api seems to return random stuff anyway.
        final String query = "beef";

        Call<Recipes> call = service.getRecipes(RecipeService.KEY, query);
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                Recipes recipes = response.body();
                Log.i("RESP", response.toString());
                int amountToLoad = recipes.getCount() <= MAX_LOAD_AMOUNT ? recipes.getCount() : MAX_LOAD_AMOUNT;
                for(int i = 0; i < amountToLoad; i++){
                    recipeList.add(recipes.getRecipes().get(i));
                }
                pageAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                // Fail
            }
        });
    }

    // PageAdapter for retrieving the fragments
    public class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return RecipeFragment.newInstance(recipeList.get(position));
        }

        @Override
        public int getCount() {
            return recipeList.size();
        }
    }
}