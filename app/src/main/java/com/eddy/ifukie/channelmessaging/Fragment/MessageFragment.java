package com.eddy.ifukie.channelmessaging.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.eddy.ifukie.channelmessaging.Downloader;
import com.eddy.ifukie.channelmessaging.LoginActivity;
import com.eddy.ifukie.channelmessaging.Messages;
import com.eddy.ifukie.channelmessaging.MessagesAdapter;
import com.eddy.ifukie.channelmessaging.OnDownloaderListener;
import com.eddy.ifukie.channelmessaging.R;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by eddyekofo on 08/03/2017.
 */

public class MessageFragment extends Fragment{

    EditText message;
    Button btn_envoyer;
    Button btn_son;
    public ListView message_list;
    public int channelid;

    public MessageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.message_fragment, container);

        if(getActivity().getIntent().hasExtra("channelID")){
            channelid = 1;
        }else {
            channelid = getActivity().getIntent().getIntExtra("channelID", 1);
        }
//        Intent intent = getIntent();
//        int id = intent.getIntExtra("channelID", 1);    //getting the first channel ID??
//        final Runnable r = new Runnable() {
//            public void run() {
//                //use to display messages: tv.append("Hello World");
//                handler.postDelayed(this, 1000);
//            }
//        };

//        handler.postDelayed(r, 1000);

        message_list = (ListView) v.findViewById(R.id.message_list);
        message = (EditText) v.findViewById(R.id.message);
        btn_envoyer = (Button) v.findViewById(R.id.envoyer);
        btn_son = (Button)v.findViewById(R.id.sound);

        SharedPreferences tokens = getActivity().getSharedPreferences(LoginActivity.MY_TOKENS, Context.MODE_PRIVATE);
            String accestoken = tokens.getString(LoginActivity.token, "");

            final HashMap<String, String> params = new HashMap<>();
            params.put("accesstoken", accestoken);

            final Handler handler = new Handler();

            final Runnable r = new Runnable() {
                public void run() {
                    if (getActivity() != null) {
                        params.put("channelid", String.valueOf(channelid));


                        Downloader d = new Downloader(getActivity().getApplicationContext(), "http://www.raphaelbischof.fr/messaging/?function=getmessages", params);
                        d.setOnDownloadListener(new OnDownloaderListener() {
                            @Override
                            public void onDownloader(String values) {

                                Gson gson = new Gson();
                                Messages m = gson.fromJson(values, Messages.class);

                                int index = message_list.getFirstVisiblePosition();

                                View v = message_list.getChildAt(0);
                                int top = (v == null) ? 0 : (v.getTop() - message_list.getPaddingTop());

                                if(getActivity() != null) {
                                    MessagesAdapter adapter = new MessagesAdapter(getActivity().getApplicationContext(), m.getMessages());
                                    message_list.setAdapter(adapter);
                                }


                                // restore index and position

                                message_list.setSelectionFromTop(index, top);
                            }

                        });
                        d.execute();


                        handler.postDelayed(this, 1000);
                    }
                }
            };
            handler.postDelayed(r, 1000);
//
//        btn_envoyer = (Button)v.findViewById(R.id.envoyer);
//        message = (EditText)v.findViewById(R.id.message);
//
        btn_envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences settings = getActivity().getSharedPreferences(LoginActivity.MY_TOKENS, 0);
                String accesstoken = settings.getString(LoginActivity.token,"");

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("accesstoken", accesstoken);
                params.put("channelid", String.valueOf(channelid));
                params.put("message", message.getText().toString());

                Downloader d = new Downloader(getActivity().getApplicationContext(), "http://www.raphaelbischof.fr/messaging/?function=sendmessage", params);
                d.execute();

                message.setText("");

            }
        });

        btn_son.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                SoundRecordDialog soundRecordDialog = SoundRecordDialog.newInstance();
                soundRecordDialog.show(getActivity().getSupportFragmentManager(), "sound");
            }
        });

        return v;

    }

    public void changeChannelID(int id) {
        this.channelid = id;
    }
}










