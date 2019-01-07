package nl.gijspost.individualassignment.fragments;

import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import nl.gijspost.individualassignment.R;
import nl.gijspost.individualassignment.models.Newsitem;

public class NewsItemFragment extends Fragment {

    private Newsitem newsItem;

    public NewsItemFragment() {
    }

    public static NewsItemFragment newInstance(Newsitem newsItem) {
        NewsItemFragment fragment = new NewsItemFragment();
        fragment.setNewsItem(newsItem);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.news_item_fragment, container, false);

        if (this.newsItem == null) {
            return rootView;
        }

        TextView titleView = (TextView) rootView.findViewById(R.id.titleView);
        TextView contentView = (TextView) rootView.findViewById(R.id.contentView);
        TextView authorView = (TextView) rootView.findViewById(R.id.authorView);
        TextView dateView = (TextView) rootView.findViewById(R.id.dateView);

        titleView.setText(this.newsItem.getTitle());
        authorView.setText(this.newsItem.getAuthor().length() > 0 ? this.newsItem.getAuthor() : "Unknown author");
        dateView.setText(convertUnixToString(this.newsItem.getDate()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String content = Html.fromHtml(this.newsItem.getContents(), Html.FROM_HTML_MODE_LEGACY).toString();
            contentView.setText(content);
            Log.i("CONTENT", content);
        } else {
            contentView.setText(this.newsItem.getContents());
        }

        return rootView;
    }

    public void setNewsItem(Newsitem newsItem){
        this.newsItem = newsItem;
    }

    public Newsitem getNewsItem(){
        return this.newsItem;
    }

    private String convertUnixToString(long unix) {
        Date date = new java.util.Date(unix*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+1"));
        return sdf.format(date);
    }
}
