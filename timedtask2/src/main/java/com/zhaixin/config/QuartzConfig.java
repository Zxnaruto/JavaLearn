package com.zhaixin.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @Program: timedtask
 * @Classname: QuartzConfig
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/02/04 16:21
 */
@Configuration
public class QuartzConfig {
    @Autowired
    @Qualifier("dataSource")
    private DataSource druidDataSource;

    // 创建 schedulerFactoryBean对象
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(/*CronTriggerFactoryBean cronTriggerFactoryBean,*/ JobFactory jobFactory) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("/application.yml"));
        factoryBean.setJobFactory(jobFactory);
        factoryBean.setDataSource(druidDataSource);

        return factoryBean;
    }

    // 通过SchedulerFactoryBean获取scheduler对象
    @Bean(name = "scheduler")
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
        return schedulerFactoryBean.getScheduler();
    }
}

