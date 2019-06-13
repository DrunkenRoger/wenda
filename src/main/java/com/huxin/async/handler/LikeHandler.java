package com.huxin.async.handler;

import com.huxin.async.EventHandler;
import com.huxin.async.EventModel;
import com.huxin.async.EventType;
import com.huxin.model.Message;
import com.huxin.model.User;
import com.huxin.service.MessageService;
import com.huxin.service.UserService;
import com.huxin.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class LikeHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;


    @Override
    public void doHandle(EventModel eventModel) {
        Message message = new Message();
        message.setFromId(WendaUtil.SYSTEM_USERID);
        message.setToId(eventModel.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(eventModel.getActorId());
        message.setContent("用户"+user.getName()+"赞了你的评论，http://127.0.0.1:8080/question/"+eventModel.getExt("questionId"));


        messageService.addMessage(message);

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
