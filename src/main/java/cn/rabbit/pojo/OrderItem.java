package cn.rabbit.pojo;

/***
 * OrderItem 类是 Order 类和 product 类的维持多对多的中间表实体
 */
public class OrderItem {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Order getOrder() {
        System.out.println("获取 OrderItem类的 order属性");
        return order;
    }

    public void setOrder(Order order) {
        System.out.println("给 OrderItem类的 order属性赋值");
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private int id;
    private  int number;
    private Order order;
    private Product product;
}
