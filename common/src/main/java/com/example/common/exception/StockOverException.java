package com.example.common.exception;

import com.example.common.enums.RespCode;
import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 21:57
 */
@Getter
public class StockOverException extends ApiException {

    public StockOverException(){
        super(RespCode.PRODUCT_STOCK_OVER);
    }

    public StockOverException(RespCode respCode) {
        super(respCode);
    }

    public StockOverException(RespCode respCode, String message) {
        super(respCode, message);
    }
}
