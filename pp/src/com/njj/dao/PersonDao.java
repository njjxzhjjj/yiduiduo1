package com.njj.dao;

import com.njj.bean.Person;
import com.njj.bean.PersonDto;

import java.util.List;
import java.util.Map;

public interface PersonDao {
    //全查
    List<Person> selectAll();//方法名和xml的名字一致
    //单查
    List<Person> selectPersonBySex(int sex);
    //List<Person> selectPersonBySex(int sex,String name);//不支持
    //查询总条数
    long selectCount();

    //查询总条数+多个参数  第一种方式 2个参数都是person类中的属性，所以可以直接把person做参数
    //实体类做参数
    long selestCountByParam01(Person person);

    // 查性别和生日 当查出的sql的语句不确定是唯一的一条的时候 一定用list
    //当多表 联查的时候， 请求的参数-定要为map或者是自己写个实体类。应用场景 多表联查的多参数查询
    List<Person> selestCountByParam02(Map map);

    //查询 分值最高的人是谁
    List<Person> selestPersonByZi();

    //分组查询 男生和女生的平均分值 各是多少
    List<PersonDto> selestAvgScore();

    //男生和女生的平均分值 大于200的值  有参数
    List<PersonDto> selestAvgScoreParam(int score);
    //男生和女生的平均分值 大于200的值  有参数  使用map
    List<Map> selestAvgScoreParam02(int score);


    //查询  姓孙的  第一种方式  不建议使用
    List<Person> selectPersonByLike(String name);

    //查询  姓孙的  第二种方式
    List<Person> selectPersonByLike02(String name);

    //查询  姓孙的  第三种方式
    List<Person> selectPersonByLike03(String name);


    //添加一条数据
    int insertPerson(Person person);

    //删除一条数据
    int deletePersonById(Integer id);  //注意：之后讲解 动态sql 那么我们的dao
    //的dao层接口中只有 基础类型int,//String不好的。 不方便执 行动态sql,对以后扩展不便
    //以后自己写代码参数一定是.. - 一个实体类，或者是个map，或者是DTO
//动态sql
    List<Person> dongTaiSelect(Person person);//动态sql，如果参数不是实体类，不是集合， 是个空参数，那么没有任何意义。
//长成 返回值是list<实体类> 参数也是同样的实体类 那么这是一个典型 的动态sql语句

    //动态的修改
    int dongTaiUpdate(Person person);

    //批量删除(2,3,4)
    void piLiangDel(Map map);

    //一对多 方法写在1方  //.把这个政为动态sqL，.. 按id，i依name. 都可以查询
    ////作业2: 写出2个表 ，city， -- .3.. 区表中原区，管城区， 升发区。 5出 1对多的动态sql|
    List<Person> selectOrdersByPersonId(Integer id);


    List<Map> dongTaiselectOrdersByPerson(Map map);
}
