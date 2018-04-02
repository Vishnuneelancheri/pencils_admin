package com.mainproject.vishnu_neelancheri.pencilsadmin.add_center;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.StationModel;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.GetPrefs;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PrefModel;

import java.util.HashMap;
import java.util.Map;

public class EditStationActivity extends AppCompatActivity {
    StationModel stationModel  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_center);
        EditText edtCenterName = findViewById(R.id.edt_txt_center_name);
        EditText edtCenterCode = findViewById(R.id.edt_txt_center_code);

        Bundle bundle = getIntent().getExtras();
        stationModel = bundle.getParcelable( getResources().getString(R.string.app_name));
        if ( stationModel == null)
            finish();
        edtCenterName.setText(stationModel.getStationName());
        edtCenterCode.setText(stationModel.getStationCode());
        findViewById( R.id.btn_submit_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCenter();
            }
        });
    }

    private void addCenter(){
        String url = PencilUtil.BASE_URL+"admin/edit_center";

        EditText edtCenterName = findViewById(R.id.edt_txt_center_name);
        EditText edtCenterCode = findViewById(R.id.edt_txt_center_code);

        String centerName = edtCenterName.getText().toString();
        String centerCode = edtCenterCode.getText().toString();

        PrefModel prefModel = GetPrefs.getInstance().getSharedPref(this);

        Map<String, String> params = new HashMap<>();

        params.put("station_name", centerName);
        params.put("station_code", centerCode);
        params.put("station_id", stationModel.getStationId());
        params.put("admin_id", prefModel.getAdminId());
        params.put("admin_token", prefModel.getToken());

        NetWorkConnection.getInstance().volleyPosting(url, params, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                if ( !response.equals("0") ){
                    PencilUtil.toaster(getApplicationContext(), "Updatied successfully");
                }
                else
                    PencilUtil.toaster(getApplicationContext(),"Can't update");
                finish();
            }

            @Override
            public void onError(String errorMessage) {
                PencilUtil.toaster(getApplicationContext(),"Can't update");
                finish();
            }
        });

    }

}
