package com.xplorist.bidforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xplorist.bidforum.mapper.*;
import com.xplorist.bidforum.model.BbsNoticeDO;
import com.xplorist.bidforum.model.BbsPostDO;
import com.xplorist.bidforum.model.BbsReplyDO;
import com.xplorist.bidforum.model.UserInfoDO;
import com.xplorist.bidforum.model.dto.*;
import com.xplorist.bidforum.service.BidForumService;
import com.xplorist.bidforum.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BidForumServiceImpl implements BidForumService {
    @Autowired
    BidForumMapper mapper;

    @Autowired
    BbsPostMapper postMapper;

    @Autowired
    BbsReplyMapper replyMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    BbsNoticeMapper noticeMapper;

    // 【01】发帖（需要token）
    @Transactional
    @Override
    public ResultDTO sendPost(BbsPostDTO param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getAttribute("userId");
        if (userId == null || "".equals(userId)) {
            return new ResultDTO("0", "【01】发帖失败,无法获取用户id", null);
        }
        param.setCreatorId(userId);
        int saveFlag = postMapper.insert(param);
        if (saveFlag <= 0) {
            //return new ResultDTO("0", "【01】发帖失败", null);
            throw new RuntimeException("保存帖子失败");
        }

        return new ResultDTO("1", "【01】发帖成功", null);
    }

    // 【02】分页查询所有帖子list
    @Override
    public ResultDTO listPostsByPage(Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // mybatis plus 的查询很烦，查询一律自己写
        //List<BbsPostDO> list = postMapper.selectList(new QueryWrapper<BbsPostDO>().lambda().eq(BbsPostDO::getDeleteFlag, 0));
        if (pageIndex == null || pageSize == null) {
            pageIndex = 1;
            pageSize = 10;
        }
        BbsPostDTO param = new BbsPostDTO();
        param.setPageIndex(pageIndex);
        param.setPageSize(pageSize);
        // 分页查询所有帖子list
        List<BbsPostDTO> list = postMapper.listBbsPostByPage(param);
        if (list == null) {
            return new ResultDTO("0", "【02】查询所有帖子list失败", null);
        }

        int rowTotal = 0;
        int pageTotal = 1;
        if (!list.isEmpty()) {
            rowTotal = list.get(0).getRowTotal();
            pageTotal = rowTotal % pageSize == 0 ? rowTotal / pageSize : (rowTotal / pageSize) + 1;
        }

        // 查询创建人
        for (int i = 0; i < list.size(); i++) {
            BbsPostDTO dto = list.get(i);
            String creatorId = dto.getCreatorId();
            UserInfoDO creator = userInfoMapper.selectOne(new QueryWrapper<UserInfoDO>().lambda().eq(UserInfoDO::getPkid, creatorId));
            dto.setCreator(creator);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("rowTotal", rowTotal);
        map.put("pageTotal", pageTotal);

        return new ResultDTO("1", "【02】查询所有帖子list成功", map);
    }

    // 【03】回复帖子（需要token）
    @Transactional
    @Override
    public ResultDTO sendReply(BbsReplyDTO param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getAttribute("userId");
        if (userId == null || "".equals(userId)) {
            return new ResultDTO("0", "回复帖子失败，用户id不能为空", null);
        }
        param.setCreatorId(userId);

        String id = UuidUtil.getUUID32();
        param.setId(id);

        String replyType = param.getReplyType();
        String receiveUserId = "";
        if ("toReply".equals(replyType)) {
            // 给回复添加子回复判断父回复id是否为空
            String parentId = param.getParentId();
            if (parentId == null || "".equals(parentId)) {
                return new ResultDTO("0", "回复类型为toReply时，parentId不能为Null或空字符串", null);
            }
            BbsReplyDO parentReplyDO = replyMapper.selectById(parentId);
            receiveUserId = parentReplyDO.getCreatorId();
        } else if ("toPost".equals(replyType)) {
            // 给帖子添加回复，无父回复id，将parentId置空
            param.setParentId(null);
            BbsPostDO postDO = postMapper.selectById(param.getBbsPostId());
            receiveUserId = postDO.getCreatorId();
        } else if (!"toPost".equals(replyType) && !"toReply".equals(replyType)) {
            return new ResultDTO("0", "【参数错误】replyType的值只能是toPost或toReply", null);
        }

        //  添加回复表数据
        int insertFlag = replyMapper.insert(param);
        if (insertFlag <= 0) {
            //return new ResultDTO("0", "【03】回复帖子失败", null);
            throw new RuntimeException("添加回复表数据失败");
        }

        // 添加通知表数据
        BbsNoticeDO notice = new BbsNoticeDO();
        notice.setBbsPostId(param.getBbsPostId());
        notice.setParentReplyId(param.getParentId());
        notice.setChildReplyId(id);
        notice.setReceiveUserId(receiveUserId);
        notice.setSendUserId(userId);
        int noticeInsertFlag = noticeMapper.insert(notice);
        if (noticeInsertFlag <= 0) {
            //return new ResultDTO("0", "添加通知表数据失败", null);
            throw new RuntimeException("添加通知表数据失败");
        }

        return new ResultDTO("1", "【03】回复帖子成功", null);
    }

    // 【04】根据ID查询单个帖子
    @Override
    public ResultDTO getPostById(String postId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (postId == null || "".equals(postId)) {
            return new ResultDTO("0", "【参数错误】postId不能为Null或空字符串", null);
        }

        // 查询单个帖子
        BbsPostDTO post = new BbsPostDTO(postMapper.selectById(postId));
        String postCreatorId = post.getCreatorId();
        UserInfoDO userInfoDO = userInfoMapper.selectOne(new QueryWrapper<UserInfoDO>().lambda().eq(UserInfoDO::getPkid, postCreatorId));
        post.setCreator(userInfoDO);

        // BbsReply【01】根据帖子ID查询根回复list
        List<BbsReplyDTO> rootList = replyMapper.listRootReplyByPostId(postId);

        // 递归查询子回复
        listChildrenReply(postId, rootList);
        post.setReplyList(rootList);

        return new ResultDTO("1", "【04】根据ID查询单个帖子成功", post);
    }

    // 递归查询子回复
    public void listChildrenReply(String postId, List<BbsReplyDTO> parentReplyList) {
        for (int i = 0; i < parentReplyList.size(); i++) {
            BbsReplyDTO parentReply = parentReplyList.get(i);
            String creatorId = parentReply.getCreatorId();
            UserInfoDO userInfoDO = userInfoMapper.selectOne(new QueryWrapper<UserInfoDO>().lambda().eq(UserInfoDO::getPkid, creatorId));
            parentReply.setCreator(userInfoDO);

            // BbsReply【02】 根据某个父回复Id查询子回复list
            List<BbsReplyDTO> replyList = replyMapper.listChildrenReplyByParentId(postId, parentReply.getId());
            parentReply.setReplyList(replyList);

            listChildrenReply(postId, replyList);
        }
    }

    // 【05】查询通知list
    @Override
    public ResultDTO listNotice(String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (userId == null || "".equals(userId)) {
            return new ResultDTO("0", "用户id不能为空", null);
        }
        // BbsNotice【01】根据接收人id查询通知list
        List<BbsNoticeDTO> list = noticeMapper.listNoticeByReceiveUserId(userId);
        for (int i = 0; i < list.size(); i++) {
            BbsNoticeDTO dto = list.get(i);
            String bbsPostId = dto.getBbsPostId();
            String parentReplyId = dto.getParentReplyId();
            String childReplyId = dto.getChildReplyId();
            String receiveUserId = dto.getReceiveUserId();
            String sendUserId = dto.getSendUserId();

            // 查询帖子
            BbsPostDO bbsPostDO = postMapper.selectById(bbsPostId);
            BbsPostDTO bbsPostDTO = new BbsPostDTO(bbsPostDO);
            dto.setPost(bbsPostDTO);

            // 查询父回复
            BbsReplyDO parentReplyDO = replyMapper.selectById(parentReplyId);
            BbsReplyDTO parentReplyDTO = new BbsReplyDTO(parentReplyDO);
            dto.setParentReply(parentReplyDTO);

            // 查询子回复
            BbsReplyDO childReplyDO = replyMapper.selectById(childReplyId);
            BbsReplyDTO childReplyDTO = new BbsReplyDTO(childReplyDO);
            dto.setChildReply(childReplyDTO);

            // 查询接收通知用户信息
            UserInfoDO receiveUserDO = userInfoMapper.selectOne(new QueryWrapper<UserInfoDO>().lambda().eq(UserInfoDO::getPkid, receiveUserId));
            UserInfoDTO receiveUserDTO = new UserInfoDTO(receiveUserDO);
            dto.setReceiveUser(receiveUserDTO);

            // 查询发送通知用户信息
            UserInfoDO sendUserDO = userInfoMapper.selectOne(new QueryWrapper<UserInfoDO>().lambda().eq(UserInfoDO::getPkid, sendUserId));
            UserInfoDTO sendUserDTO = new UserInfoDTO(sendUserDO);
            dto.setSendUser(sendUserDTO);
        }

        return new ResultDTO("1", "【05】查询通知list成功", list);
    }

    // 【06】删除单条通知(需要token)
    @Transactional
    @Override
    public ResultDTO deleteSingleNotice(String noticeId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getAttribute("userId");
        if (noticeId == null || "".equals(noticeId)) {
            return new ResultDTO("0", "通知id不能为空", null);
        }

        BbsNoticeDO noticeDO = noticeMapper.selectById(noticeId);
        if (noticeDO == null) {
            throw new RuntimeException("通知id无效，不存在此通知id");
        }
        // 查询此用户是否是此通知的接收人
        if (!userId.equals(noticeDO.getReceiveUserId())) {
            throw new RuntimeException("此用户不是此通知的接收人");
        }

        noticeDO.setDeleteFlag(1);
        noticeDO.setDeleteTime(new Date());
        int updateFlag = noticeMapper.updateById(noticeDO);
        if (updateFlag <= 0) {
           throw new RuntimeException("删除失败");
        }

        return new ResultDTO("1", "【06】删除单条通知(需要token)成功", null);
    }

    // 【07】一键删除当前用户的所有通知（需要token）
    @Transactional
    @Override
    public ResultDTO deleteAllNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getAttribute("userId");

        // 查询此用户的所有通知
        List<BbsNoticeDTO> list = noticeMapper.listNoticeByReceiveUserId(userId);
        for (int i = 0; i < list.size(); i++) {
            BbsNoticeDTO dto = list.get(i);
            dto.setDeleteFlag(1);
            dto.setDeleteTime(new Date());
            int updateFlag = noticeMapper.updateById(dto);
            if (updateFlag <= 0) {
                throw new RuntimeException("删除所有通知失败");
            }
        }

        return new ResultDTO("1", "【07】一键删除当前用户的所有通知（需要token）成功", null);
    }

    // 【08】删除帖子（需要token）
    @Transactional
    @Override
    public ResultDTO deletePost(String postId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getAttribute("userId");
        if (postId == null || "".equals(postId)) {
            throw new RuntimeException("帖子id不能为空");
        }

        BbsPostDO bbsPostDO = postMapper.selectById(postId);
        String creatorId = bbsPostDO.getCreatorId();
        UserInfoDO userInfoDO = userInfoMapper.selectOne(new QueryWrapper<UserInfoDO>().lambda().eq(UserInfoDO::getPkid, userId));
        String username = userInfoDO.getUsername();
        if (!"admin".equals(username) && !creatorId.equals(userId)) {
            throw new RuntimeException("当前操作人无权限删除此帖子");
        }

        bbsPostDO.setDeleteFlag(1);
        bbsPostDO.setDeleteTime(new Date());
        int updateFlag = postMapper.updateById(bbsPostDO);
        if (updateFlag <= 0) {
            throw new RuntimeException("删除帖失败");
        }

        return new ResultDTO("1","【08】删除帖子成功", null);
    }

    // 【09】删除回复（需要token）
    @Transactional
    @Override
    public ResultDTO deleteReply(String replyId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getAttribute("userId");
        if (replyId == null || "".equals(replyId)) {
            throw new RuntimeException("回复id不能为空");
        }

        BbsReplyDO bbsReplyDO = replyMapper.selectById(replyId);
        String creatorId = bbsReplyDO.getCreatorId();
        UserInfoDO userInfoDO = userInfoMapper.selectOne(new QueryWrapper<UserInfoDO>().lambda().eq(UserInfoDO::getPkid, userId));
        String username = userInfoDO.getUsername();
        if (!"admin".equals(username) && !creatorId.equals(userId)) {
            throw new RuntimeException("当前操作人无权限删除此回复");
        }

        bbsReplyDO.setDeleteFlag(1);
        bbsReplyDO.setDeleteTime(new Date());
        int updateFlag = replyMapper.updateById(bbsReplyDO);
        if (updateFlag <= 0) {
            throw new RuntimeException("删除回复失败");
        }

        return new ResultDTO("1", "【09】删除回复成功", null);
    }
}
