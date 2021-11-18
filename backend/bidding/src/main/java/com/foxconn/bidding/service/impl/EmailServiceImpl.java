package com.foxconn.bidding.service.impl;

import com.foxconn.bidding.mapper.BillMapper;
import com.foxconn.bidding.mapper.UserMapper;
import com.foxconn.bidding.model.*;
import com.foxconn.bidding.service.EmailService;
import com.foxconn.bidding.util.EmailUtil;
import com.foxconn.bidding.util.MoneyNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private UserMapper userMapper;

    // 發佈訂單發送通知郵件
    @Override
    public ResultParam publish_bill_send_email(String bill_pkid) {
        //String address = "";
        String subject = "【模治檢具訂單信息服務平台】";
        String content = "【模治檢具訂單信息服務平台】";

        BILL_bean bill_bean = billMapper.query_bill_by_pkid(bill_pkid);
        String bill_no = bill_bean.getBill_no();
        String pd_type = bill_bean.getPd_type();
        String send_user_pkid = bill_bean.getSend_user_pkid();
        USER_INFO_bean send_user = userMapper.findUserById(send_user_pkid);
        String send_dept_name = send_user.getDept_name();
        String send_username = send_user.getUsername();
        // 根據訂單信息查詢相應的接單方郵箱地址
        List<String> email_list = userMapper.query_email_by_bill_info(bill_bean);
        if(email_list.isEmpty()) {
            throw new RuntimeException("無接單方郵箱地址");
        }
        /*
        for(int i = 0; i < email_list.size(); i++) {
            String email = email_list.get(i);
            if(i != email_list.size() - 1) {
                address += email + ";";
            } else {
                address = email;
            }
        }
         */

        // 生成郵件標題
        subject += "發單方【" + send_dept_name + "（" + send_username + "）】新發佈了"
                + "單號為【" + bill_no + "】的【" + pd_type + "】訂單";
        // 生成郵件內容
        content += "發單方<b style='color: blue;'>" + send_dept_name + "（" + send_username + "）</b>新發佈了"
                + "單號為<b style='color: blue;'>" + bill_no + "</b>的<b style='color: blue;'>" + pd_type + "</b>訂單<br><br>";
        content += "<b style='color: red;'>網址</b>:&nbsp;&nbsp;<a href='http://10.244.168.180/bid'>http://10.244.168.180/bid</a>";
        content += "&nbsp;&nbsp;&nbsp;&nbsp;(<b style='color: red;'>請用Chrome瀏覽器（谷歌瀏覽器）打開</b>)<br>";
        content += "<hr><b style='color: black;'>【訂單詳情】</b><br><br>";
        content += "<b style='color: black;'>類型</b>：<b style='color: blue;'>" + pd_type + "</b><br>";
        content += "<b style='color: black;'>單號</b>：<b style='color: blue;'>" + bill_no + "</b><br>";
        content += "<b style='color: black;'>發單方</b>：<b style='color: blue;'>" + send_dept_name + "（" + send_username + "）</b><br>";
        content += "<b style='color: black;'>需求數量</b>：<b style='color: blue;'>" + bill_bean.getAmount() + "</b><br>";
        content += "<b style='color: black;'>接受總價</b>：<b style='color: blue;'>" + bill_bean.getTotal_price() + bill_bean.getMoney_type() + "</b><br>";
        content += "<b style='color: black;'>交貨時間</b>：<b style='color: blue;'>" + bill_bean.getDeliver_date() + "</b><br>";
        content += "<b style='color: black;'>交貨地點</b>：<b style='color: blue;'>" + bill_bean.getDeliver_address() + "</b><br>";
        content += "<b style='color: black;'>交貨方式</b>：<b style='color: blue;'>" + bill_bean.getDeliver_way() + "</b><br>";
        content += "<b style='color: black;'>制作要求</b>：<b style='color: blue;'>" + bill_bean.getMake_requ() + "</b><br>";

        Boolean f_sendEmail = false;
        for(String address_: email_list) {
            f_sendEmail = EmailUtil.sendEmail(EmailContent.of(address_, subject, content));
            if(!f_sendEmail) {
                throw new RuntimeException("發佈訂單發送通知郵件失敗");
            }
        }

        return new ResultParam("1","發佈訂單發送通知郵件成功", null);
    }

    // 忘記密碼，發送驗證碼郵件
    @Override
    public ResultParam send_verification_code_mail(USER_INFO_bean user_bean, VERIFICATION_CODE_bean code_bean) {
        String code = code_bean.getCode();

        // 郵件地址
        String address = user_bean.getEmail();

        // 郵件主題
        String subject = "【模治檢具訂單信息服務平台】忘記密碼-驗證碼";

        // 郵件內容
        String content = "【模治檢具訂單信息服務平台】忘記密碼-驗證碼<br>";
        content += "驗證碼為：<b style='color: red;'>" + code + "</b>";

        Boolean f_sendEmail = EmailUtil.sendEmail(EmailContent.of(address, subject, content));
        if(!f_sendEmail) {
            throw new RuntimeException("發送驗證碼郵件失敗");
        }

        return new ResultParam("1", "發送驗證碼郵件成功", null);
    }

    // 獲取賬號信息的郵件內容
    public String getUserInfoContent(String content, USER_INFO_bean user_bean) {
        content += "<b style='color: red;'>網址</b>:&nbsp;&nbsp;<a href='http://10.244.168.180/bid'>http://10.244.168.180/bid</a>";
        content += "&nbsp;&nbsp;&nbsp;&nbsp;(<b style='color: red;'>請用Chrome瀏覽器（谷歌瀏覽器）打開</b>)<br>";
        content += "<hr>【賬號信息】：<br>";
        content += "賬號名稱：" + user_bean.getUsername() + "<br>";
        content += "賬號類別：" + ("send".equals(user_bean.getSend_recv_type()) ? "發單方": "接單方") + "<br>";
        content += "廠區：" + user_bean.getFctry_zone() + "<br>";
        content += "次集團：" + user_bean.getSecn_cmpy() + "<br>";
        content += "事業群：" + user_bean.getEntrps_group() + "<br>";
        content += "產品處：" + user_bean.getPd_office() + "<br>";
        content += "單位代碼：" + user_bean.getDept_code() + "<br>";
        content += "單位名稱：" + user_bean.getDept_name() + "<br>";
        content += "交易法人：" + user_bean.getLegal_person() + "<br>";
        content += "費用代碼：" + user_bean.getCost_code() + "<br>";
        if("recv".equals(user_bean.getSend_recv_type())) {
            content += "加工範圍：";
            String recv_mnufc_range_rel_id = user_bean.getRecv_mnufc_range_rel_id();
            List<RECV_MNUFC_RANGE_bean> recv_range_list = userMapper.query_recv_range_list(recv_mnufc_range_rel_id);
            for (RECV_MNUFC_RANGE_bean range: recv_range_list) {
                content += "&nbsp;&nbsp;" + range.getName();
            }
            content += "<br>";
        }
        content += "業務窗口：" + user_bean.getBusis_mngr() + "<br>";
        content += "固定電話：" + user_bean.getTel() + "<br>";
        content += "移動電話：" + user_bean.getPhone() + "<br>";
        content += "電子郵箱：" + user_bean.getEmail() + "<br>";
        content += "賬號簡介：" + user_bean.getSummary() + "<br>";
        if(user_bean.getRej_msg() != null && !"".equals(user_bean.getRej_msg())) {
            content += "駁回信息：" + user_bean.getRej_msg() + "<br>";
        }

        return content;
    }

    // 提交註冊，發送郵件給管理員
    @Override
    public ResultParam register_send_email_to_admin(USER_INFO_bean user_bean) {
        // 查詢管理員郵箱
        USER_INFO_bean admin_user = userMapper.query_user_by_username("admin");
        String address = admin_user.getEmail();

        // 郵件主題
        String subject = "【模治檢具訂單信息服務平台-賬號審核】管理員，你好，有新的賬號註冊需要你審核";

        // 郵件內容
        String content = "【模治檢具訂單信息服務平台-賬號審核】管理員，你好，有新的賬號註冊需要你審核<br>";
        content = getUserInfoContent(content, user_bean);

        Boolean f_sendEmail = EmailUtil.sendEmail(EmailContent.of(address, subject, content));
        if(!f_sendEmail) {
            throw new RuntimeException("提交註冊，發送郵件給管理員失敗");
        }

        return new ResultParam("1", "提交註冊，發送郵件給管理員成功", null);
    }

    // 賬號審核通過或駁回，發送郵件給該賬號的郵箱（1:通過，2：駁回）
    @Override
    public ResultParam user_au_send_email(USER_INFO_bean user_bean, String au_type) {
        String au_type_str =  ("1".equals(au_type)? "通過": "駁回");

        // 郵件地址
        String address = user_bean.getEmail();

        // 郵件主題
        String subject = "【模治檢具訂單信息服務平台-賬號審核】你提交的賬號註冊,管理員已";
        subject += au_type_str;

        // 郵件內容
        String content = subject;
        content = getUserInfoContent(content, user_bean);

        Boolean f_sendEmail = EmailUtil.sendEmail(EmailContent.of(address, subject, content));
        if(!f_sendEmail) {
            throw new RuntimeException("賬號已審核" + au_type_str + "，發送郵件給該賬號的郵箱失敗");
        }

        return new ResultParam("1", "賬號已審核 " + au_type_str + "，發送郵件給該賬號的郵箱成功", null);
    }

    // 每天早上9點發送發單和中標匯總情況郵件
    @Override
    public ResultParam sendStatisticsEmail() {
        // 查詢昨天發單的總筆數和總金額
        StatisticsContent last_day_send_statistics = billMapper.query_last_day_send_statistics();
        last_day_send_statistics = processStatisticsContent(last_day_send_statistics);
        String num_ls = MoneyNumberUtil.addDot(last_day_send_statistics.getTotal_num());
        String money_ls = MoneyNumberUtil.addDot(last_day_send_statistics.getTotal_money());

        // 查詢截止昨天累計發單的總筆數和總金額
        StatisticsContent total_send_statistics = billMapper.query_total_send_statistics();
        total_send_statistics = processStatisticsContent(total_send_statistics);
        String num_ts = MoneyNumberUtil.addDot(total_send_statistics.getTotal_num());
        String money_ts = MoneyNumberUtil.addDot(total_send_statistics.getTotal_money());

        // 查詢昨天中標的總筆數和總金額
        StatisticsContent last_day_win_bid_statistics = billMapper.query_last_day_win_bid_statistics();
        last_day_win_bid_statistics = processStatisticsContent(last_day_win_bid_statistics);
        String num_lw = MoneyNumberUtil.addDot(last_day_win_bid_statistics.getTotal_num());
        String money_lw = MoneyNumberUtil.addDot(last_day_win_bid_statistics.getTotal_money());

        // 查詢截止昨天累計中標的總筆數和總金額
        StatisticsContent total_win_bid_statistics = billMapper.query_total_win_bid_statistics();
        total_win_bid_statistics = processStatisticsContent(total_win_bid_statistics);
        String num_tw = MoneyNumberUtil.addDot(total_win_bid_statistics.getTotal_num());
        String money_tw = MoneyNumberUtil.addDot(total_win_bid_statistics.getTotal_money());

        String preDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * 7));
        String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date(new Date().getTime() - 1000 * 60 * 60 * 24));

        // 郵件地址
        String address = "hzcd-mis-sys4@mail.foxconn.com";

        // 郵件主題
        String subject = "【模治檢具訂單信息服務平台】上週[" + preDate + "~" + nowDate + "]訂單發單及中標狀況匯總";

        // 郵件內容
        String content = "<b style='color: blue;'>【模治檢具訂單信息服務平台】上週[" + preDate + "~" + nowDate + "]訂單發單及中標狀況匯總</b><br>";
        content += "<b style='color: red;'>網址</b>:&nbsp;&nbsp;<a href='http://10.244.168.180/bid'>http://10.244.168.180/bid</a>";
        content += "&nbsp;&nbsp;&nbsp;&nbsp;(<b style='color: red;'>請用Chrome瀏覽器（谷歌瀏覽器）打開</b>)<br><hr>";
        content += "<table border=\"1\">\n" +
                "<tr><td rowspan=\"2\"><b style='color: red;'>上週匯總</b>，" + preDate + "~" + nowDate + "</td><td>發單筆數:<b style='color: red;'>" + num_ls + "</b>筆</td><td>發單金額:<b style='color: red;'>" + money_ls + "</b>RMB</td></tr>\n" +
                "<tr><td>中標筆數:<b style='color: red;'>" + num_lw + "</b>筆</td><td>中標金額:<b style='color: red;'>" + money_lw + "</b>RMB</td></tr>\n" +
                "<tr><td rowspan=\"2\"><b style='color: red;'>累計匯總</b>，截止" + nowDate + "</td><td>發單筆數:<b style='color: red;'>" + num_ts + "</b>筆</td><td>發單金額:<b style='color: red;'>" + money_ts + "</b>RMB</td></tr>\n" +
                "<tr><td>中標筆數:<b style='color: red;'>" + num_tw + "</b>筆</td><td>中標金額:<b style='color: red;'>" + money_tw + "</b>RMB</td></tr>\n" +
                "</table>";

        // 查詢老闆郵箱地址list
        List<BOSS_EMAIL_ADDRESS_bean> boss_email_list = billMapper.query_boss_email_list();
        for(BOSS_EMAIL_ADDRESS_bean b: boss_email_list) {
            log.info(b.getEmail());
        }

        /* 測試代碼開始 */
        /*
        boss_email_list = new ArrayList<>();
        BOSS_EMAIL_ADDRESS_bean boos_email = new BOSS_EMAIL_ADDRESS_bean();
        boos_email.setEmail(address);
        boss_email_list.add(boos_email);
        */
        /* 測試代碼結束 */

        for(int i = 0; i < boss_email_list.size(); i++) {
            BOSS_EMAIL_ADDRESS_bean address_bean = boss_email_list.get(i);
            address = address_bean.getEmail();
            // 發送郵件
            Boolean f_sendEmail = EmailUtil.sendEmail(EmailContent.of(address, subject, content));
            if(!f_sendEmail) {
                log.error(nowDate + "早上9點發送發單和中標匯總情況郵件失敗");
                throw new RuntimeException("每天早上9點發送發單和中標匯總情況郵件失敗");
            }
            log.info("<" + address +  ">" + nowDate + "早上9點發送發單和中標匯總情況郵件成功");
        }

        return new ResultParam("1", "每天早上9點發送發單和中標匯總情況郵件成功", null);
    }

    // 處理StatisticsContent類對象
    @Override
    public StatisticsContent processStatisticsContent(StatisticsContent obj) {
        if(obj == null) {
            throw new RuntimeException("StatisticsContent類的示例對象為空");
        }
        Long total_money = obj.getTotal_money();

        if(total_money == null) {
            total_money = 0L;
            obj.setTotal_money(total_money);
        }

        return obj;
    }

    // 郵件通知發單用戶選標
    @Override
    public ResultParam noticeSendUserPickBid() {
        List<BILL_bean> bill_list = billMapper.query_need_pick_bid_bill();
        for(int i = 0; i < bill_list.size(); i++) {
            BILL_bean bill_bean = bill_list.get(i);
            String bill_no = bill_bean.getBill_no();
            String send_user_pkid = bill_bean.getSend_user_pkid();
            USER_INFO_bean send_user = userMapper.findUserById(send_user_pkid);
            String email = send_user.getEmail();

            // 郵件地址
            String address = email;

            // 郵件主題
            String subject = "【模治檢具訂單信息服務平台】選標通知-單號[" + bill_no + "]的訂單競價結束，請選標";

            // 郵件內容
            String content = "<b style='color: blue;'>【模治檢具訂單信息服務平台】選標通知-單號[" + bill_no + "]的訂單競價結束，請選標</b><br>";
            content += "<b style='color: red;'>網址</b>:&nbsp;&nbsp;<a href='http://10.244.168.180/bid'>http://10.244.168.180/bid</a>";
            content += "&nbsp;&nbsp;&nbsp;&nbsp;(<b style='color: red;'>請用Chrome瀏覽器（谷歌瀏覽器）打開</b>)<br><hr>";

            // 發送郵件
            Boolean f_sendEmail = EmailUtil.sendEmail(EmailContent.of(address, subject, content));
            if(!f_sendEmail) {
                log.error("<" + address +  ">郵件通知發單用戶單號[" + bill_no + "]的訂單需要選標失敗");
                throw new RuntimeException("郵件通知發單用戶選標失敗");
            }
            log.info("<" + address +  ">郵件通知發單用戶單號[" + bill_no + "]的訂單需要選標成功");
        }

        return new ResultParam("1", "郵件通知發單用戶選標成功", null);
    }

    // 郵件通知發單用戶此訂單流標
    @Override
    public ResultParam noticeSendUserBillFlowBid(USER_INFO_bean user_info_bean, BILL_bean bill_bean) {
        String email = user_info_bean.getEmail();
        String bill_no = bill_bean.getBill_no();

        // 郵件地址
        String address = email;

        // 郵件主題
        String subject = "【模治檢具訂單信息服務平台】流標通知-單號[" + bill_no + "]的訂單競價結束但無用戶接單，已流標";

        // 郵件內容
        String content = "<b style='color: blue;'>【模治檢具訂單信息服務平台】流標通知-單號[" + bill_no + "]的訂單競價結束但無用戶接單，已流標</b><br>";
        content += "<b style='color: red;'>網址</b>:&nbsp;&nbsp;<a href='http://10.244.168.180/bid'>http://10.244.168.180/bid</a>";
        content += "&nbsp;&nbsp;&nbsp;&nbsp;(<b style='color: red;'>請用Chrome瀏覽器（谷歌瀏覽器）打開</b>)<br><hr>";

        // 發送郵件
        Boolean f_sendEmail = EmailUtil.sendEmail(EmailContent.of(address, subject, content));
        if(!f_sendEmail) {
            log.error("<" + address +  ">郵件通知發單用戶單號[" + bill_no + "]的訂單流標失敗");
            throw new RuntimeException("郵件通知發單用戶此訂單流標失敗");
        }
        log.info("<" + address +  ">郵件通知發單用戶單號[" + bill_no + "]的訂單流標成功");

        return new ResultParam("1", "郵件通知發單用戶此訂單流標成功", null);
    }
}
