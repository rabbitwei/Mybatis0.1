package cn.rabbit.dao;

import cn.rabbit.pojo.Category;
import cn.rabbit.pojo.Product;
import cn.rabbit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> listProduct() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Product> products = new ArrayList<>();
        try {
            products = sqlSession.selectList("listProduct");
            //Mybatis 的事务是默认开启了
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return products;
    }

    public Product getProduct(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        Product product = new Product();
        try {
            product = sqlSession.selectOne("getProduct", id);
            //Mybatis 的事务是默认开启了
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return product;
    }
}
