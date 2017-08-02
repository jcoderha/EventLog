package com.example.asus.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEventActivity extends AppCompatActivity {

    EditText editText;
    Button addEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        editText = (EditText) findViewById(R.id.text);
        addEvent = (Button) findViewById(R.id.button_addEvent);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("text", editText.getText().toString());
                resultIntent.putExtra("date", System.currentTimeMillis());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
