package com.zhaixin.springanoncation.common;

import lombok.Data;

/**
 * @Program: springanoncation
 * @Classname: Result
 * @Author: Zhai
 * @Description:
 * @Date: 2021/07/05 15:49
 */
@Data
public class Result {
        private String code;
        private String msg;
        private Object data;

        public Result() {

        }

        public static Result error(String code, String message, Object data) {
            Result result = new Result();
            result.setCode(code);
            result.setMsg(message);
            result.setData(null);
            return result;
        }

}

