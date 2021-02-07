package com.zhaixin.job;

import com.zhaixin.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class CornJob extends QuartzJobBean {
    @Value("${quartz.job.data.name}")
    private String jobDataName;
    @Autowired
    private QuartzService quartzService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取执行任务的信息，打印到日志
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String data = jobDataMap.getString(jobDataName);
        log.info("cron data:{}", data);
        // 具体定时任务
        System.out.println("cronJob: " + new Date());
    }
}
