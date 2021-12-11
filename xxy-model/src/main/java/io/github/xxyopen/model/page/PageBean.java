package io.github.xxyopen.model.page;

import lombok.Data;

import java.util.List;

/**
 * 分页数据模型，封装普通分页数据
 *
 * @param <T> 分页集合类型
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/9
 */
@Data
public class PageBean<T>{

    /**
     * 页码
     */
    private long pageNum;
    /**
     * 每页大小
     */
    private long pageSize;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 分页数据集
     */
    private List<? extends T> list;

    /**
     * 该构造函数用于通用分页查询的场景
     * 接收普通分页数据和普通集合
     */
    public PageBean(long pageNum, long pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

    public static <T> PageBean<T> of(long pageNum, long pageSize, long total, List<T> list) {
        return new PageBean<>(pageNum, pageSize, total, list);
    }

    /**
     * 获取分页数
     * */
    public long getPages() {
        if (this.pageSize == 0L) {
            return 0L;
        } else {
            long pages = this.getTotal() / this.pageSize;
            if (this.getTotal() % this.pageSize != 0L) {
                ++pages;
            }

            return pages;
        }
    }

}
