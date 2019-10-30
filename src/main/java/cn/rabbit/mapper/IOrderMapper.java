package cn.rabbit.mapper;

import cn.rabbit.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderMapper {

    @Select("select * from order_")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderItems", column = "id",
            many = @Many(select = "cn.rabbit.mapper.IOrderItemMapper.listByOrder"))
    })
    List<Order> listOrder();

    @Select("select * from order_ where id = #{id}")
    Order getOrder(int id);

    @Insert({"insert into order_ (code) value(#{code})"})
    //通过LAST_INSERT_ID()获取刚插入记录的自增主键值，
    // 在insert语句执行后，执行select LAST_INSERT_ID()就可以获取自增主键。
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void addOrder(Order order);

    @Delete("delete from Order_ where id = #{id}")
    void deleteOrder(int id);

    @Update("update order_ set code = #{code} where id = #{id}")
    void updateOrder(Order order);
}
