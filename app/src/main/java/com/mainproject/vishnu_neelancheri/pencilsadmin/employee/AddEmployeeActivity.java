package com.mainproject.vishnu_neelancheri.pencilsadmin.employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.add_center.ViewAndSelectStationActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.StationModel;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.GetPrefs;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PrefModel;

import java.util.HashMap;
import java.util.Map;

public class AddEmployeeActivity extends AppCompatActivity implements View.OnClickListener{
    private final int SELECT_STATION = 100;
    private StationModel mStationModel;
    private String jobId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        findViewById(R.id.btn_select_station).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        getJobid();
    }
    private void getJobid(){
        String url = PencilUtil.BASE_URL+"admin/request_job_id";
        NetWorkConnection.getInstance().volleyGetting(url, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                jobId = response;
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("mes", errorMessage);
                finish();
            }
        });
    }
    @Override
    public void onClick(View view ){
        switch ( view.getId() ){
            case R.id.btn_select_station:{
                Intent intent = new Intent( AddEmployeeActivity.this, ViewAndSelectStationActivity.class);
                startActivityForResult( intent, SELECT_STATION);
            }
            break;
            case R.id.btn_submit:{
                submitData();
            }
            break;
            default:break;
        }
    }

    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent data ){
        if ( requestCode == SELECT_STATION && resultCode == RESULT_OK ){
            Bundle bundle = data.getExtras();
            mStationModel = bundle.getParcelable( getResources().getString(R.string.app_name ));
            if ( mStationModel != null ){
                TextView txtStationName = findViewById( R.id.txt_center_name);
                TextView txtStationCode = findViewById(R.id.txt_center_code);

                txtStationName.setText( mStationModel.getStationName() );
                txtStationCode.setText( mStationModel.getStationCode() );
            }else{
                PencilUtil.toaster( AddEmployeeActivity.this, "No data");
            }
        }else {
            PencilUtil.toaster( AddEmployeeActivity.this, "No data");
        }
    }
    private void submitData(){
        if ( mStationModel == null || mStationModel.getStationId() == null ){
            PencilUtil.toaster( this, "No station choosed");
            return;
        }
        String url = PencilUtil.BASE_URL + "admin/add_employee";

        EditText edtTxtName = findViewById( R.id.edit_txt_name);
        EditText edtTxtPhone = findViewById( R.id.edit_txt_phone );
        EditText edtTxtPassword = findViewById( R.id.edit_txt_password );

        String name = edtTxtName.getText().toString();
        String phone = edtTxtPhone.getText().toString();
        String password = edtTxtPassword.getText().toString();
        String stationId = mStationModel.getStationId();

        PrefModel prefModel = GetPrefs.getInstance().getSharedPref( this );

        if ( prefModel == null ){
            PencilUtil.toaster( this, "App error");
            return;
        }
        Map< String, String > params = new HashMap<>();
        params.put("admin_token", prefModel.getToken() );
        params.put("admin_id", prefModel.getAdminId() );
        params.put("job_id", jobId );

        params.put("employee_name", name );
        params.put("employee_phone", phone );
        params.put("employee_password", password );
        params.put("employee_station_id", mStationModel.getStationId() );

        NetWorkConnection.getInstance().volleyPosting(url, params, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    AddEmployeeResoponseModel addEmployeeResoponseModel = new Gson().fromJson(
                            response, AddEmployeeResoponseModel.class
                    );
                    PencilUtil.toaster( AddEmployeeActivity.this , addEmployeeResoponseModel.getMessage() );
                    finish();
                }catch ( Exception e ){
                    PencilUtil.toaster( AddEmployeeActivity.this , e.toString() );
                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }
}
