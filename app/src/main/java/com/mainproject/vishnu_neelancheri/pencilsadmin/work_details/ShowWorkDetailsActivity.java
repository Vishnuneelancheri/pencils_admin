package com.mainproject.vishnu_neelancheri.pencilsadmin.work_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;

import java.util.ArrayList;

public class ShowWorkDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewShowWork;
    private ArrayList<WorkModel> workModelList;
    Boolean isPendingOnly = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_work_details);
        recyclerViewShowWork = findViewById( R.id.recycler_show_work );
        recyclerViewShowWork.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewShowWork.setLayoutManager( layoutManager );
        try{
            Bundle bundle = getIntent().getExtras();
            isPendingOnly = bundle.getBoolean("isPendingOnly" );
            workModelList = bundle.getParcelableArrayList(getResources().getString(R.string.app_name ));
            WorkTodayAdapter workTodayAdapter = new WorkTodayAdapter(getApplicationContext(), workModelList, new WorkTodayAdapter.SelectTodayWork() {
                @Override
                public void select(WorkModel WorkModel) {

                }
            }, isPendingOnly);
            recyclerViewShowWork.setAdapter( workTodayAdapter );
        }catch ( NullPointerException ignored ){

        }
    }

}
