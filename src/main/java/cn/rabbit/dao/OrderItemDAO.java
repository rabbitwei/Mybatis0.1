package cn.rabbit.dao;

import cn.rabbit.pojo.Order;
import cn.rabbit.pojo.OrderItem;
import cn.rabbit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderItemDAO {
    public void addOrderItem(OrderItem orderItem) {

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            sqlSession.insert("addOrderItem", orderItem);
            //Mybatis 的事务是默认开启了
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }
    public void deletOrderItem(Map<String, Object> param) {

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            sqlSession.delete("deleteOrderItem", param);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }
}
