package cn.rabbit.mapper;

import cn.rabbit.dao.CategoryDynaSqlProvider;
import cn.rabbit.pojo.Category;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface ICategoryMapper {

    @InsertProvider(type=CategoryDynaSqlProvider.class,method="add")
    int add(Category category);

    @DeleteProvider(type=CategoryDynaSqlProvider.class,method="delete")
    void delete(int id);

    @SelectProvider(type=CategoryDynaSqlProvider.class,method="get")
    Category get(int id);

    @UpdateProvider(type=CategoryDynaSqlProvider.class,method="update")
    int update(Category category);

    @SelectProvider(type=CategoryDynaSqlProvider.class,method="list")
    List<Category> list();
}
