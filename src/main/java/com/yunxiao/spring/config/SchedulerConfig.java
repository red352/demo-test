package com.yunxiao.spring.config;

import cn.hutool.cron.pattern.CronPattern;
import cn.hutool.cron.pattern.CronPatternBuilder;
import cn.hutool.cron.pattern.CronPatternUtil;
import cn.hutool.cron.pattern.Part;
import com.yunxiao.spring.schelder.TaskSchedulerService;
import com.yunxiao.spring.task.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.support.CronTrigger;

import java.time.Instant;
import java.util.Date;

/**
 * @author LuoYunXiao
 * @since 2023/10/17 23:55
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public TaskSchedulerService taskSchedulerService() {
        TaskSchedulerService taskSchedulerService = new TaskSchedulerService();
        taskSchedulerService.setPoolSize(2);
        return taskSchedulerService;
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired TaskSchedulerService ts) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                String cron = CronPatternBuilder.of().set(Part.SECOND, "*/2").build();
                Date date = CronPatternUtil.nextDateAfter(CronPattern.of(cron), Date.from(Instant.now()), true);
                System.out.println(date);

                for (int i = 0; i < 10; i++) {
                    ts.schedule(new ScheduledTask(i), new CronTrigger(cron));
                }
            }
        };
    }
}
