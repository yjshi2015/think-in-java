package com.syj.demo.thread.guardedSuspension;

import java.util.Random;

public class Controller {

    // 处理浏览器发来的请求
    String handleWebReq() {
        int id = new Random().nextInt(1000);
        Message msg1 = new Message();
        msg1.id = id;

        // 创建GuardedObject实例
        GuardedObject<Message> go = GuardedObject.create(id);
        // 发送消息
//        send(msg1);
        // 等待MQ消息
        Message result = go.get(t -> t != null);

        return "ok";
    }

    void onMessage(Message msg) {
        // 唤醒等待的线程
        GuardedObject.fireEvent(msg.id, msg);
    }

    class Message {
        public int id;
        public String content;
    }
}
