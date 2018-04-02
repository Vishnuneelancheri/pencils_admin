package com.mainproject.vishnu_neelancheri.pencilsadmin.add_paper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.employee.EmployeeModel;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewAllPaperActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllPaper;
    RecyclerAllPaper recyclerAllPaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_paper);
        recyclerViewAllPaper = findViewById( R.id.recycler_all_paper );
        recyclerViewAllPaper.setHasFixedSize( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAllPaper.setLayoutManager(layoutManager);
        initialSetUp();
    }
    private void initialSetUp(){
        String url = PencilUtil.BASE_URL+"admin/get_all_paper";
        NetWorkConnection.getInstance().volleyGetting(url, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    Type type = new TypeToken< ArrayList<AllPaperModel> >(){}.getType();
                    ArrayList<AllPaperModel> allPaperModelArrayList = new GsonBuilder().create().fromJson(response, type );
                    if ( allPaperModelArrayList.size() > 0){
                        recyclerAllPaper = new RecyclerAllPaper(allPaperModelArrayList, getApplicationContext(), new RecyclerAllPaper.SelectPaper() {
                            @Override
                            public void Select(AllPaperModel allPaperModel) {
                                Intent intent = new Intent( ViewAllPaperActivity.this, EditPaperActivity.class );
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(getResources().getString(R.string.app_name), allPaperModel);
                                intent.putExtras( bundle );
                                startActivity( intent );
                            }
                        });
                        recyclerViewAllPaper.setAdapter( recyclerAllPaper );
                    }else {
                        PencilUtil.toaster( getApplicationContext(), "No data");
                    }
                }catch ( Exception e){
                    PencilUtil.toaster( getApplicationContext(), "No data");
                }
            }

            @Override
            public void onError(String errorMessage) {
                PencilUtil.toaster( getApplicationContext(), errorMessage );
            }
        });
    }
}
