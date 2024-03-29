package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by stacy on 12/13/2016.
 */
public class BackendHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        String greeting = req.getParameter("greeting");

        BackendDTO response = new BackendDTO();
        response.setGreeting(greeting + " from cluster Backend");
        response.setTime(System.currentTimeMillis());
        response.setIp(getIp());
        PrintWriter out = res.getWriter();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out,response);

    }

    private String getIp(){
        String hostname = null;

        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }

        return hostname;
    }
}
