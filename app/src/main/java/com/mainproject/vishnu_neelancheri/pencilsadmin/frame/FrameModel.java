package com.mainproject.vishnu_neelancheri.pencilsadmin.frame;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 3/11/2018
 */

public class FrameModel {
    @SerializedName("frame_master_id")
    private String frameMasterId;
    @SerializedName("frame_name")
    private String frameName;

    public String getFrameMasterId() {
        return frameMasterId;
    }

    public void setFrameMasterId(String frameMasterId) {
        this.frameMasterId = frameMasterId;
    }

    public String getFrameName() {
        return frameName;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }
}
