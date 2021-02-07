package com.zhaixin.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.zhaixin.job.CornJob;
import com.zhaixin.job.SendMessageJob;
import com.zhaixin.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/schedule")
@RestController
public class QuartzController {
    @Autowired
    private QuartzService quartzService;

    // 根据给定时间执行，然后删除
    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String time, @RequestParam String data) {
        Date date = DateUtil.parse(time, DatePattern.NORM_DATETIME_FORMAT);
        String jobName = quartzService.scheduleFixTimeJob(SendMessageJob.class, date, data);
        return jobName;
    }

    // 根据Corn表达式定时执行
    @PostMapping("/useCorn")
    public String userCorn(@RequestParam String corn, @RequestParam String data) {
        String jobName = quartzService.scheduleJob(CornJob.class, corn, data);
        return jobName;
    }

    @PostMapping("/cancel")
    public boolean cancel(@RequestParam String jobName) {
        boolean success = quartzService.cancelScheduleJob(jobName);
        return success;
    }

    @PostMapping("/update")
    public boolean update(@RequestParam String jobName, @RequestParam String corn) {
        boolean success = quartzService.updateScheduleJob(jobName, corn);
        return success;
    }

}
