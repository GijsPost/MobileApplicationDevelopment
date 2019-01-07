package nl.gijspost.individualassignment;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

import nl.gijspost.individualassignment.models.App;
import nl.gijspost.individualassignment.viewmodels.AppViewModel;

/*
    An App to track news from Games on steam.

 */
public class MainActivity extends AppCompatActivity implements AppAdapter.AppClickListener{

    private AppViewModel viewModel;
    private AppAdapter adapter;
    private RecyclerView recyclerView;
    private List<App> apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_title);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddAppActivity.class), 10);
            }
        });

        this.viewModel = new AppViewModel(getApplicationContext());
        this.viewModel.getApps().observe(this, new Observer<List<App>>() {
            @Override
            public void onChanged(@Nullable List<App> list) {
                apps = list;
                updateUI();
            }
        });

        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ItemTouchHelper.SimpleCallback callback = getTouchHelperCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(this.recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            int appId = data.getIntExtra("appid", 0);
            String appName = data.getStringExtra("appname");

            if (appId == 0 || appName == null) {
                Snackbar.make(recyclerView, "Couldn't add new game, try again...",4000).show();
                return;
            }

            App newApp = new App(appId, appName);
            this.viewModel.insert(newApp);
            apps.add(newApp);
            this.updateUI();
            Snackbar.make(recyclerView, "Successfully added " + appName,1000).show();
        }
    }

    // Swipe callback (Removes item from list)
    @NonNull
    private ItemTouchHelper.SimpleCallback getTouchHelperCallback() {
        return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            //Remove item when swiped
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int itemIndex = (viewHolder.getAdapterPosition());
                viewModel.delete(apps.remove(itemIndex));
            }
        };
    }

    @Override
    public void appOnClick(int i) {
        Intent intent = new Intent(this, ViewNewsActivity.class);
        intent.putExtra("appid", apps.get(i).getAppId());
        intent.putExtra("appname", apps.get(i).getName());
        startActivity(intent);
    }

    // Refresh certain ui elements
    private void updateUI() {
        if (this.adapter == null) {
            this.adapter = new AppAdapter(apps, this);
            this.recyclerView.setAdapter(this.adapter);
        } else {
            this.adapter.swap(apps);
        }
    }

}
