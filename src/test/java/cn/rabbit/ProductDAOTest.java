package cn.rabbit;

import cn.rabbit.mapper.IProductMapper;
import cn.rabbit.pojo.Product;
import cn.rabbit.util.MybatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductDAOTest {
    private SqlSession sqlSession;
    private IProductMapper mapper;

    @Before
    public void getSqlSession() {
        sqlSession = MybatisUtil.getSqlSession();
        mapper = sqlSession.getMapper(IProductMapper.class);
    }

    @After
    public void closeSession() {
        sqlSession.commit();
        MybatisUtil.closeSqlSession();
    }

    @Test
    public void listProduct() {
        System.out.println(mapper.listProduct());
    }

    @Test
    public void addProduct() {
        Product product = new Product();
        product.setName("testDAO");
        product.setPrice(10.11);
        mapper.addproduct(product);
        listProduct();
    }

    @Test
    public void updateProduct() {
        Product product = new Product();
        product.setId(28);
        product.setName("testDAOUpdate");
        product.setPrice(10.11);
        mapper.updateProduct(product);
        listProduct();
    }

    @Test
    public  void deleteProduct() {
        mapper.deleteProduct(28);
        listProduct();
    }

    //================================多对一的测试方法============================
    @Test
    public void listProduct2() {
        List<Product> products = mapper.listProduct();
        for (Product p : products) {
            String cname = p.getCategory() != null ? p.getCategory().getName() : "无";
            System.out.println(p + "\t对应的分类是:\t" + cname);
        }
    }
}

