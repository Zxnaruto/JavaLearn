package com.zhaixin.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @Program: timedtask
 * @Classname: MyJob
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/02/04 16:19
 */
public class MyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("触发定时任务："+ new Date()+ jobExecutionContext.getTrigger());
    }
}

