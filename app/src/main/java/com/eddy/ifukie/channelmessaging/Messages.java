package com.eddy.ifukie.channelmessaging;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ifukie on 27/02/2017.
 */
public class Messages {
    private List<Message> messages = new ArrayList<>();

    public Messages() {
        //left empty
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
