package io.github.xxyopen.cache.timer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 缓存清理任务执行器，用于在需要过期支持的缓存中（如ExpireAfterWriteCache）清理过期对象
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/8
 */
public enum ClearTimerExecutor {

    /**
     * 单例对象
     */
    INSTANCE;


    /**
     * 构造
     */
    ClearTimerExecutor() {
        this.clearTimerExecutor = new ScheduledThreadPoolExecutor(1);
    }


    /**
     * 清理定时器
     */
    private final ScheduledExecutorService clearTimerExecutor;


    /**
     * 启动定时任务
     * @param task  清理任务
     * @param cycle 循环周期（单位毫秒）
     */
    public void schedule(Runnable task, long cycle) {
        this.clearTimerExecutor.scheduleAtFixedRate(task, cycle, cycle, TimeUnit.MILLISECONDS);
    }


}
