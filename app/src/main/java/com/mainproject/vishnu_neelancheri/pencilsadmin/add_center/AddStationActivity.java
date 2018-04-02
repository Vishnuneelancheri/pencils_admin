package com.mainproject.vishnu_neelancheri.pencilsadmin.add_center;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.GetPrefs;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PrefModel;

import java.util.HashMap;
import java.util.Map;

public class AddStationActivity extends AppCompatActivity {
    private String jobId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_center);
        getJobId();
        findViewById( R.id.btn_submit_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCenter();
            }
        });
    }
    private void getJobId(){
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
    private void addCenter(){
        String url = PencilUtil.BASE_URL+"admin/add_workstation";

        EditText edtCenterName = findViewById(R.id.edt_txt_center_name);
        EditText edtCenterCode = findViewById(R.id.edt_txt_center_code);

        String centerName = edtCenterName.getText().toString();
        String centerCode = edtCenterCode.getText().toString();

        PrefModel prefModel = GetPrefs.getInstance().getSharedPref(this);

        Map<String, String> params = new HashMap<>();

        params.put("station_name", centerName);
        params.put("station_code", centerCode);
        params.put("job_id", jobId);
        params.put("admin_id", prefModel.getAdminId());
        params.put("admin_token", prefModel.getToken());

        NetWorkConnection.getInstance().volleyPosting(url, params, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                try{
                    AddStationModel addStationModel = gson.fromJson( response, AddStationModel.class );
                    PencilUtil.toaster( AddStationActivity.this, addStationModel.getMessage() );
                    if ( addStationModel.getStatus() == 1 ){
                        finish();
                    }
                }catch (Exception e){
                    PencilUtil.toaster( AddStationActivity.this, e.toString());
                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }

}
