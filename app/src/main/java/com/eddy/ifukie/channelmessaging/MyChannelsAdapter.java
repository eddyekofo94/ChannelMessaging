package com.eddy.ifukie.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ifukie on 06/02/2017.
 */
public class MyChannelsAdapter extends ArrayAdapter<Channel>{
    private final Context context;
    private final List<Channel> value;
    private String message = "Number of connected user(s): ";

    public MyChannelsAdapter(Context context, List<Channel> value) {
        super(context,R.layout.channel_list_row, value);
        this.context = context;
        this.value = value;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.channel_list_row, parent, false);
        TextView  tvChannel = (TextView) rowView.findViewById(R.id.text_name);
        TextView tvUser =  (TextView) rowView.findViewById(R.id.text_users);

        Channel chnnl = value.get(position);
        tvChannel.setText(chnnl.getName());

        tvUser.setText(message + chnnl.getConnectedusers());

        return rowView;
    }
}
