package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 3/11/2018
 */

public class ViewFrameStockModel {
    @SerializedName("balance")
    private String balance;
    @SerializedName("station_name")
    private String stationName;
    @SerializedName("framename")
    private String frameName;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getFrameName() {
        return frameName;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }
}
