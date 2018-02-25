package com.mainproject.vishnu_neelancheri.pencilsadmin.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.SplashScreenActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.home.HomeActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById( R.id.btn_submit ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void login(){
        EditText editTxtUserName = findViewById( R.id.edt_txt_username );
        EditText edtTxtPassword = findViewById( R.id.edt_txt_pwd );

        final String userName = editTxtUserName.getText().toString();
        final String password = edtTxtPassword.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue( this );
        String url = PencilUtil.BASE_URL+"admin/admin_login" /*"http://192.168.1.6:8080/pencils_api/index.php/admin/request_job_id"*/;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseResponse( response );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_name", userName );
                params.put("password", password );
                return params;
            }
        };
        requestQueue.add( stringRequest );
    }
    private void parseResponse(String  response ){
        Gson gson = new Gson();
        try{
            LoginModel loginModel = gson.fromJson( response, LoginModel.class );
            Toast.makeText( this, loginModel.getMessage(), Toast.LENGTH_SHORT ).show();

            if ( loginModel.getStatus() == 1 ){
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences( getResources().getString( R.string.app_name), Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( getResources().getString(R.string.token), loginModel.getToken() );
                editor.putString( getResources().getString(R.string.admin_id), loginModel.getAdminId() );
                if (editor.commit()){
                    Intent intent = new Intent( LoginActivity.this, HomeActivity.class );
                    startActivity( intent );
                    finish();
                }

            }

        }catch ( Exception e ){

        }
    }
}
