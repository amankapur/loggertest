package com.example.loggertest;

import android.util.Log;
import com.parse.ParseObject;

import java.util.Hashtable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: amankapur91
 * Date: 10/18/13
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Logger {

    /*
        Saves the object to remote server on parse
        The data types matter, the hash must go from string -> Object
        The datatype passed into object must match the one on Parse.com

        Example:
            Logger logger = new Logger();
            Hashtable<String, Object> data = new Hashtable<String, Object>();
            data.put("event_id", 100);
            data.put("event_title", "test event );
            data.put("packets", 1232323222);
            logger.save(data);
     */
    public void save(Hashtable data){
        ParseObject log = new ParseObject("LogTests");
        Set<String> keys = data.keySet();
        for(String key: keys){
            Log.i("parse", key + ": " + data.get(key));
            log.put(key, data.get(key));
        }
        log.saveInBackground();
    }
}
