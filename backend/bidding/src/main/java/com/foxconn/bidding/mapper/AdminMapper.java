package com.foxconn.bidding.mapper;

import com.foxconn.bidding.model.BulletinDO;
import com.foxconn.bidding.model.USER_INFO_bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    // 管理員查詢賬號審核任務(分頁)
    List<USER_INFO_bean> admin_query_user_au_list_pagi(USER_INFO_bean param);

    // 保存公告
    Integer saveBulletin(BulletinDO param);

    // 刪除公告
    Integer deleteBulletin();

    // 查詢公告list
    List<BulletinDO> listBulletin();
}
