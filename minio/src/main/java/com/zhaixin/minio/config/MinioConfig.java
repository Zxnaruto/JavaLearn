package com.zhaixin.minio.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: minio
 * @Classname: MinioConfig
 * @Author: Zhai
 * @Description: MinioConfig
 * @Date: 2021/07/07 18:10
 */
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
    }

}

