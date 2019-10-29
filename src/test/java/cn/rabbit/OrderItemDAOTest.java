package cn.rabbit;

import cn.rabbit.dao.OrderDAO;
import cn.rabbit.dao.OrderItemDAO;
import cn.rabbit.dao.ProductDAO;
import cn.rabbit.pojo.Order;
import cn.rabbit.pojo.OrderItem;
import cn.rabbit.pojo.Product;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderItemDAOTest {
    ProductDAO productDAO = new ProductDAO();
    OrderDAO orderDAO = new OrderDAO();
    OrderItemDAO orderItemDAO = new OrderItemDAO();
    @Test
    public void addOrderItem() {
        Product product = productDAO.getProduct(5);
        Order order = orderDAO.getOrder(1);

        //多对多建立关系
        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(100);
        orderItem.setProduct(product);
        orderItem.setOrder(order);

        orderItemDAO.addOrderItem(orderItem);
        System.out.println("订单项建立成功");
    }

    @Test
    public void deleteOrderItem() {
        Product product = productDAO.getProduct(5);
        Order order = orderDAO.getOrder(1);

        //多对多建立关系
        Map<String,Object> param = new HashMap<>();
        param.put("pid", product.getId());
        param.put("oid", order.getId());
        orderItemDAO.deletOrderItem(param);
        System.out.println("订单项删除成功");

    }
}
