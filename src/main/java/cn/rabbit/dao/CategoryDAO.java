package cn.rabbit.dao;


import cn.rabbit.pojo.Category;
import cn.rabbit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDAO {

    /**
     * 添加类别
     *
     * @param category 类别对象
     */
    public void add(Category category) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        try {
            sqlSession.insert("add", category);
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


    /***
     * 获取所有的类别数据
     * @return 类别数据集合
     */
    public List<Category> list() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Category> categories = new ArrayList<>();
        try {
            categories.addAll(sqlSession.<Category>selectList("list"));
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return categories;
    }

    /***
     * 删除指定类别
     * @param id 指定类型的 id
     */
    public void delete(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            sqlSession.delete("delete", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }

    /***
     * 更新指定类型
     * @param category 新的类型
     */
    public void update(Category category) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            sqlSession.update("update", category);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }

    /***
     * 根据类型名进行模糊查询
     * @param name  查询的类型名
     * @return 类型的集合
     */
    public List<Category> listCategoryByName(String name) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Category> categories = new ArrayList<>();
        try {
            categories = sqlSession.selectList("listCategoryByName", name);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return categories;
    }

    /***
     * 分页查询
     * @param start 开始页
     * @param count 总数
     * @return 查询到的类型集合
     */
    public List<Category> pagination(int start, int count) {
        List<Category> categories = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("start", start);
        map.put("count", count);
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            categories = sqlSession.selectList("pagination", map);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return categories;
    }

    /***
     * 查询，可以根据类型名或者进行分页查询
     * @param param 查询的参数， 参数可以只能是 start，count 一组 或 name
     * @return 查询类型的集合
     */
    public List<Category> listCategoryByNameOrPagination(Map<String, Object> param) {
        List<Category> categories = new ArrayList<>();
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            categories = sqlSession.selectList("listCategoryByNameOrPagination", param);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return categories;
    }

    public List<Category> listCategory(String sqlID, Map<String, Object> param) {
        List<Category> categories = new ArrayList<>();
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            categories = sqlSession.selectList(sqlID, param);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return categories;
    }

    public List<Category> listCategoryById(List<Integer> list) {
        List<Category> categories = new ArrayList<>();
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            categories = sqlSession.selectList("listCategoryById", list);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return categories;
    }
}
