package com.foxconn.bidding.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxconn.bidding.model.EmailContent;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 郵件工具類
 */
@Slf4j
public class EmailUtil {
    // 使用apache httpclient 發送json數據
    private static String sendJsonWithApache(String json) throws Exception {
        String result = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        // String url = "http://localhost:8080/SmtpSend1/sendmail";
        String url = "http://10.244.231.65:8080/Smtp/sendmail";
        HttpPost postRequest = new HttpPost(url);
        String encodeCharset = "utf-8";
        StringEntity input = new StringEntity(json, encodeCharset);
        input.setContentType("application/json;charset:" + encodeCharset);
        postRequest.setEntity(input);

        HttpResponse response = httpClient.execute(postRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            log.error("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        // 將請求的響應結果打印出來
        BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), encodeCharset));
        String output = "";
        log.info("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            result += output;
            log.info(output);
        }
        httpClient.close();

        return result;
    }

    // 發送郵件
    public static Boolean sendEmail(EmailContent mc) {
        Boolean flag = true;

        String to = mc.getTo();
        if(to == null || "".equals(to)) {
            log.error("發送郵件失敗，郵件收件人為空");
            throw new RuntimeException("發送郵件失敗，郵件收件人為空");
        }

        String subject = mc.getSubject();
        if(subject == null || "".equals(subject)) {
            log.error("發送郵件失敗，郵件標題為空");
            throw new RuntimeException("發送郵件失敗，郵件標題為空");
        }

        String body = mc.getBody();
        if(body == null || "".equals(body)) {
            log.error("發送郵件失敗，郵件內容為空");
            throw new RuntimeException("發送郵件失敗，郵件內容為空");
        }

        try {
            mc.setSubtype("text/html");
            mc.setUserid("T0D507");
            mc.setPassword("TYYKjtXMnZyn");
            // 將對象轉化爲json
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(mc);
            // 發送json到郵件api
            String sj = sendJsonWithApache(jsonInString);
            log.info("【發送郵件結果】:" + sj);
            log.info("郵件已經發送成功\n【地址】：<" + mc.getTo() + ">【主題】:" + mc.getSubject());
        } catch (Exception e) {
            flag = false;
            log.error(e.toString(), e.fillInStackTrace());
            e.printStackTrace();
        }

        return flag;
    }
}
