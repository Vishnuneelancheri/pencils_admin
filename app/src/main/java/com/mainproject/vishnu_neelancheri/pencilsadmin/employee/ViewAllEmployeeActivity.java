package com.mainproject.vishnu_neelancheri.pencilsadmin.employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mainproject.vishnu_neelancheri.pencilsadmin.R;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.GetPrefs;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetWorkConnection;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.NetworkResponse;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PencilUtil;
import com.mainproject.vishnu_neelancheri.pencilsadmin.utils.PrefModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewAllEmployeeActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<EmployeeModel> mEmployeeModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);

        mRecyclerView = findViewById( R.id.recycler_view_employee);
        mRecyclerView.setHasFixedSize( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        getAllEmployee();
    }
    private void getAllEmployee(){
        String url = PencilUtil.BASE_URL + "admin/get_all_employee";
        NetWorkConnection.getInstance().volleyPosting(url, new HashMap<String, String>(), this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                try{
                    Type type = new TypeToken< ArrayList<EmployeeModel> >(){}.getType();
                    mEmployeeModelList = new GsonBuilder().create().fromJson(response, type);
                    if ( mEmployeeModelList.size() < 1 ){
                        PencilUtil.toaster( ViewAllEmployeeActivity.this , "No data got" );
                        finish();
                        return;
                    }
                    populateRecycler( mEmployeeModelList );

                }catch ( Exception e ){
                    PencilUtil.toaster( ViewAllEmployeeActivity.this , e.toString() );
                    finish();
                }
            }

            @Override
            public void onError(String errorMessage) {
                PencilUtil.toaster( ViewAllEmployeeActivity.this , "No data got" );
                finish();
            }
        });
    }
    public void populateRecycler( ArrayList<EmployeeModel> employeeModelList ){
        RecyclerAdapterViewAllEmployee recyclerAdapterViewAllEmployee = new RecyclerAdapterViewAllEmployee(
                employeeModelList, new RecyclerAdapterViewAllEmployee.SelectEmployee() {
            @Override
            public void onSelect(EmployeeModel employeeModel) {

            }

            @Override
            public void onViewWork(EmployeeModel employeeModel) {

            }

            @Override
            public void onPendingWork(EmployeeModel employeeModel) {

            }

            @Override
            public void onTempDisable(EmployeeModel employeeModel) {
                tempDisable( employeeModel );
            }

            @Override
            public void onPermenentDisable(EmployeeModel employeeModel) {
                permenentlyDisable( employeeModel );
            }
        }
        );
        mRecyclerView.setAdapter( recyclerAdapterViewAllEmployee );
    }
    private void tempDisable( EmployeeModel employeeModel ){
        String url = PencilUtil.BASE_URL + "admin/temp_disable_employee";

        PrefModel prefModel = GetPrefs.getInstance().getSharedPref( ViewAllEmployeeActivity.this );
        if ( prefModel.getAdminId() == null || prefModel.getToken() == null ){
            finish();
            return;
        }

        Map<String, String> param = new HashMap<>();
        param.put("admin_token", prefModel.getToken());
        param.put("admin_id", prefModel.getAdminId());
        param.put("employee_id", employeeModel.getEmployeeId());
        if ( employeeModel.getActivationStatus().equals("1"))
            param.put("status", "2");
        else
            param.put("status", "1");

        NetWorkConnection.getInstance().volleyPosting(url, param, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                if ( response.equals("1")){
                    PencilUtil.toaster(ViewAllEmployeeActivity.this, "Success");
                }
                else {
                    PencilUtil.toaster(ViewAllEmployeeActivity.this, "Failed");
                }
                finish();
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
    private void permenentlyDisable( EmployeeModel employeeModel ){
        String url = PencilUtil.BASE_URL + "admin/permenent_disable_employee";

        PrefModel prefModel = GetPrefs.getInstance().getSharedPref( ViewAllEmployeeActivity.this );
        if ( prefModel.getAdminId() == null || prefModel.getToken() == null ){
            finish();
            return;
        }

        Map<String, String> param = new HashMap<>();
        param.put("admin_token", prefModel.getToken());
        param.put("admin_id", prefModel.getAdminId());
        param.put("employee_id", employeeModel.getEmployeeId());


        NetWorkConnection.getInstance().volleyPosting(url, param, this, new NetworkResponse() {
            @Override
            public void onSuccess(String response) {
                if ( response.equals("1")){
                    PencilUtil.toaster(ViewAllEmployeeActivity.this, "Employee permanently disabled");
                }
                else {
                    PencilUtil.toaster(ViewAllEmployeeActivity.this, "Employee can't permanently disabled");
                }
                finish();
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
