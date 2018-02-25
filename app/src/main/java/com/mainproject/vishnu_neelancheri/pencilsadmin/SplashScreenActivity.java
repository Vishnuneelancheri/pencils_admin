package com.mainproject.vishnu_neelancheri.pencilsadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mainproject.vishnu_neelancheri.pencilsadmin.home.HomeActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent ;
                SharedPreferences sharedPreferences =
                        getApplicationContext().getSharedPreferences( getResources().getString( R.string.app_name), Context.MODE_PRIVATE );
                if ( !sharedPreferences.getString(getResources().getString(R.string.token), "").isEmpty()
                        && !sharedPreferences.getString(getResources().getString(R.string.admin_id), "").isEmpty()  ){
                    intent = new Intent( SplashScreenActivity.this, HomeActivity.class );
                }else {
                    intent = new Intent( SplashScreenActivity.this, LoginActivity.class );
                }
                startActivity( intent );
                finish();

            }
        }, 1000 );
    }
}
