package net.spring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zihad
 * @since 11/10/24
 */
@Component
public class TaskThreadPoolExecutor extends ThreadPoolExecutor implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(TaskThreadPoolExecutor.class);

    public static final int CORE_POOL_SIZE = 4;
    public static final int MAX_POOL_SIZE = 4;
    public static final int WAIT_TIME = 15;
    public static final int BLOCKING_QUEUE_CAPACITY = 32;

    public TaskThreadPoolExecutor() {
        super(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                WAIT_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(BLOCKING_QUEUE_CAPACITY)
        );
    }

    @Override
    public void afterPropertiesSet() {
        int threadCount = prestartAllCoreThreads();

        log.info("[TASK-THREAD-POOL] threadCount:{}", threadCount);
    }
}
