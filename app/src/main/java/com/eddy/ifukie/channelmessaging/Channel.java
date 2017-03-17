package com.eddy.ifukie.channelmessaging;

/**
 * Created by ifukie on 06/02/2017.
 */
public class Channel {
    private int channelID;
    private String name;
    private String connectedusers;

    public Channel() {
    }

    public Channel(int channelID, String name, String connectedusers) {
        this.channelID = channelID;
        this.name = name;
        this.connectedusers = connectedusers;
    }

    public int getChannelID() {
        return channelID;
    }

    public String getName() {
        return name;
    }

    public String getConnectedusers() {
        return connectedusers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConnectedusers(String connectedusers) {
        this.connectedusers = connectedusers;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }
}
