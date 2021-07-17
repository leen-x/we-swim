package com.leenx.demo.chatserver.user;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/13 2:39 下午
 **/
public class UserStorage {
    private static final Map<String, UserInfo> userSet = new HashMap<>();

    static {
        userSet.put("gongben", UserInfo.builder()
                .name("gongben")
                .pw("gongben123")
                .build());
        userSet.put("hindung", UserInfo.builder()
                .name("hindung")
                .pw("hindung123")
                .build());
    }

    public static UserInfo getUser(String userId) {
        return userSet.get(userId);
    }

    public static Map<String, UserInfo> getUsers() {
        return userSet;
    }

}
