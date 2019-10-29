package cn.rabbit;


import cn.rabbit.dao.CategoryDAO;
import cn.rabbit.pojo.Category;
import org.junit.Test;

public class CategoryDAOTest {

    private CategoryDAO categoryDAO = new CategoryDAO();

    @Test
    public void add() {
        Category c = new Category();
        c.setName("数码");
        categoryDAO.add(c);
    }
    @Test
    public void list() {
        System.out.println(categoryDAO.list());
    }

    @Test
    public void update() {
        Category c = new Category();
        c.setId(10);
        c.setName("testhaha");
        categoryDAO.update(c);
    }

    @Test
    public void delete() {
        categoryDAO.delete(10);
    }

    @Test
    public void listCategoryByName() {
        System.out.println(categoryDAO.listCategoryByName("tem"));
    }

    @Test
    public void pagination() {
        System.out.println(categoryDAO.pagination(0, 5));
    }
}
