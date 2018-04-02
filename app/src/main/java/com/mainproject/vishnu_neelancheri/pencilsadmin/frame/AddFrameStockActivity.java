package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.StatusMessageReturn;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.GetPrefs;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PrefModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFrameStockActivity extends AppCompatActivity {
    private Spinner spinnerFrame, spinnerStation;
    private EditText edtTxtQty;
    private FrameStockAddInitialSetUp frameStockAddInitialSetUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_frame_stock);
        spinnerFrame = findViewById( R.id.spinner_frame );
        spinnerStation = findViewById( R.id.spinner_center );
        edtTxtQty = findViewById( R.id.edt_qty );
        initialSetupment();
        findViewById( R.id.btn_submit ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }
    private void initialSetupment(){
        String url = PencilUtil.BASE_URL + "admin/get_framemaster_station";
        NetWorkConnection.getInstance().volleyGetting(url, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    frameStockAddInitialSetUp = new Gson().fromJson( response, FrameStockAddInitialSetUp.class );
                     populateSpinner( frameStockAddInitialSetUp );
                }catch (Exception e ){
                    PencilUtil.toaster( getApplicationContext(),"No data");
                    finish();
                }
            }

            @Override
            public void onError(String errorMessage) {
                PencilUtil.toaster( getApplicationContext(),"No data available");
                finish();
            }
        });
    }
    private void populateSpinner(FrameStockAddInitialSetUp frameStockAddInitialSetUp){
        ArrayList<String> frameNameList = new ArrayList<>();
        for ( FrameModel frameModel : frameStockAddInitialSetUp.getFrameList()
             ) {
            frameNameList.add( frameModel.getFrameName() );
        }
        ArrayList<String> stationList = new ArrayList<>();
        for ( StationModel stationModel : frameStockAddInitialSetUp.getStationList()
                ) {
            stationList.add( stationModel.getStationName()  );
        }
        ArrayAdapter<String> adapterFrame = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, frameNameList);
        spinnerFrame.setAdapter( adapterFrame );
        ArrayAdapter<String> adapterStation = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, stationList );
        spinnerStation.setAdapter( adapterStation );

    }
    private void submit(){
        String qty = edtTxtQty.getText().toString();
        if ( qty.isEmpty() ){
            PencilUtil.toaster( this, "Please enter quantity ");
            return;
        }
        String url = PencilUtil.BASE_URL+"admin/add_frame_stock";
        PrefModel prefModel = GetPrefs.getInstance().getSharedPref(this);
        Map<String,String> param = new HashMap<>();
        param.put("admin_token", prefModel.getToken() );
        param.put("admin_id", prefModel.getAdminId() );
        param.put("job_id", frameStockAddInitialSetUp.getJobId() );
        int framePosition = spinnerFrame.getSelectedItemPosition();
        int stationPosition = spinnerStation.getSelectedItemPosition();
        param.put("station_id", frameStockAddInitialSetUp.getStationList().get(stationPosition).getStationId() );
        param.put("frame_id", frameStockAddInitialSetUp.getFrameList().get(framePosition).getFrameMasterId() );
        param.put("qty", qty );
        NetWorkConnection.getInstance().volleyPosting(url, param, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    StatusMessageReturn statusMessageReturn = new Gson().fromJson( response, StatusMessageReturn.class );
                    PencilUtil.toaster(getApplicationContext(), statusMessageReturn.getMessage() );
                    finish();
                }catch ( Exception e ){
                    PencilUtil.toaster(getApplicationContext(), "Server error.." );
                    finish();
                }
            }

            @Override
            public void onError(String errorMessage) {
                PencilUtil.toaster(getApplicationContext(), "Server No data" );
                finish();
            }
        });
    }

}
