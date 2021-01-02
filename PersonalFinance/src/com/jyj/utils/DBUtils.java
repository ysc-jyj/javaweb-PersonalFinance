package com.jyj.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jyj.bean.Payment;
import org.apache.commons.beanutils.BeanUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DBUtils {

    //连接
    private static Connection getConnection() {
        //读取配置文件
        Connection conn = null;
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/db.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }

    //  保存对象
    public static boolean save(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
//          返回更新的记录数
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, null, ps, null);
        }

        return count != null && count > 0 ? true : false;
    }

    public static <T> T getSingleObj(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T bean = null;
        try {
            conn = DBUtils.getConnection();
            //String sql="select id,username,birthday from users where id=? and username=?";
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }

            rs = ps.executeQuery();

            //获取结果集元数据（描述结果集的数据）
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int colnum = rsmd.getColumnCount();

            while (rs.next()) {
                //key存放列名，value存放列值，for循环完成之后，rowMap存放了一条记录
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= colnum; i++) {
                    String columnName = rsmd.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);
                    //判断查询出来的类的类型，如果是java.sql转成java.util
                    if (columnValue instanceof java.sql.Date) {
                        java.sql.Date date = (java.sql.Date) columnValue;
                        columnValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName, columnValue);
                }
                //创建一个User对象，给这个User对象属性赋值
                bean = clazz.newInstance();

                //根据列名，id == 给User对象id属性赋值的方法名，setId,"set"+Id=setId,调用setId方法给user对象赋值，map中的value
                //循环rowMap给User所有属性赋值 entry{key,value}[{id:1},{username:"king"},{"birthday":"2020-10-14"}]
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {//entry:声明类型

                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, null, ps, rs);
        }
        return bean;
    }

    //  查询记录数量
    public static Integer getCount(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();
            //String sql="select id,username,birthday from users where id=? and username=?";
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }

            rs = ps.executeQuery();

            while (rs.next()) {

                count = rs.getInt(1);//第一列count(*)
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, null, ps, rs);
        }

        return count;

    }

    //  查询对象
    //  泛型 类型参数，类型变量，类型可以变化
    public static <T> List<T> getList(Class<T> clazz,String sql,Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<T> list=null;
        ResultSetMetaData rsmd;
        try{
            conn= DBUtils.getConnection();
            ps=conn.prepareStatement(sql);
            if(args!=null && args.length>0){
                for(int i=0;i<args.length;i++){
                    ps.setObject(i+1,args[i]);
                }
            }

            rs=ps.executeQuery();

            //获取结果集元数据（描述结果集的数据）
            rsmd=rs.getMetaData();
            //获取结果集的列数
            int colnum=rsmd.getColumnCount();

            //查询多条
            list=new ArrayList<>();


            while(rs.next()){
                //key存放列名，value存放列值，for循环完成之后，rowMap存放了一条记录
                Map<String,Object> rowMap=new HashMap<String,Object>();
                for(int i=1;i<=colnum;i++){
                    String columnName=rsmd.getColumnLabel(i);
                    Object columnValue=rs.getObject(columnName);
                    //判断查询出来的类的类型，如果是java.sql转成java.util
                    if(columnValue instanceof java.sql.Date){
                        java.sql.Date date=(java.sql.Date)columnValue;
                        columnValue=new java.util.Date(date.getTime());

                    }
                    rowMap.put(columnName,columnValue);
                }
                //创建一个User对象，给这个User对象属性赋值
                T bean=clazz.newInstance();

                //根据列名，id == 给User对象id属性赋值的方法名，setId,"set"+Id=setId,调用setId方法给user对象赋值，map中的value
                //循环rowMap给User所有属性赋值
                for(Map.Entry<String,Object> entry:rowMap.entrySet()){//entry:声明类型

                    String propertyName=entry.getKey();
                    Object propertyValue=entry.getValue();
                    BeanUtils.setProperty(bean,propertyName,propertyValue);
                }

                list.add(bean);

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn, null,ps, rs);
        }

        return list;
    }
    //更新（删除）
    public static boolean update(String sql,Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        Integer count=0;
        conn=DBUtils.getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if(args!=null&&args.length>0){
                for(int i = 0;i<args.length;i++){
                    if(args[i] instanceof java.util.Date){
                        java.util.Date date=(java.util.Date)args[i];
                        args[i]=new java.sql.Date(date.getTime());
                    }
                    ps.setObject(i+1,args[i]);
                }

            }
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,null,ps,null);
        }
        return  count>0?true:false;
    }
    //关闭连接
    public static void close(Connection conn, Statement stmt, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
