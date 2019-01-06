package nl.gijspost.numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    NumbersService service;
    NumberTriviaAdapter adapter;
    List<NumberTrivia> numbers;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Instantiate variables
        this.service = NumbersService.retrofit.create(NumbersService.class);
        this.numbers = new ArrayList<NumberTrivia>();
        this.adapter = new NumberTriviaAdapter(this.numbers);
        this.recyclerView = findViewById(R.id.recyclerView);

        // Set recyclerView properties and attach adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.adapter);

        // Declare fab onClickListeners
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<NumberTrivia> call = service.getRandomTrivia();
                call.enqueue(new Callback<NumberTrivia>() {
                    @Override
                    public void onResponse(Call<NumberTrivia> call, Response<NumberTrivia> response) {
                        NumberTrivia trivia = response.body();
                        addNumber(trivia);
                    }
                    @Override
                    public void onFailure(Call<NumberTrivia> call, Throwable t) {
                        call = service.getRandomTrivia();
                        call.enqueue(this);
                    }
                });
            }
        });
    }

    // Add a number to the list
    public void addNumber(NumberTrivia number) {
        this.adapter.addNumber(number);
    }
}
