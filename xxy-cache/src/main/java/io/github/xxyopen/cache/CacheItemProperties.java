package io.github.xxyopen.cache;

import lombok.Data;

/**
 * 缓存条目属性
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/7
 */
@Data
public class CacheItemProperties {

    /**
     * 缓存写入的时间（纳秒）
     * */
    private long writeNanos;


}
