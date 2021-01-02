package com.jyj.dao;

import com.jyj.bean.Asset;
import com.jyj.bean.User;
import com.jyj.utils.DBUtils;

public class AssetDao {

    public boolean saveAsset(Asset asset) {
        String sql="insert into assets(email,card,weChat,alipay,other)" +
                "values(?,?,?,?,?)";
        return  DBUtils.save(sql,asset.getEmail(),asset.getCard(),asset.getWeChat(),asset.getAlipay(),asset.getOther());//返回boolean值

    }
    public Asset selectAssetByEmail(String email) {
        String sql = "select email,card,weChat,alipay,other from assets where email=?";
        return DBUtils.getSingleObj(Asset.class,sql,email);
    }
}
