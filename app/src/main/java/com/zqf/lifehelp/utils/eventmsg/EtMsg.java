package com.zqf.lifehelp.utils.eventmsg;

/**
 * Created by zqf on 2018/7/10.
 * EventBus消息分发
 */

public class EtMsg {


    private String msg_type;
    private String msg;

    public EtMsg(String msg_type, String msg) {
        this.msg_type = msg_type;
        this.msg = msg;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public String getMsg() {
        return msg;
    }
}
