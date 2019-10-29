package cn.rabbit.dao;

import cn.rabbit.pojo.Order;
import cn.rabbit.pojo.Product;
import cn.rabbit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<Order> listOrder() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Order> orders = new ArrayList<>();
        try {
            orders = sqlSession.selectList("listOrder");
            //Mybatis 的事务是默认开启了
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return orders;
    }

    public Order getOrder(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        Order order = new Order();
        try {
            order = sqlSession.selectOne("getOrder", id);
            //Mybatis 的事务是默认开启了
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return order;
    }
}
