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
            //每一个请求都给其分配一个线程进行操作
//            new Thread(new RequestRunnable(socket,httpRequestAndResponse)).start();
            //从线程池中获取线程执行请求任务
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

            SpikedogWebServletScan spikedogWebServletScan = new SpikedogWebServletScan();
            servletHashMap = spikedogWebServletScan.servletScan();

            httpRequestAndResponse=new HttpRequestAndResponse(servletHashMap);


            //创建了一个定长线程池，只有6个线程
            service = Executors.newFixedThreadPool(100);



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
