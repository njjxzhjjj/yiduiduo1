package com.njj.dao;

import com.njj.bean.Human;
import com.njj.bean.HumanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HumanDAO {
//所有----单表  增--数据  删---根据id删除 根据条件删除(动态sql) 改----条件修改(动态sql)
//查--按主键id查对象,查总条数 (动态)  查所有的数据(动态)
    long countByExample(HumanExample example);// 用example类 查询总条数，动态的sql去查询总条数
      //当example类为null的时候，sql 语句等于如下
        //select count(*) from person
      //当example类 不为 null的时候，sql 语句等于如下
    //select count(*) from human where gender=2   example参数如何传递？
    int deleteByExample(HumanExample example);//按条件删除

    int deleteByPrimaryKey(Integer id);//按主键id删除

    int insert(Human record);//不用他  当Human 对象所有的属性都在可以用它

    int insertSelective(Human record);//尽量用他！！！ 出错了再用上面的

    List<Human> selectByExample(HumanExample example); //动态查询所有

    Human selectByPrimaryKey(Integer id);//主键id查询

    int updateByExampleSelective(@Param("record") Human record, @Param("example") HumanExample example);//动态批量删除 用这个

    int updateByExample(@Param("record") Human record, @Param("example") HumanExample example);//动态批量删除 不用

    int updateByPrimaryKeySelective(Human record);//按主键id修改一个对象 一个数据

    int updateByPrimaryKey(Human record);//千万  不用
}