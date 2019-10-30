package cn.rabbit.mapper;

import cn.rabbit.pojo.OrderItem;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderItemMapper {


    //通过 oid 字段来获取order的属性值
    @Select("select * from order_item_ where oid = #{oid}")
    //然后通过 pid 字段来获取product的属性值
    @Results({
            @Result(property = "product", column = "pid",
                    one = @One(select = "cn.rabbit.mapper.IProductMapper.get"))
    })
    List<OrderItem> listByOrder(int oid);
}
