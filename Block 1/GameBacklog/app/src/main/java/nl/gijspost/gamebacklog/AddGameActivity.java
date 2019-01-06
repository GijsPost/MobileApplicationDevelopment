package nl.gijspost.gamebacklog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.util.Date;

public class AddGameActivity extends MainActivity {

    private EditText gameNameInput;
    private EditText gamePlatformInput;
    private EditText gameNotesInput;
    private Spinner gameStatusInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameNameInput = findViewById(R.id.gameNameInput);
        gamePlatformInput = findViewById(R.id.gamePlatformInput);
        gameNotesInput = findViewById(R.id.gameNotesInput);
        gameStatusInput = findViewById(R.id.gameStatusInput);

        FloatingActionButton fab = findViewById(R.id.saveButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameName = gameNameInput.getText().toString();
                String gamePlatform = gamePlatformInput.getText().toString();
                String gameNotes = gameNotesInput.getText().toString();
                String gameStatus = gameStatusInput.getSelectedItem().toString();
                String gameDate = DateFormat.getDateInstance().format(new Date());

                if (!TextUtils.isEmpty(gameName) && !TextUtils.isEmpty(gamePlatform) && !TextUtils.isEmpty(gameNotes) && !TextUtils.isEmpty(gameStatus)) {
                    Game newGame = new Game(gameName, gamePlatform, gameNotes, gameStatus, gameDate);
                    new GameAsyncTask(INSERT).execute(newGame);

                    // Empty fields
                    gameNameInput.setText("");
                    gamePlatformInput.setText("");
                    gameNotesInput.setText("");

                    setResult(RESULT_OK);
                    finish();
                } else {
                    Snackbar.make(view, "One or more fields were left empty", 5000).show();
                }
            }
        });
    }

}