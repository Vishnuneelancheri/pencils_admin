package com.mainproject.vishnu_neelancheri.pencilsadmin.add_center;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.StationModel;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.ViewFrameStockModel;
import com.mainproject.vishnu_neelancheri.pencilsadmin.home.HomeActivity;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAllStationActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner spinnerStatio, spinnerFrame;
    private EditText edtFrameName, edtFramePrice;
    private Button btnEdtCntre, btnDisableCntre, btnSubmit;
    private  ArrayList<StationModel> mStationModelList;
    private List<FrameOfCenter> mFrameOfCentersList;
    private String idFramePrice="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_station);
        spinnerStatio = findViewById( R.id.spinner_station );
        spinnerFrame = findViewById( R.id.spinner_frame );
        edtFrameName = findViewById( R.id.edt_txt_frame_name );
        edtFramePrice = findViewById( R.id.edt_txt_frame_price );
        btnEdtCntre = findViewById( R.id.btn_edit_center );
        btnDisableCntre = findViewById( R.id.btn_disable );
        btnSubmit = findViewById( R.id.btn_submit );

        btnEdtCntre.setOnClickListener(this);
        btnDisableCntre.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);


        spinnerStatio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getFrameOfSpinner(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                PencilUtil.toaster(getApplicationContext(), "NO DATAd");
            }
        });
        spinnerFrame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edtFrameName.setText( mFrameOfCentersList.get(i).getFrameName() );
                edtFramePrice.setText( mFrameOfCentersList.get(i).getFramePrice() );
                idFramePrice = mFrameOfCentersList.get(i).getIdFramePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        initialSetup();
    }
    @Override
    public void onClick(View view){
        switch ( view.getId() ){
            case R.id.btn_edit_center:{
                editStation();
            }
            break;
            case R.id.btn_disable:{
                disableStation();
            }
            break;
            case R.id.btn_submit:{
                updateFramePrice();
            }
            break;
        }
    }
    private void initialSetup(){
        String url = PencilUtil.BASE_URL + "admin/get_all_station";
        NetWorkConnection.getInstance().volleyGetting(url, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    TempStationModel tempStationModel = new Gson().fromJson( response, TempStationModel.class );
                    if ( tempStationModel.getStationModelList().size() > 0 ){
                        setupStationSpinner(tempStationModel.getStationModelList());
                    }else {
                        PencilUtil.toaster(getApplicationContext(), "No data");
                        finish();
                    }
                }catch (Exception e){
                    PencilUtil.toaster(getApplicationContext(), "NO DATA");
                    finish();
                }
            }

            @Override
            public void onError(String errorMessage) {
                PencilUtil.toaster(getApplicationContext(), "NO DATA...");
                finish();
            }
        });
    }
    private void setupStationSpinner(ArrayList<StationModel> stationModelList){
       mStationModelList = stationModelList;
       ArrayList<String> tempList = new ArrayList<>();
        for (StationModel stationModel: stationModelList ) {
            tempList.add( stationModel.getStationName()+" ( "+stationModel.getStationCode() +" )" );
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_spinner_dropdown_item, tempList );
        spinnerStatio.setAdapter( arrayAdapter );

    }
    public void getFrameOfSpinner(int position){
        String stationId = mStationModelList.get(position).getStationId();
        String url = PencilUtil.BASE_URL+"admin/get_frame_of_center";
        Map<String,String> params = new HashMap<>();
        params.put("id_station", stationId);
        NetWorkConnection.getInstance().volleyPosting(url, params, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    Type listType = new TypeToken<List<FrameOfCenter>>(){}.getType();
                    List<FrameOfCenter>  frameOfCenterList = (List<FrameOfCenter>) new Gson().fromJson( response, listType);
                    mFrameOfCentersList = frameOfCenterList;
                    List<String> tempFrameOFcenteresList = new ArrayList<>();
                    for (FrameOfCenter frameOfCenter: mFrameOfCentersList   ) {
                        tempFrameOFcenteresList.add(frameOfCenter.frameName + " ( Rs."+ frameOfCenter.framePrice+")");
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( getBaseContext(),
                    android.R.layout.simple_spinner_dropdown_item, tempFrameOFcenteresList );
                    spinnerFrame.setAdapter(arrayAdapter);
                    /*spinnerStatio.setAdapter( arrayAdapter );*/
                }catch ( Exception e ){

                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
    public class TempStationModel{
        public ArrayList<StationModel> getStationModelList() {
            return stationModelList;
        }

        public void setStationModelList(ArrayList<StationModel> stationModelList) {
            this.stationModelList = stationModelList;
        }

        @SerializedName("work_station")
        private ArrayList<StationModel> stationModelList;
    }
    public class FrameOfCenter{
        @SerializedName("id_frame_price")
        private String idFramePrice;
        @SerializedName("frame_id")
        private String frameId;
        @SerializedName("price")
        private String framePrice;
        @SerializedName("station_id")
        private String stationId;
        @SerializedName("name")
        private String frameName;

        public String getFrameName() {
            return frameName;
        }

        public void setFrameName(String frameName) {
            this.frameName = frameName;
        }

        public String getIdFramePrice() {
            return idFramePrice;
        }

        public void setIdFramePrice(String idFramePrice) {
            this.idFramePrice = idFramePrice;
        }

        public String getFrameId() {
            return frameId;
        }

        public void setFrameId(String frameId) {
            this.frameId = frameId;
        }

        public String getFramePrice() {
            return framePrice;
        }

        public void setFramePrice(String framePrice) {
            this.framePrice = framePrice;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }
    }
    private void updateFramePrice(){
        if (edtFramePrice.getText().toString().isEmpty()){
            PencilUtil.toaster(getApplicationContext(), "Please enter data");
            return;
        }
        String url = PencilUtil.BASE_URL + "admin/update_frame";
        Map<String,String> param = new HashMap<>();
        param.put("id_frame_price", idFramePrice );
        param.put("frame_price", edtFramePrice.getText().toString() );
        NetWorkConnection.getInstance().volleyPosting(url, param, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                if ( !response.equals("0")){
                    PencilUtil.toaster(getApplicationContext(), "Succesfully updated");
                }else {
                    PencilUtil.toaster(getApplicationContext(), "Can't update");
                }
                finish();
            }

            @Override
            public void onError(String errorMessage) {
                PencilUtil.toaster(getApplicationContext(), "Can't update");
            }
        });
    }
    private void disableStation(){
        String url = PencilUtil.BASE_URL+ "admin/disable_station";
        Map<String,String> param = new HashMap<>();
        param.put("station_id", mStationModelList.get(spinnerStatio.getSelectedItemPosition()).getStationId());
        NetWorkConnection.getInstance().volleyPosting(url, param, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                if ( !response.equals("0 ") ){
                    PencilUtil.toaster(getApplicationContext(), "Succesfully updated");
                }else
                    PencilUtil.toaster(getApplicationContext(), "Can't update");
                finish();
            }

            @Override
            public void onError(String errorMessage) {
                PencilUtil.toaster(getApplicationContext(), "Can't update");
                finish();
            }
        });
    }
    private void editStation(){
        StationModel stationModel = mStationModelList.get(spinnerStatio.getSelectedItemPosition());
        Intent intent = new Intent( ViewAllStationActivity.this,EditStationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(getResources().getString(R.string.app_name), stationModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
