package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 3/11/2018
 */

public class FrameStockAddInitialSetUp {
    @SerializedName("frame")
    private List<FrameModel> frameList;
    @SerializedName("station")
    private List<StationModel> stationList;
    @SerializedName("job_id")
    private String jobId;

    public List<FrameModel> getFrameList() {
        return frameList;
    }

    public void setFrameList(List<FrameModel> frameList) {
        this.frameList = frameList;
    }

    public List<StationModel> getStationList() {
        return stationList;
    }

    public void setStationList(List<StationModel> stationList) {
        this.stationList = stationList;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
