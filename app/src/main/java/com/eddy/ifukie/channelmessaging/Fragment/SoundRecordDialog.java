package com.eddy.ifukie.channelmessaging.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.eddy.ifukie.channelmessaging.R;

public class SoundRecordDialog extends DialogFragment {

    //Variables
    private Button record_btn;
    private Button play_btn;
    private Button cancel_btn;
    private Button ok_btn;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.activity_sound_record_dialog, null);

        ok_btn = (Button) v.findViewById(R.id.ok_button);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel_btn = (Button) v.findViewById(R.id.cancelButton);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User cancelled the dialog
                SoundRecordDialog.this.getDialog().cancel();
            }
        });

        record_btn = (Button)v.findViewById(R.id.record_sound);
        play_btn = (Button)v.findViewById(R.id.replay_sound);

        return builder.create();
    }

    public static SoundRecordDialog newInstance() {
        SoundRecordDialog newFragment = new SoundRecordDialog();
        return newFragment;
    }
}
