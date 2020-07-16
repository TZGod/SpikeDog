package org.tzgod.spikedog;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SpikeDogServer {

    public static void main(String[] args) throws IOException {
        //创建一个ServerSocket对象
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("服务器等待接收");
            //每来一个请求都会有一个socket对象被创建
            //当没有请求的时候，会一致阻塞在此处
            Socket socket = serverSocket.accept();
            System.out.println(socket);

            //从输入流中可以读取到浏览器发给我们的消息
            InputStream inputStream = socket.getInputStream();
            //用来存放input读取到的数据
            byte[] buffer = new byte[1024];
            //用来记录读取了多少个字节
            int len = 0;
            //如果没有数据了，input会返回一个-1
            while (true) {
                //浏览器如果不关闭，此处的流就会一直等待
                len=inputStream.read(buffer);
//                if (len == -1) {
//                    break;
//                }
                System.out.println(new String(buffer,0,len));
                if (len < 1024) {
                    break;
                }
            }
            //给浏览器一个响应，http协议响应

            OutputStream outputStream = socket.getOutputStream();

            String body = "<html><head></head><body><h1>HelloWorld</h1></body></html>";

            String str = "HTTP/1.1 200 OK\n" +
                   // "Location: http://www.baidu.com\n"+
                    "Date: Mon, 27 Jul 2009 12:28:53 GMT\n" +
                    "Server: Apache\n" +
                    "Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT\n" +
                    "ETag: \"34aa387-d-1568eb00\"\n" +
                    "Accept-Ranges: bytes\n" +
                    "Content-Length: "+body.length()+"\n" +
                    "Vary: Accept-Encoding\n" +
                    "Content-Type: text/html\n"+
                    "\n"+body;
            outputStream.write(str.getBytes());
            outputStream.flush();

            inputStream.close();
            outputStream.close();



        }


//        System.out.println("结束");

    }
}
