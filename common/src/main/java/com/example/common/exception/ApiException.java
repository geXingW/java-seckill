package com.example.common.exception;

import com.example.common.enums.RespCode;
import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 22:19
 */
@Getter
public class ApiException extends RuntimeException {
    protected RespCode respCode;

    public ApiException(RespCode respCode) {
        this.respCode = respCode;
    }

    public ApiException(RespCode respCode, String message) {
        super(message);
        this.respCode = respCode;
    }
}
