package com.mainproject.vishnu_neelancheri.pencilsadmin.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.add_center.AddCenterActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.add_paper.AddPaperActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.employee.AddEmployeeActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.employee.ViewAllEmployeeActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.AddMasterFrameActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btn_add_paper).setOnClickListener(this);
        findViewById(R.id.btn_add_center).setOnClickListener(this);
        findViewById(R.id.btn_add_frame).setOnClickListener( this );
        findViewById(R.id.btn_add_employee).setOnClickListener( this );
        findViewById( R.id.btn_view_employee ).setOnClickListener( this );
    }
    @Override
    public void onClick(View view){
        Intent intent;
        switch ( view.getId() ){
            case R.id.btn_add_paper:{
                intent = new Intent(HomeActivity.this, AddPaperActivity.class );
                startActivity( intent );
            }
            break;
            case R.id.btn_add_center:{
                intent = new Intent( HomeActivity.this, AddCenterActivity.class );
                startActivity(intent);
            }
            break;
            case R.id.btn_add_frame:{
                intent = new Intent( HomeActivity.this, AddMasterFrameActivity.class );
                startActivity(intent);
            }
            break;
            case R.id.btn_add_employee:{
                intent = new Intent( HomeActivity.this, AddEmployeeActivity.class );
                startActivity(intent);
            }
            break;
            case R.id.btn_view_employee:{
                intent = new Intent( HomeActivity.this, ViewAllEmployeeActivity.class );
                startActivity(intent);
            }
            break;
        }
    }

}
