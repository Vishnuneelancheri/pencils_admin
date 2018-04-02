package com.mainproject.vishnu_neelancheri.pencilsadmin.add_paper;

import android.content.Context;
import android.renderscript.AllocationAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;

import java.util.ArrayList;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 3/11/2018
 */

public class RecyclerAllPaper extends RecyclerView.Adapter<RecyclerAllPaper.AllPaperViewHolder>{
    ArrayList<AllPaperModel> allPaperModelList;
    Context context;
    SelectPaper selectPaper;
    public RecyclerAllPaper(ArrayList<AllPaperModel> allPaperModelList, Context context , SelectPaper selectPaper){
        this.allPaperModelList = allPaperModelList;
        this.context = context;
        this.selectPaper = selectPaper;
    }
    public static class AllPaperViewHolder extends RecyclerView.ViewHolder{
        TextView txtPaperName, txtCartoonPrice, txtPortraitPrice;
        LinearLayout linearParent;
        Button btnEdt;
        public AllPaperViewHolder(View view){
            super(view);
            txtPaperName = view.findViewById(R.id.txt_paper_name);
            txtCartoonPrice = view.findViewById( R.id.txt_cartoon_price );
            txtPortraitPrice = view.findViewById( R.id.txt_portrait_price );
            linearParent = view.findViewById( R.id.parent );
            btnEdt = view.findViewById(R.id.btn_edit );
        }
    }
    @Override
    public int getItemCount(){
        return  allPaperModelList.size();
    }
    @Override
    public AllPaperViewHolder onCreateViewHolder(ViewGroup viewGroup, int parent ){
        View view = LayoutInflater.from(viewGroup.getContext() ).inflate(R.layout.recycler_all_paper, viewGroup, false );
        return new AllPaperViewHolder( view );
    }
    @Override
    public void onBindViewHolder(AllPaperViewHolder allPaperViewHolder, int position){
        int pos = allPaperViewHolder.getAdapterPosition();
        final AllPaperModel allPaperModel = allPaperModelList.get(pos );
        allPaperViewHolder.txtPaperName.setText(allPaperModel.getPaperName());
        allPaperViewHolder.txtCartoonPrice.setText( allPaperModel.getPaperCartoonPrice() );
        allPaperViewHolder.txtPortraitPrice.setText( allPaperModel.getPaperPortraitPrice());
        allPaperViewHolder.btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPaper.Select( allPaperModel );
            }
        });
    }
    public interface SelectPaper{
        void Select(AllPaperModel allPaperModel);
    }
}
