package com.imk7.welearn.welearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.imk7.welearn.welearn.Model.SaveSharedPreference;
import com.imk7.welearn.welearn.Model.Score;
import com.imk7.welearn.welearn.Model.ScoreResponse;
import com.imk7.welearn.welearn.Service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    TextView txName, txUsername, txEmail, txPhone;
    TextView score1,score2,score3,score4;
    ProgressBar progress1,progress2,progress3,progress4;
    String name,username,email,phone;
    int modul1,modul2,modul3,modul4;;

//    Retrofit.Builder builer = new Retrofit.Builder()
//            .baseUrl("https://welearnapp.000webhostapp.com/")
//            .addConverterFactory(GsonConverterFactory.create());
//
//    Retrofit retrofit = builer.build();
//
//    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");
        email = intent.getStringExtra("EMAIL");
        phone = intent.getStringExtra("PHONE");
        modul1 = intent.getIntExtra("MOD1",0);
        modul2 = intent.getIntExtra("MOD2",0);
        modul3 = intent.getIntExtra("MOD3",0);
        modul4 = intent.getIntExtra("MOD4",0);

        txName = findViewById(R.id.profile_name);
        txUsername = findViewById(R.id.profile_username);
        txEmail = findViewById(R.id.profile_email);
        txPhone = findViewById(R.id.profile_phone);

        progress1 = findViewById(R.id.progress_modul1);
        progress2 = findViewById(R.id.progress_modul2);
        progress3 = findViewById(R.id.progress_modul3);
        progress4 = findViewById(R.id.progress_modul4);

        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        score3 = findViewById(R.id.score3);
        score4 = findViewById(R.id.score4);

        txName.setText(name);
        txUsername.setText(username);
        txEmail.setText(email);
        txPhone.setText(phone);

        score1.setText(String.valueOf(modul1));
        score2.setText(String.valueOf(modul2));
        score3.setText(String.valueOf(modul3));
        score4.setText(String.valueOf(modul4));
        progress1.setProgress(modul1);
        progress2.setProgress(modul2);
        progress3.setProgress(modul3);
        progress4.setProgress(modul4);

//        Call<Score> call = userClient.getScore(SaveSharedPreference.getToken(ProfileActivity.this));
//        call.enqueue(new Callback<Score>() {
//            @Override
//            public void onResponse(Call<Score> call, Response<Score> response) {
//                if(response.isSuccessful()) {
//                    modul1 = response.body().getModul1();
//                    modul2 = response.body().getModul2();
//                    modul3 = response.body().getModul3();
//                    modul4 = response.body().getModul4();
//                    score1.setText(String.valueOf(modul1));
//                    score2.setText(String.valueOf(modul2));
//                    score3.setText(String.valueOf(modul3));
//                    score4.setText(String.valueOf(modul4));
//                    progress1.setProgress(modul1);
//                    progress2.setProgress(modul2);
//                    progress3.setProgress(modul3);
//                    progress4.setProgress(modul4);
//                }
//            }
//            @Override
//            public void onFailure(Call<Score> call, Throwable t) {
//
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
        intent.putExtra("USERNAME",username);
        intent.putExtra("NAME",name);
        intent.putExtra("EMAIL",email);
        intent.putExtra("PHONE",phone);
        intent.putExtra("MOD1",modul1);
        intent.putExtra("MOD2",modul2);
        intent.putExtra("MOD3",modul3);
        intent.putExtra("MOD4",modul4);
        startActivity(intent);
        finish();
    }
}
