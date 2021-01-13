package com.example.myfinalproj.model.authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutRequest {
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("os_type")
    @Expose
    private String osType;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }
}
