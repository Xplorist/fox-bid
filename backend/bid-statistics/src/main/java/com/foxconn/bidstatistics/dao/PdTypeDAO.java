package com.foxconn.bidstatistics.dao;

import com.foxconn.bidstatistics.model.PdTypeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 產品類型DAO
 */
@Repository
@Mapper
public interface PdTypeDAO {
    // 查詢訂單類型list
    List<PdTypeDO> listPdType();
}
