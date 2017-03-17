package com.eddy.ifukie.channelmessaging;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by eddyekofo on 03/03/2017.
 */

public class DownloaderImage extends AsyncTask<String, String, String>{
    private MessagesAdapter myContext;
    private ArrayList<OnDownloaderListener> listeners = new ArrayList<>();
    private String url;
    private String name;
    private int type;

    public DownloaderImage(MessagesAdapter myContext, String url, String name) {
        this.myContext = myContext;
        this.url = url;
        this.name = name;
    }

    public DownloaderImage(){}

    public void setDownloaderList(OnDownloaderListener listener) {

        this.listeners.add(listener);

    }

    @Override
    protected String doInBackground(String... input) {

        String response = "";

        downloadFromUrl(url, name);

        return response;
    }

    public void downloadFromUrl(String fileURL, String fileName) {
        try {
            URL url = new URL(fileURL);
            File file = new File(fileName);
            file.createNewFile();
            /* Open a connection to that URL. */
            URLConnection ucon = url.openConnection();
            /* Define InputStreams to read from the URLConnection.*/
            InputStream is = ucon.getInputStream();
            /* Read bytes to the Buffer until there is nothing more to
            read(-1) and write on the fly in the file.*/
            FileOutputStream fos = new FileOutputStream(file);
            final int BUFFER_SIZE = 23 * 1024;
            BufferedInputStream bis = new BufferedInputStream(is,
                    BUFFER_SIZE);
            byte[] baf = new byte[BUFFER_SIZE];
            int actual = 0;
            while (actual != -1) {
                fos.write(baf, 0, actual);
                actual = bis.read(baf, 0, BUFFER_SIZE);
            }
            fos.close();
        } catch (IOException e) {
            //TODO HANDLER
        }
    }

    @Override
    protected void onPostExecute(String result) {

        for (OnDownloaderListener oneListener : listeners)
        {
            oneListener.onDownloader(result);
        }

    }


}
