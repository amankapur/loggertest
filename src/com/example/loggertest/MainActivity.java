package com.example.loggertest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.parse.Parse;
import com.parse.ParseAnalytics;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    public Bitmap img;
    Reporter reporter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Parse.initialize(this, "dkaXkrtblAjpqpJIEukTJsXRalkOr3BYYIX1I0Cb", "RlN9RXcCtHFmssa42ogwmJe526mHhWdAr5dPxneK");
        ParseAnalytics.trackAppOpened(getIntent());

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    //Your code goes here
                    reporter = new Reporter("Image test1");
                    reporter.Start();
                    Log.i("Bitmap", "YOYOYOYOYOYO");
                    Log.i("UUID", "" + android.os.Process.myUid());
                    img = loadBitmap("https://scontent-b-ord.xx.fbcdn.net/hphotos-ash4/388222_10151049095415367_977788384_n.jpg");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
            reporter.End();
            Log.i("UUID", "" + android.os.Process.myUid());
            ImageView imgView =(ImageView)findViewById(R.id.ImageView01);
            imgView.setImageBitmap(img);
        } catch (InterruptedException e) {
            Log.i("bitmap", "thread error");
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