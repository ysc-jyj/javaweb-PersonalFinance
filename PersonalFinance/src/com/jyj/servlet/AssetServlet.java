package com.jyj.servlet;

import com.jyj.bean.Asset;
import com.jyj.bean.User;
import com.jyj.dao.AssetDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//弃用
@WebServlet(urlPatterns= "/AssetServlet")
public class AssetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user=(User)request.getSession().getAttribute("SESSION_USER");
        AssetDao assetDao=new AssetDao();
        Asset assets=assetDao.selectAssetByEmail(user.getEmail());
        request.setAttribute("assets",assets);
        request.getRequestDispatcher("assets.jsp").forward(request,response);
    }
}
