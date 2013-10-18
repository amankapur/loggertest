package com.example.loggertest;

import android.net.TrafficStats;

import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: amankapur91
 * Date: 10/18/13
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Reporter {

    private long start_time;
    private long end_time;
    private long start_packets_upload;
    private long start_bytes_upload;
    private long start_packets_download;
    private long start_bytes_download;
    private long end_bytes_upload;
    private long end_packets_upload;
    private long end_bytes_download;
    private long end_packets_download;
    private int uid;
    private String title;

    public Reporter (String t) {
        title = t;
//        Start();
    }

    public void Start () {
        uid = android.os.Process.myUid();
        start_packets_download = TrafficStats.getUidRxPackets(uid);
        start_bytes_upload = TrafficStats.getUidTxBytes(uid);
        start_bytes_download = TrafficStats.getUidRxBytes(uid);
        start_packets_upload = TrafficStats.getUidTxPackets(uid);
        start_time = System.currentTimeMillis();


    }

    public void End () {
        end_time = System.currentTimeMillis();
        end_packets_download = TrafficStats.getUidRxPackets(uid);
        end_bytes_upload = TrafficStats.getUidTxBytes(uid);
        end_bytes_download = TrafficStats.getUidRxBytes(uid);
        end_packets_upload = TrafficStats.getUidTxPackets(uid);
        Logger logger = new Logger();
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        data.put("end_time", end_time);
        data.put("start_time", start_time);
        data.put("packets_download", end_packets_download - start_packets_download);
        data.put("packets_upload", end_packets_upload - start_packets_upload);
        data.put("bytes_download", end_bytes_download - start_bytes_download);
        data.put("bytes_upload", end_bytes_upload - start_bytes_upload);
        data.put("duration", end_time - start_time);
        data.put("title", title);
        logger.save(data);

    }

}