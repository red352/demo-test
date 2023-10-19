package com.yunxiao.spring.task;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LuoYunXiao
 * @since 2023/10/17 22:26
 */
@Component
@NoArgsConstructor
@Slf4j
public class ScheduledTask implements Runnable {

    private Integer taskId;

    private static final ExecutorService taskExecutor = Executors.newVirtualThreadPerTaskExecutor();

    public ScheduledTask(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        taskExecutor.submit(() -> {
            log.debug("任务Id:{}任务调度了", taskId);
        });
    }
}
