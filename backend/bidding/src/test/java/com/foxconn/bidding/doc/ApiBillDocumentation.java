package com.foxconn.bidding.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 訂單模塊api測試
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiBillDocumentation {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation).snippets().withTemplateFormat(TemplateFormats.asciidoctor()).withEncoding("UTF-8")
                        .and().uris().withScheme("http").withHost("10.244.231.103").withPort(14000))
                .alwaysDo(document("{method-name}/{step}/"))
                .build();
    }

    // 【3】未登錄查詢訂單list(分頁查詢)
    @Test
    public void query_bill_list_not_login() throws Exception {
        Map<String, Object> billBean = new HashMap<>();
        billBean.put("recv_num_type", "multiple");
        billBean.put("pageIndex", 1);
        billBean.put("pageSize", 2);

        this.mockMvc.perform(post("/bill/query_bill_list_not_login")
                .contentType("application/json;charset=UTF-8")
                .content(this.mapper.writeValueAsString(billBean)))
                .andExpect(status().isOk())
                .andDo(document("query_bill_list_not_login",
                        requestFields(
                                fieldWithPath("recv_num_type").description("接單方個數類型, all:全部，single:單個，multiple:多個"),
                                fieldWithPath("pageIndex").description("分頁頁碼"),
                                fieldWithPath("pageSize").description("分頁每頁條數")),
                        responseFields(
                                subsectionWithPath("code").description("結果代碼"),
                                subsectionWithPath("msg").description("結果信息"),
                                subsectionWithPath("t").description("結果數據"),
                                subsectionWithPath("t.bill_list").description("訂單列表"),
                                subsectionWithPath("t.page_total").description("分頁總頁數"),
                                subsectionWithPath("t.row_total").description("分頁數據總條數"),
                                subsectionWithPath("t.bill_list[].give_recv_user_list").description("參與報價的接單用戶list"),
                                subsectionWithPath("t.bill_list[].give_recv_user_list[].username").description("接單用戶的用戶名"),
                                subsectionWithPath("t.bill_list[].give_recv_user_list[].dept_name").description("接單用戶的單位名稱"),
                                subsectionWithPath("t.bill_list[].give_recv_user_list[].give_price").description("接單用戶的報價對象"),
                                subsectionWithPath("t.bill_list[].give_recv_user_list[].give_price.total_price").description("接單用戶的總報價金額"))
                ));
    }
}
