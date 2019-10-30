package cn.rabbit.mapper;

import cn.rabbit.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ICategoryMapper {
    @Select("select * from category_")
    List<Category> listCategory();

    @Select("select * from category_ where id = #{id}")
    Category getCategory(int id);

    @Insert({"insert into category_ (name) value(#{name})"})
    //通过LAST_INSERT_ID()获取刚插入记录的自增主键值，
    // 在insert语句执行后，执行select LAST_INSERT_ID()就可以获取自增主键。
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    int addCategory(Category category);

    @Delete("delete from category_ where id = #{id}")
    void deleteCategory(int id);

    @Update("update category_ set name = #{name} where id = #{id}")
    void updateCategory(Category category);
}
