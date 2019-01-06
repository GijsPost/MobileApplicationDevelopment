package nl.gijspost.studentportal;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortalActivity extends AppCompatActivity {

    private EditText urlInputField;
    private EditText labelInputField;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);
        getSupportActionBar().setTitle("Add a portal");

        urlInputField = findViewById(R.id.urlInput);
        labelInputField = findViewById(R.id.portalLabelInput);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlInput = urlInputField.getText().toString();
                String labelInput = labelInputField.getText().toString();

                if (urlInput.length() > 0 && labelInput.length() > 0){
                    Intent data = new Intent();
                    data.putExtra("URL", urlInput);
                    data.putExtra("LABEL", labelInput);

                    // Done adding; send data
                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}