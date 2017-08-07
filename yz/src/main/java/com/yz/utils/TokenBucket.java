package com.yz.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 令牌限流逻辑
 *
 * @author yuweixiang
 * @version $ Id: TokenBucket.java, v 0.1 16/9/14 下午3:28 yuweixiang Exp $
 */
public class TokenBucket {

    /** 日志 */
    private static Logger LOGGER    = LoggerFactory.getLogger(TokenBucket.class);

    /** 当前时间 */
    public volatile static long timeStamp     = new Date().getTime() - 1000;
    /** 当前时间 抢票 */
    public volatile static long timeStampGrab = new Date().getTime() - 1000;

    /** 当前令牌数量*/
    public volatile static long tokens;
    /** 当前令牌数量 抢票*/
    public volatile static long tokensGrab;

    /**
     * 判断是否被控制,授权,如果能够获得则返回true,否则直接返回false
     *
     * @return 判断结果
     */
    public static boolean grant(long realCapacity, long realRate) {
        long now = new Date().getTime();
        // 先添加令牌
        tokens = Math.min(realCapacity, tokens + ((now - timeStamp) * realRate / 1000));
        if (tokens < 1) {
            // 若不到1个令牌,则拒绝
            LOGGER.info("无法获取令牌!被限流!");
            return false;
        } else {
            timeStamp = now;
            // 还有令牌,则领取令牌
            tokens -= 1;
            return true;
        }
    }

    /**
     * 判断是否被控制,授权,如果能够获得则返回true,否则直接返回false
     *
     * @return 判断结果
     */
    public static boolean grantGrab(long realCapacity, long realRate) {
        long now = new Date().getTime();
        // 先添加令牌
        tokensGrab = Math.min(realCapacity, tokensGrab + ((now - timeStampGrab) * realRate / 1000));
        if (tokensGrab < 1) {
            // 若不到1个令牌,则拒绝
            LOGGER.info("抢票无法获取令牌!被限流!");
            return false;
        } else {
            timeStampGrab = now;
            // 还有令牌,则领取令牌
            tokensGrab -= 1;
            return true;
        }
    }
}
