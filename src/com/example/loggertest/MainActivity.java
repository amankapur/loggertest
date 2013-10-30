package com.example.loggertest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import com.parse.Parse;
import com.parse.ParseAnalytics;

import java.util.LinkedList;

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

        Log.i("uuid", "apples are awesome");

        LinkedList<MyTaskParams> q = new LinkedList<MyTaskParams>();
        q.add( new MyTaskParams(urls[0], actualData[0], q) );
        q.add( new MyTaskParams(urls[1], actualData[1], q) );
        q.add( new MyTaskParams(urls[2], actualData[2], q) );

        new DownloadFilesTask().execute(q.pop());
    }


}