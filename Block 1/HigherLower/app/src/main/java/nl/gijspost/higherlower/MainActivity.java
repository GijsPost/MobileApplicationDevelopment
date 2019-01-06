package nl.gijspost.higherlower;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView throwListView;
    private ArrayAdapter<String> throwsArrayAdapter;
    private List<String> throwList;
    private List<Integer> throwIntegerList;
    private ImageView diceImageView;
    private List<Drawable> diceList;
    private FloatingActionButton lowerButton;
    private FloatingActionButton higherButton;
    private TextView highscoreView;
    private TextView scoreView;

    private Integer score;
    private Integer highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // The throw list view
        throwListView = findViewById(R.id.throwList);
        // List of the previous throws
        throwIntegerList = new ArrayList<Integer>();
        // List for the throwListView, string representation of the throwIntegerList
        throwList = new ArrayList<String>();
        // List that holds the drawable images of the dices
        diceList = new ArrayList<Drawable>();
        // The image view for the dices
        diceImageView = findViewById(R.id.diceImage);
        // Adds all images to the dice list
        diceList.add(getResources().getDrawable(R.drawable.d1));
        diceList.add(getResources().getDrawable(R.drawable.d2));
        diceList.add(getResources().getDrawable(R.drawable.d3));
        diceList.add(getResources().getDrawable(R.drawable.d4));
        diceList.add(getResources().getDrawable(R.drawable.d5));
        diceList.add(getResources().getDrawable(R.drawable.d6));
        // The higher and lower buttons
        lowerButton = findViewById(R.id.lowerButton);
        higherButton = findViewById(R.id.higherButton);
        // The text views for the scores output
        highscoreView = findViewById(R.id.highscoreView);
        scoreView = findViewById(R.id.scoreView);

        lowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastRoll = throwIntegerList.get(throwIntegerList.size() - 1);
                evaluateGuess("low", throwDice(), lastRoll);
            }
        });

        higherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastRoll = throwIntegerList.get(throwIntegerList.size() - 1);
                evaluateGuess("high", throwDice(), lastRoll);
            }
        });

        score = 0;
        highscore = 0;
        highscoreView.setText("Highscore: " + highscore);
        scoreView.setText("Score: " + score);
        throwDice();
    }

    public void evaluateGuess(String input, int newRoll, int lastRoll) {
        if (input == "high") {
            if (newRoll > lastRoll) {
                score++;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else if (newRoll < lastRoll) {
                Toast.makeText(this, "You lost...", Toast.LENGTH_SHORT).show();
                if (highscore < score) {
                    highscore = score;
                }
                score = 0;
            } else {
                Toast.makeText(this, "Equal roll, nothing happened", Toast.LENGTH_SHORT).show();
            }
        } else if (input == "low") {
            if (newRoll < lastRoll) {
                score++;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else if (newRoll > lastRoll) {
                Toast.makeText(this, "You lost...", Toast.LENGTH_SHORT).show();
                if (highscore < score) {
                    highscore = score;
                }
                score = 0;
            } else {
                Toast.makeText(this, "Equal roll, nothing happened", Toast.LENGTH_SHORT).show();
            }
        }
        highscoreView.setText("Highscore: " + highscore);
        scoreView.setText("Score: " + score);
    }

    public int throwDice() {
        int roll = generateRoll(1,6);
        throwIntegerList.add(roll);
        throwList.add("Throw was: " + roll);
        Drawable imageToSet = diceList.get(roll - 1);
        updateUI(imageToSet);
        return roll;
    }

    public int generateRoll(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    private void updateUI(Drawable imageToSet) {
        if (throwsArrayAdapter == null) {
            throwsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, throwList);
            throwListView.setAdapter(throwsArrayAdapter);
        } else {
            throwsArrayAdapter.notifyDataSetChanged();
        }
        diceImageView.setImageDrawable(imageToSet);
        throwListView.setSelection(throwList.size() - 1);
    }
}
