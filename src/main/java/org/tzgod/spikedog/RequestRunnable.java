package org.tzgod.spikedog;

import java.io.IOException;
import java.net.Socket;

/**
 * 每来了一个用户请求都会创建一个新的线程进行操作处理
 */
public class RequestRunnable implements Runnable {

    private Socket socket;
    private org.tzgod.spikedog.HttpRequestAndResponse httpRequestAndResponse;


    public RequestRunnable(Socket socket, org.tzgod.spikedog.HttpRequestAndResponse httpRequestAndResponse) {
        this.socket = socket;
        this.httpRequestAndResponse = httpRequestAndResponse;
    }

    public void run() {
        System.out.println(">>"+Thread.currentThread().getName()+"::"+socket);
        try {
            //分配一个线程去处理请求的工作并返回202
            httpRequestAndResponse.httpAccepted(socket);
            //获取请求处理请求
            httpRequestAndResponse.request(socket);
            //给浏览器一个响应，http协议响应
            //---响应返回HelloWorld页面
            httpRequestAndResponse.response(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
