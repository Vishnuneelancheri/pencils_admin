package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;

import java.util.ArrayList;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 2/24/2018
 */

public class RecyclerViewFormAdapter extends RecyclerView.Adapter<RecyclerViewFormAdapter.ViewFormHolder>{
    private ArrayList<StationModel> stationModelList;
    public static class ViewFormHolder extends RecyclerView.ViewHolder{
        TextView txtViewCenter;
        EditText edtTxtPrice;
        public ViewFormHolder(View view ){
            super(view);
            txtViewCenter = view.findViewById(R.id.txt_center_name);
            edtTxtPrice = view.findViewById(R.id.edt_txt_price);
        }

    }
    public RecyclerViewFormAdapter(ArrayList<StationModel> stationModelList){
        this.stationModelList = stationModelList;
    }
    @Override
    public int getItemCount(){
        return stationModelList.size();
    }

    @Override
    public ViewFormHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.recycler_view_frame_form , parent, false );
        return  new ViewFormHolder( view );
    }

    @Override
    public void onBindViewHolder(ViewFormHolder viewFormHolder, int position ){
        int pos = viewFormHolder.getAdapterPosition();
        viewFormHolder.txtViewCenter.setText( stationModelList.get(pos).getStationName()  );

    }

}
