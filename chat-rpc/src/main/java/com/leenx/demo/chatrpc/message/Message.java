package com.leenx.demo.chatrpc.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/13 8:11 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {
    private int code;

    private String msg;

    private int messageType;

    private String from;

    private T body;
}
