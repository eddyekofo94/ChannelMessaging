package com.eddy.ifukie.channelmessaging;

/**
 * Created by ifukie on 27/02/2017.
 */
public class Message {
    //Post response
    /*
            "userID": 1,
			"message": "Ceci est un test",
			"date": "2015-01-22 21:12:17",
			"imageUrl": "http://www.joomlaworks.net/images/demos/galleries/abstract/7.jpg"
     */

    /*
    http://stackoverflow.com/questions/31041100/downloading-image-in-android-using-asynctask-for-recyclerview
     */
    private int userID;
    private String message;
    private String date;
    private String username;
    private String imageUrl;

    public Message() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

