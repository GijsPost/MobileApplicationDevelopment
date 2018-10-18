package nl.gijspost.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PortalAdapter.PortalClickListener{

    private List<Portal> portals;
    private PortalAdapter portalAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        portals = new ArrayList<>();
        portals.add(new Portal("Rooster", "https://rooster.hva.nl/"));
        portals.add(new Portal("Vlo", "https://vlo.informatica.hva.nl/"));
        portals.add(new Portal("HvA", "http://www.hva.nl/"));
        portals.add(new Portal("A-Z Lijst", "https://student.hva.nl/hbo-ict/a-z/az.html"));
        updateUI();

        //Start the add portal activity when we clicked on the add button.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddPortalActivity.class), 10);
            }
        });

    }

    //update the user interface
    private void updateUI(){
        if(portalAdapter == null){
            portalAdapter = new PortalAdapter(portals, this);
            recyclerView.setAdapter(portalAdapter);
        } else {
            portalAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String newLabel = (String) data.getExtras().getString("LABEL");
            String newUrl = (String) data.getExtras().getString("URL");

            if (newLabel == null || newUrl == null) {
                Snackbar.make(recyclerView, "Couldn't add new portal, try again...",4000).show();
                return;
            }

            portals.add(new Portal(newLabel, newUrl));
            this.updateUI();
            Snackbar.make(recyclerView, "Added new Portal with name: " + newLabel ,1000).show();
        }
    }

    @Override
    public void portalOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, WebPortalActivity.class);
        intent.putExtra("URL", portals.get(i).getPortalUrl());
        startActivity(intent);
    }
}
