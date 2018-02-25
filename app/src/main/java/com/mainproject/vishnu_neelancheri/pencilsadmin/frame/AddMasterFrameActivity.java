package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddMasterFrameActivity extends AppCompatActivity implements View.OnClickListener{
    private String jobId = "";
    ArrayList< StationModel > staionModelList;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_master_frame);

        findViewById( R.id.btn_submit).setOnClickListener(this);
        mRecyclerView = findViewById( R.id.recycler_show_frame_form );
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        getJobIdWithStation();
    }
    private void getJobIdWithStation(){
        String url = PencilUtil.BASE_URL+"admin/get_all_station_with_jobid";
        NetWorkConnection.getInstance().volleyGetting(url, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response){
                Gson gson = new Gson();
                try{
                    JobWithStationModel jobWithStationModel = gson.fromJson( response, JobWithStationModel.class );
                    jobId = jobWithStationModel.getJobId();
                    staionModelList = jobWithStationModel.getStationModelList();
                    viewForm( staionModelList );
                }catch ( Exception e ){
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("mes", errorMessage);
                finish();
            }
        });

    }

    @Override
    public void onClick(View view){
        if ( jobId.isEmpty() ){
            PencilUtil.toaster( AddMasterFrameActivity.this, "No Job id");

        }else {
            String url = PencilUtil.BASE_URL+"admin/add_frame_price";
            if ( staionModelList.size() < 0 ){
                PencilUtil.toaster( this, "No data");
                return;
            }
            for ( int i = 0; i < staionModelList.size(); i++ ){
                View memberView = mRecyclerView.getChildAt(i);
                EditText edtTxtPrice = memberView.findViewById( R.id.edt_txt_price );
                staionModelList.get( i ).setPrice( edtTxtPrice.getText().toString() );
            }
            String priceDetails = "";
            try{
                priceDetails = new Gson().toJson(staionModelList);
                Log.d("","");
            }catch ( Exception e ){
                PencilUtil.toaster( AddMasterFrameActivity.this, "Error on data");
                return;
            }
            EditText edtTxt = findViewById( R.id.edt_txt_frame_name );
            String tempFrameName = edtTxt.getText().toString();

            PrefModel prefModel = GetPrefs.getInstance().getSharedPref(this);

            Map<String, String> params = new HashMap<>();
            params.put("frame_name", tempFrameName );
            params.put("admin_token", prefModel.getToken());
            params.put("admin_id", prefModel.getAdminId() );
            params.put("price_details", priceDetails );
            params.put("job_id", jobId);

            NetWorkConnection.getInstance().volleyPosting( url, params, this, new NetworkResponse() {
                @Override
                public void onSuccess(String response) {
                    Gson gson = new Gson();
                    try{
                        AddMasterFrameAddResponseModel addMasterFrameAddResponseModel = gson.fromJson( response, AddMasterFrameAddResponseModel.class);
                        PencilUtil.toaster( AddMasterFrameActivity.this, addMasterFrameAddResponseModel.getMessage() );
                        finish();
                    }catch ( Exception e ){

                    }
                }

                @Override
                public void onError(String errorMessage) {

                }
            });

        }
    }
    private void viewForm( ArrayList <StationModel > stationModelList ){
        RecyclerViewFormAdapter recyclerViewFormAdapter = new RecyclerViewFormAdapter( stationModelList );
        mRecyclerView.setAdapter( recyclerViewFormAdapter );

    }
}
