package com.mainproject.vishnu_neelancheri.pencilsadmin.add_paper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.GetPrefs;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PrefModel;

import java.util.HashMap;
import java.util.Map;

public class EditPaperActivity extends AppCompatActivity implements View.OnClickListener{
    private String jobId = "";
    private AllPaperModel allPaperModel = new AllPaperModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_paper);
        findViewById(R.id.btn_submit_paper).setOnClickListener( this );
        Bundle bundle = getIntent().getExtras();
        allPaperModel = bundle.getParcelable(getResources().getString(R.string.app_name));
        EditText edtPaperName = findViewById( R.id.edit_txt_paper_name );
        EditText edtPaperPortraitPrice = findViewById(R.id.edit_txt_portrait_price);
        EditText edtTxtPaperCartoonPrice = findViewById( R.id.edit_txt__cartoon_price);
        edtPaperName.setText( allPaperModel.getPaperName() );
        edtPaperPortraitPrice.setText( allPaperModel.getPaperPortraitPrice() );
        edtTxtPaperCartoonPrice.setText( allPaperModel.getPaperCartoonPrice() );
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

        String url = PencilUtil.BASE_URL+"admin/update_paper_paper";
        Map<String, String> params = new HashMap<>();
        params.put("paper_name", paperName );
        params.put("paper_cartoon_price", cartoonPrice);
        params.put("paper_portrait_price", porttratiPrice);
        params.put("id_paper_cartoon_price", allPaperModel.getIdPaperCartoonPrice());
        params.put("id_paper_portrait_price", allPaperModel.getIdPaperPortraitPrice() );
        params.put("id_paper", allPaperModel.getPaperId() );
        params.put("admin_id", prefModel.getAdminId());
        params.put("admin_token", prefModel.getToken());
        params.put("job_id", jobId );
        NetWorkConnection.getInstance().volleyPosting(url, params, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    if ( !response.equals("0"))
                        PencilUtil.toaster(getApplicationContext(), "Updated Successfully");
                    else PencilUtil.toaster( getApplicationContext(), "Updation failed");
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
