package com.imk7.welearn.welearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.imk7.welearn.welearn.Model.GetUserResponse;
import com.imk7.welearn.welearn.Model.Score;
import com.imk7.welearn.welearn.Service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreenActivity extends AppCompatActivity {

    Retrofit.Builder builer = new Retrofit.Builder()
            .baseUrl("https://welearnapp.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builer.build();

    UserClient userClient = retrofit.create(UserClient.class);

    String username,name,email,phone;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(sharedPref.getString("token","").length()!=0) {
            Call<GetUserResponse> callUser = userClient.getUser(sharedPref.getString("token",""));
            callUser.enqueue(new Callback<GetUserResponse>() {
                @Override
                public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                    if(response.isSuccessful()){
                        username = response.body().getUser().getUsername();
                        name = response.body().getUser().getName();
                        email = response.body().getUser().getEmail();
                        phone = response.body().getUser().getNoHP();

                        Call<Score> callScore = userClient.getScore(sharedPref.getString("token",""));
                        callScore.enqueue(new Callback<Score>() {
                            @Override
                            public void onResponse(Call<Score> call, Response<Score> response) {
                                if(response.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                                    intent.putExtra("USERNAME",username);
                                    intent.putExtra("NAME",name);
                                    intent.putExtra("EMAIL",email);
                                    intent.putExtra("PHONE",phone);
                                    intent.putExtra("MOD1",response.body().getModul1());
                                    intent.putExtra("MOD2",response.body().getModul2());
                                    intent.putExtra("MOD3",response.body().getModul3());
                                    intent.putExtra("MOD4",response.body().getModul4());
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<Score> call, Throwable t) {
                                Intent intent = new Intent(getApplicationContext(),WelcomeScreenActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                    } else {
                        Intent intent = new Intent(getApplicationContext(),WelcomeScreenActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<GetUserResponse> call, Throwable t) {
                    Intent intent = new Intent(getApplicationContext(),WelcomeScreenActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(),WelcomeScreenActivity.class);
                    startActivity(intent);
                    finish();
                }
            },1200L);
        }
    }
}
