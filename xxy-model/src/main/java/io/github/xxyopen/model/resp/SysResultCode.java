package io.github.xxyopen.model.resp;

import io.github.xxyopen.model.resp.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统级别的响应状态码
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/9
 */
@Getter
@AllArgsConstructor
public enum SysResultCode implements IResultCode {

    /**
     * 成功
     * */
    OK(200,"SUCCESS"),

    /**
     * 服务器异常
     * */
    ERROR(500,"未知异常，请联系管理员！"),

    /**
     * 参数错误
     * */
    PARAM_ERROR(400,"非法参数！"),

    /**
     * 拒绝访问
     * */
    FORBIDDEN(403,"拒绝访问！");

    private int code;
    private String msg;
}
