package com.example.loggertest;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: amankapur91
 * Date: 10/30/13
 * Time: 2:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyTaskParams {
    String url;
    String actualData;
    LinkedList<MyTaskParams> queue;

    MyTaskParams(String url, String actualData, LinkedList<MyTaskParams> queue) {
        this.url = url;
        this.actualData = actualData;
        this.queue = queue;
    }
}
