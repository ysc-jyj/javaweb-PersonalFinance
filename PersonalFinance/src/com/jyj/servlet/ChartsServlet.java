package com.jyj.servlet;

import com.jyj.bean.Asset;
import com.jyj.bean.Charts;
import com.jyj.bean.User;
import com.jyj.dao.AssetDao;
import com.jyj.dao.ChartsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ChartsServlet")
public class ChartsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        User user = (User) request.getSession().getAttribute("SESSION_USER");
        AssetDao assetDao = new AssetDao();
        Asset assets = assetDao.selectAssetByEmail(user.getEmail());
        if ("seek".equals(method)) {
            List<Charts> chartsList = null;//支出
            List<Charts> chartsList2 = null;//收入
            List<Charts> chartsList3=null;//总结
            ChartsDao chartsDao = new ChartsDao();
            String year=request.getParameter("year");
            String month = request.getParameter("month");
            String str=year.concat("-").concat(month);
            String sql = "select type,sum(money) as typeTotalMoney from payment where email='" + user.getEmail() + "' and pdate like '"+str+ "%' and payments='支出' group by type";
            chartsList = chartsDao.getChartsListByMonth(sql);
            request.setAttribute("chartsList", chartsList);
            //request.getRequestDispatcher("charts.jsp").forward(request, response);
            String sql2 = "select type,sum(money) as typeTotalMoney from payment where email='" + user.getEmail() + "' and pdate like '"+str+ "%' and payments='收入' group by type";
            chartsList2 = chartsDao.getChartsListByMonth(sql2);
            request.setAttribute("chartsList2", chartsList2);
            String sql3 ="select totalPay,totalIncome,balance,Time as timeT from total where email='"+user.getEmail()+"' and Time like '"+str+ "%' group by Time";
            chartsList3=chartsDao.getChartsListByMonth(sql3);
            request.setAttribute("chartsList3",chartsList3);
            assets = assetDao.selectAssetByEmail(user.getEmail());
            request.setAttribute("assets", assets);
            request.getRequestDispatcher("charts.jsp").forward(request, response);
            }else{
            request.setAttribute("assets", assets);
            request.getRequestDispatcher("charts.jsp").forward(request, response);
        }


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);



    }
}
