package com.imk7.welearn.welearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.imk7.welearn.welearn.Model.GetUserResponse;
import com.imk7.welearn.welearn.Model.SaveSharedPreference;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
                if(SaveSharedPreference.getToken(SplashScreenActivity.this).length()!=0) {
                    Call<GetUserResponse> callUser = userClient.getUser(SaveSharedPreference.getToken(SplashScreenActivity.this));
                    callUser.enqueue(new Callback<GetUserResponse>() {
                        @Override
                        public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {

                            String username = response.body().getUser().getUsername();
                            String name = response.body().getUser().getName();
                            String email = response.body().getUser().getEmail();
                            String phone = response.body().getUser().getNoHP();

                            Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                            intent.putExtra("USERNAME",username);
                            intent.putExtra("NAME",name);
                            intent.putExtra("EMAIL",email);
                            intent.putExtra("PHONE",phone);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<GetUserResponse> call, Throwable t) {
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
        },1500L);
    }
}
