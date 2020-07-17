package org.tzgod.spikedog;


import org.tzgod.spikedog.annotation.WebServlet;
import org.tzgod.spikedog.exception.UriUnuniqueException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class SpikedogWebServletScan {

    /**
     * 加在所有的Servlet，并且保存到了Map中
     * @return
     * @throws URISyntaxException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UriUnuniqueException
     */
    public HashMap<String, org.tzgod.spikedog.HttpServlet> servletScan() throws URISyntaxException, ClassNotFoundException, IllegalAccessException, InstantiationException, UriUnuniqueException {
        HashMap<String, org.tzgod.spikedog.HttpServlet> servletHashMap = new HashMap<String, org.tzgod.spikedog.HttpServlet>();
        String basePackage = "com.qianfeng.test";
        String path = basePackage.replaceAll("\\.", "/");
        URL url = SpikedogWebServletScan.class.getResource("/");
        File file = new File(url.getPath()+path);
        File[] files = file.listFiles();
        for (int i = 0,size=files.length; i < size; i++) {

            //文件
            if (files[i].isFile()) {
                //所有的class文件
                String filename = files[i].getName();
                if (filename.endsWith(".class")) {
                    String[] strings = filename.split("\\.");
                    String className = strings[0];
                    String allClassName = basePackage+"."+className;
                    Class<?> aClass = Class.forName(allClassName);
                    WebServlet annotation = aClass.getAnnotation(WebServlet.class);
                    if (annotation != null) {
                        String uri = annotation.value();
                        if (servletHashMap.get(uri) != null) {
                            throw new UriUnuniqueException("出现了两个一样的地址：" + uri);
                        }
                        org.tzgod.spikedog.HttpServlet servlet = (org.tzgod.spikedog.HttpServlet) aClass.newInstance();
                        servletHashMap.put(uri,servlet);
                    }
                }
            } else {
                //文件夹
            }
        }

        return servletHashMap;
    }
}
