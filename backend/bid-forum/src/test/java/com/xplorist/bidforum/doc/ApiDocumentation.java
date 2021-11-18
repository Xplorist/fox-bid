package com.xplorist.bidforum.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xplorist.bidforum.model.dto.ResultDTO;
import com.xplorist.bidforum.remote.BidSsoRemote;
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
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDocumentation {
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BidSsoRemote bidSsoRemote;

    private MockMvc mockMvc;

    // 全局token
    private String token;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation).snippets().withTemplateFormat(TemplateFormats.asciidoctor()).withEncoding("UTF-8")
                        .and().uris().withScheme("http").withHost("10.244.231.103").withPort(14000))
                .alwaysDo(document("{method-name}/{step}/"))
                .build();

        this.token = login();
    }

    // 模拟登录获得token
    public String login() {
        ResultDTO resultDTO = bidSsoRemote.loginAjax("send", DigestUtils.md5DigestAsHex("send".getBytes()));
        if ("1".equals(resultDTO.getCode())) {
            LinkedHashMap<String, String> map = (LinkedHashMap) resultDTO.getT();
            return map.get("token");
        } else {
            return "";
        }
    }

    // 【01】发帖（需要token）
    //@Test
    public void sendPost() throws Exception {
        /*
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("title", "test title");
        params.add("content", "test content");
        */
        Map<String, Object> dto = new HashMap<>();
        dto.put("title", "Spring Rest Docs test title ");
        dto.put("content", "Spring Rest Docs test content ");

        this.mockMvc.perform(post("/sendPost")
                .header("token", token)
                .content(this.objectMapper.writeValueAsString(dto))
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("send-post",
                        requestFields(
                                fieldWithPath("title").description("帖子标题"),
                                fieldWithPath("content").description("帖子内容")
                        ),
                        responseFields(
                                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                                fieldWithPath("msg").description("结果信息"),
                                fieldWithPath("t").description("结果数据")
                        )
                ));
    }

    // 【02】分页查询所有帖子list
    @Test
    public void listPostsByPage() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("pageIndex", "1");
        params.add("pageSize", "10");

        this.mockMvc.perform(
                get("/listPostsByPage")
                .params(params)
                .contentType("application/json;charset=UTF-8")
                )
                .andExpect(status().isOk())
                .andDo(document("list-posts-by-page",
                        requestParameters(
                                parameterWithName("pageIndex").description("分页页码"),
                                parameterWithName("pageSize").description("每页数据条数")
                        ),
                        responseFields(
                                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                                fieldWithPath("msg").description("结果信息"),
                                subsectionWithPath("t").description("结果对象"),
                                fieldWithPath("t.rowTotal").description("分页数据总条数"),
                                fieldWithPath("t.pageTotal").description("分页数据总页数"),
                                fieldWithPath("t.list").description("帖子列表"),
                                fieldWithPath("t.list.[].id").description("帖子表id"),
                                fieldWithPath("t.list.[].title").description("帖子标题"),
                                //fieldWithPath("t.list.[].content").description("帖子内容"),
                                fieldWithPath("t.list.[].creatorId").description("发帖人id"),
                                fieldWithPath("t.list.[].createTime").description("发帖时间"),
                                fieldWithPath("t.list.[].updateTime").description("修改时间"),
                                fieldWithPath("t.list.[].deleteFlag").description("删除标志（0：未删，1：已删）"),
                                fieldWithPath("t.list.[].deleteTime").description("删除时间"),
                                fieldWithPath("t.list.[].replyList").description("帖子的回复列表"),
                                subsectionWithPath("t.list.[].creator").description("帖子创建人"),
                                fieldWithPath("t.list.[].creator.username").description("帖子创建人用户名"),
                                fieldWithPath("t.list.[].creator.deptName").description("帖子创建人部门名称")
                                )));

    }

    // 【03】回复帖子（需要token）
    //@Test
    public void sendReply() throws Exception {
        Map<String, Object> dto = new HashMap<>();
        dto.put("replyType", "toReply");
        dto.put("bbsPostId", "1243012815476793346");
        dto.put("parentId", "1243055519082500097");
        dto.put("content", "test reply toReply");

        this.mockMvc.perform(
                post("/sendReply")
                .header("token", token)
                .contentType("application/json;charset=UTF-8")
                .content(this.objectMapper.writeValueAsString(dto))
        ).andExpect(status().isOk())
        .andDo(document("send-reply",
                requestFields(
                        fieldWithPath("replyType").description("回复类型（toPost:回复帖子; toReply:给回复添加子回复）"),
                        fieldWithPath("bbsPostId").description("帖子表ID"),
                        fieldWithPath("parentId").description("父回复表ID"),
                        fieldWithPath("content").description("内容")
                        ),
                responseFields(
                        fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                        fieldWithPath("msg").description("结果信息"),
                        subsectionWithPath("t").description("结果对象")
                        )
                )
        );
    }

    // 【04】根据ID查询单个帖子
    @Test
    public void getPostById() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("postId", "1243012815476793346");

        this.mockMvc.perform(
                get("/getPostById")
                        .params(params)
                        .contentType("application/json;charset=UTF-8")
        )
                .andExpect(status().isOk())
                .andDo(document("get-post-by-id",
                        requestParameters(
                                parameterWithName("postId").description("帖子id")
                        ),
                        responseFields(
                                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                                fieldWithPath("msg").description("结果信息"),
                                subsectionWithPath("t").description("结果对象"),
                                fieldWithPath("t.id").description("帖子表id"),
                                fieldWithPath("t.title").description("帖子标题"),
                                fieldWithPath("t.content").description("帖子内容"),
                                fieldWithPath("t.creatorId").description("发帖人id"),
                                fieldWithPath("t.createTime").description("发帖时间"),
                                fieldWithPath("t.updateTime").description("修改时间"),
                                fieldWithPath("t.deleteFlag").description("删除标志（0：未删，1：已删）"),
                                fieldWithPath("t.deleteTime").description("删除时间"),
                                subsectionWithPath("t.creator").description("帖子创建人"),
                                fieldWithPath("t.creator.username").description("帖子创建人用户名"),
                                fieldWithPath("t.creator.deptName").description("帖子创建人部门名称"),
                                fieldWithPath("t.replyList").description("帖子的回复列表"),
                                fieldWithPath("t.replyList.[].id").description("回复表id"),
                                fieldWithPath("t.replyList.[].bbsPostId").description("帖子表id"),
                                fieldWithPath("t.replyList.[].parentId").description("父回复表id"),
                                fieldWithPath("t.replyList.[].content").description("回复内容"),
                                fieldWithPath("t.replyList.[].creatorId").description("创建人id"),
                                fieldWithPath("t.replyList.[].createTime").description("创建时间"),
                                fieldWithPath("t.replyList.[].updateTime").description("修改时间"),
                                fieldWithPath("t.replyList.[].deleteFlag").description("删除标志（0：未删，1：已删）"),
                                fieldWithPath("t.replyList.[].deleteTime").description("删除时间"),
                                fieldWithPath("t.replyList.[].replyType").description("回复类型（toPost:回复帖子; toReply:给回复添加子回复）"),
                                fieldWithPath("t.replyList.[].replyList").description("回复的回复列表"),
                                subsectionWithPath("t.replyList.[].creator").description("回复创建人"),
                                fieldWithPath("t.replyList.[].creator.username").description("回复创建人用户名"),
                                fieldWithPath("t.replyList.[].creator.deptName").description("回复创建人部门名称")
                        )));
    }

    // 【05】查询通知list
    @Test
    public void listNotice() throws Exception {
        // 查询前先新增
        sendReply();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("userId", "95B2AE66BC23A45AE050F40A5BE93AA7");

        this.mockMvc.perform(get("/listNotice")
                .params(params)
                .contentType("application/json;charset=UTF-8"))
        .andExpect(status().isOk())
        .andDo(document("listNotice", requestParameters(
                parameterWithName("userId").description("用户id")
        ),responseFields(
                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                fieldWithPath("msg").description("结果信息"),
                subsectionWithPath("t").description("结果对象"),
                fieldWithPath("t.[].id").description("通知id"),
                fieldWithPath("t.[].bbsPostId").description("帖子id"),
                fieldWithPath("t.[].parentReplyId").description("父回复id"),
                fieldWithPath("t.[].childReplyId").description("子回复id"),
                fieldWithPath("t.[].receiveUserId").description("接收人id（父回复创建人id）"),
                fieldWithPath("t.[].sendUserId").description("发送人id（子回复创建人id）"),
                fieldWithPath("t.[].createTime").description("创建时间"),
                fieldWithPath("t.[].updateTime").description("修改时间"),
                fieldWithPath("t.[].deleteFlag").description("删除标志（0：未删，1：已删）"),
                fieldWithPath("t.[].deleteTime").description("删除时间"),
                subsectionWithPath("t.[].post").description("帖子"),
                fieldWithPath("t.[].post.title").description("帖子标题"),
                fieldWithPath("t.[].post.content").description("帖子内容"),
                subsectionWithPath("t.[].parentReply").description("父回复"),
                fieldWithPath("t.[].parentReply.content").description("回复内容"),
                subsectionWithPath("t.[].childReply").description("子回复"),
                fieldWithPath("t.[].childReply.content").description("子回复内容"),
                subsectionWithPath("t.[].receiveUser").description("接收通知人（父回复创建人）"),
                fieldWithPath("t.[].receiveUser.username").description("接收通知人（父回复创建人）用户名"),
                subsectionWithPath("t.[].sendUser").description("发送通知人（子回复创建人）"),
                fieldWithPath("t.[].sendUser.username").description("发送通知人（子回复创建人）用户名")
                )
        ));
    }

    // 【06】删除单条通知(需要token)
    //@Test
    public void deleteSingleNotice() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("noticeId", "1245169515931729922");

        //this.token = login();// 单个方法测试时使用，全局测试时注释

        this.mockMvc.perform(get("/deleteSingleNotice")
                .params(params)
                .header("token", this.token)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(document("deleteSingleNotice",
                        requestParameters(
                                parameterWithName("noticeId").description("通知id")
                        ),
                        responseFields(
                                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                                fieldWithPath("msg").description("结果信息"),
                                subsectionWithPath("t").description("结果对象")
                        )
                ));
    }

    // 【07】一键删除当前用户的所有通知（需要token）
    //@Test
    public void deleteAllNotice() throws Exception {
       /* MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("key1", "value1");
        params.add("key2", "value2");*/

        //this.token = login();// 单个方法测试时使用，全局测试时注释

        this.mockMvc.perform(get("/deleteAllNotice")
                //.params(params)
                .header("token", this.token)
                .contentType("application/json;charset=UTF-8")
        ).andExpect(status().isOk())
                .andDo(document("deleteAllNotice",
                        /*requestParameters(
                                parameterWithName("key1").description("key1描述"),
                                parameterWithName("key2").description("key2描述")
                        ),*/
                        responseFields(
                                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                                fieldWithPath("msg").description("结果信息"),
                                subsectionWithPath("t").description("结果对象")
                        )
                ));
    }

    // 【08】删除帖子（需要token）
    //@Test
    public void deletePost() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("postId", "1243732259484254210");

        //this.token = login();// 单个方法测试时使用，全局测试时注释

        this.mockMvc.perform(get("/deletePost")
                .params(params)
                .header("token", this.token)
                .contentType("application/json;charset=UTF-8")
        ).andExpect(status().isOk())
                .andDo(document("deletePost",
                        requestParameters(
                                parameterWithName("postId").description("帖子id")
                        ),
                        responseFields(
                                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                                fieldWithPath("msg").description("结果信息"),
                                subsectionWithPath("t").description("结果对象")
                        )
                ));
    }

    // 【09】删除回复（需要token）
    //@Test
    public void deleteReply() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("replyId", "DDC6950FA38F4E23B416BB0051A7A044");

        //this.token = login();// 单个方法测试时使用，全局测试时注释

        this.mockMvc.perform(get("/deleteReply")
                .params(params)
                .header("token", this.token)
                .contentType("application/json;charset=UTF-8")
        ).andExpect(status().isOk())
                .andDo(document("deleteReply",
                        requestParameters(
                                parameterWithName("replyId").description("回复id")
                        ),
                        responseFields(
                                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                                fieldWithPath("msg").description("结果信息"),
                                subsectionWithPath("t").description("结果对象")
                        )
                ));
    }

    // get请求测试模板(需要token)
    //@Test
    public void getTemplate() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("key1", "value1");
        params.add("key2", "value2");

        //this.token = login();// 单个方法测试时使用，全局测试时注释

        this.mockMvc.perform(get("/getTemplate")
                .params(params)
                .header("token", this.token)
                .contentType("application/json;charset=UTF-8")
        ).andExpect(status().isOk())
        .andDo(document("getTemplate",
                requestParameters(
                        parameterWithName("key1").description("key1描述"),
                        parameterWithName("key2").description("key2描述")
                ),
                responseFields(
                        fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                        fieldWithPath("msg").description("结果信息"),
                        subsectionWithPath("t").description("结果对象")
                )
                ));
    }

    // post请求测试模板(需要token)
    //@Test
    public void postTemplate() throws Exception {
        Map<String, Object> dto = new HashMap<>();
        dto.put("key1", "value1");
        dto.put("key2", "value2");

        //this.token = login();// 单个方法测试时使用，全局测试时注释

        this.mockMvc.perform(
                post("/postTemplate")
                        .header("token", this.token)
                        .contentType("application/json;charset=UTF-8")
                        .content(this.objectMapper.writeValueAsString(dto))
        ).andExpect(status().isOk())
                .andDo(document("postTemplate",
                        requestFields(
                                fieldWithPath("key1").description("key1描述"),
                                fieldWithPath("key2").description("key2描述")
                        ),
                        responseFields(
                                fieldWithPath("code").description("结果代码（0：失败，1：成功）"),
                                fieldWithPath("msg").description("结果信息"),
                                subsectionWithPath("t").description("结果对象")
                        )
                        )
                );
    }
}
