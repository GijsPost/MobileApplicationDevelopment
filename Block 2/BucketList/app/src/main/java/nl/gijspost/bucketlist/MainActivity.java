package nl.gijspost.bucketlist;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BucketListAdapter.checkChangedCallback {

    private List<BucketList> list;

    private BucketListAdapter adapter;
    private RecyclerView rv;
    private BucketListViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddItem();
            }
        });
        list = new ArrayList<BucketList>();

        // Init viewmodel
        this.vm = new BucketListViewModel(getApplicationContext());
        this.vm.getAllBucketLists().observe(this, new Observer<List<BucketList>>() {
            @Override
            public void onChanged(@Nullable List<BucketList> lists) {
                list = lists;
                updateUI();
            }
        });

        this.rv = findViewById(R.id.recyclerView);
        this.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ItemTouchHelper.SimpleCallback callback = getTouchHelperCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(this.rv);

        updateUI();
    }

    private void onAddItem() {
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.add_item,null);

        // Creates a new dialog for adding an item
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(layout);
        builder.setPositiveButton(R.string.addItemDialogAdd, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = ((EditText) layout.findViewById(R.id.titleText)).getText().toString();
                String desc = ((EditText) layout.findViewById(R.id.descriptionText)).getText().toString();
                vm.insert(new BucketList(title, desc, false));
            }
        });
        builder.setNegativeButton(R.string.addItemDialogCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Close and do nothing
            }
        });
        builder.setTitle(R.string.addItemDialogTitle);
        AlertDialog alert = builder.create();

        Window window = alert.getWindow();
        if(window != null) {
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }
        alert.show();
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
                vm.delete(list.remove(itemIndex));
            }
        };
    }

    // Refresh certain ui elements
    private void updateUI() {
        if (this.adapter == null) {
            this.adapter = new BucketListAdapter(list, this);
            this.rv.setAdapter(this.adapter);
        } else {
            this.adapter.swapList(list);
        }
    }

    @Override
    public void checkChanged(int pos, boolean checked) {
        BucketList item = this.list.get(pos);
        item.setDone(checked);
        this.vm.update(item);
    }
}
