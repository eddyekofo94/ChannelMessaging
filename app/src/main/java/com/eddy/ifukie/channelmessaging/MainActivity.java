package com.eddy.ifukie.channelmessaging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.eddy.ifukie.channelmessaging.Fragment.ChannelListFragment;
import com.eddy.ifukie.channelmessaging.Fragment.MessageFragment;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    setContentView(R.layout.activity_main);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChannelListFragment fragA = (ChannelListFragment) getSupportFragmentManager().findFragmentById(R.id.channelFragment);
        MessageFragment fragB = (MessageFragment) getSupportFragmentManager().findFragmentById(R.id.messageFragment);

        FragmentManager fragmentManager = this.getSupportFragmentManager();


        if(fragB == null || !fragB.isInLayout()){
            Channel channel = (Channel)fragA.lvChannels.getAdapter().getItem(position);

            Intent i = new Intent(getApplicationContext(), MessagesActivity.class);
            i.putExtra("channelID", channel.getChannelID());
            startActivity(i);
        } else {
            Channel channel = (Channel)fragA.lvChannels.getAdapter().getItem(position);
            fragB.changeChannelID(channel.getChannelID());
    }
}
}
