package nl.gijspost.individualassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddAppActivity extends AppCompatActivity {

    private EditText appIdInputField;
    private EditText appNameInputField;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app);
        getSupportActionBar().setTitle(R.string.add_app_title);

        appIdInputField = findViewById(R.id.appIdInput);
        appNameInputField = findViewById(R.id.appNameInput);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int appIdInput = Integer.parseInt(appIdInputField.getText().toString());
                String nameInput = appNameInputField.getText().toString();

                if (appIdInput != 0 && nameInput.length() > 0){
                    Intent data = new Intent();
                    data.putExtra("appname", nameInput);
                    data.putExtra("appid", appIdInput);

                    // Done adding; send data
                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}
