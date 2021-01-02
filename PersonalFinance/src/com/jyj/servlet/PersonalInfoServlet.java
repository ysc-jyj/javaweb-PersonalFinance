package com.jyj.servlet;

import com.jyj.bean.User;
import com.jyj.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/PersonalInfoServlet")
@MultipartConfig
public class PersonalInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置post提交时候，参数解码格式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
       String method = request.getParameter("method");
        //修改个人信息
        PrintWriter output = response.getWriter();
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        //修改
        String password = request.getParameter("newpassword");
        //String headshot=request.getParameter("headshot");
        //User user=requestDataObj(request);
        UserDao userDao = new UserDao();
        String headshot=userDao.getUserByUsername(email).getHeadshot();
        User user = new User(email, password, username,headshot);

        boolean flag = userDao.updateUserInfo(user);
        if (flag) {
            output.write("ok");
            //output.write("{\"data\":\"ok\"}");
            request.getSession().setAttribute("SESSION_USER", user);
        } else {
            output.write("false");
            //output.write("{\"data\":\"false\"}");
        }
        if("upload".equals(method)){
            Part part= request.getPart("headShot");
            //上传文件名
            String fileName = part.getSubmittedFileName();
            //创建文件保存目录
            String dir=this.getServletContext().getRealPath("/img");
            //判断目录是否
            File imgDir=new File(dir);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }
            //上传到服务器的路径imgDir+'/'+fileName
            part.write(dir+"/"+fileName);
            userDao=new UserDao();
            //从session中取出User
            user=(User)request.getSession().getAttribute("SESSION_USER");
            userDao.updateHeadShot(user.getEmail(),fileName);
            user= userDao.getUserByUsername(email);
            request.getSession().setAttribute("SESSION_USER", user);
            //请求转发
            request.getRequestDispatcher("personal.jsp").forward(request,response);
        }


        //重定向
        //response.sendRedirect("personal.jsp");


    }

    //
    private User requestDataObj(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //从sesion中取出登录用户email
        User user1 = (User) request.getSession().getAttribute("SESSION_USER");
        User user = new User(null, username, password, null);
        user.setEmail(user1.getEmail());
        return user;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
