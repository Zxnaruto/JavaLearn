package com.zhaixin.service;

import com.zhaixin.entity.QuartzJob;

public interface QuartzService {
    // 添加job,包含修改
    String addJob(QuartzJob quartzJob);

    // 触发job
    String triggerJob(String jobName, String jobGroup);

    // 暂停job
    String pauseJob(String jobName, String jobGroup);

    // 恢复job
    String resumeJob(String jobName, String jobGroup);

    // 删除job
    String deleteJob(String jobName, String jobGroup);
}
