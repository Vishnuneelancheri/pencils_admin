package com.mainproject.vishnu_neelancheri.pencilsadmin.employee;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 2/24/2018
 */

public class EmployeeModel implements Parcelable {
    @SerializedName("employee_id")
    private String employeeId;
    @SerializedName("name")
    private String name;
    @SerializedName("station_name")
    private String stationName;
    @SerializedName("station_code")
    private String stationCode;
    @SerializedName("phone")
    private String mobilePhone;
    @SerializedName("activation_status")
    private String activationStatus;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.employeeId);
        dest.writeString(this.name);
        dest.writeString(this.stationName);
        dest.writeString(this.stationCode);
        dest.writeString(this.mobilePhone);
        dest.writeString(this.activationStatus);
    }

    public EmployeeModel() {
    }

    protected EmployeeModel(Parcel in) {
        this.employeeId = in.readString();
        this.name = in.readString();
        this.stationName = in.readString();
        this.stationCode = in.readString();
        this.mobilePhone = in.readString();
        this.activationStatus = in.readString();
    }

    public static final Parcelable.Creator<EmployeeModel> CREATOR = new Parcelable.Creator<EmployeeModel>() {
        @Override
        public EmployeeModel createFromParcel(Parcel source) {
            return new EmployeeModel(source);
        }

        @Override
        public EmployeeModel[] newArray(int size) {
            return new EmployeeModel[size];
        }
    };
}
