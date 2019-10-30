package cn.rabbit;

import cn.rabbit.mapper.IOrderMapper;
import cn.rabbit.pojo.Order;
import cn.rabbit.pojo.OrderItem;
import cn.rabbit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrderDAOTest {
    private SqlSession sqlSession;
    private IOrderMapper mapper;

    @Before
    public void initSqlSession() {
        sqlSession = MybatisUtil.getSqlSession();
        //使用Mapper接口，不在使用session直接操作了，因为没有了映射sql语句的id。
        mapper = sqlSession.getMapper(IOrderMapper.class);
    }

    @After
    public void closeSqlSession() {
        //记住：除了查询，其他操作都需要提交事务的，否则就会看到
        //数据有添加，但是数据库全没有该数据。
        sqlSession.commit();
        MybatisUtil.closeSqlSession();
    }

    @Test
    public void listOrder() {
        System.out.println(mapper.listOrder());
    }

    @Test
    public void getOrder() {
        System.out.println(mapper.getOrder(21));
    }

    @Test
    public void addOrder() {
        Order o = new Order();
        o.setCode("code000C");
        mapper.addOrder(o);
        //获取插入数据后，自增键的值
        System.out.println(o.getId());
        listOrder();
    }

    @Test
    public void deleteOrder() {
        mapper.deleteOrder(48);
        listOrder();
    }

    @Test
    public void updateOrder() {
        Order o = new Order();
        o.setCode("使用注解");
        o.setId(3);
        mapper.updateOrder(o);
        listOrder();
    }

    //==================多对多的测试方法 ============================
    @Test
    public void listOrder2() {
        List<Order> os = mapper.listOrder();
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois= o.getOrderItems();
            if(null!=ois){
                for (OrderItem oi : ois) {
                    System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
                }
            }

        }
    }
}
