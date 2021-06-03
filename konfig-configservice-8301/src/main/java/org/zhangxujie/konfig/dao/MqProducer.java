package org.zhangxujie.konfig.dao;

public interface MqProducer {


    void send(String topic, Object msg);


}
