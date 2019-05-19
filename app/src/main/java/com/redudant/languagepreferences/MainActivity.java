package com.redudant.languagepreferences;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    SharedPreferences sharedPreferences;

    //method save permanent languange using SharedPreferences
    public void setLanguage(String language) {


        sharedPreferences.edit().putString("language", language).apply();

        textView.setText(language);
    }

    //method view option menu titik tiga
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //method item click optiojn
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.english) {

            setLanguage("English");
        } else if (item.getItemId() == R.id.indonesia) {
            setLanguage("Indonesia");
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.redudant.languagepreferences", Context.MODE_PRIVATE);

        String language = sharedPreferences.getString("language", "");

        if (language == "") {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Chooes a language")
                    .setMessage("Whicth language would you like? ")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            setLanguage("English");

                        }
                    })
                    .setNegativeButton("Indonesia", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            setLanguage("Indonesia");

                        }
                    })
                    .show();
        } else {

            textView.setText(language);
        }
    }
}
