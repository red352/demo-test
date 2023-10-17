package com.yunxiao.spring.task;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author LuoYunXiao
 * @since 2023/10/17 22:26
 */
@Component
@NoArgsConstructor
public class ScheduledTask implements Runnable {

    private Integer taskId;

    private static final ExecutorService taskExecutor = Executors.newVirtualThreadPerTaskExecutor();

    public ScheduledTask(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        taskExecutor.submit(() -> {
            System.out.println(taskId + "任务调度了...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
