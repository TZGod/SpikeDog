package org.tzgod.test;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        System.out.println(new Date());

        //模拟前端传输http协议
        org.tzgod.spikedog.HttpProtocol httpProtocol = new org.tzgod.spikedog.HttpProtocol();
        String http = "GET /demo?name=zhangsan HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Connection: keep-alive\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "Sec-Fetch-Site: none\n" +
                "Sec-Fetch-Mode: navigate\n" +
                "Sec-Fetch-User: ?1\n" +
                "Sec-Fetch-Dest: document\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Accept-Language: zh-CN,zh;q=0.9";


//        String[] strings = http.split("\n");
//        System.out.println(strings);
//        for (int i = 0,len=strings.length; i < len; i++) {
//            if (i==0) {
//                String[] stateLinesParam = strings[0].split(" ");
//                httpProtocol.setRequestMethod(stateLinesParam[0]);
//                httpProtocol.setUrl(stateLinesParam[1]);
//            }
//        }


    }
}
