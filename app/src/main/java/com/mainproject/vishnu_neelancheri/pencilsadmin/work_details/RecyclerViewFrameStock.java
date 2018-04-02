package com.mainproject.vishnu_neelancheri.pencilsadmin.work_details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.RecyclerViewFormAdapter;
import com.mainproject.vishnu_neelancheri.pencilsadmin.frame.ViewFrameStockModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 3/11/2018
 */

public class RecyclerViewFrameStock extends RecyclerView.Adapter<RecyclerViewFrameStock.ViewFrameHolder>{
    private List<ViewFrameStockModel> viewFrameStockModelList;
    public RecyclerViewFrameStock(List<ViewFrameStockModel> viewFrameStockModelList){
        this.viewFrameStockModelList = viewFrameStockModelList;
    }
    public static class ViewFrameHolder extends RecyclerView.ViewHolder {
        TextView txtStationName, txtFrameName, txtBalance;
        public ViewFrameHolder(View view ){
            super(view);
            txtStationName = view.findViewById( R.id.txt_station );
            txtFrameName = view.findViewById( R.id.txt_frame_name );
            txtBalance = view.findViewById( R.id.txt_balance );
        }
    }
    @Override
    public int getItemCount(){
        return viewFrameStockModelList.size();
    }

    @Override
    public RecyclerViewFrameStock.ViewFrameHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.recycler_view_frame_stock , parent, false );
        return  new ViewFrameHolder( view );
    }

    @Override
    public void onBindViewHolder(ViewFrameHolder viewFrameHolder, int position ){
        int pos = viewFrameHolder.getAdapterPosition();
        ViewFrameStockModel viewFrameStockModel = viewFrameStockModelList.get(pos);
        viewFrameHolder.txtStationName.setText(viewFrameStockModel.getStationName());
        viewFrameHolder.txtFrameName.setText( viewFrameStockModel.getFrameName() );
        viewFrameHolder.txtBalance.setText( viewFrameStockModel.getBalance() );
    }
}
