package cn.rabbit.pojo;

import java.util.List;

/**
 * Order 与 OrderItem 类 是一对多的关系
 * OrderItem 与 Product 类是多对一的关系
 * 本质上， Order 与 Product 的关系是通过 OrderItem 类来维持的。
 * 即 Order 一对多 OrderItem， OrderItem 多对一 Product
 * 所以 Order 多对多 Product 的关系 实际上是 one - many - one 的关系。
 *
 */
public class Order {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    private int id;
    private String code;
    List<OrderItem> orderItems;
}
