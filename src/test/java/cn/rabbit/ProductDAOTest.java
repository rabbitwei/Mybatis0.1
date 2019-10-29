package cn.rabbit;

import cn.rabbit.dao.ProductDAO;
import cn.rabbit.pojo.Product;
import org.junit.Test;

import java.util.List;

public class ProductDAOTest {
    private ProductDAO productDAO = new ProductDAO();
    @Test
    public void listProduct() {
        List<Product> list =  productDAO.listProduct();
        for (Product p : list) {
            System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
        }
    }
}
