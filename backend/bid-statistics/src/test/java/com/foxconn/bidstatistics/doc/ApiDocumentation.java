package com.foxconn.bidstatistics.doc;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDocumentation{

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

    // 【01】實時看板
    @Test
    public void realtimeDataBoard() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("factory", "全部");

        this.mockMvc.perform(get("/realtimeDataBoard").params(params)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("realtime-data-board",
                        requestParameters(parameterWithName("factory").description("廠區")),
                        responseFields(fieldWithPath("code").description("結果代碼"),
                                fieldWithPath("msg").description("結果信息"),
                                fieldWithPath("t").description("結果數據"),
                                fieldWithPath("t.curMonth").description("本月數據"),
                                fieldWithPath("t.preMonth").description("上月數據"),
                                fieldWithPath("t.*[].date").description("日期"),
                                fieldWithPath("t.*[].curDaySum").description("當天總計"),
                                fieldWithPath("t.*[].totalSum").description("截止當天總累計"),
                                fieldWithPath("t.*[].thisMonthSum").description("本月累計"),
                                fieldWithPath("t.*[].preMonthSum").description("上月累計"))));
    }

    // 【02】發佈類型
    @Test
    public void publishType() throws Exception {
        this.mockMvc.perform(get("/publishType").contentType("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andDo(document("publish-type", responseFields(
                    fieldWithPath("code").description("結果代碼"),
                    fieldWithPath("msg").description("結果信息"),
                    fieldWithPath("t").description("結果數據"),
                    fieldWithPath("t.[].name").description("發佈類型名稱"),
                    fieldWithPath("t.[].value").description("發佈類型數量"))));
    }

    // 【03】客戶營銷
    @Test
    public void customerMarketing() throws Exception {
        this.mockMvc.perform(get("/customerMarketing").contentType("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andDo(document("customer-marketing", responseFields(
                    fieldWithPath("code").description("結果代碼"),
                    fieldWithPath("msg").description("結果信息"),
                    fieldWithPath("t").description("結果數據"),
                    fieldWithPath("t.[].name").description("用戶類型名稱"),
                    fieldWithPath("t.[].value").description("用戶類型數量")
            )));
    }

    // 【04】訂單分析
    @Test
    public void billAnalysis() throws Exception {
        this.mockMvc.perform(get("/billAnalysis").contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("bill-analysis", responseFields(
                        fieldWithPath("code").description("結果代碼"),
                        fieldWithPath("msg").description("結果信息"),
                        fieldWithPath("t").description("結果數據"),
                        fieldWithPath("t.[].name").description("訂單流程狀態類型名稱"),
                        fieldWithPath("t.[].value").description("訂單流程狀態類型數量")
                )));
    }

    // 【05】廠部分析
    @Test
    public void factoryAnalysis() throws Exception {
        this.mockMvc.perform(get("/factoryAnalysis").contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("factory-analysis", responseFields(
                        fieldWithPath("code").description("結果代碼"),
                        fieldWithPath("msg").description("結果信息"),
                        fieldWithPath("t").description("結果數據"),
                        fieldWithPath("t.[].name").description("廠區名稱"),
                        fieldWithPath("t.[].value").description("廠區屬性list"),
                        fieldWithPath("t.[].value[].name").description("廠區屬性名"),
                        fieldWithPath("t.[].value[].value").description("廠區屬性值")
                )));
    }

    // 【06】發單用戶實時排行
    @Test
    public void sendUserRank() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("timeLimit", "thisYear");

        this.mockMvc.perform(get("/sendUserRank").params(params).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("send-user-rank",
                        requestParameters(parameterWithName("timeLimit").description("時間範圍（本月：thisMonth, 本年：thisYear）")),
                        responseFields(
                                fieldWithPath("code").description("結果代碼"),
                                fieldWithPath("msg").description("結果信息"),
                                fieldWithPath("t").description("結果數據"),
                                fieldWithPath("t.[].userId").description("用戶id"),
                                fieldWithPath("t.[].username").description("用戶名"),
                                fieldWithPath("t.[].deptName").description("單位名稱"),
                                fieldWithPath("t.[].sendNum").description("發單數"),
                                fieldWithPath("t.[].sendPrice").description("發單金額"),
                                fieldWithPath("t.[].receiveRate").description("（被）接單率"),
                                fieldWithPath("t.[].score").description("客服評分"),
                                fieldWithPath("t.[].payment").description("付款時效"),
                                fieldWithPath("t.[].service").description("服務態度")
                        )));
    }

    // 【07】接單用戶實時排行
    @Test
    public void receiveUserRank() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("timeLimit", "thisYear");

        this.mockMvc.perform(get("/receiveUserRank").params(params).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("receive-user-rank",
                        requestParameters(parameterWithName("timeLimit").description("時間範圍（本月：thisMonth, 本年：thisYear）")),
                        responseFields(
                                fieldWithPath("code").description("結果代碼"),
                                fieldWithPath("msg").description("結果信息"),
                                fieldWithPath("t").description("結果數據"),
                                fieldWithPath("t.[].userId").description("用戶id"),
                                fieldWithPath("t.[].username").description("用戶名"),
                                fieldWithPath("t.[].deptName").description("單位名稱"),
                                fieldWithPath("t.[].receiveNum").description("接单數"),
                                fieldWithPath("t.[].winNum").description("中標數"),
                                fieldWithPath("t.[].winPrice").description("中標金額"),
                                fieldWithPath("t.[].winRate").description("中標率"),
                                fieldWithPath("t.[].score").description("綜合評分"),
                                fieldWithPath("t.[].outRate").description("出貨時效"),
                                fieldWithPath("t.[].outQuality").description("出貨質量"),
                                fieldWithPath("t.[].service").description("服務態度")
                        )));
    }
}
