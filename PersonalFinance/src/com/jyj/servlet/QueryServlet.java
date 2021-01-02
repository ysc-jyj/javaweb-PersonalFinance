package com.jyj.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyj.bean.User;
import com.jyj.dao.UserDao;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebServlet(urlPatterns = "/QueryServlet")
public class QueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=utf-8");
       /* String type = request.getParameter("type");*/
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = RandomStringUtils.randomAlphanumeric(new Random().nextInt(10) + 4);//唯一性？
        User user = new User(email, password, name, null);
        UserDao userDao = new UserDao();
        User user1 = userDao.selectApplicantEmailCount(user);
        Map<String, Object> map = new HashMap<String, Object>();
        //if (count > 0) {
        //邮箱已注册
        if (user1 != null) {
            map.put("userExsit", true);
            //map.put("msg","此用户名太受欢迎,请更换一个");
        }else{
            map.put("userExsit", false);
            //map.put("msg","用户名可用");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
