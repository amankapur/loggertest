package com.example.loggertest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.TrafficStats;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.parse.Parse;
import com.parse.ParseAnalytics;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public Bitmap img;
    Reporter reporter;
    public String[] urls = new String[5];
    public String[] actualData = new String[5];

    int i =0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Parse.initialize(this, "dkaXkrtblAjpqpJIEukTJsXRalkOr3BYYIX1I0Cb", "RlN9RXcCtHFmssa42ogwmJe526mHhWdAr5dPxneK");
        ParseAnalytics.trackAppOpened(getIntent());


             urls[0] = "http://www.ndaccess.com/Sample/Images/Image1.jpg";
        actualData[0] = "31436";

        urls[1] = "http://asia.olympus-imaging.com/products/dslr/e510/sample/images/sample_01.jpg";
        actualData[1] = "6798992";

        urls[2] = "http://www.komar.org/faq/camera/auto-focus-test/micro-auto-focus-test-2.gif";
        actualData[2] = "1325988";

        for(i = 0; i < 3; i++){

            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        //Your code goes here
                        reporter = new Reporter("Image test1");
                        reporter.Start();
    //                    Log.i("Bitmap", "YOYOYOYOYOYO");
    //                    Log.i("UUID", "" + android.os.Process.myUid());
                        img = loadBitmap(urls[i]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
            try {
                thread.join();
                reporter.End(actualData[i]);
    //            Log.i("UUID", "" + TrafficStats.getUidRxBytes(android.os.Process.myUid()));
    //            Log.i("UUID", "" + TrafficStats.getUidRxPackets(android.os.Process.myUid()));
                ImageView imgView =(ImageView)findViewById(R.id.ImageView01);
                imgView.setImageBitmap(img);
            } catch (InterruptedException e) {
                Log.i("bitmap", "thread error");
            }

        }
        File dir = new File("/proc/uid_stat/");


        String[] children = dir.list();
        List uids = new ArrayList();
        Log.i("NewUID", "" + children);
        if (children != null) {
            Log.i("NewUID", "child found");
            for (int i = 0; i < children.length; i++) {
                Log.i("NewUID", "child found");
                int uid = Integer.parseInt(children[i]);
                if ((uid >= 0 && uid < 2000) || (uid >= 10000)) {
                    uids.add( uid);
                    Log.i("NewUID", "" + uid);
                    Log.i("NewUID", "" + TrafficStats.getUidRxBytes(uid));

                }
            }
        }

    }


    public static Bitmap loadBitmap(String url) {

        URL newurl = null;
        try {
            newurl = new URL(url);
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
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