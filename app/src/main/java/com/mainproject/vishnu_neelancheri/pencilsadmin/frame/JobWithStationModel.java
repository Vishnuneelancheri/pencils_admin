package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 2/24/2018
 */

public class JobWithStationModel {
    @SerializedName("job_id")
    private String jobId;

    @SerializedName("status")
    private String status;

    @SerializedName("work_station")
    private ArrayList<StationModel> stationModelList;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<StationModel> getStationModelList() {
        return stationModelList;
    }

    public void setStationModelList(ArrayList<StationModel> stationModelList) {
        this.stationModelList = stationModelList;
    }
}
