package com.example.common.mapper;

import com.example.common.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 12:05
 */
@Mapper
public interface ProductMapper {

    Product queryById(@Param("id") long id);

    /**
     * 根据商品ID和数据扣减库存
     *
     * @param productId 商品ID
     * @param quantity 扣减数量
     * @return 影响行数
     */
    @Update("UPDATE product SET `stock` = `stock` - #{quantity} WHERE id = #{id}")
    int reduceStockById(@Param("id")long productId, @Param("quantity") int quantity);

    @Select("SELECT id, name, stock, sale_cnt, version FROM product")
    List<Product> queryList();

    int updateStockById(long id, long stock);

    int getVersionById(long id);

    Product getVersionAndStockById(long productId);

    int reduceStockByIdAndVersion(long id, int stock, long version);
}
