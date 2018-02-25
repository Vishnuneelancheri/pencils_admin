package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 2/24/2018.
 */

public class StationModel implements Parcelable {
    @SerializedName("station_id")
    private String stationId;

    @SerializedName("station_name")
    private String stationName;

    @SerializedName("station_code")
    private String stationCode;

    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stationId);
        dest.writeString(this.stationName);
        dest.writeString(this.stationCode);
        dest.writeString(this.price);
    }

    public StationModel() {
    }

    protected StationModel(Parcel in) {
        this.stationId = in.readString();
        this.stationName = in.readString();
        this.stationCode = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<StationModel> CREATOR = new Parcelable.Creator<StationModel>() {
        @Override
        public StationModel createFromParcel(Parcel source) {
            return new StationModel(source);
        }

        @Override
        public StationModel[] newArray(int size) {
            return new StationModel[size];
        }
    };
}
