package nl.gijspost.gamebacklog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

public class UpdateGameActivity extends MainActivity {

    private EditText gameName;
    private EditText gamePlatform;
    private EditText gameNotes;
    private Spinner gameStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_game);

        final Game updatedGame = getIntent().getParcelableExtra("gameToUpdate");
        final int position = getIntent().getIntExtra("position", -1);

        gameName = findViewById(R.id.gameNameInput);
        gamePlatform = findViewById(R.id.gamePlatformInput);
        gameNotes = findViewById(R.id.gameNotesInput);
        gameStatus = findViewById(R.id.gameStatusInput);

        gameName.setText(updatedGame.getGameName());
        gamePlatform.setText(updatedGame.getGamePlatform());
        gameNotes.setText(updatedGame.getGameNotes());

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gameStatuses,
                android.R.layout.simple_spinner_item
        );
        gameStatus.setAdapter(adapter);
        String[] statuses = getResources().getStringArray(R.array.gameStatuses);
        gameStatus.setSelection(Arrays.asList(statuses).indexOf(updatedGame.getGameStatus()));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = gameName.getText().toString();
                String platform = gamePlatform.getText().toString();
                String notes = gameNotes.getText().toString();
                String status = gameStatus.getSelectedItem().toString();
                String date = DateFormat.getDateInstance().format(new Date());

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(platform) || TextUtils.isEmpty(notes) || TextUtils.isEmpty(status)) {
                    Snackbar.make(view, "One or more fields were left empty...", 5000);
                }

                updatedGame.setGameName(name);
                updatedGame.setGamePlatform(platform);
                updatedGame.setGameNotes(notes);
                updatedGame.setGameStatus(status);
                updatedGame.setGameDate(date);

                new MainActivity.GameAsyncTask(UPDATE).execute(updatedGame);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}