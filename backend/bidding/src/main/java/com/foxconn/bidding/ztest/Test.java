package com.foxconn.bidding.ztest;

import com.foxconn.bidding.model.BILL_bean;
import com.foxconn.bidding.model.EmailContent;
import com.foxconn.bidding.model.GIVE_PRICE_MSTR_bean;
import com.foxconn.bidding.util.EmailUtil;
import com.foxconn.bidding.util.MoneyNumberUtil;
import com.foxconn.bidding.util.SimpleEncodeUtil;
import org.springframework.beans.BeanUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        // test();
        // test2();
        //testSendEmail();
        //testRandomCode();
        //testDecodePassword();
        //testNumberOperation();
        //testNumberUtil();
        testDate();
    }

    public static void testDate() {
        String lastDay = new SimpleDateFormat("yyy-MM-dd").format(new Date(new Date().getTime() - 1000 * 60 * 60 * 24));
        System.out.println(lastDay);
    }

    public static void testNumberUtil() {
        Long money = 3124124831465738446L;
        System.out.println(MoneyNumberUtil.addDot(money));
    }

    public static void testNumberOperation() {
        System.out.println(2 % 3);
    }

    public static void testDecodePassword() {
        String encode = SimpleEncodeUtil.encode("\u0017\u001B\fVJHIH");
        System.out.println(encode);
    }

    public static void testRandomCode() {
        Integer random_code = new Random().nextInt(888889) + 111111;
        System.out.println(random_code);
    }

    public static void testSendEmail() {
        String address = "hzcd-mis-sys4@mail.foxconn.com";
        String subject = "【模治檢具訂單管理平台】";
        String content = "【模治檢具訂單管理平台】";

        BILL_bean bill_bean = new BILL_bean();
        String bill_no = bill_bean.getBill_no();
        String pd_type = bill_bean.getPd_type();

        String send_dept_name = "【測試賬號】測試發單方單位";
        String send_username = "send";

        // 生成郵件標題
        subject += "發單方【" + send_dept_name + "（" + send_username + "）】新發佈了"
                + "單號為【" + bill_no + "】的【" + pd_type + "】訂單";
        // 生成郵件內容
        content += "發單方<b style='color: blue;'>" + send_dept_name + "（" + send_username + "）</b>新發佈了"
                + "單號為<b style='color: blue;'>" + bill_no + "</b>的<b style='color: blue;'>" + pd_type + "</b>訂單<br><br>";
        content += "<b style='color: red;'>網址</b>:&nbsp;&nbsp;<a href='http://10.244.231.103:8086'>http://10.244.231.103:8086</a>";
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

        Boolean f_sendEmail = EmailUtil.sendEmail(EmailContent.of(address, subject, content));
        if(!f_sendEmail) {
            throw new RuntimeException("發佈訂單發送通知郵件失敗");
        }
    }

    public static void test2() {
        List<GIVE_PRICE_MSTR_bean> zone = new ArrayList<>();
        GIVE_PRICE_MSTR_bean bean_1 = new GIVE_PRICE_MSTR_bean();
        bean_1.setBill_pkid("bean_1");
        bean_1.setDeliver_date("2019-10-30 00:00:00");
        GIVE_PRICE_MSTR_bean bean_2 = new GIVE_PRICE_MSTR_bean();
        bean_2.setBill_pkid("bean_2");
        bean_2.setDeliver_date("2019-10-22 00:00:00");
        GIVE_PRICE_MSTR_bean bean_3 = new GIVE_PRICE_MSTR_bean();
        bean_3.setBill_pkid("bean_3");
        bean_3.setDeliver_date("2019-10-15 00:00:00");
        GIVE_PRICE_MSTR_bean bean_4 = new GIVE_PRICE_MSTR_bean();
        bean_4.setBill_pkid("bean_4");
        bean_4.setDeliver_date("2019-10-20 00:00:00");
        GIVE_PRICE_MSTR_bean bean_5 = new GIVE_PRICE_MSTR_bean();
        bean_5.setBill_pkid("bean_5");
        bean_5.setDeliver_date("2019-10-11 00:00:00");
        GIVE_PRICE_MSTR_bean bean_6 = new GIVE_PRICE_MSTR_bean();
        bean_6.setBill_pkid("bean_6");
        bean_6.setDeliver_date("2019-10-31 00:00:00");

        zone.add(bean_1);
        zone.add(bean_2);
        zone.add(bean_3);
        zone.add(bean_4);
        zone.add(bean_5);
        zone.add(bean_6);

        for(int i = 0; i < zone.size() - 1; i++) {
            for(int j = i + 1; j < zone.size(); j++) {
                GIVE_PRICE_MSTR_bean bean_i = zone.get(i);
                GIVE_PRICE_MSTR_bean bean_j = zone.get(j);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date deliver_date_i = null;
                Date deliver_date_j = null;
                try {
                    deliver_date_i = sdf.parse(bean_i.getDeliver_date());
                    deliver_date_j = sdf.parse(bean_j.getDeliver_date());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                GIVE_PRICE_MSTR_bean i_bean = new GIVE_PRICE_MSTR_bean();
                GIVE_PRICE_MSTR_bean j_bean = new GIVE_PRICE_MSTR_bean();
                BeanUtils.copyProperties(bean_i, i_bean);
                BeanUtils.copyProperties(bean_j, j_bean);

                if(deliver_date_i.after(deliver_date_j)) {
                    zone.set(i, j_bean);
                    zone.set(j, i_bean);
                } else {
                    zone.set(i, i_bean);
                    zone.set(j, j_bean);
                }
            }
        }

        for(int i = 0; i < zone.size(); i++) {
            GIVE_PRICE_MSTR_bean bean = zone.get(i);
            System.out.println(bean.getBill_pkid());
        }
    }

    public static void test() {
        Integer send_total_price = 5856;
        Integer recv_total_price = 10086;
        Float diff_ratio = 0.00f;
        diff_ratio = (send_total_price - recv_total_price) / (float) send_total_price;
        diff_ratio = Math.round(diff_ratio * 100) / (float) 100;

        System.out.println(diff_ratio);
        System.out.println(diff_ratio < -0.05);
    }
}
