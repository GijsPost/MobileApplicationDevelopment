package nl.gijspost.individualassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nl.gijspost.individualassignment.fragments.NewsItemFragment;
import nl.gijspost.individualassignment.models.AppNewsResponse;
import nl.gijspost.individualassignment.models.Appnews;
import nl.gijspost.individualassignment.models.Newsitem;
import nl.gijspost.individualassignment.services.GameNewsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewNewsActivity extends AppCompatActivity {

    private GameNewsService gameNewsService;
    private List<Newsitem> newsItemList;
    private PageAdapter pageAdapter;
    private ViewPager viewPager;

    private final int MAX_FRAGMENT_AMOUNT = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news_item);

        Intent intent = getIntent();
        int appId = intent.getIntExtra("appid", 0);
        String appName = intent.getStringExtra("appname");
        getSupportActionBar().setTitle(appName + " - News & Articles");

        this.newsItemList = new ArrayList<Newsitem>();
        this.pageAdapter = new PageAdapter(getSupportFragmentManager());
        this.viewPager = findViewById(R.id.viewPager);
        this.viewPager.setAdapter(this.pageAdapter);

        // Create a service and call for data
        this.gameNewsService = GameNewsService.retrofit.create(GameNewsService.class);
        getGameNews(appId);
    }

    private void getGameNews(int appId)
    {
        Call<AppNewsResponse> call = gameNewsService.getGameNewsList(appId, MAX_FRAGMENT_AMOUNT, "json");
        String url = call.request().url().toString();
        call.enqueue(new Callback<AppNewsResponse>() {
            @Override
            public void onResponse(Call<AppNewsResponse> call, Response<AppNewsResponse> response) {
                Appnews list = response.body().getAppnews();
                newsItemList = list.getNewsitems();
                pageAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<AppNewsResponse> call, Throwable t) {
                // Fail
            }
        });
    }

    // PageAdapter for managing fragments
    public class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return NewsItemFragment.newInstance(newsItemList.get(position));
        }

        @Override
        public int getCount() {
            return newsItemList.size();
        }
    }
}
