package com.xplorist.bidforum.controller;

import com.xplorist.bidforum.model.dto.BbsPostDTO;
import com.xplorist.bidforum.model.dto.BbsReplyDTO;
import com.xplorist.bidforum.model.dto.ResultDTO;
import com.xplorist.bidforum.service.BidForumService;
import com.xplorist.bidforum.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
//@RequestMapping("/bid-forum")
public class BidForumController {
    @Autowired
    BidForumService svc;

    // 【01】发帖（需要token）
    @VerifyToken
    @RequestMapping(value = "/sendPost", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ResultDTO sendPost(@RequestBody @Valid BbsPostDTO param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.sendPost(param, request, response);
    }

    // 【02】分页查询所有帖子list
    @RequestMapping(value = "/listPostsByPage", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO listPostsByPage(Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.listPostsByPage(pageIndex, pageSize, request, response);
    }

    // 【03】回复帖子（需要token）
    @VerifyToken
    @RequestMapping(value = "/sendReply", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ResultDTO sendReply(@RequestBody @Valid BbsReplyDTO param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.sendReply(param, request, response);
    }

    // 【04】根据ID查询单个帖子
    @RequestMapping(value = "/getPostById", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO getPostById(String postId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.getPostById(postId, request, response);
    }

    // 【05】查询通知list
    @RequestMapping(value = "/listNotice", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO listNotice(String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.listNotice(userId, request, response);
    }

    // 【06】删除单条通知(需要token)
    @VerifyToken
    @RequestMapping(value = "/deleteSingleNotice", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO deleteSingleNotice(String noticeId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.deleteSingleNotice(noticeId, request, response);
    }

    // 【07】一键删除当前用户的所有通知（需要token）
    @VerifyToken
    @RequestMapping(value = "/deleteAllNotice", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO deleteAllNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.deleteAllNotice(request, response);
    }

    // 【08】删除帖子（需要token）
    @VerifyToken
    @RequestMapping(value = "/deletePost", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO deletePost(String postId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.deletePost(postId, request, response);
    }

    // 【09】删除回复（需要token）
    @VerifyToken
    @RequestMapping(value = "/deleteReply", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO deleteReply(String replyId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.deleteReply(replyId, request, response);
    }

    /* 模板 放在最后 */
    // 模板
    @RequestMapping(value = "/template", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO template(String param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResultDTO.of("", "");
    }
}
