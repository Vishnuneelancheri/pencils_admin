package com.mainproject.vishnu_neelancheri.pencilsadmin.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.add_center.AddStationActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.add_center.ViewAllStationActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.add_paper.AddPaperActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.add_paper.ViewAllPaperActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.employee.AddEmployeeActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.employee.ViewAllEmployeeActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.AddFrameStockActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.AddMasterFrameActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.ViewFrameStockActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.GetPrefs;

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
        findViewById( R.id.btn_logout ).setOnClickListener( this );
        findViewById( R.id.btn_view_frame_stock ).setOnClickListener( this );
        findViewById( R.id.btn_add_frame_stock ).setOnClickListener( this );
        findViewById( R.id.btn_all_paper ).setOnClickListener( this );
        findViewById( R.id.btn_view_center ).setOnClickListener( this );
        findViewById( R.id.btn_cancellation_request ).setOnClickListener( this );
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
                intent = new Intent( HomeActivity.this, AddStationActivity.class );
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
            case R.id.btn_logout:{
                if (GetPrefs.getInstance().logout( getApplicationContext() )){
                    finish();
                }
            }
            break;
            case R.id.btn_view_frame_stock:{
                intent = new Intent( HomeActivity.this, ViewFrameStockActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_add_frame_stock:{
                intent = new Intent( HomeActivity.this, AddFrameStockActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_all_paper:{
                intent = new Intent( HomeActivity.this, ViewAllPaperActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_view_center:{
                intent = new Intent( HomeActivity.this, ViewAllStationActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_cancellation_request :{
                intent = new Intent( HomeActivity.this, ViewAllStationActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

}
