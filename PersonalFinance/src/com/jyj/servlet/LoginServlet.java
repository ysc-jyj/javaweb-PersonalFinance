package com.jyj.servlet;

import com.jyj.bean.User;
import com.jyj.dao.UserDao;
import com.jyj.utils.CookieEncryptTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取前台提交的email和密码
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String remeberMe=request.getParameter("remeberMe");
        UserDao userDao=new UserDao();
        User user = userDao.getUserByEmailAndPass(email, password);
        //user=new User()
        if(user!=null){
            //将登陆对象信息保存到session中
            request.getSession().setAttribute("SESSION_USER",user);
            //通过Cookie记住邮箱密码
            remeberMe(remeberMe,email,password,request,response);
            response.sendRedirect("/PersonalFinance/index.jsp");
        }else{
            //通过response对象给客户端一个错误提示
            PrintWriter output = response.getWriter();
            output.write("<script>");
            output.write("alert('用户名或密码错误！');");
            output.write("window.location.href='login.jsp'");
            output.write("</script>");
            output.flush();
            output.close();
        }
    }
    private void remeberMe(String remeberMe,String email,String password,HttpServletRequest request,HttpServletResponse response){
        if("true".equals(remeberMe)){
            Cookie cookie = new Cookie("COOKIE_EMAIL", CookieEncryptTool.encodeBase64(email));
            cookie.setPath("/");
            cookie.setMaxAge(365*24*3600);
            response.addCookie(cookie);
        }else{
            Cookie[] cookies=request.getCookies();
            if(cookies!=null){
                for(Cookie cookie:cookies){
                    if ("COOKIE_EMAIL".equals(cookie.getName())||"COOKIE_PASSWORD".equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }

                }
            }
        }
    }
}
