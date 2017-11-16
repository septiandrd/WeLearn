package com.imk7.welearn.welearn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.imk7.welearn.welearn.Model.SaveSharedPreference;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String username,name,email,phone;
    int modul1,modul2,modul3,modul4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");
        name = intent.getStringExtra("NAME");
        email = intent.getStringExtra("EMAIL");
        phone = intent.getStringExtra("PHONE");
        modul1 = intent.getIntExtra("MOD1",0);
        modul2 = intent.getIntExtra("MOD2",0);
        modul3 = intent.getIntExtra("MOD3",0);
        modul4 = intent.getIntExtra("MOD4",0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        TextView txName = header.findViewById(R.id.nav_name);
        TextView txEmail = header.findViewById(R.id.nav_email);
        txName.setText(name);
        txEmail.setText(email);

        setTitle("Home");
        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment,mainMenuFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
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

        } else if (id == R.id.nav_logout) {

            SaveSharedPreference.setToken(MainMenuActivity.this,"");
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Confirm Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentLogout = new Intent(getApplicationContext(),WelcomeScreenActivity.class);
                            startActivity(intentLogout);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                            navigationView.setCheckedItem(R.id.nav_home);
                        }
                    })
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
