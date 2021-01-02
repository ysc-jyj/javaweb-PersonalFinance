package com.jyj.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyj.bean.Asset;
import com.jyj.bean.User;
import com.jyj.dao.AssetDao;
import com.jyj.dao.UserDao;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=utf-8");
        String type = request.getParameter("type");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = RandomStringUtils.randomAlphanumeric(new Random().nextInt(10) + 4);//唯一性？
        User user = new User(email, password, name, null);
        PrintWriter output = response.getWriter();
        UserDao userDao = new UserDao();
        Integer count = userDao.selectApplicantEmailCount(email);
        if(count>0){
            output.write("<script>");
            output.write("alert('申请注册的email已经被占用');");
            output.write("window.location.href='register.jsp'");
            output.write("</script>");
            output.flush();
            output.close();
        }else {
            boolean flag = userDao.saveUser(user);
            //注册成功跳转页面——重定向
            // if(flag.equals("yes")){
            if (flag) {
                Asset asset = new Asset(null, email, 0, 0, 0, 0);
                // asset.setEmail(user.getEmail());
                AssetDao assetDao = new AssetDao();
                assetDao.saveAsset(asset);
                response.sendRedirect("/PersonalFinance/login.jsp");
            } else {//失败——请求转发返回注册页面
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            }
        }

        //}

        // }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
