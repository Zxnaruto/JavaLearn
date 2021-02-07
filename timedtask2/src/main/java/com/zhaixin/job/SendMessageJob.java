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
public class SendMessageJob extends QuartzJobBean {

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
        log.info("send message data:{}", data);
        // 具体发送的操作，发送什么内容， 给什么人
        System.out.println("SendMessageJob:" + new Date());
        // 删除定时任务
        quartzService.cancelScheduleJob(trigger.getKey().getName());
    }
}
