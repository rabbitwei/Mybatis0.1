package cn.rabbit.mapper;

import cn.rabbit.pojo.Category;
import cn.rabbit.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductMapper {

    @Select("select * from product_")
    @Results({
            //如果我们需要cid属性值的话，也需要手动的显示映射的
            @Result(property = "cid", column = "cid"),
            //将查询得到的cid,映射到下面的 column = "cid",
            //然后调用ICategoryMapper 的 getCategory()方法，传入参数 cid，不是id
            //获取得到的返回值赋给 category 属性
            @Result(property = "category", column = "cid",
                    one = @One(select = "cn.rabbit.mapper.ICategoryMapper.getCategory"))})
    List<Product> listProduct();

    @Select("select * from product_ where id = #{id}")
    Product getProduct(int id);

    @Insert("insert into product_ (name, price) values (#{name}, #{price})")
    void addproduct(Product product);

    @Delete("delete from product_ where id = #{id}")
    void deleteProduct(int id);

    @Update("update product_ set name = #{name}, price = #{price} where id = #{id}")
    void updateProduct(Product product);


    //=====================一对多被调用的方法==============================
    //根据分类id获取产品集合
    @Select("select * from product_ where cid = #{cid}")
    List<Product> listByCategory(int cid);


    @Select("select * from product_ where id = #{id}")
    Product get(int id);

}
