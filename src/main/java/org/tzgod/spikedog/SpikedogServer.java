package org.tzgod.spikedog;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpikedogServer {

    public static HashMap<String, HttpServlet> servletHashMap;
    public static ServerSocket serverSocket;
    private HttpRequestAndResponse httpRequestAndResponse;
    private ExecutorService service;


    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, URISyntaxException {

        //新建Spikedog服务器
        SpikedogServer spikedogServer = new SpikedogServer();
        //开始
        spikedogServer.start();
    }

    /*
     * 接收请求
     * */
    private void start() throws IOException {
        while (true) {
            //每来一个请求都会有一个socket对象被创建
            //当没有请求的时候，accept方法会阻塞在此处
            final Socket socket = serverSocket.accept();
            //从线程池中获取线程执行请求任务
            //将socket,httpRequestAndResponse当前事例 注入new RequestRunnable
            //通过run方法启用
            service.execute(new RequestRunnable(socket,httpRequestAndResponse));
        }
    }


    /**
    * 服务器
    * */
    public SpikedogServer() {
        try {
            //创建一个ServerSocket对象监听端口8080
            serverSocket = new ServerSocket(8080);

            System.out.println("Spikedog start");
            System.out.println("Scan All Servlet");

            //扫描Servlet
            SpikedogWebServletScan spikedogWebServletScan = new SpikedogWebServletScan();
            servletHashMap = spikedogWebServletScan.servletScan();

            //结果servletHashMap注入new HttpRequestAndResponse事例
            httpRequestAndResponse=new HttpRequestAndResponse(servletHashMap);


            //创建了一个定长线程池，只有6个线程
            service = Executors.newFixedThreadPool(6);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }












}
