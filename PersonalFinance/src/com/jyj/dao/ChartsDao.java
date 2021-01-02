package com.jyj.dao;

import com.jyj.bean.Charts;
import com.jyj.utils.DBUtils;

import java.util.List;

public class ChartsDao {
    public List<Charts> getChartsListByMonth(String sql){
        return DBUtils.getList(Charts.class,sql);
    }

}
