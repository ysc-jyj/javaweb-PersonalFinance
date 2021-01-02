package com.jyj.dao;

import com.jyj.bean.Payment;
import com.jyj.bean.User;
import com.jyj.utils.DBUtils;

import java.util.List;

public class PaymentDao {
    //分页显示
    public List<Payment> getPaymentListByPage(String sql){
        List<Payment> paymentList= DBUtils.getList(Payment.class,sql);
        return paymentList;
    }

/*    public Integer getPaymentCount(String sql,Object...param) {
        Integer count=DBUtils.getCount(sql,param);
        return count;
    }*/
    //根据email查询账目
    public Integer getPaymentCount(String email) {
        String sql="select count(*) from payment a where a.email=?";
        Integer count=DBUtils.getCount(sql,email);
        return count;

    }
    //保存数据
    public boolean savePayment(Payment payment) {
        String sql="insert into payment(email,payments,type,way,money,pdate,remarks) values(?,?,?,?,?,?,?)";
        return  DBUtils.save(sql,payment.getEmail(),payment.getPayments(),
                payment.getType(),payment.getWay(),payment.getMoney(),payment.getPdate(),
                payment.getRemarks());//返回boolean值
    }

    public boolean deletePayment(Payment payment) {
        String sql="delete from payment where ID=?";
        return DBUtils.update(sql,payment.getID());

    }
    //根据邮箱查询月份
}
