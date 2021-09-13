package com.njj.dao;

import com.njj.bean.City;
import com.njj.bean.CityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CityDAO {
    long countByExample(CityExample example);

    int deleteByExample(CityExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(City record);

    int insertSelective(City record);

    List<City> selectByExample(CityExample example);

    City selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") City record, @Param("example") CityExample example);

    int updateByExample(@Param("record") City record, @Param("example") CityExample example);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    //动态代理
    List<Map> dongTaicitydiqu(Map map);
}