package com.leenx.demo.chatrpc.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/15 7:20 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectReq {
    private String userId;

    private String userPw;
}
