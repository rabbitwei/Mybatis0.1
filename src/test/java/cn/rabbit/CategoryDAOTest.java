package cn.rabbit;


import cn.rabbit.mapper.ICategoryMapper;
import cn.rabbit.pojo.Category;
import cn.rabbit.util.MybatisUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryDAOTest {
    private SqlSession sqlSession;
    private ICategoryMapper mapper;
    @Before
    public void initSqlSession() {
        sqlSession = MybatisUtil.getSqlSession();
        //使用Mapper接口，不在使用session直接操作了，没有没有了映射sql语句的id。
        mapper = sqlSession.getMapper(ICategoryMapper.class);
    }
    @After
    public void closeSqlSession() {
        //记住：除了查询，其他操作都需要提交事务的，否则就会看到
        //数据有添加，但是数据库全没有该数据。
        sqlSession.commit();
        MybatisUtil.closeSqlSession();
    }
    @Test
    public void listCategory() {
        System.out.println(mapper.listCategory());
    }
    @Test
    public void getCategory() {
        System.out.println(mapper.getCategory(21));
    }

    @Test
    public void addCategory() {
        Category c = new Category();
        c.setName("科技");
        mapper.addCategory(c);
        //获取插入数据后，自增键的值
        System.out.println(c.getId());
        listCategory();
    }

    @Test
    public void deleteCategory() {
        mapper.deleteCategory(48);
        listCategory();
    }

    @Test
    public void updateCategory() {
        Category c = new Category();
        c.setName("使用注解");
        c.setId(45);
        mapper.updateCategory(c);
        listCategory();
    }
}
