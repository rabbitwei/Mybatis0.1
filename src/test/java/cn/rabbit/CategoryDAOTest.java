package cn.rabbit;


import cn.rabbit.mapper.ICategoryMapper;
import cn.rabbit.pojo.Category;
import cn.rabbit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryDAOTest {
    private SqlSession sqlSession;
    private ICategoryMapper mapper;
    private int id = 12;
    @Before
    public void getSqlSession() {
        sqlSession = MybatisUtil.getSqlSession();
        mapper = sqlSession.getMapper(ICategoryMapper.class);
    }

    @After
    public void closeSqlSession() {
        sqlSession.commit();
        list();
        MybatisUtil.closeSqlSession();
    }


    @Test
    public void add() {
        Category c = new Category();
        c.setName("数码");
        mapper.add(c);
    }
    @Test
    public void list() {
        System.out.println(mapper.list());
    }

    @Test
    public void update() {
        Category c = new Category();
        c.setId(id);
        c.setName("testhaha");
        mapper.update(c);
    }

    @Test
    public void delete() {
        mapper.delete(id);
    }

    @Test
    public void get() {
        System.out.println(mapper.get(id));
    }






}
