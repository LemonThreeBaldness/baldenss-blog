package com.markerhub.controller;

import cn.hutool.core.lang.Assert;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.User;
import com.markerhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统健康检查类，用于检测系统连接的各种组件
 *
 * @author sihgyu
 * @date 2021/3/4 11:40 下午
 */

@RestController
public class CheckController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/health_check", method = RequestMethod.POST)
    public Result healthCheck() {
        Map<String, String> checkMap = new HashMap<>();
        try {
            User user = userService.getById(1L);
            Assert.notNull(user, "user is null! mysql has error");
        } catch (Exception e) {
            return Result.fail("mysql has error", e);
        }
        checkMap.put("mysql", "ok");

        // 后续增加redis，mq等
        return Result.succ(checkMap);
    }

}
