package io.github.xxyopen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据模型
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/9
 * @param <T> 分页集合类型
 */
@Data
@NoArgsConstructor
public class Page<T> {

    /**
     * 页码
     * */
    private Integer pageNum;
    /**
     * 每页大小
     * */
    private Integer pageSize;
    /**
     * 总记录数
     * */
    private Long total;
    /**
     * 分页数据集
     * */
    private List<? extends T> list;

    /**
     * 该构造函数用于通用分页查询的场景
     * 接收普通分页数据和普通集合
     */
    public Page(Integer pageNum, Integer pageSize, Long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

}
