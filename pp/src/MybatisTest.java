import com.njj.bean.Human;
import com.njj.bean.HumanExample;
import com.njj.bean.Person;
import com.njj.bean.PersonDto;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MybatisTest {
    private SqlSession sqlSession;//请讲一下mybatis的执行流程
    private SqlSessionFactory sqlSessionFactory;
    @Before

    public void before() throws Exception {
        //加载并读取xml
        String path = "SqlMapConfig.xml";
        InputStream is = Resources.getResourceAsStream(path);
        //sql 链接的工厂 类
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession);//.sqlsession. = . org. apache . ibatis. session. defaul ts . Defaul tSq lSession@71423665
        //sqlSession.close();
    }
    //全查  select *from person 讲的是
    @Test
    public void test01()  {
        //mybatis
        List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selectAll");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //单查
    @Test
    public void test02()  {
        //mybatis
        List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selectPersonBySex",2);
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //查询总条数 ，这个主要学习的是返回的数据类型，和上面的数据类型不一致
    @Test
    public void test03()  {
        //mybatis
      long o = sqlSession.selectOne("com.njj.dao.PersonDao.selectCount");
        System.out.println("o = " + o);
        sqlSession.close();
    }
    //带参数查询 第一种方式  实体类传参数 多用于----单表查询
    @Test
    public void test04()  {
        Person person = new Person();
        person.setScore(100);
        person.setGender(2);
        long o = sqlSession.selectOne("com.njj.dao.PersonDao.selestCountByParam01", person);
        System.out.println("o = " + o);
        sqlSession.close();
    }

    //带参数查询 第二种方式  map传参数 多用于----多表查询
    @Test
    public void test05() throws ParseException {
        String date="2020-10-14";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sf.parse(date);
        Map map = new HashMap();
        map.put("gender",2);//key一定要和#{gender}保持一致
        map.put("birthday",birthday);//key一定要和#{birthday}保持一致
        List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selestCountByParam02",map);
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
//子查询  最高分数
    @Test
    public void test06()  {
        List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selestPersonByZi");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //男女生的平均分 分组查询
    @Test
    public void test07()  {
        List<PersonDto> personDtos = sqlSession.selectList("com.njj.dao.PersonDao.selestAvgScore");
        for (PersonDto personDto : personDtos) {
            System.out.println("personDto = " + personDto);
        }
        sqlSession.close();
    }

//男生和女生的平均分值 大于200的值  有参数
    @Test
    public void test08()  {
        List<PersonDto> personDtos = sqlSession.selectList("com.njj.dao.PersonDao.selestAvgScoreParam", 200);
        for (PersonDto personDto : personDtos) {
            System.out.println("personDto = " + personDto);
        }
        sqlSession.close();
    }
    //男生和女生的平均分值 大于200的值  有参数 使用map接收
    @Test
    public void test09()  {
        List<Map> mapList = sqlSession.selectList("com.njj.dao.PersonDao.selestAvgScoreParam02", 200);
        for (Map map : mapList) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }
    //查询  姓孙的
    @Test
    public void test10() {
        Map map = new HashMap();
        map.put("name", "孙");
        //: There is no getter for property named 'name' 因为$是拼接的,没有getter 这个概念，
        List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selectPersonByLike", map);
        //List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selectPersonByLike","孙");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

        //查询  姓孙的 可以用  第二种方式
        @Test
        public void test11()  {

            List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selectPersonByLike02","孙");
            for (Person person : personList) {
                System.out.println("person = " + person);
            }
            sqlSession.close();
    }

    //查询  姓孙的 可以用  第三种方式
    @Test
    public void test12()  {

        List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selectPersonByLike03","孙");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //以上就是单表的所有查询!!.看好这些例子， 以后模仿去公司写
   //添加  insert into
    @Test
    public void test13() {
        Person person = new Person();
        person.setName("xx的对象");
        person.setGender(2);
        person.setBirthday(new Date());
        person.setAddress("北京");
        person.setScore(1000);
        int insert = sqlSession.insert("com.njj.dao.PersonDao.insertPerson", person);
        System.out.println("insert = " + insert);
        sqlSession.commit();  //用于增删改
        sqlSession.close();
      }

      //删除一条数据
    @Test
    public void test14() {
        int delete = sqlSession.delete("com.njj.dao.PersonDao.deletePersonById", 17);
        System.out.println("delete = " + delete);
        sqlSession.commit();  //用于增删改
        sqlSession.close();
      }

      //动态sql 重点难点  也是高薪的起点
    //动态sql其实是 让达到1条xml中的语句可以实现n多种查询
    //那么要实现多种查询 ，就要有硬性的条件，你的参数要多，参数要多 1放弃单个属性 （int string）改用实体类
    //2参数改为map  今天所学的推翻昨天的  那么就需要 总结昨天所学的
    //第一类 特征：1、返回值 -----> 正常表的结果集 对应的是person实体类
    //2 都是select *from person 开头的
    //1、select * from person    if如果where 后面没参数那么就是全查
    //1.2 select * from person where gender =2  if如果... where 后面参数是gender. 那么就是单查gender
    //1.3 select * from person where gender = #{gender} and birthday >#{birthday}
    //1.4 select * from person where name like '${name}%'
    //1-4 可以合n为1   只需要把where后面的参数做个if 判断

    //第二类 特征 1、返回值---->一个数，单行单列  非person实体类，是一个数据类型
    //特征2 select count(*) from person 开头的
    //2.1  select count(*) from person;
    //2.2 select count(*) from person where sex=? score>100;

    //综上所述  ！！！  以上sql可以进行动态 判断形成一个sql  ！！ 这个叫做动态sql



     //动态查询
    @Test
    public void test15() {
        Person person = new Person();
        //null 就是全查
        //person.setId(16); //select *from person p WHERE p.id=?
        person.setScore(200);
        person.setGender(2);
        List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.dongTaiSelect", person);
        for (Person person1 : personList) {
            System.out.println("person1 = " + person1);
        }
        sqlSession.close();
    }
    //动态修改 其实就是有选择性 的修改多个字段，可以修改女孩分数，日期等等
    //动态修改
    @Test
    public void test16() {
        Person person = new Person();
        person.setId(16);
        person.setGender(2);
        person.setBirthday(new Date());
        int update = sqlSession.update("com.njj.dao.PersonDao.dongTaiUpdate", person);
        System.out.println("update = " + update);
        sqlSession.commit();
        sqlSession.close();
    }
    //批量删除 delete xxx in (2,3,4)
    //构造IDS
    @Test
    public void test17() {
        List<Integer> idList=new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        Map map = new HashMap();
        map.put("ids",idList);
        int delete = sqlSession.delete("com.njj.dao.PersonDao.piLiangDel", map);
        System.out.println("delete = " + delete);
        sqlSession.commit();
        sqlSession.close();
    }
    //以上代码不用手写，因为谁写谁垃圾
    //xml 不需要你写 ！！dao不需要你写
    //但是需要你看懂

    //这是重点 逆向生成 公司都用
    //没有写一行代码 但是已经动态的查总条数已经完成
    @Test
    public void test18() {
        //select count(*) from human where gender=2
        HumanExample example = new HumanExample();//创建一个例子类
        HumanExample.Criteria criteria = example.createCriteria();//用例子类实现查沟的规则或者标准
        //criteria.andGenderEqualTo(2); //select count(*) from human WHERE ( gender = ? )
       // criteria.andAddressEqualTo("西京");//select count(*) from human WHERE gender = 2 and address = "西京"

        //查询地址是西京的人有几个？
      //  criteria.andAddressLike("%"+"西京"+"%");//select count(*) from human WHERE gender = 2 and address like %西京%

        //查询家住在北京的或者分数是500的人 select * from human where address="北京" or score=500
        //因为 criteria 查询标准里面没有 or 但是有 in
        //select * from human where
        //example.or().andAddressEqualTo("北京");// or不要driteria类
        //example.or().andScoreEqualTo(500.00);//Iselect count(*) from human. WHERE ( address = ?) or( . score= ?)

        //select * from human where id=1 or id=4 or id=5
       /* example.or().andIdEqualTo(1);
        example.or().andIdEqualTo(4);
        example.or().andIdEqualTo(5);*/
       List<Integer> ids=new ArrayList<>();
        ids.add(1);
        ids.add(4);
        ids.add(5);
       criteria.andIdIn(ids);

        //.当example的criteria不用赋值的时候， 则是... Preparing: select count(*) from human
        long o = sqlSession.selectOne("com.njj.dao.HumanDAO.countByExample", example);
        System.out.println("o = " + o);
        sqlSession.close();
    }


            //查询:
            // select * from . human; 全查
        //select * from . human where gender=2
        //select * from . human where gender=1
        //select * from . human where id=1
        //select * from . human where score<80
        //select * from . human where score>80 and gender=1
        //select * from . human where score>80 and gender=1 and address like "%郑州%"
        //以上所有sql语句需要写一个
            @Test
            public void test19() {
                // select * from . human; 全查  ---不带参数
                HumanExample example = new HumanExample();//创建一个例子类
                List<Human> humans = sqlSession.selectList("com.njj.dao.HumanDAO.selectByExample", example);
                for (Human human : humans) {
                    System.out.println("human = " + human);
                }
                sqlSession.close();
            }

            @Test
            public void test19_01() {
                //select * from . human where gender=2 ---带参数
                HumanExample example = new HumanExample();//创建一个例子类
                HumanExample.Criteria criteria = example.createCriteria();
                criteria.andGenderEqualTo(2);
                List<Human> humans = sqlSession.selectList("com.njj.dao.HumanDAO.selectByExample", example);
                for (Human human : humans) {
                    System.out.println("human = " + human);
                }
                sqlSession.close();
            }

            @Test
            public void test19_02() {
                ////select * from . human where id=1 ---带参数
                HumanExample example = new HumanExample();//创建一个例子类
                HumanExample.Criteria criteria = example.createCriteria();
                criteria.andIdEqualTo(1);
                List<Human> humans = sqlSession.selectList("com.njj.dao.HumanDAO.selectByExample", example);
                for (Human human : humans) {
                    System.out.println("human = " + human);
                }
                sqlSession.close();
            }
            @Test
            public void test19_03() {
                ////select * from . human where score<80 ---带参数
                HumanExample example = new HumanExample();//创建一个例子类
                HumanExample.Criteria criteria = example.createCriteria();
                criteria.andScoreLessThan(80.0);
                List<Human> humans = sqlSession.selectList("com.njj.dao.HumanDAO.selectByExample", example);
                for (Human human : humans) {
                    System.out.println("human = " + human);
                }
                sqlSession.close();
            }

             @Test
            public void test19_04() {
                //select * from . human where score>80 and gender=1 ---带参数
                HumanExample example = new HumanExample();//创建一个例子类
                HumanExample.Criteria criteria = example.createCriteria();
                criteria.andScoreGreaterThan(80.0);
                criteria.andGenderEqualTo(1);
                List<Human> humans = sqlSession.selectList("com.njj.dao.HumanDAO.selectByExample", example);
                for (Human human : humans) {
                    System.out.println("human = " + human);
                }
                sqlSession.close();
            }

            @Test
            public void test19_05() {
                //select * from . human where score>80 and gender=1 and address like "%郑州%" ---带参数
                HumanExample example = new HumanExample();//创建一个例子类
                HumanExample.Criteria criteria = example.createCriteria();
                criteria.andScoreGreaterThan(80.0);
                criteria.andGenderEqualTo(1);
                criteria.andAddressLike("%"+"郑州"+"%");
                List<Human> humans = sqlSession.selectList("com.njj.dao.HumanDAO.selectByExample", example);
                for (Human human : humans) {
                    System.out.println("human = " + human);
                }
                sqlSession.close();
            }

    //增加
    @Test
    public void test20(){
        Human human = new Human();
        human.setGender(2);
        human.setName("刘晓云2");
        human.setAddress("北京");

       // int insert = sqlSession.insert("com.njj.dao.HumanDAO.insertSelective", human);        // insert into human (`name`, gender, address) values (?, ?, ? )
        int insert = sqlSession.insert("com.njj.dao.HumanDAO.insert", human);  // insert into human (id, `name`, gender, birthday, address, score ) values (?, ?, ?, ?, ?, ? )
      // Parameters: null, 张彬彬(String), 2(Integer), null,北京(String)， null，当数据库中有的字段有默认值。
        //不能为null的时候，该增加的方法就会出错， 所以要用长的insertSelective

        System.out.println("insert = " + insert);
        sqlSession.commit();
        sqlSession.close();
    }

    //工具的删除
    @Test
    public void test21(){
        int delete = sqlSession.delete("com.njj.dao.HumanDAO.deleteByPrimaryKey", 7);//delete from human where id = ?
        System.out.println("delete = " + delete);
        sqlSession.commit();
        sqlSession.close();
    }
    //条件删除
    //1、删除所有女生 2、单婵分数晓宇20的  3、删除名字中带有云的人  4、删除女生并且分数小于20
    // 5. |删除女生并且分数小于20的并且名字中含有带云的人
    @Test
    public void test22(){
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria=example.createCriteria();
        criteria.andGenderEqualTo(2);
        criteria.andScoreLessThan(20.0);
        criteria.andNameLike("%"+"云"+"%");
        int delete = sqlSession.delete("com.njj.dao.HumanDAO.deleteByExample", example);//delete from human WHERE ( gender = ? and score = ? )
        System.out.println("delete = " + delete);
        sqlSession.commit();
        sqlSession.close();
    }

    //修改一个对象的 把悟空改为八戒
    @Test
    public void test23(){
        Human human = new Human();
        human.setId(4);
        human.setName("八戒");
        int update = sqlSession.update("com.njj.dao.HumanDAO.updateByPrimaryKeySelective",human);//update human SET `name` = ? where id = ?
        //int update = sqlSession.update("com.njj.dao.HumanDAO.updateByPrimaryKey",human);//Preparing: update human set `name` = ?, gender = ?, birthday = ?, address = ?, score = ? where id = ?
       // DEBUG - ==> Parameters: 八戒1(String), null, null, null, null, 4(Integer)
        //修改千万不可以使用updateByPrimaryKey ，会造成 原有字段变成null
        System.out.println("update = " + update);
        sqlSession.commit();
        sqlSession.close();
    }

    //批量的动态修改---分数超过100分都改为100  因为传两个参数 没办法测试
    @Test
    public void test24(){
        Human human = new Human();
        human.setScore(100.0);
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria=example.createCriteria();
        criteria.andScoreGreaterThan(100.0);

       // int update = sqlSession.update("com.njj.dao.HumanDAO.updateByExampleSelective", example, human);
    }

    //按照主键id查询
    @Test
    public void test25(){
        Human o = sqlSession.selectOne("com.njj.dao.HumanDAO.selectByPrimaryKey", 4);
        System.out.println("o = " + o);
        sqlSession.close();
    }

    //动态查询所有数据
    //1、查询分数大于100的人
    //1、查询分数大于100的人 并且生日大于2020-11-4的人
    @Test
    public void test26() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2020-11-04");
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria=example.createCriteria();
        criteria.andScoreGreaterThan(100.0);
        criteria.andBirthdayGreaterThan(date);//select id, `name`, gender, birthday, address, score from human WHERE ( score > ? and birthday > ? )
       criteria.andGenderEqualTo(2);//select id, `name`, gender, birthday, address, score from human WHERE ( score > ? and birthday > ? and gender = ? )
        List<Human> humanList = sqlSession.selectList("com.njj.dao.HumanDAO.selectByExample", example);//select id, `name`, gender, birthday, address, score from human WHERE ( score > ? )
        for (Human human : humanList) {
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }

    //一对多
    @Test
    public void test27(){
        List<Person> personList = sqlSession.selectList("com.njj.dao.PersonDao.selectOrdersByPersonId", 1);
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //一对多  动态查询
    //一对多 方法写在1方  //.把这个政为动态sqL，.. 按id，i依name. 都可以查询
    @Test
    public void test28(){
        Map map = new HashMap();
        map.put("name","孙尚香");
        List<Map> map1 = sqlSession.selectList("com.njj.dao.PersonDao.dongTaiselectOrdersByPerson", map);
        for (Map map2 : map1) {
            System.out.println("map2 = " + map2);
        }
        sqlSession.close();
    }

    //作业2: 写出2个表 ，city， -- .3.. 区表中原区，管城区， 升发区。 5出 1对多的动态sql|
    @Test
    public void test29(){
        Map map = new HashMap();
        map.put("name","经开区");
        List<Map> map1 = sqlSession.selectList("com.njj.dao.CityDAO.dongTaicitydiqu", map);
        for (Map map2 : map1) {
            System.out.println("map2 = " + map2);
        }
        sqlSession.close();
    }
}

