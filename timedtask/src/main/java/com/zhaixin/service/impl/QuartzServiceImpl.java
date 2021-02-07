package com.zhaixin.service.impl;

import com.zhaixin.entity.QuartzJob;
import com.zhaixin.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Program: timedtask
 * @Classname: QuartzServiceImpl
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/02/04 18:08
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @Override
    public String addJob(QuartzJob quartzJob) {
        try {
            // 如果是修改就删除旧的
            if (!StringUtils.isEmpty(quartzJob.getOldJobGroup())) {
                JobKey key = new JobKey(quartzJob.getOldJobName(), quartzJob.getOldJobGroup());
                scheduler.deleteJob(key);
            }
            // 构建job信息
            Class cls = Class.forName(quartzJob.getJobClassName());
            cls.newInstance();
            JobDetail jobDetail = JobBuilder
                    .newJob(cls)
                    .withIdentity(quartzJob.getJobName(), quartzJob.getJobGroup())
                    .withDescription(quartzJob.getDescription())
                    .build();
            // 触发时间
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression().trim());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+quartzJob.getJobName(), quartzJob.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            // 交由Scheduler安排触发
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("addJob error");
            return "error";
        }
        log.info("addJob success");
        return "success";
    }

    @Override
    public String triggerJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try{
            scheduler.triggerJob(key);
        }catch (Exception e){
            log.error("triggerJob error");
            return "error";
        }
        log.info("triggerJob success");
        return "success";
    }

    @Override
    public String pauseJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try{
            scheduler.pauseJob(key);
        }catch (Exception e){
            log.error("pauseJob error");
            return "error";
        }
        log.info("pauseJob success");
        return "success";
    }

    @Override
    public String resumeJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try{
            scheduler.resumeJob(key);
        }catch (Exception e) {
            log.error("resumeJob error");
            return "error";
        }
        log.info("resumeJob success");
        return "success";
    }

    @Override
    public String deleteJob(String jobName, String jobGroup) {
        try{
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,jobGroup);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName,jobGroup));
        }catch (Exception e){
            log.error("deleteJob error");
            return "error";
        }
        log.info("deleteJob success");
        return "success";
    }
}

