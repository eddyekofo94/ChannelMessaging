package com.eddy.ifukie.channelmessaging;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;
import java.util.List;

/**
 * Created by ifukie on 27/02/2017.
 */
public class MessagesAdapter extends ArrayAdapter<Message> implements OnDownloaderListener {

    private final Context context;
    private final List<Message> values;
    private View messageRowView;
    private String url;


    public MessagesAdapter(Context context, List<Message> values) {
        super(context, R.layout.message_row, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    public View getView (int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        messageRowView = inflater.inflate(R.layout.message_row, parent, false);

        TextView message_text = (TextView) messageRowView.findViewById(R.id.txtMessage);
        message_text.setText(values.get(position).getMessage());

        TextView message_date =  (TextView) messageRowView.findViewById(R.id.txtDate);
        message_date.setText(values.get(position).getDate());

        TextView message_user = (TextView) messageRowView.findViewById(R.id.text_user);
        message_user.setText(values.get(position).getUsername() + ": " );

        url = values.get(position).getImageUrl();
        final ImageView userImg = (ImageView) messageRowView.findViewById(R.id.imgUser);

        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(userImg) {
            @Override
            protected void setResource(Bitmap resource) {

                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                userImg.setImageDrawable(circularBitmapDrawable);

            }
        });

        return messageRowView;
    }

    @Override
    public void onDownloader(String content) {

        File imgFile = new File(url);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView imgUser = (ImageView) messageRowView.findViewById(R.id.imgUser);
            imgUser.setImageBitmap(myBitmap);
        }
    }

}
