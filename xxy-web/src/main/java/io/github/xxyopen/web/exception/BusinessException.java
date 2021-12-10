package io.github.xxyopen.web.exception;

import io.github.xxyopen.model.IResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常，用于处理用户请求时，业务错误时抛出
 * @author xiongxiaoyang
 * @version 1.0
 * @since 2020/5/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private IResultCode resultCode;

    public BusinessException(IResultCode resultCode) {
        //不调用父类Throwable的fillInStackTrace()方法生成栈追踪信息，提高应用性能
        //构造器之间的调用必须在第一行
        super(resultCode.getMsg(), null, false, false);
        this.resultCode = resultCode;
    }


}
