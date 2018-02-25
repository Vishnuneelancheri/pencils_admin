package com.mainproject.vishnu_neelancheri.pencilsadmin.employee;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;

import java.util.List;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 2/24/2018
 **/

public class RecyclerAdapterViewAllEmployee extends RecyclerView.Adapter<RecyclerAdapterViewAllEmployee.ViewAllEmployeeHolder> {
    private List<EmployeeModel> mEmployeeModelList;
    private SelectEmployee mSelectEmployee;
    public RecyclerAdapterViewAllEmployee(List<EmployeeModel> employeeModelList, SelectEmployee selectEmployee ){
        this.mEmployeeModelList = employeeModelList;
        this.mSelectEmployee = selectEmployee;
    }
    public static class ViewAllEmployeeHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtEmpPhone, txtEmpStationCode;
        private Button btnViework, btnPendingWork, btnTempDisable, btnPermenentlyDisable;
        private LinearLayout linearParent;
        public ViewAllEmployeeHolder(View view ){
            super( view );
            txtName = view.findViewById( R.id.txt_employee_name );
            txtEmpPhone = view.findViewById( R.id.txt_employee_phone );
            txtEmpStationCode = view.findViewById( R.id.txt_employee_station_code );

            btnViework = view.findViewById( R.id.btn_view_work );
            btnPendingWork = view.findViewById( R.id.btn_viw_pending );
            btnTempDisable = view.findViewById( R.id.btn_temp_disable );
            btnPermenentlyDisable = view.findViewById( R.id.btn_permenent_disable );

            linearParent = view.findViewById( R.id.parent );
        }
    }
    @Override
    public int getItemCount(){
        return mEmployeeModelList.size();
    }
    @Override
    public ViewAllEmployeeHolder onCreateViewHolder(ViewGroup parent, int type){
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.recycle_view_employee , parent, false );
        return new ViewAllEmployeeHolder( view );
    }
    @Override
    public void onBindViewHolder( ViewAllEmployeeHolder viewAllEmployeeHolder , int pos ){
        int i = viewAllEmployeeHolder.getAdapterPosition();
        final EmployeeModel employeeModel = mEmployeeModelList.get(i);
        viewAllEmployeeHolder.txtName.setText( employeeModel.getName() );
        viewAllEmployeeHolder.txtEmpPhone.setText( employeeModel.getMobilePhone() );
        viewAllEmployeeHolder.txtEmpStationCode.setText( employeeModel.getStationCode() );
        switch ( employeeModel.getActivationStatus() ){
            case "1":{
                viewAllEmployeeHolder.btnTempDisable.setText("Temp disable");
            }break;
            case "2":{
                viewAllEmployeeHolder.btnTempDisable.setText("Enable");
            }break;
            case "3":{
                viewAllEmployeeHolder.btnPermenentlyDisable.setText("Permenently disabled");
                viewAllEmployeeHolder.btnPermenentlyDisable.setEnabled(false);
                viewAllEmployeeHolder.btnTempDisable.setVisibility(View.GONE);
            }break;
            default:break;
        }
        viewAllEmployeeHolder.btnViework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectEmployee.onViewWork( employeeModel );
            }
        });
        viewAllEmployeeHolder.btnPendingWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectEmployee.onPendingWork( employeeModel );
            }
        });
        viewAllEmployeeHolder.btnTempDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectEmployee.onTempDisable( employeeModel );
            }
        });
        viewAllEmployeeHolder.btnPermenentlyDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectEmployee.onPermenentDisable( employeeModel );
            }
        });
        viewAllEmployeeHolder.linearParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectEmployee.onSelect( employeeModel );
            }
        });
    }

    public interface SelectEmployee{
        void onSelect(EmployeeModel employeeModel );
        void onViewWork(EmployeeModel employeeModel );
        void onPendingWork(EmployeeModel employeeModel );
        void onTempDisable(EmployeeModel employeeModel );
        void onPermenentDisable(EmployeeModel employeeModel );
    }
}