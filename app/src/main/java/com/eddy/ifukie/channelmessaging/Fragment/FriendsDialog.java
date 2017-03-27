package com.eddy.ifukie.channelmessaging.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.eddy.ifukie.channelmessaging.R;

/**
 * Created by ifukie on 27/03/2017.
 */
public class FriendsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.add_friend)
                .setPositiveButton(R.string.yes_fr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // ADD friend
                    }
                })
                .setNegativeButton(R.string.no_fr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public static FriendsDialog newInstance() {
        FriendsDialog newFragment = new FriendsDialog();
        return newFragment;
    }
}
