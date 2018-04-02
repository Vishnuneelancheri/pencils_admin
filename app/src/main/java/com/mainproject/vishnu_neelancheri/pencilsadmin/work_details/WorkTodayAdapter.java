package com.mainproject.vishnu_neelancheri.pencilsadmin.work_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;

import java.util.ArrayList;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 3/7/2018
 */

public class WorkTodayAdapter extends RecyclerView.Adapter<WorkTodayAdapter.WorkTodayHolder>{
    ArrayList<WorkModel  > workModelList  = new ArrayList<>();
    private SelectTodayWork selectTodayWork;
    private Context mContext;
    private boolean isPendingOnly;
    public static class WorkTodayHolder extends RecyclerView.ViewHolder{
        TextView txtBill, txtPhone, txtName, txtFrame, TxtPaper, txtCourier, txtTotal, txtBalnce;
        LinearLayout parent;
        public WorkTodayHolder(View view){
            super(view);
            txtBill = view.findViewById( R.id.txt_bill_no );
            txtName = view.findViewById( R.id.txt_bill_name);
            txtPhone = view.findViewById( R.id.txt_phone );
            txtFrame = view.findViewById( R.id.txt_frame_name);
            TxtPaper = view.findViewById( R.id.txt_paper_details );
            txtCourier = view.findViewById( R.id.txt_courier_charge );
            txtTotal = view.findViewById( R.id.total_price );
            txtBalnce = view.findViewById( R.id.txt_balance );
            parent = view.findViewById(R.id.parent);
        }
    }
    public WorkTodayAdapter (Context context,ArrayList<WorkModel  > workModelList , SelectTodayWork selectTodayWork , boolean isPendingOnly){


        this.isPendingOnly = isPendingOnly;

        if ( isPendingOnly ){
            for ( WorkModel workmodel: workModelList
                 ) {
                if ( workmodel.getBillPaymentStatus() == 1 )
                    this.workModelList.add(workmodel);
            }
        }else {
            this.workModelList = workModelList;
        }

        this.selectTodayWork = selectTodayWork;
        mContext = context;
    }
    @Override
    public int getItemCount(){
        return  workModelList.size();
    }
    public interface SelectTodayWork{
        void select(WorkModel WorkModel );
    }
    @Override
    public WorkTodayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_todays_work, parent, false);
        return  new WorkTodayHolder( view );
    }
    @Override
    public void onBindViewHolder(WorkTodayHolder workTodayHolder, int pos){
        int pstion = workTodayHolder.getAdapterPosition();
        final WorkModel WorkModel = workModelList.get( pstion );

        if ( WorkModel.getBillPaymentStatus() == 3 && isPendingOnly == true ){
            workTodayHolder.parent.setVisibility(View.INVISIBLE);
            workModelList.remove(pstion);
        }
        else if ( WorkModel.getBillPaymentStatus() == 2 && isPendingOnly == true ){
            workTodayHolder.parent.setVisibility(View.INVISIBLE);
            workModelList.remove(pstion);
        }
        else {
            workTodayHolder.txtBill.setText( WorkModel.getBillId() );
            workTodayHolder.txtName.setText( WorkModel.getCustName() );
            workTodayHolder.txtPhone.setText( WorkModel.getCustPhone() );
            workTodayHolder.txtFrame.setText(WorkModel.getFrameName() );
            workTodayHolder.TxtPaper.setText( WorkModel.getPaperCart() );
            workTodayHolder.txtCourier.setText( WorkModel.getCourierCharge() );
            workTodayHolder.txtTotal.setText( WorkModel.getTotalPrice() );
            workTodayHolder.txtBalnce.setText( WorkModel.getBalance() );
            workTodayHolder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectTodayWork.select( WorkModel );
                }
            });
            if ( WorkModel.getBillPaymentStatus() == 1 ){
                workTodayHolder.parent.setBackgroundColor( mContext.getResources().getColor( R.color.non_completion ));
            } else if ( WorkModel.getBillPaymentStatus() == 2 ){
                workTodayHolder.parent.setBackgroundColor( mContext.getResources().getColor( R.color.draw_complete ));
            }else if ( WorkModel.getBillPaymentStatus() == 3 ){
                workTodayHolder.parent.setBackgroundColor( mContext.getResources().getColor( R.color.completed ));
            }
        }

    }
}

