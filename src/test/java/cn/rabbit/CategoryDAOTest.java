package cn.rabbit;


import cn.rabbit.dao.CategoryDAO;
import cn.rabbit.pojo.Category;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Test
    public void listCategoryByNameOrPagination() {
        Map<String, Object> param = new HashMap<>();
        param.put("start", 0);
        param.put("count", 5);

        //查询条件是 分页的条件
        System.out.println(categoryDAO.listCategoryByNameOrPagination(param));

        Map<String, Object> param2 = new HashMap<>();
        param2.put("name", "tem");
        //查询条件是 模糊查询的条件
        System.out.println(categoryDAO.listCategoryByNameOrPagination(param2));

    }

    @Test
    public void listCategory() {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "tem");
        param.put("id", 5);
        System.out.println(categoryDAO.listCategory("listCategory", param));

    }

    @Test
    public void listCategory2() {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "tem");     //模糊参数

        System.out.println(categoryDAO.listCategory("listCategory2", param));

    }

    @Test
    public void listCategoryId() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(10);
        list.add(13);
        System.out.println(categoryDAO.listCategoryById(list));
    }
}
