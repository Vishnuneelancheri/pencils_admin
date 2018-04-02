package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;
import com.mainproject.vishnu_neelancheri.pencilsadmin.work_details.RecyclerViewFrameStock;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViewFrameStockActivity extends AppCompatActivity {
    private RecyclerView recycerShowFrameStock;
    private RecyclerViewFrameStock recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_frame_stock);
        recycerShowFrameStock = findViewById( R.id.recycler_view_frame  );
        recycerShowFrameStock.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycerShowFrameStock.setLayoutManager( layoutManager );
        initialLoading();
    }

    private void initialLoading(){
        String url = PencilUtil.BASE_URL+"admin/get_frame_stock_balance";
        NetWorkConnection.getInstance().volleyGetting(url, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                Type listType = new TypeToken<List<ViewFrameStockModel>>(){}.getType();
                List<ViewFrameStockModel> viewFrameStockModelList = (List<ViewFrameStockModel>) new Gson().fromJson(response, listType );
                recyclerAdapter = new RecyclerViewFrameStock( viewFrameStockModelList );
                recycerShowFrameStock.setAdapter( recyclerAdapter );
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
