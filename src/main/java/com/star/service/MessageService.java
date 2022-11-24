package com.star.service;

import com.star.domain.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_message】的数据库操作Service
* @createDate 2022-11-19 23:10:24
*/
public interface MessageService extends IService<Message> {

    List<Message> listMessage();

    Message getEmailByParentId(Long parentId);

    void saveMessage(Message message, Message parentMessage);
}
