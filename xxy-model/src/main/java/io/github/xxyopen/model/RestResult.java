package io.github.xxyopen.model;


import lombok.Data;

/**
 * Rest请求响应模型
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/9
 * @param <T> 响应数据类型
 */
@Data
public class RestResult<T>{

    /**
     * 响应状态码，200表示成功
     * */
    private int code = SysResultCode.OK.getCode();

    /**
     * 响应消息
     * */
    private String msg = SysResultCode.OK.getMsg();

    /**
     * 响应中的数据
     * */
    private T data;

    private RestResult() {

    }

    private RestResult(IResultCode ResponseStatus) {
        this.code = ResponseStatus.getCode();;
        this.msg = ResponseStatus.getMsg();
    }

    private RestResult(T data) {
        this.data = data;
    }

    /**
     * 业务处理成功,无数据返回
     * */
    public static RestResult<Void> ok() {
        return new RestResult<>();
    }

    /**
     * 业务处理成功，有数据返回
     * */
    public static <T> RestResult<T> ok(T data) {
        return new RestResult<>(data);
    }

    /**
     * 业务处理失败
     * */
    public static RestResult<Void> fail(IResultCode ResponseStatus) {
        return new RestResult<>(ResponseStatus);
    }


    /**
     * 系统错误
     * */
    public static RestResult<Void> error() {
        return new RestResult<>(SysResultCode.ERROR);
    }

    /**
     * 判断是否请求成功
     * */
    public boolean isOk(){
        return this.code == SysResultCode.OK.getCode();
    }
}

