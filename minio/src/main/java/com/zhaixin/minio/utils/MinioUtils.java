package com.zhaixin.minio.utils;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.messages.Bucket;
import io.minio.policy.PolicyType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * @Program: minio
 * @Classname: MinioUtils
 * @Author: Zhai
 * @Description:
 * @Date: 2021/07/07 18:15
 */
@Slf4j
@Component
public class MinioUtils {
    @Autowired
    private MinioClient client;


    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
        }
    }

    @SneakyThrows
    public void createBucketWithPolicyType(String bucketName, String policyType) {
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
            if (!StringUtils.isEmpty(policyType)) {
                if (PolicyType.READ_ONLY.getValue().equals(policyType)) {
                    client.setBucketPolicy(bucketName, "*.*", PolicyType.READ_ONLY);
                }
                if (PolicyType.NONE.getValue().equals(policyType)) {
                    client.setBucketPolicy(bucketName, "*.*", PolicyType.NONE);
                }
                if (PolicyType.READ_WRITE.getValue().equals(policyType)) {
                    client.setBucketPolicy(bucketName, "*.*", PolicyType.READ_WRITE);
                }
                if (PolicyType.WRITE_ONLY.getValue().equals(policyType)) {
                    client.setBucketPolicy(bucketName, "*.*", PolicyType.WRITE_ONLY);
                }
            }
        }
    }

    /**
     * 获取全部bucket
     */
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return client.listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        client.removeBucket(bucketName);
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        return client.presignedGetObject(bucketName, objectName, expires);
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        return client.getObject(bucketName, objectName);
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        client.putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
        client.putObject(bucketName, objectName, stream, size, contextType);
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
        return client.statObject(bucketName, objectName);
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        client.removeObject(bucketName, objectName);
    }

    /**
     * 上传文件
     *
     * @param file       文件
     * @param bucketName 存储桶
     * @return
     */
    public String uploadFile(MultipartFile file, String bucketName) throws Exception {
        // 判断上传文件是否为空
        if (null == file || 0 == file.getSize()) {
            return "上传文件不能为空";
        }
        try {
            // 判断存储桶是否存在
            createBucket(bucketName);
            // 文件名
            String originalFilename = file.getOriginalFilename();
            // 新的文件名
            String fileName = bucketName + "_" + System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 开始上传
            client.putObject(bucketName, fileName, file.getInputStream(), file.getContentType());
            //System.out.println("url:" +Endpoint+ "/" + bucketName + "/" + fileName);
        } catch (Exception e) {
            log.warn("上传文件报错");
        }
        return "success";
    }

    /*
     * 下载文件
     *
     * */
    public void downloadFile(String fileUrl, String bucketName, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(fileUrl)) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            String data = "文件下载失败";
            OutputStream ps = response.getOutputStream();
            ps.write(data.getBytes("UTF-8"));
            return;
        }
        try {
            // 拿到文件路径
            String url = fileUrl.split("9000/")[1];
            // 获取文件对象
            String objectName = url.substring(url.indexOf("/") + 1);
            String objectNameResult = objectName.substring(objectName.indexOf("/") + 1);
            InputStream object = getObject(bucketName, objectNameResult);
            byte buf[] = new byte[1024];
            int length = 0;
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(url.substring(url.lastIndexOf("/") + 1), "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            OutputStream outputStream = response.getOutputStream();
            // 输出文件
            while ((length = object.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
            // 关闭输出流
            outputStream.close();
        } catch (Exception ex) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            String data = "文件下载失败";
            OutputStream ps = response.getOutputStream();
            ps.write(data.getBytes("UTF-8"));
        }
    }

    /*
     * 获取文件链接
     *
     * */
    public String getDownloadUrl(String fileUrl, String bucketName) throws IOException {
        try {
            if (StringUtils.isEmpty(fileUrl)) {
                return null;
            }
            // 拿到文件路径
            String url = fileUrl.split("9000/")[1];
            // 获取文件对象
            String objectName = url.substring(url.indexOf("/") + 1);
            String objectNameResult = objectName.substring(objectName.indexOf("/") + 1);
            return getObjectURL(bucketName, objectNameResult, 10000);

        } catch (Exception ex) {
            log.warn("获取连接失败");
        }
        return null;
    }

    public static void main(String[] args) {
        String a = "http://localhost:9000/z/zx.png";
        String url = a.split("9000/")[1];
        System.out.println(url);
        System.out.println(url.substring(url.indexOf("/") + 1));
    }
}

