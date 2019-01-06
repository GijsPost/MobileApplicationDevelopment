package nl.gijspost.geoguessswipe;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GeoAdapter geoAdapter;
    private RecyclerView recyclerView;
    private List<Geo> geoLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.geoLocations = new ArrayList<>();

        for (int i = 0; i < GEOS.length; i++) {
            this.geoLocations.add(GEOS[i]);
        }

        recyclerView = findViewById(R.id.geoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        geoAdapter = new GeoAdapter(geoLocations);
        recyclerView.setAdapter(geoAdapter);

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int index = (viewHolder.getAdapterPosition());
                Geo geoToCheck = geoLocations.get(index);

                if ((geoToCheck.isInEurope() && swipeDir == ItemTouchHelper.LEFT) || (!geoToCheck.isInEurope() && swipeDir == ItemTouchHelper.RIGHT)) {
                    Snackbar.make(recyclerView, "Correct!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Snackbar.make(recyclerView, "Wrong!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

                geoLocations.remove(index);
                updateUI();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void updateUI() {
        geoAdapter.notifyDataSetChanged();
    }

    private static final Geo[] GEOS = {
            new Geo(true, R.drawable.img1_yes_denmark),
            new Geo(false, R.drawable.img2_no_canada),
            new Geo(false, R.drawable.img3_no_bangladesh),
            new Geo(true, R.drawable.img4_yes_kazachstan), // Kazachstan is not in europe, btw.
            new Geo(false, R.drawable.img5_no_colombia),
            new Geo(true, R.drawable.img6_yes_poland),
            new Geo(true, R.drawable.img7_yes_malta),
            new Geo(false, R.drawable.img8_no_thailand)
    };
}
