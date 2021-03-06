package com.mainproject.vishnu_neelancheri.pencilsadmin.add_paper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.add_center.AddStationModel;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.GetPrefs;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PrefModel;

import java.util.HashMap;
import java.util.Map;

public class AddPaperActivity extends AppCompatActivity implements View.OnClickListener{
    private String jobId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paper);

        findViewById(R.id.btn_submit_paper).setOnClickListener( this );
        getJobId();
    }

    @Override
    public void onClick(View view){
        addPaper();
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
    private void addPaper(){

        EditText edtPaperName = findViewById( R.id.edit_txt_paper_name );
        EditText edtPaperPortraitPrice = findViewById(R.id.edit_txt_portrait_price);
        EditText edtTxtPaperCartoonPrice = findViewById( R.id.edit_txt__cartoon_price);

        String paperName = edtPaperName.getText().toString();
        String porttratiPrice = edtPaperPortraitPrice.getText().toString();
        String cartoonPrice = edtTxtPaperCartoonPrice.getText().toString();

        PrefModel prefModel = GetPrefs.getInstance().getSharedPref( this );

        String url = PencilUtil.BASE_URL+"admin/add_paper";
        Map<String, String> params = new HashMap<>();
        params.put("paper_name", paperName );
        params.put("paper_cartoon_price", cartoonPrice);
        params.put("paper_portrait_price", porttratiPrice);
        params.put("admin_id", prefModel.getAdminId());
        params.put("admin_token", prefModel.getToken());
        params.put("job_id", jobId );
        NetWorkConnection.getInstance().volleyPosting(url, params, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    AddStationModel addStationModel = new Gson().fromJson( response, AddStationModel.class );

                    PencilUtil.toaster(getApplicationContext(), addStationModel.getMessage());
                    finish();
                }catch ( Exception e){

                }
                Log.e("mes", response);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("mes", errorMessage);
            }
        });
    }
}
