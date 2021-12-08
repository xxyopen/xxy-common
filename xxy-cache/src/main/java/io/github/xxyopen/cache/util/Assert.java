package io.github.xxyopen.cache.util;

import lombok.experimental.UtilityClass;

/**
 * 断言工具
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/8
 */
@UtilityClass
public class Assert{

    /**
     * 非法状态断言，断言失败则抛出IllegalStateException
     * @param expression 断言表达式，true｜false
     * @param template 断言失败后的异常信息模版
     * @param args 断言失败后的异常信息参数
     * */
    public void requireState(boolean expression, String template, Object... args) {
        if (!expression) {
            throw new IllegalStateException(String.format(template, args));
        }
    }

    /**
     * 非法参数断言，断言失败则抛出IllegalArgumentException
     * @param expression 断言表达式，true｜false
     * @param template 断言失败后的异常信息模版
     * @param args 断言失败后的异常信息参数
     * */
    public void requireArgument(boolean expression, String template, Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(template, args));
        }
    }


}
