package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.domain.entity.Message;
import com.star.service.MessageService;
import com.star.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
* @author Participate
* @description 针对表【t_message】的数据库操作Service实现
* @createDate 2022-11-19 23:10:24
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{
    @Autowired
    private JavaMailSender javaMailSender;
    private List<Message> tempReplys = new ArrayList<>();
    @Override
    public List<Message> listMessage() {
        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Message::getParentMessageId,-1);
        List<Message> messages = list(queryWrapper);
        for (Message message : messages) {
            Long messageId = message.getId();
            String nickname = message.getNickname();
            LambdaQueryWrapper<Message> lambdaQueryWrapper = new LambdaQueryWrapper<Message>();
            lambdaQueryWrapper.eq(Message::getParentMessageId,messageId);
            List<Message> childMessage = list(lambdaQueryWrapper);
            //查出子评论
            combineChildren(childMessage, nickname);
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return messages;
    }

    @Override
    public Message getEmailByParentId(Long parentId) {
        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Message::getId,parentId);
        queryWrapper.orderByDesc(Message::getCreateTime);
        return getOne(queryWrapper);
    }

    @Override
    public void saveMessage(Message message, Message parentMessage) {
        if(!StringUtils.isEmpty(parentMessage)){

            String parentNickname = parentMessage.getNickname();
            String nickName = message.getNickname();
            String comtent = "亲爱的" + parentNickname + "，您在【ONESTARの客栈】的评论收到了来自" + nickName + "的回复！内容如下：" + "\r\n" + "\r\n" +  message.getContent();
            String parentEmail = parentMessage.getEmail();

            // 发送邮件
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("ONESTARの客栈评论回复");  //主题
            simpleMailMessage.setText(comtent);   //内容
            simpleMailMessage.setTo(parentEmail); //接收者的邮箱
            simpleMailMessage.setFrom("2711542571@qq.com");//发送者邮箱
            javaMailSender.send(simpleMailMessage);
        }
        save(message);
    }

    private void combineChildren(List<Message> childMessages, String parentNickname1) {
        //判断是否有一级子回复
        if(childMessages.size() > 0){
            //循环找出子留言的id
            for(Message childMessage : childMessages){
                String parentNickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname1);
                tempReplys.add(childMessage);
                Long childId = childMessage.getId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }
    private void recursively(Long childId, String parentNickname1) {
        //根据子一级留言的id找到子二级留言
        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Message::getParentMessageId,childId);
        queryWrapper.orderByDesc(Message::getCreateTime);
        List<Message> replayMessages = list(queryWrapper);

        if(replayMessages.size() > 0){
            for(Message replayMessage : replayMessages){
                String parentNickname = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickname1);
                Long replayId = replayMessage.getId();
                tempReplys.add(replayMessage);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }
}




