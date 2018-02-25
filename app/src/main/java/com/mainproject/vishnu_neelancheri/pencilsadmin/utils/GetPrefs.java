package com.mainproject.vishnu_neelancheri.pencilsadmin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mainproject.vishnu_neelancheri.pencilsadmin.R;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 2/20/2018
 */

public class GetPrefs {
    public static volatile GetPrefs getPrefs;
    public static GetPrefs getInstance(){
        if ( getPrefs == null ){
            synchronized ( GetPrefs.class ){
                if ( getPrefs == null ){
                    getPrefs = new GetPrefs();
                }
            }
        }
        return getPrefs;
    }
    public PrefModel getSharedPref(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences( context.getResources().getString( R.string.app_name), Context.MODE_PRIVATE );
        PrefModel prefModel = new PrefModel();
        prefModel.setToken( sharedPreferences.getString(context.getResources().getString(R.string.token), "") );
        prefModel.setAdminId(sharedPreferences.getString(context.getResources().getString(R.string.admin_id), ""));
        return prefModel;
    }
}
