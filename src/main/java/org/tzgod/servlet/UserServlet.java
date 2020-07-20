package org.tzgod.servlet;


import org.tzgod.spikedog.HttpServlet;
import org.tzgod.spikedog.annotation.WebServlet;

@WebServlet("/user")
public class UserServlet implements HttpServlet {

    public void doGet() {
        System.out.println("Get方法");
    }

    public void doPost() {
        System.out.println("Post方法");
    }
}
