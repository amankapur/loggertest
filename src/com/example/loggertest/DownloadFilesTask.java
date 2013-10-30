package com.example.loggertest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: amankapur91
 * Date: 10/30/13
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */

public class DownloadFilesTask extends AsyncTask<MyTaskParams, Integer, Long> {

    final Reporter reporter = new Reporter("Image test1");
    MyTaskParams params;
    @Override
    protected Long doInBackground(MyTaskParams... params) {
        Log.i("uuid", "doing in background");
        this.params = params[0];
        reporter.Start();
        Bitmap img = loadBitmap(params[0].url);
        return Long.parseLong(params[0].actualData);

    }

    protected void onPostExecute(Long result) {

        Log.i("done", "completed async task");
        reporter.End(result.toString());
        if (params.queue.size() > 0){
            new DownloadFilesTask().execute(params.queue.pop());
        }
    }


    private static Bitmap loadBitmap(String url) {

        URL newurl = null;
        try {
            newurl = new URL(url);
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            return mIcon_val;


        } catch (MalformedURLException e) {
            Log.i("bitmap", e.toString());
            return null;
        }
        catch (IOException e) {
            Log.i("bitmap", e.toString());
            return  null;
        }


    }
}
