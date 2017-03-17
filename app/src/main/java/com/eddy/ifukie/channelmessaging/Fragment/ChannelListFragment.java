package com.eddy.ifukie.channelmessaging.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.eddy.ifukie.channelmessaging.Channels;
import com.eddy.ifukie.channelmessaging.Downloader;
import com.eddy.ifukie.channelmessaging.LoginActivity;
import com.eddy.ifukie.channelmessaging.MainActivity;
import com.eddy.ifukie.channelmessaging.MyChannelsAdapter;
import com.eddy.ifukie.channelmessaging.OnDownloaderListener;
import com.eddy.ifukie.channelmessaging.R;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by eddyekofo on 08/03/2017.
 */

public class ChannelListFragment extends android.support.v4.app.Fragment {

    String accestoken;
    public ListView lvChannels;

    public ChannelListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.channels_fragment, container);
        lvChannels = (ListView) v.findViewById(R.id.listViewChannels);
        lvChannels.setOnItemClickListener((MainActivity)getActivity());

        SharedPreferences tokens = getActivity().getSharedPreferences(LoginActivity.MY_TOKENS, Context.MODE_PRIVATE);
        if (tokens != null) {
            accestoken = tokens.getString(LoginActivity.token, "");

            HashMap<String, String> params = new HashMap<>();
            params.put("accesstoken", accestoken);

            Downloader d = new Downloader(getActivity(), "http://www.raphaelbischof.fr/messaging/?function=getchannels", params);
            d.setOnDownloadListener(new OnDownloaderListener() {
                @Override
                public void onDownloader(String values) {
                    Gson gson = new Gson();
                    Channels c = gson.fromJson(values, Channels.class);
                    lvChannels.setAdapter(new MyChannelsAdapter(getActivity().getApplicationContext(), c.getChannels()));
                }
            });
            d.execute();
        }
        return v;
    }

}
