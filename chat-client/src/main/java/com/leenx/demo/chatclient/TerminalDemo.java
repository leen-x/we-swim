package com.leenx.demo.chatclient;

import java.util.concurrent.TimeUnit;

/**
 * @author linsongxiong
 * @Description:
 * @date 2021/07/15 9:11 δΈε
 **/
public class TerminalDemo {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("");
                System.out.println("πδ½ ιη");
                System.out.println("Shouting down ...");
            }
        });
        TimeUnit.SECONDS.sleep(10);
    }
}
