package com.jyj.dao;

import com.jyj.bean.User;
import com.jyj.utils.DBUtils;

public class UserDao {
    //保存注册对象
    public boolean saveUser(User user) {
        String sql="insert into user(email,password,username,headshot)" +
                "values(?,?,?,?)";
        return  DBUtils.save(sql,user.getEmail(),user.getPassword(),user.getUsername(),user.getHeadshot());//返回boolean值

    }
    //  根据email查询对象
    public User getUserByEmailAndPass(String email, String password) {
        String sql="select email,password,username,headshot from user where email=? and password=?";
        return DBUtils.getSingleObj(User.class,sql,email,password);
    }
    //根据email和密码查询对象
    public Integer selectApplicantEmailCount(String email) {
        String sql="select count(*) from user a where a.email=?";
        Integer count=DBUtils.getCount(sql,email);
        return count;

    }

    public User selectApplicantEmailCount(User user) {
        String sql="select email from user a where a.email=?";
        return DBUtils.getSingleObj(User.class,sql,user.getEmail());

    }
    //根据email查询对象全部信息
    public User getUserByUsername(String email) {
        String sql="select email,password,username,headshot from user where email=?";
        return DBUtils.getSingleObj(User.class,sql,email);
    }
    //
    public Integer selectUsernameCount(String username) {
        String sql="select count(*) from user a where a.username=?";
        Integer count=DBUtils.getCount(sql,username);
        return count;
    }

    public boolean updateUserInfo(User user) {
        String sql="update user set username=?,password=? where email=?";
        boolean flag=DBUtils.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
        return flag;
    }

    public void updateHeadShot(String email, String headShot) {
        String sql="update user set headshot=? where email=? ";
        DBUtils.update(sql,headShot,email);
    }
}
