package cn.rabbit;

import cn.rabbit.dao.OrderDAO;
import cn.rabbit.pojo.Order;
import cn.rabbit.pojo.OrderItem;
import org.junit.Test;

import java.util.List;

public class OrderDAOTest {
    private OrderDAO orderDAO = new OrderDAO();

    @Test
    public void listOrder() {
        List<Order> orders = orderDAO.listOrder();
        for (Order o : orders) {
            System.out.println(o.getCode());
            List<OrderItem> orderItems = o.getOrderItems();
            for (OrderItem oi : orderItems) {
                System.out.format("\t%s\t%f\t%d%n",
                        oi.getProduct().getName(), oi.getProduct().getPrice(), oi.getNumber());
            }
        }
    }
}
