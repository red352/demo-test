package com.yunxiao.spring.config;

import cn.hutool.cron.pattern.CronPattern;
import cn.hutool.cron.pattern.CronPatternBuilder;
import cn.hutool.cron.pattern.CronPatternUtil;
import cn.hutool.cron.pattern.Part;
import com.yunxiao.spring.scheduler.TaskSchedulerManager;
import com.yunxiao.spring.task.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author LuoYunXiao
 * @since 2023/10/17 23:55
 */
@Configuration
@Slf4j
public class SchedulerConfig {

//    @Bean
    public TaskSchedulerManager taskSchedulerService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2);
        return new TaskSchedulerManager(scheduler);
    }

//    @Bean
    public CommandLineRunner commandLineRunner(@Autowired TaskSchedulerManager ts) {
        return args -> {
            String cron = CronPatternBuilder.of().set(Part.SECOND, "*/2").build();
            Date date = CronPatternUtil.nextDateAfter(CronPattern.of(cron), Date.from(Instant.now()), true);
            log.debug(date.toString());

            for (int i = 0; i < 10; i++) {
                ts.schedule(String.valueOf(i), new CronTask(new ScheduledTask(i), cron));
            }
            TimeUnit.SECONDS.sleep(4);
            ts.destroy();
        };
    }
}
