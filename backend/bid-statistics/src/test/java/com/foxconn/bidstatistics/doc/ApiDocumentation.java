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

    // ???01???????????????
    @Test
    public void realtimeDataBoard() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("factory", "??????");

        this.mockMvc.perform(get("/realtimeDataBoard").params(params)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("realtime-data-board",
                        requestParameters(parameterWithName("factory").description("??????")),
                        responseFields(fieldWithPath("code").description("????????????"),
                                fieldWithPath("msg").description("????????????"),
                                fieldWithPath("t").description("????????????"),
                                fieldWithPath("t.curMonth").description("????????????"),
                                fieldWithPath("t.preMonth").description("????????????"),
                                fieldWithPath("t.*[].date").description("??????"),
                                fieldWithPath("t.*[].curDaySum").description("????????????"),
                                fieldWithPath("t.*[].totalSum").description("?????????????????????"),
                                fieldWithPath("t.*[].thisMonthSum").description("????????????"),
                                fieldWithPath("t.*[].preMonthSum").description("????????????"))));
    }

    // ???02???????????????
    @Test
    public void publishType() throws Exception {
        this.mockMvc.perform(get("/publishType").contentType("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andDo(document("publish-type", responseFields(
                    fieldWithPath("code").description("????????????"),
                    fieldWithPath("msg").description("????????????"),
                    fieldWithPath("t").description("????????????"),
                    fieldWithPath("t.[].name").description("??????????????????"),
                    fieldWithPath("t.[].value").description("??????????????????"))));
    }

    // ???03???????????????
    @Test
    public void customerMarketing() throws Exception {
        this.mockMvc.perform(get("/customerMarketing").contentType("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andDo(document("customer-marketing", responseFields(
                    fieldWithPath("code").description("????????????"),
                    fieldWithPath("msg").description("????????????"),
                    fieldWithPath("t").description("????????????"),
                    fieldWithPath("t.[].name").description("??????????????????"),
                    fieldWithPath("t.[].value").description("??????????????????")
            )));
    }

    // ???04???????????????
    @Test
    public void billAnalysis() throws Exception {
        this.mockMvc.perform(get("/billAnalysis").contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("bill-analysis", responseFields(
                        fieldWithPath("code").description("????????????"),
                        fieldWithPath("msg").description("????????????"),
                        fieldWithPath("t").description("????????????"),
                        fieldWithPath("t.[].name").description("??????????????????????????????"),
                        fieldWithPath("t.[].value").description("??????????????????????????????")
                )));
    }

    // ???05???????????????
    @Test
    public void factoryAnalysis() throws Exception {
        this.mockMvc.perform(get("/factoryAnalysis").contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("factory-analysis", responseFields(
                        fieldWithPath("code").description("????????????"),
                        fieldWithPath("msg").description("????????????"),
                        fieldWithPath("t").description("????????????"),
                        fieldWithPath("t.[].name").description("????????????"),
                        fieldWithPath("t.[].value").description("????????????list"),
                        fieldWithPath("t.[].value[].name").description("???????????????"),
                        fieldWithPath("t.[].value[].value").description("???????????????")
                )));
    }

    // ???06???????????????????????????
    @Test
    public void sendUserRank() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("timeLimit", "thisYear");

        this.mockMvc.perform(get("/sendUserRank").params(params).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("send-user-rank",
                        requestParameters(parameterWithName("timeLimit").description("????????????????????????thisMonth, ?????????thisYear???")),
                        responseFields(
                                fieldWithPath("code").description("????????????"),
                                fieldWithPath("msg").description("????????????"),
                                fieldWithPath("t").description("????????????"),
                                fieldWithPath("t.[].userId").description("??????id"),
                                fieldWithPath("t.[].username").description("?????????"),
                                fieldWithPath("t.[].deptName").description("????????????"),
                                fieldWithPath("t.[].sendNum").description("?????????"),
                                fieldWithPath("t.[].sendPrice").description("????????????"),
                                fieldWithPath("t.[].receiveRate").description("??????????????????"),
                                fieldWithPath("t.[].score").description("????????????"),
                                fieldWithPath("t.[].payment").description("????????????"),
                                fieldWithPath("t.[].service").description("????????????")
                        )));
    }

    // ???07???????????????????????????
    @Test
    public void receiveUserRank() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("timeLimit", "thisYear");

        this.mockMvc.perform(get("/receiveUserRank").params(params).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("receive-user-rank",
                        requestParameters(parameterWithName("timeLimit").description("????????????????????????thisMonth, ?????????thisYear???")),
                        responseFields(
                                fieldWithPath("code").description("????????????"),
                                fieldWithPath("msg").description("????????????"),
                                fieldWithPath("t").description("????????????"),
                                fieldWithPath("t.[].userId").description("??????id"),
                                fieldWithPath("t.[].username").description("?????????"),
                                fieldWithPath("t.[].deptName").description("????????????"),
                                fieldWithPath("t.[].receiveNum").description("?????????"),
                                fieldWithPath("t.[].winNum").description("?????????"),
                                fieldWithPath("t.[].winPrice").description("????????????"),
                                fieldWithPath("t.[].winRate").description("?????????"),
                                fieldWithPath("t.[].score").description("????????????"),
                                fieldWithPath("t.[].outRate").description("????????????"),
                                fieldWithPath("t.[].outQuality").description("????????????"),
                                fieldWithPath("t.[].service").description("????????????")
                        )));
    }
}
