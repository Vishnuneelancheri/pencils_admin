package com.mainproject.vishnu_neelancheri.pencilsadmin.add_center;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.JobWithStationModel;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.StationModel;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;

import java.util.HashMap;

public class ViewAndSelectStationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewAndSelectStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_select_station);
        mRecyclerViewAndSelectStation = findViewById(R.id.recycler_view_selection_station );
        mRecyclerViewAndSelectStation.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewAndSelectStation.setLayoutManager(layoutManager);
        getStationList();
    }   
    public void getStationList(){
        String url = PencilUtil.BASE_URL + "admin/get_all_station";
        NetWorkConnection.getInstance().volleyPosting(url, new HashMap<String, String>(), ViewAndSelectStationActivity.this,
                new NetworkResponse() {
                    @Override
                    public void onSuccess(String response) {
                       populateRecycler( response );
                    }

                    @Override
                    public void onError(String errorMessage) {
                        PencilUtil.toaster( ViewAndSelectStationActivity.this, errorMessage );
                        finish();
                    }
                });
    }
    private void populateRecycler(String response){
        try{
            JobWithStationModel jobWithStationModel = new Gson().fromJson( response, JobWithStationModel.class );
            if ( jobWithStationModel.getStationModelList().size() > 0 ){
                RecyclerVIewAndSelectStation recyclerVIewAndSelectStation =
                        new RecyclerVIewAndSelectStation(jobWithStationModel.getStationModelList(), new RecyclerVIewAndSelectStation.Select() {
                            @Override
                            public void onSelect(StationModel stationModel) {
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putParcelable( getResources().getString(R.string.app_name), stationModel);
                                intent.putExtras( bundle );
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        });
                mRecyclerViewAndSelectStation.setAdapter( recyclerVIewAndSelectStation );
            }
        }catch ( Exception e ){
            PencilUtil.toaster( ViewAndSelectStationActivity.this,"No data" );
            finish();
        }
    }
}
