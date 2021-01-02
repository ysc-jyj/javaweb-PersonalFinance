package com.jyj.servlet;

import com.jyj.bean.Asset;
import com.jyj.bean.Payment;
import com.jyj.bean.TablePage;
import com.jyj.bean.User;
import com.jyj.dao.AssetDao;
import com.jyj.dao.PaymentDao;
import com.jyj.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/BookkeepingServlet")
public class BookkeepingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("SESSION_USER");
        String method=request.getParameter("method");
        //showTable(request,response,user);
        //从前台取数据
        if ("add".equals(method)) {
            Payment payment = requestDataObj(request);
            PaymentDao paymentDao = new PaymentDao();

            boolean flag = paymentDao.savePayment(payment);//标记是否记账成功
            if (flag) {
               showTable(request,response,user);
            } else {
                //通过response对象给客户端一个错误提示
                PrintWriter output = response.getWriter();
                output.write("<script>");
                output.write("alert('记账失败，请重新记录！');");
                output.write("window.location.href='record.jsp'");
                output.write("</script>");
                output.flush();
                output.close();
                showTable(request,response,user);

            }
        }else if("delete".equals(method)){
            String ID=request.getParameter("ID");
            Integer id=Integer.parseInt(ID);
            Payment payment = new Payment(id);
            PaymentDao paymentDao = new PaymentDao();
            boolean flag = paymentDao.deletePayment(payment);//标记是否记账成功
            if (flag) {
                showTable(request,response,user);
            } else {
                //通过response对象给客户端一个错误提示
                PrintWriter output = response.getWriter();
                output.write("<script>");
                output.write("alert('删除失败！');");
                output.write("window.location.href='record.jsp'");
                output.write("</script>");
                output.flush();
                output.close();
                showTable(request,response,user);
            }

        }else {
            showTable(request,response,user);
        }

    }
    //  获取页面数据封装账目
    private Payment requestDataObj(HttpServletRequest request) {
       /* String ID=request.getParameter("ID");
        Integer id=Integer.parseInt(ID);*/
        String payments = request.getParameter("payments");
        String type = request.getParameter("type");
        String way = request.getParameter("way");
        String money = request.getParameter("money");//将字符串转换成double类型
        String pdate = request.getParameter("pdate");
        String remarks = request.getParameter("remarks");
        Double money1 = Double.parseDouble(money);
        //从session中取出登录的用户的email
        User user = (User) request.getSession().getAttribute("SESSION_USER");
        Payment payment = new Payment( null,user.getEmail(), payments, type, way, money1, pdate, remarks);
        return payment;
    }
  private void showTable(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = null;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.valueOf(pageSizeStr);
        } else {
            pageSize = 5;
        }
        //2.当前是第几页 currentPage
        String currentPageStr = request.getParameter("currentPage");
        Integer currentPage = null;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.valueOf(currentPageStr);
        } else {
            currentPage = 1;
        }
        //3.一共有多少行数据 totalRows
        Integer totalRows = 0;
        PaymentDao paymentDao = new PaymentDao();
        user=(User)request.getSession().getAttribute("SESSION_USER");
        String email = user.getEmail();
        //查询行数
        totalRows = paymentDao.getPaymentCount(email);
        Integer startRow = (currentPage - 1) * pageSize;
        //加了ID
        StringBuffer sqlRow = new StringBuffer("SELECT ID,pdate,payments,type,way,money,remarks FROM payment where email=\""+email+"\"");
        sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
        //把所有公司信息查询出来

        List<Payment> paymentList = paymentDao.getPaymentListByPage(sqlRow.toString());
        TablePage tablePage = new TablePage(currentPage, pageSize, totalRows, paymentList);
        request.setAttribute("TablePage", tablePage);
        //BUG
        request.getRequestDispatcher("record.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
