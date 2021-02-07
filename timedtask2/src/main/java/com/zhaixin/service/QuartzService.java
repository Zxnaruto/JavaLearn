package com.zhaixin.service;

import org.quartz.Job;

import java.util.Date;

public interface QuartzService {
    /*
     * 传入corn表达式进行任务调度
     * */
    String scheduleJob(Class<? extends Job> jobBeanClass, String cron, String data);

    /*
     * 指定时间的任务调度
     * */
    String scheduleFixTimeJob(Class<? extends Job> jobBeanClass, Date time, String data);

    /*
     *取消定时任务
     * */
    Boolean cancelScheduleJob(String jobName);

    /*
    * 修改定时任务
    * */
    Boolean updateScheduleJob(String jobName, String corn);
}
