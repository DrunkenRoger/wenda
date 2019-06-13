package com.huxin.async.handler;

import com.huxin.async.EventHandler;
import com.huxin.async.EventModel;
import com.huxin.async.EventType;
import com.huxin.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginExceptionHandler implements EventHandler {
    @Autowired
    MailSender mailSender;

    @Override
    public void doHandle(EventModel eventModel) {
        // xxx判断发现这个用户登录异常
        Map<String, Object> map = new HashMap<>();
        map.put("username",eventModel.getExt("username"));
        mailSender.sendWithHTMLTemplate(eventModel.getExt("email"),"登陆IP异常","mails/login_exception.html",map);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
