package com.leenx.demo.chatrpc.message;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/15 7:17 下午
 **/
@AllArgsConstructor
public enum MessageType {
    /**
     * 链接操作
     */
    CONNECT_OPT(1),

    /**
     * 用户消息操作
     */
    USER_MSG(2),
    ;
    public int code;

    public static MessageType byCode(int code) {
        return Arrays.stream(MessageType.values())
                .filter(item -> code == item.code)
                .findFirst().orElse(null);
    }
}
