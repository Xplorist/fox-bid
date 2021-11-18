package com.foxconn.bidstatistics.domain;

import com.foxconn.bidstatistics.dao.GivePriceMstrDAO;
import com.foxconn.bidstatistics.model.GivePriceMstrDO;
import com.foxconn.bidstatistics.model.query.GivePriceMstrQuery;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 報價BO
 */
@Data
@Component
public class GivePriceMstrBO {
    private GivePriceMstrDO gpmDO;// 報價DO
    private GivePriceMstrQuery query;// 報價Query

    private GivePriceMstrDAO dao;// 報價DAO

    @Autowired
    public GivePriceMstrBO(GivePriceMstrDAO dao) {
        this.dao = dao;
    }




}
