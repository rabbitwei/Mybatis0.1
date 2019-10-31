package cn.rabbit;

import cn.rabbit.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductDAOTest {
    private SqlSession session1;
    private SqlSession session2;
    @Before
    public void getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session1 = sqlSessionFactory.openSession();
        session2 = sqlSessionFactory.openSession();
    }


    @Test
    public void listProduct() {
        List<Product> list = session1.selectList("listProduct");
        for (Product p : list) {
            System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
        }
    }
    @Test
    public void getProduct() {
        Product p1 = session1.selectOne("getProduct", 1);
        System.out.println(p1);

        Product p2 = session1.selectOne("getProduct", 1);
        System.out.println(p2);

        session1.commit();
        session1.close();

        Product p3 = session2.selectOne("getProduct", 1);
        System.out.println(p3);


        session2.commit();
        session2.close();
    }
}
