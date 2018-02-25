package com.mainproject.vishnu_neelancheri.pencilsadmin.add_center;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.StationModel;

import java.util.ArrayList;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 2/24/2018
 */

public class RecyclerVIewAndSelectStation extends RecyclerView.Adapter<RecyclerVIewAndSelectStation.ViewStationHolder> {
    private Select mSelect;
    ArrayList<StationModel> mStationModelList;
    public static class ViewStationHolder extends RecyclerView.ViewHolder{
        TextView txtStationName, txtStationCode;
        LinearLayout linearParent;
        public ViewStationHolder(View view ){
            super(view);
            txtStationName = view.findViewById( R.id.txt_station_name );
            txtStationCode = view.findViewById( R.id.txt_station_code );
            linearParent = view.findViewById( R.id.parent );
        }
    }
    public RecyclerVIewAndSelectStation( ArrayList<StationModel> stationModelList, Select select ){
        this.mStationModelList = stationModelList;
        mSelect = select;
    }
    @Override
    public ViewStationHolder onCreateViewHolder(ViewGroup parent , int viewType ){
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.recycler_view_selection_station, parent, false);
        return  new ViewStationHolder( view );
    }
    @Override
    public int getItemCount(){
        return  mStationModelList.size();
    }
    @Override
    public void onBindViewHolder( ViewStationHolder viewStationHolder, int position){
        int pos = viewStationHolder.getAdapterPosition();
        final StationModel stationModel = mStationModelList.get( pos );
        viewStationHolder.txtStationName.setText( stationModel.getStationName());
        viewStationHolder.txtStationCode.setText( stationModel.getStationCode());
        viewStationHolder.linearParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelect.onSelect( stationModel );
            }
        });
    }
    public interface Select{
        void onSelect( StationModel stationModel );
    }

}
