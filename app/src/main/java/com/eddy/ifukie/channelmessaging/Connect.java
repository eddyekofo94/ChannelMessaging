package com.eddy.ifukie.channelmessaging;

/**
 * Created by eddyekofo94 on 30/01/2017.
 */

public class Connect {
    private String response;
    private int code;
    private String accesstoken;

    //Constructor
    public Connect() {
        //Keep empty.
    }

    public String getResponse() {
        return response;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public int getCode() {
        return code;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    @Override
    public String toString() {
        return "Connect{" +
                "response='" + response + '\'' +
                ", code=" + code +
                ", accesstoken='" + accesstoken + '\'' +
                '}';
    }
}
