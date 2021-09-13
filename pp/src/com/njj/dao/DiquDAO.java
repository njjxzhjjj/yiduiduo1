package com.njj.dao;

import com.njj.bean.Diqu;
import com.njj.bean.DiquExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiquDAO {
    long countByExample(DiquExample example);

    int deleteByExample(DiquExample example);

    int deleteByPrimaryKey(Integer qid);

    int insert(Diqu record);

    int insertSelective(Diqu record);

    List<Diqu> selectByExample(DiquExample example);

    Diqu selectByPrimaryKey(Integer qid);

    int updateByExampleSelective(@Param("record") Diqu record, @Param("example") DiquExample example);

    int updateByExample(@Param("record") Diqu record, @Param("example") DiquExample example);

    int updateByPrimaryKeySelective(Diqu record);

    int updateByPrimaryKey(Diqu record);
}