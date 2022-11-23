package com.example.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 23:04
 */
@Slf4j
@Component
public class IdGenerator {
    /**
     * 机房Id
     */
    private long workerId =0;

    /**
     * 机器Id
     */
    private final long datacenterId = 1;

    private final Snowflake snowFlake = IdUtil.getSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init(){
        try{
            //获取本机的ip地址编码
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId: " + workerId);
        }catch (Exception e){
            e.printStackTrace();
            log.warn("当前机器的workerId获取失败 ----> " + e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long nextId(){
        return snowFlake.nextId();
    }

    public synchronized long nextId(long workerId, long datacenterId){
        return IdUtil.getSnowflake(workerId, datacenterId).nextId();
    }
}
