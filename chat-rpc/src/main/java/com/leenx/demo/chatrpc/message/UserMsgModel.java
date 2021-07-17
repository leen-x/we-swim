package com.leenx.demo.chatrpc.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/17 11:04 上午
 **/
@Data
@Builder
@AllArgsConstructor
public class UserMsgModel {
    /**
     * from user
     */
    private String form;

    /**
     * to user
     */
    private String to;

    /**
     * 文本内容
     */
    private String content;
}
