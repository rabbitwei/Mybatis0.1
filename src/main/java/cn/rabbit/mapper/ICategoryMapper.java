package cn.rabbit.mapper;

import cn.rabbit.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ICategoryMapper {

    @Select("select * from category_")
    @Results({
            //为什么这里需要设一个@Result来映射 id属性，而 name属性不需要？
            /*因为Mybatis只能单字段单映射，由于第二个 column = "id" 使用了Mybatis 的自动映射，
             *所以这里只能自己手动的添加一个@Resutl，来让Mybatis映射
             *如果没有这个@Result的映射，那么取出来的Category对象的id都为0*/
            @Result(property = "id", column = "id"),
            //这里为什么要设置 column = "id"？
            //因为调用 listByCategory 需要传入 Category的id作为条件查询
            //然后得到返回值赋值给products 属性
            @Result(property = "products", javaType = List.class, column = "id",
            many = @Many(select = "cn.rabbit.mapper.IProductMapper.listByCategory"))})
    List<Category> listCategory();

    @Select("select * from category_ where id = #{id}")
    Category getCategory(int id);

    @Insert({"insert into category_ (name) value(#{name})"})
    //通过LAST_INSERT_ID()获取刚插入记录的自增主键值，
    // 在insert语句执行后，执行select LAST_INSERT_ID()就可以获取自增主键。
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void addCategory(Category category);

    @Delete("delete from category_ where id = #{id}")
    void deleteCategory(int id);

    @Update("update category_ set name = #{name} where id = #{id}")
    void updateCategory(Category category);

    @Select("select * from category_ where id = #{id}")
    Category get(int id);

}
