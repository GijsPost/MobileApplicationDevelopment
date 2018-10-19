package nl.gijspost.gamebacklog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GameAdapter.GameClickListener {

    private List<Game> games;
    private GameAdapter gameAdapter;
    private RecyclerView recyclerView;

    public static String EXTRA_GAME = "Game";
    public static int ADD_GAME_REQUEST_CODE = 100;
    public static int UPDATE_GAME_REQUEST_CODE = 200;
    private int mModifyPosition;

    public final int GET_ALL = 0;
    public final int DELETE = 1;
    public final int UPDATE = 2;
    public final int INSERT = 3;

    static GameDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = GameDatabase.getInstance(this);

        new GameAsyncTask(GET_ALL).execute();

        games = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        gameAdapter = new GameAdapter(games, this);
        recyclerView.setAdapter(gameAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddGameActivity.class);
                startActivityForResult(intent, ADD_GAME_REQUEST_CODE);
            }
        });


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        new GameAsyncTask(DELETE).execute(games.get(position));
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    public void onDatabaseUpdate(List list) {
        games = list;
        updateUI();
    }


    private void updateUI() {
        gameAdapter.setGames(games);
    }

    @Override
    public void gameOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, UpdateGameActivity.class);
        intent.putExtra("gameToUpdate", games.get(i));
        intent.putExtra("position", i);
        startActivityForResult(intent, UPDATE_GAME_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_GAME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                new GameAsyncTask(GET_ALL).execute();
                updateUI();
            }
        }
        if (requestCode == UPDATE_GAME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                new GameAsyncTask(GET_ALL).execute();
                updateUI();
            }
        }
    }

    public class GameAsyncTask extends AsyncTask<Game, Void, List> {

        private int taskCode;

        public GameAsyncTask(int taskCode) {
            this.taskCode = taskCode;
        }


        @Override
        protected List doInBackground(Game... games) {
            switch (taskCode) {
                case DELETE:
                    database.gameDao().deleteGames(games[0]);
                    break;

                case UPDATE:
                    database.gameDao().updateGames(games[0]);
                    break;

                case INSERT:
                    database.gameDao().addGames(games[0]);
                    break;
            }

            return database.gameDao().getGames();
        }


        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            onDatabaseUpdate(list);
        }

    }


}
