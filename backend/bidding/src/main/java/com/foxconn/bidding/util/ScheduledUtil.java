package com.foxconn.bidding.util;

import com.foxconn.bidding.service.BillService;
import com.foxconn.bidding.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定時任務工具類
 */
@Slf4j
@Component
public class ScheduledUtil {
    @Autowired
    private EmailService emailService;
    @Autowired
    private BillService billService;

    // 每周星期一早上9點發送發單和中標匯總情況郵件
    @Scheduled(cron = "0 0 9 ? * 1")
    public void sendStatisticsEmailTask() {
        emailService.sendStatisticsEmail();

        // 日誌記錄
        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.info("于【" + nowDate + "】執行了定時任務【@Scheduled(cron = 0 0 9 ? * 1)】sendStatisticsEmailTask【每周星期一早上9點發送發單和中標匯總情況郵件】");
    }

    // 整點定時任務
    @Scheduled(cron = "0 0 8,12 ? * 1-6")
    public void eachHourTask() {
        // 郵件通知發單用戶選標
        emailService.noticeSendUserPickBid();

        // 自動流標
        // 查詢當前時間大於等於競標結束時間且無投標的訂單，將其設為流標
        billService.auto_flow_bid();

        // 日誌記錄
        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.info("于【" + nowDate + "】執行了定時任務【@Scheduled(cron = 0 0 8,12 ? * 1-6)】eachHourTask【整點定時任務】");
    }
}
