package io.github.xxyopen.model;

/**
 * 定义响应状态码结构
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/9
 */
public interface IResultCode {

    /**
     * 获取响应状态码
     */
    int getCode();

    /**
     * 获取响应消息
     */
    String getMsg();
}
