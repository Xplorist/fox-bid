package com.xplorist.bidforum.service;

import com.xplorist.bidforum.model.dto.BbsPostDTO;
import com.xplorist.bidforum.model.dto.BbsReplyDTO;
import com.xplorist.bidforum.model.dto.ResultDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BidForumService {
    // 【01】发帖（需要token）
    ResultDTO sendPost(BbsPostDTO param, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【02】分页查询所有帖子list
    ResultDTO listPostsByPage(Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【03】回复帖子（需要token）
    ResultDTO sendReply(BbsReplyDTO param, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【04】根据ID查询单个帖子
    ResultDTO getPostById(String postId, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【05】查询通知list
    ResultDTO listNotice(String userId, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【06】删除单条通知(需要token)
    ResultDTO deleteSingleNotice(String noticeId, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【07】一键删除当前用户的所有通知（需要token）
    ResultDTO deleteAllNotice(HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【08】删除帖子（需要token）
    ResultDTO deletePost(String postId, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【09】删除回复（需要token）
    ResultDTO deleteReply(String replyId, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
