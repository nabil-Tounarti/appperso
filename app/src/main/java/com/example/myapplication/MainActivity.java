package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.security.AccessController;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "yufiyfiyfg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Toast.makeText(v.getContext(), "hello", Toast.LENGTH_LONG).show();
            }
        });
        EditText plain_text_input = (EditText) findViewById(R.id.plain_text_input);
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button2 = (Button) findViewById(R.id.button2);
        SharedPreferences shr = PreferenceManager.getDefaultSharedPreferences(this);
        String s =shr.getString("Textview","vide");
        textView.setText(s);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String retour = plain_text_input.getText().toString();
                shr.edit().putString("Textview",retour).apply();
                textView.setText(retour);

            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                EditText editText = (EditText) findViewById(R.id.plain_text_input);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(v.getContext(), WebviewActivity.class);
                startActivity(intent);
            }
        });

    }
}


