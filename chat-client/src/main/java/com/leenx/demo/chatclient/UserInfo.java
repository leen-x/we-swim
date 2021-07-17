package com.leenx.demo.chatclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/13 2:39 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String name;

    private String pw;
}
