package com.example.loggertest;

import android.os.AsyncTask;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: amankapur91
 * Date: 10/30/13
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SerialExecutor {
    LinkedList<MyTaskParams> queue;

    public SerialExecutor() {
        queue = new LinkedList<MyTaskParams>();
    }

    public void queue(AsyncTask task, MyTaskParams params) {
        queue.add(params);
    }

    public void run(){
        if (queue.size() > 0){
            new DownloadFilesTask().execute(queue.pop());
        }
    }


}