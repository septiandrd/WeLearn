package com.imk7.welearn.welearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.imk7.welearn.welearn.Model.SaveSharedPreference;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        if(SaveSharedPreference.getUSERNAME(WelcomeScreenActivity.this).length()!=0) {
            Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void SignUpOnClick(View v) {
        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void LoginOnClick(View v) {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
