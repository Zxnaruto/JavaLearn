package com.zhaixin.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.zhaixin.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {
    @Value("${quartz.group.name}")
    private String group;
    @Value("${quartz.job.data.name}")
    private String jobDataName;
    @Autowired
    private Scheduler scheduler;

    @Override
    public String scheduleJob(Class<? extends Job> jobBeanClass, String cron, String data) {
        // 创建需要执行的任务
        String jobName = UUID.fastUUID().toString();
        JobDetail jobDetail = JobBuilder.newJob(jobBeanClass)
                .withIdentity(jobName, group)
                .usingJobData(jobDataName, data)
                .build();
        // 创建触发器，指定时间
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();

        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("创建定时任务失败");
        }

        return jobName;
    }

    @Override
    public String scheduleFixTimeJob(Class<? extends Job> jobBeanClass, Date time, String data) {
        // 日期转cron表达式
        String corn = String.format("%d %d %d %d %d ? %d",
                DateUtil.second(time),
                DateUtil.minute(time),
                DateUtil.hour(time, true),
                DateUtil.dayOfMonth(time),
                DateUtil.month(time),
                DateUtil.year(time));

       return scheduleJob(jobBeanClass, corn, data);
    }

    @Override
    public Boolean cancelScheduleJob(String jobName) {
        boolean success = false;
        try{
            // 暂停触发器
            scheduler.pauseTrigger(new TriggerKey(jobName, group));
            // 移除触发器中的任务
            scheduler.unscheduleJob(new TriggerKey(jobName, group));
            // 删除任务
            scheduler.deleteJob(new JobKey(jobName, group));
            success = true;
        }catch (SchedulerException e){
            e.printStackTrace();
            log.error("删除定时任务失败");
        }
        return success;
    }

    @Override
    public Boolean updateScheduleJob(String jobName, String corn) {
        boolean success = false;
        TriggerKey key = new TriggerKey(jobName, group);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(key)
                .withSchedule(scheduleBuilder)
                .build();
        try{
            scheduler.rescheduleJob(key, cronTrigger);
            success = true;
        }catch (SchedulerException e) {
            e.printStackTrace();
        }
        return success;
    }
}
